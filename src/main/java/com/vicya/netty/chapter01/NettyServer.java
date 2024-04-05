package com.vicya.netty.chapter01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class NettyServer
{
    public static void main(String[] args)
    {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //boss线程组用于监听连接事件，worker线程组用于处理连接事件
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        //绑定线程组
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>()
        {
            @Override
            protected void initChannel(NioSocketChannel channel)
            {
                channel.pipeline().addLast(new StringDecoder());
                channel.pipeline().addLast(new SimpleChannelInboundHandler<String>()
                {
                    @Override
                    protected void channelRead0(ChannelHandlerContext context, String msg)
                    {
                        System.out.println(msg);
                    }
                });
            }
        }).bind(8000);
    }
}
