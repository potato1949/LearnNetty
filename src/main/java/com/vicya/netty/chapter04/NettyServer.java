package com.vicya.netty.chapter04;

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
        //相当于IO变成的两大线程组
        //boosGroup用于接受新连接
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup用于处理每个连接的数据读写
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        //服务端启动工作
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)//绑定线程模型
                .channel(NioServerSocketChannel.class)//指定IO模型
                .childHandler(new ChannelInitializer<NioSocketChannel>()//绑定读写处理逻辑
                {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception
                    {
                        channel.pipeline().addLast(new StringDecoder());
                        channel.pipeline().addLast(new SimpleChannelInboundHandler<String>()
                        {
                            @Override
                            protected void channelRead0(ChannelHandlerContext context, String msg) throws Exception
                            {
                                System.out.println(msg);
                            }
                        });
                    }
                });

        serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>()
        {
            @Override
            protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception
            {
                System.out.println("服务端启动中");
            }
        });

        bindPort(serverBootstrap, 8000);
    }

    private static void bindPort(ServerBootstrap serverBootstrap, int port)
    {
        serverBootstrap.bind(port).addListener(future ->
        {
            if (future.isSuccess())
            {
                System.out.println("端口[" + port + "]绑定成功！");
            } else
            {
                System.out.println("端口[" + port + "]绑定失败！");
                bindPort(serverBootstrap, port + 1);
            }
        });
    }
}
