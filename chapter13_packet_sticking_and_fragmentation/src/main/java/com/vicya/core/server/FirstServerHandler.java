package com.vicya.core.server;

import com.vicya.protocol.SimpleProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class FirstServerHandler extends SimpleChannelInboundHandler<SimpleProtocol>
{
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SimpleProtocol simpleProtocol) throws Exception
    {
        System.out.println(simpleProtocol.getMsg());
    }
}
