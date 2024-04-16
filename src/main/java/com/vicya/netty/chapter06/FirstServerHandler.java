package com.vicya.netty.chapter06;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class FirstServerHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ": 服务端收到数据-> " + byteBuf.toString(StandardCharsets.UTF_8));

        ByteBuf reply = getByteBuf(ctx);
        ctx.writeAndFlush(reply);
    }

    public ByteBuf getByteBuf(ChannelHandlerContext ctx)
    {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "你好, 请关注我的公众号".getBytes(StandardCharsets.UTF_8);
        buffer.writeBytes(bytes);
        return buffer;
    }
}
