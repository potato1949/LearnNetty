package com.vicya;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SimpleServer
{
    public static void main(String[] args)
    {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>()
                {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception
                    {
                        //添加入站处理器
                        channel.pipeline().addLast(new InboundHandlerA());

                        //添加出站处理器
                        channel.pipeline().addLast(new OutboundHandlerA());
                        channel.pipeline().addLast(new InboundHandlerB());
                        channel.pipeline().addLast(new OutboundHandlerB());
                        channel.pipeline().addLast(new InboundHandlerC());
                        channel.pipeline().addLast(new OutboundHandlerC());
                    }
                });

        serverBootstrap.bind(58000).addListener(new ChannelFutureListener()
        {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception
            {
                if (future.isSuccess())
                {
                    System.out.println("服务端启动成功");
                } else
                {
                    System.out.println("服务端启动失败");
                }
            }
        });
    }
}
