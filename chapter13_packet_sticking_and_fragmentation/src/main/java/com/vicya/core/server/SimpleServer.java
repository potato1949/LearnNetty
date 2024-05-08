package com.vicya.core.server;

import com.vicya.core.common.ProtocolDecoder;
import com.vicya.core.common.ProtocolEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

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
                        channel.pipeline().addLast(new Splitter(Integer.MAX_VALUE, 4, 4));
                        channel.pipeline().addLast(new ProtocolDecoder());
                        channel.pipeline().addLast(new FirstServerHandler());
                        channel.pipeline().addLast(new ProtocolEncoder());
                    }
                });
        serverBootstrap.bind(58000);
    }
}
