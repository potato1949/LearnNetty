package com.vicya.core.client.handler;

import com.vicya.protocol.packet.impl.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket>
{
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception
    {
        System.out.println(new Date() + ": 客户端收到消息: " + messageResponsePacket.getMessage());
    }
}
