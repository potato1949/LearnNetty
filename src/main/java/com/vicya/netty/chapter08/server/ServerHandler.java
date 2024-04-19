package com.vicya.netty.chapter08.server;

import com.vicya.netty.chapter08.protocol.packet.impl.LoginRequestPacket;
import com.vicya.netty.chapter08.protocol.packet.Packet;
import com.vicya.netty.chapter08.protocol.packet.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        ByteBuf byteBuf = (ByteBuf) msg;

        //解码
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginRequestPacket)
        {
            LoginRequestPacket loginRequestPacket = ((LoginRequestPacket) packet);

            if (valid(loginRequestPacket))
            {
                System.out.println("succ");
            }else
            {
                System.out.println("fail");
            }
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket)
    {
        return true;
    }
}
