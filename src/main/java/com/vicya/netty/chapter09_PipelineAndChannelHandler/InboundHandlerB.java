package com.vicya.netty.chapter09_PipelineAndChannelHandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class InboundHandlerB extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        System.out.println("InboundHandlerB get msg: " + msg);
        super.channelRead(ctx, msg);
    }
}
