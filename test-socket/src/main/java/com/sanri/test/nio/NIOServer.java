package com.sanri.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    private Selector selector;

    public NIOServer() {
    }

    public NIOServer initServer(int port) throws IOException {
        // 创建 serversocket 并绑定端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
        //打开一个 selector
        selector = Selector.open();

        //注册 accept 事件监听到 selector
        //当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        return this;
    }

    public void listen() throws IOException {
        System.out.println("服务端启动成功！");
        // 轮询访问selector
        while(true){
            int select = selector.select();
            Set<SelectionKey> selectionKeys = this.selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                // 删除已选的key,以防重复处理
                iterator.remove();
                if(selectionKey.isAcceptable()){
                    //接受请求事件
                    handleAccept(selectionKey);
                }else if(selectionKey.isReadable()){
                    handleRead(selectionKey);
                }
            }
        }
    }

    /**
     * 处理读操作
     * @param selectionKey
     */
    private void handleRead(SelectionKey selectionKey) throws IOException {
        SelectableChannel channel = selectionKey.channel();
        SocketChannel socketChannel = (SocketChannel) channel;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(byteBuffer);
        byte[] array = byteBuffer.array();
        String recive = new String(array);
        System.out.println("服务端收到数据 :"+recive);

        //回写数据
        ByteBuffer writeData = ByteBuffer.wrap(("recive:"+recive).getBytes());
        socketChannel.write(writeData);
    }

    /**
     * 处理新客户端连接
     * @param selectionKey
     * @throws IOException
     */
    private void handleAccept(SelectionKey selectionKey) throws IOException {
        //处理请求事件
        SelectableChannel channel = selectionKey.channel();
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) channel;
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("新客户端连接:"+socketChannel);
        socketChannel.configureBlocking(false);
        socketChannel.register(this.selector,SelectionKey.OP_READ);
    }

    

    public static void main(String[] args) throws IOException {
        NIOServer nioServer = new NIOServer();
        nioServer.initServer(10086).listen();
    }
}
