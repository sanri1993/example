package foo;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sanri.DeviceLogin;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-context.xml"})
public class AppTest {
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	@Resource(name = "redisDelTemplate")
	private RedisTemplate redisDelTemplate;
	
	@Test
	public void testUse() {}
	
	
	final static String container = "sanri";

	// maxmemory 1g 测试
	
	@Test
	public void testHashDel(){
		redisTemplate.delete(container);
	}

	@Test
	public void testPutLargeData2Redis(){
		final String prefix = "abc_";
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		// 测试存放 10 万数据到 redis 需要的时间 大概 18 秒
		for (int i=0;i<100000;i++){
			redisTemplate.opsForValue().set(UUID.randomUUID().toString(),i+"");
		}
		stopWatch.stop();
		System.out.println("存放 10 万数据花费时间为:"+ DurationFormatUtils.formatDuration(stopWatch.getTime(),"HH:mm:ss.S"));
	}

	@Test
	public void testSetLargeHashData(){
		final String container = DateFormatUtils.format(System.currentTimeMillis(),"yyyyMMddHHmm");
		System.out.println("容器名为:"+container);
		largeHashKey(container,100000);
	}

	private void largeHashKey(String container,int size) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
		for (int i=0;i<size;i++){
			DeviceLogin deviceLogin = new DeviceLogin();
			deviceLogin.setCreateTime(new Date());
			String sn = UUID.randomUUID().toString();
			deviceLogin.setSn(sn);
			deviceLogin.setLastRtTrack(new Date());
			deviceLogin.setSpeed(10);
			opsForHash.put(container,sn,deviceLogin);
		}
		stopWatch.stop();
		System.out.println("大 key ["+size+" 万]存放花费时间:"+stopWatch.getTime() + "ms");
	}

	@Test
	public void testLargeKeyDelete(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		redisTemplate.delete("abc");
		stopWatch.stop();

		// 2038 ms 大约两秒钟
		System.out.println("大 key [100 万]删除花费时间:"+stopWatch.getTime() + " ms");
	}

	@Test
	public void testLargeKeyDeleteUseFlushDb(){
		//1426 ms 大约 1 秒钟
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		redisTemplate.move("abc",2);
		redisDelTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
				redisConnection.flushDb();
				return null;
			}
		});
		stopWatch.stop();

		// 2038 ms 大约两秒钟
		System.out.println("大 key [100 万]删除花费时间:"+stopWatch.getTime() + " ms");
	}

	@Test
	public void largeHashSet(){
		// 167514 ms 差不多两分钟 100 万数据量 开启事务 maxmemory 1G
		//184536ms 不设置 maxmemory 100 万数据量 开启事务

		// 不设置 maxmemory 500 万数据量 不开启事务 挂了
		largeHashKey("abc",5000000);
	}

}
