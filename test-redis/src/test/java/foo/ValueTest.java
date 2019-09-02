package foo;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sanri.DeviceLogin;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-context.xml"})
public class ValueTest {

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	@Test
	public void testValue(){
		Object object = redisTemplate.opsForValue().get("timestamp_deviceLogin");
		Long timestamp = NumberUtils.toLong(ObjectUtils.toString(object));
		System.out.println(timestamp);
		System.out.println(DateFormatUtils.format(timestamp, "yyyy-MM-dd HH:mm:ss"));
	}
	
	@Test
	public void testLength(){
		System.out.println(redisTemplate.opsForHash().size("sanri"));
		redisTemplate.opsForHash().put("sanri", "156"+System.currentTimeMillis(), "1");
		System.out.println(redisTemplate.opsForHash().size("sanri"));
	}
	
}
