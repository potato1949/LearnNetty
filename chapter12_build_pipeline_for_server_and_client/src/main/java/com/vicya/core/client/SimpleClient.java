package com.vicya.core.client;

import com.vicya.core.client.handler.AutoLoginHandler;
import com.vicya.core.client.handler.LoginResponseHandler;
import com.vicya.core.client.handler.MessageResponseHandler;
import com.vicya.core.common.PacketDecoder;
import com.vicya.core.common.PacketEncoder;
import com.vicya.protocol.PacketCodeC;
import com.vicya.protocol.packet.impl.MessageRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Scanner;

public class SimpleClient
{
    public static void main(String[] args)
    {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>()
                {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception
                    {
                        channel.pipeline().addLast(new PacketDecoder());
                        channel.pipeline().addLast(new AutoLoginHandler());
                        channel.pipeline().addLast(new LoginResponseHandler());
                        channel.pipeline().addLast(new MessageResponseHandler());
                        channel.pipeline().addLast(new PacketEncoder());
                    }
                });

        bootstrap.connect("localhost", 58000).addListener((ChannelFutureListener) channelFuture ->
        {
            if (channelFuture.isSuccess())
            {
                System.out.println("连接成功!");

                Channel channel = channelFuture.channel();
                startConsoleThread(channel);
            } else
            {
                System.err.println("连接失败!");
            }
        });
    }

    private static void startConsoleThread(Channel channel)
    {
        new Thread(()->{
            while(!Thread.interrupted())
            {
                if (LoginUtil.hasLogin(channel))
                {
                    System.out.println("输入消息发送至服务端:");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                    messageRequestPacket.setMessage(line);
                    channel.writeAndFlush(messageRequestPacket);
                }
            }
        }).start();
    }
}
