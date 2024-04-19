package com.vicya.netty.chapter08.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

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
                    protected void initChannel(NioSocketChannel socketChannel) throws Exception
                    {
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });

        bootstrap.connect("localhost", 58000);
    }
}
