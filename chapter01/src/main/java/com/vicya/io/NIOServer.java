package com.vicya.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 实现一个简单的NIO服务器用于接收客户端的连接，并输出客户端发送的消息
 * 只使用匿名类实现
 */
public class NIOServer
{
    public static void main(String[] args) throws IOException
    {
        //serverSelector负责轮询是否有新连接
        Selector serverSelector = Selector.open();
        //clientSelector负责轮询连接是否有数据可读
        Selector clientSelector = Selector.open();

        //开启一个线程用于监听是否有新连接
        new Thread(() ->
        {
            try
            {
                //监听8000端口
                //ServerSocketChannel是NIO中的概念，表示一个监听新连接的通道
                ServerSocketChannel listenerChannel = ServerSocketChannel.open();
                listenerChannel.socket().bind(new java.net.InetSocketAddress(8000));
                listenerChannel.configureBlocking(false);
                //OP_ACCEPT表示监听新连接事件
                listenerChannel.register(serverSelector, java.nio.channels.SelectionKey.OP_ACCEPT);

                while (true)
                {
                    if (serverSelector.select(1) > 0)//获取有事件的通道
                    {
                        Set<SelectionKey> set = serverSelector.selectedKeys();//获取所有的事件
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext())
                        {
                            SelectionKey key = keyIterator.next();
                            if (key.isAcceptable())//如果是新连接事件
                            {
                                try
                                {
                                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                    clientChannel.configureBlocking(false);
                                    clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                } finally
                                {
                                    keyIterator.remove();
                                }
                            }
                        }
                    }
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }).start();

        new Thread(() ->
        {
            try
            {
                while (true)
                {
                    if (clientSelector.select(1) > 0)//获取有事件的通道
                    {
                        Set<SelectionKey> set = clientSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();

                        while (keyIterator.hasNext())
                        {
                            SelectionKey key = keyIterator.next();
                            if (key.isReadable())//如果是可读事件
                            {
                                try
                                {
                                    SocketChannel clientChannel = (SocketChannel) key.channel();
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    clientChannel.read(byteBuffer);//读取数据
                                    byteBuffer.flip();
                                    System.out.println(new String(byteBuffer.array()));
                                } finally
                                {
                                    keyIterator.remove();
                                    key.interestOps(SelectionKey.OP_READ);
                                }
                            }
                        }
                    }
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }).start();
    }
}
