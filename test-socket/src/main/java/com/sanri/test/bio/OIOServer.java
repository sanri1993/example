package com.sanri.test.bio;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OIOServer {
  static   ExecutorService executorService = Executors.newCachedThreadPool();
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(10086);
        System.out.println("服务启动,监听 10086 端口...");
        while(true){
            Socket client = serverSocket.accept();
            System.out.println(DateFormatUtils.ISO_DATETIME_FORMAT.format(System.currentTimeMillis()) +"新客户端:"+client.getRemoteSocketAddress());
            executorService.submit(() -> {
                try {
                    handle(client);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }
    }

    private static void handle(Socket client) throws IOException {
        InputStream inputStream = client.getInputStream();
        OutputStream outputStream = client.getOutputStream();
        byte [] bytes = new byte[1024];
        while(true){
            int read = inputStream.read(bytes);
            if(read != -1){
                String readLine = new String(bytes, 0, read);
                System.out.println(readLine);

                String writeLine = "recive:"+readLine;
                System.out.println("回写数据为:"+writeLine);
                outputStream.write(writeLine.getBytes(),0,writeLine.getBytes().length);
            }else{
                break;
            }
        }
        System.out.println("socket 关闭");
        IOUtils.closeQuietly(client);
    }
}
