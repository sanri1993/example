package com.sanri.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();

        // worker 线程池
        EventLoopGroup worker = new NioEventLoopGroup();

        try{
            //设置线程池
            bootstrap.group(worker);

            //设置 socket 工厂
            bootstrap.channel(NioSocketChannel.class);

            //设置管理工厂
            bootstrap.handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new StringEncoder());
                    pipeline.addLast(new StringDecoder());
                    pipeline.addLast(new ClientHandler());

                }
            });

            //连接服务端
            ChannelFuture channelFuture = bootstrap.connect("localhost", 10086);

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
