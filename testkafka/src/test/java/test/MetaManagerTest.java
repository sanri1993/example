package test;

import com.sanri.MetaManager;
import org.junit.Before;
import org.junit.Test;

/**
 * 创建人     : sanri
 * 创建时间   : 2018/8/12-14:07
 * 功能       :
 */
public class MetaManagerTest {
    MetaManager metaManager ;

    @Before
    public void init(){
        metaManager = new MetaManager("localhost:2181");
    }

    @Test
    public void testCreateTopic(){

        metaManager.createTopic("test",1,1);
    }

    @Test
    public void testListTopics(){
        metaManager.listTopics();
    }

    @Test
    public void testDescTopic(){
        metaManager.descTopics("test");
    }

    @Test
    public void testDeleteTopic(){
        metaManager.deleteTopic("test");
    }

}
