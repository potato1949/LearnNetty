package com.vicya.io;

import java.net.Socket;

public class IOClient
{
    public static void main(String[] args)
    {
        new Thread(() ->
        {
            try
            {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true)
                {
                    try
                    {
                        socket.getOutputStream().write("Hello, World!".getBytes());
                        Thread.sleep(1000);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }).start();
    }
}
