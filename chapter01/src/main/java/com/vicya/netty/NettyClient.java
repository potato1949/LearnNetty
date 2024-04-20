package com.vicya.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient
{
    public static void main(String[] args) throws InterruptedException
    {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>()
        {
            @Override
            protected void initChannel(Channel channel)
            {
                channel.pipeline().addLast(new StringEncoder());
            }
        });

        Channel channel = bootstrap.connect("localhost", 8000).channel();
        while (true)
        {
            channel.writeAndFlush("Hello, World!");
            Thread.sleep(2000);
        }
    }
}
