package com.vicya.core.client;

import com.vicya.core.common.ProtocolDecoder;
import com.vicya.core.common.ProtocolEncoder;
import com.vicya.core.server.FirstServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

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
                        channel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 4, 4));
                        channel.pipeline().addLast(new ProtocolDecoder());
                        channel.pipeline().addLast(new FirstClientHandler());
                        channel.pipeline().addLast(new ProtocolEncoder());
                    }
                });
        bootstrap.connect("localhost", 58000);
    }
}
