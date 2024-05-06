package com.vicya.core.server.handler;

import com.vicya.protocol.packet.impl.MessageRequestPacket;
import com.vicya.protocol.packet.impl.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket>
{
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception
    {
        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
        channelHandlerContext.channel().writeAndFlush(responsePacket);
    }
}
