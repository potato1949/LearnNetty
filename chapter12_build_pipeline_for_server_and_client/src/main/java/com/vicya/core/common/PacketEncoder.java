package com.vicya.core.common;

import com.vicya.protocol.PacketCodeC;
import com.vicya.protocol.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet>
{
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception
    {
        PacketCodeC.encode(byteBuf, packet);
    }
}
