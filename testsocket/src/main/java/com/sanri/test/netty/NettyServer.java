package com.sanri.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //boss worker
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try{
            //设置线程池
            serverBootstrap.group(boss,worker);

            //设置 socket 工厂
            serverBootstrap.channel(NioServerSocketChannel.class);

            //设置管理工厂
            serverBootstrap.childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel serverSocketChannel) throws Exception {
                    ChannelPipeline pipeline = serverSocketChannel.pipeline();
                    pipeline.addLast(new StringEncoder());
                    pipeline.addLast(new StringDecoder());
                    pipeline.addLast(new ServerHandle());
                }
            });

            //设置参数，TCP参数
//            serverBootstrap.option(ChannelOption.SO_BACKLOG, 2048);//serverSocketchannel的设置，链接缓冲池的大小
//            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);//socketchannel的设置,维持链接的活跃，清除死链接
//            serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);//socketchannel的设置,关闭延迟发送

            //绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(10086);
            System.out.println("服务端启动...");
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            //释放资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
