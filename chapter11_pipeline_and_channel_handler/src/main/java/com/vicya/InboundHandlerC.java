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

        //从最后一个处理器开始出站
        ctx.channel().writeAndFlush(buffer);

        //从当前所在处理器开始出站
//        ctx.writeAndFlush(buffer);

        //如果添加顺序是InA,OutA,InB,OutB,InC,OutC 如果在InC开始执行的话 则前者会从OutC开始，后者会直接从InC往前找
    }
}
