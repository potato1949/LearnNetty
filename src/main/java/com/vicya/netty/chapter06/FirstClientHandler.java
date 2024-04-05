package com.vicya.netty.chapter06;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.println(new Date() + ": 客户端写入数据");
        //获取数据
        ByteBuf buffer = getByteBuf(ctx);
        //写数据
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx)
    {
        //获取二进制抽象ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();
        //准备数据，指定字符串的字符集为UTF-8
        byte[] bytes = "你好，闪电侠！".getBytes(StandardCharsets.UTF_8);
        //填充数据到ByteBuf
        buffer.writeBytes(bytes);
        //返回ByteBuf
        return buffer;
    }
}
