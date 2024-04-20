package com.vicya.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IOServer
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(8000);
        new Thread(() ->
        {
            while (true)
            {
                try
                {
                    Socket socket = serverSocket.accept();
                    new Thread(() ->
                    {
                        try
                        {
                            int len;
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            while ((len = inputStream.read(data)) != -1)
                            {
                                System.out.println(Thread.currentThread().getName() + ":" + new String(data, 0, len));
                                Thread.sleep(5000);
                            }
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        } catch (InterruptedException e)
                        {
                            throw new RuntimeException(e);
                        }
                    }).start();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
