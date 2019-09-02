package foo;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;

public class TestStopWatch {
	
	@Test
	public void test2() throws InterruptedException{
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Thread.sleep(1000);
		System.out.println("用时:"+stopWatch.getTime());
		stopWatch.reset();stopWatch.start();
		
		Thread.sleep(1000);
		stopWatch.stop();
		System.out.println("第二个用时:"+stopWatch.getTime());
		
	}
}
