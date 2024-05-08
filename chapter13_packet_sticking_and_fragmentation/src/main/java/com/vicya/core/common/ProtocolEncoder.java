package com.vicya.core.common;

import com.vicya.protocol.PacketCodeC;
import com.vicya.protocol.SimpleProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

public class ProtocolEncoder extends MessageToByteEncoder<SimpleProtocol>
{
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, SimpleProtocol simpleProtocol, ByteBuf byteBuf) throws Exception
    {
        PacketCodeC.encode(byteBuf, simpleProtocol);
    }
}
