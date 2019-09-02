package com.sanri.test;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 * 创建人     : sanri
 * 创建时间   : 2018/8/28-21:28
 * 功能       :
 */
public class JettyStarter {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        // 添加 handler
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase(".");

        server.setHandler(resourceHandler);

        server.start();
        server.join();
    }
}
