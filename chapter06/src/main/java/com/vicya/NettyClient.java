package com.vicya;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.TimeUnit;

public class NettyClient
{
    public static void main(String[] args)
    {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>()
                {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception
                    {
                        channel.pipeline().addLast(new FirstClientHandler());
                    }
                });


        connect(bootstrap, 5);
    }

    private static void connect(Bootstrap bootstrap, int retryNum)
    {
        ChannelFuture future = bootstrap.connect("127.0.0.1", 8000);
        future.addListener(new GenericFutureListener<Future<? super Void>>()
        {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception
            {
                if (future.isSuccess())
                {
                    System.out.println("connect success");
                } else if (retryNum == 0)
                {
                    System.out.println("connect fail, stop try");
                } else
                {
                    System.out.println("connect fail remain try times:" + retryNum);
                    int delay = 5 - retryNum + 1;
                    bootstrap.config().group().schedule(() -> connect(bootstrap, retryNum - 1), delay, TimeUnit.SECONDS);
                }
            }
        });
    }
}
