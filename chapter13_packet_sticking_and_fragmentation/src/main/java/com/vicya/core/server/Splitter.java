package com.vicya.core.server;

import com.vicya.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class Splitter extends LengthFieldBasedFrameDecoder
{
    public Splitter(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength)
    {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception
    {
        if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER)
        {
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
