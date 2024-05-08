package com.vicya.core.client;

import com.vicya.protocol.PacketCodeC;
import com.vicya.protocol.SimpleProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class FirstClientHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        for (int i = 0; i < 1000; i++)
        {
            SimpleProtocol protocol = new SimpleProtocol();
            protocol.setMsg("你好，欢迎关注我的微信公众号");
            ctx.channel().writeAndFlush(protocol);
        }
    }
}
