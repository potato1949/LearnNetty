package com.vicya;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class InboundHandlerC extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        System.out.println("InboundHandlerC get msg: " + msg);
        super.channelRead(ctx, msg);

        // 注意：此处需要写入数据，触发ChannelOutboundHandlerAdapter
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes("发出消息".getBytes(StandardCharsets.UTF_8));
        ctx.channel().writeAndFlush(buffer);

//        ctx.writeAndFlush(buffer); ==> 从当前处理器往后触发，当前配置的顺序会导致ChannelOutboundHandlerAdapter无法触发
    }
}
