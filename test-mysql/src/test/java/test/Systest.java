package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sanri.NumberUtil;
import com.sanri.mapper.DeviceLoginMapper;
import com.sanri.mapper.TrackInfoMapper;
import com.sanri.pojo.DeviceLogin;
import com.sanri.pojo.TrackInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class Systest {
	
	private List<TrackInfo> trackInfos = new ArrayList<TrackInfo>();
	
	@Test
	public void testInsert(){
		TrackInfo trackInfo = new TrackInfo();
		trackInfo.setSn("213456");
		int insert = trackInfoMapper.insertOne(trackInfo);
		System.out.println(insert);
	}	
	@Before
	public void before(){
		trackInfos = trackInfoMapper.selectAll();
		System.out.println("size:"+trackInfos.size());
	}
	
	@Autowired
	private TrackInfoMapper trackInfoMapper;
	@Autowired
	private DeviceLoginMapper deviceLoginMapper;
	
	@Test
	public void testUse(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		TrackInfo selectBySn = trackInfoMapper.selectBySn("8160606000");
		System.out.println(selectBySn);
		stopWatch.stop();
		System.out.println(stopWatch.getTime());
	}
	
	@Test
	public void test10wSql(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		int i =0 ;
		for (TrackInfo trackInfo : trackInfos) {
			trackInfoMapper.updateDeviceTime(trackInfo.getSn());
			i++;
			if(i % 100 == 0){
				System.out.println("当前 :"+(i)+",总共:"+(trackInfos.size()) + ",比例:"+(NumberUtil.percent(i, trackInfos.size(), 2))+",时间:"+stopWatch.getTime());
			}
		}
		stopWatch.stop();
		System.out.println(stopWatch.getTime());
	}
	
	@Test
	public void test10AppendSql(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		List<TrackInfo> subTrackInfos = null;
		for(int i=0;i<trackInfos.size();i++){
			if(i % 100 == 0){
				if(i != 0 ){
					//更新数据 
					trackInfoMapper.updateDeviceTimeList(subTrackInfos);
				}
				System.out.println("当前 :"+(i)+",总共:"+(trackInfos.size()) + ",比例:"+(NumberUtil.percent(i, trackInfos.size(), 2))+",时间:"+stopWatch.getTime());
				subTrackInfos = new ArrayList<>();
			}
			subTrackInfos.add(trackInfos.get(i));
		}
		
		stopWatch.stop();
	}
	
	@Test
	public void testTmptable(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		List<TrackInfo> subTrackInfos = null;
		for(int i=0;i<trackInfos.size();i++){
			if(i % 3500 == 0){
				if(i != 0 ){
					//更新数据 
					long timestamp = System.currentTimeMillis();
					trackInfoMapper.updateUserTmpTable(subTrackInfos,timestamp);
				}
				System.out.println("当前 :"+(i)+",总共:"+(trackInfos.size()) + ",比例:"+(NumberUtil.percent(i, trackInfos.size(), 2))+"%,时间:"+stopWatch.getTime());
				subTrackInfos = new ArrayList<>();
			}
			subTrackInfos.add(trackInfos.get(i));
		}
		
		//最后还需要做一次更新
		long timestamp = System.currentTimeMillis();
		trackInfoMapper.updateUserTmpTable(subTrackInfos,timestamp);
		System.out.println("当前 :"+trackInfos.size()+",总共:"+(trackInfos.size()) + ",比例:100%,时间:"+stopWatch.getTime());
		stopWatch.stop();
	}
	
	@Test
	public void testTmpTableAllData(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		trackInfoMapper.updateUserTmpTable(trackInfos,System.currentTimeMillis());
		stopWatch.stop();
		System.out.println("花费时间:"+stopWatch.getTime());
	}
	
	@Test
	public void testInsertOnDUPLICATE(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		List<TrackInfo> subTrackInfos = null;
		for(int i=0;i<trackInfos.size();i++){
			if(i % 3500 == 0){
				if(i != 0 ){
					trackInfoMapper.updateUserInsert(subTrackInfos);
				}
				System.out.println("当前 :"+(i)+",总共:"+(trackInfos.size()) + ",比例:"+(NumberUtil.percent(i, trackInfos.size(), 2))+"%,时间:"+stopWatch.getTime());
				subTrackInfos = new ArrayList<>();
			}
			subTrackInfos.add(trackInfos.get(i));
		}
		trackInfoMapper.updateUserInsert(subTrackInfos);
		stopWatch.stop();
		System.out.println("花费时间:"+stopWatch.getTime());
	}
	
	@Test
	public void testInsertOnOnce(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		trackInfoMapper.updateUserInsert(trackInfos);
		stopWatch.stop();
		System.out.println("花费时间:"+stopWatch.getTime());
	}
	
	@Test
	public void testErrorInsert(){
		DeviceLogin deviceLogin = new DeviceLogin();
		deviceLogin.setSn("15689");
		deviceLogin.setLastLoginTime(new Date());
		List<DeviceLogin> deviceLogins = new ArrayList<>();
		deviceLogins.add(deviceLogin);
		deviceLoginMapper.batchUpdate(deviceLogins,System.currentTimeMillis());
	}
}
