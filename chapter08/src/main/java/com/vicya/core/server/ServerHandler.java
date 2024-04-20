package com.vicya.core.server;

import com.vicya.protocol.packet.Packet;
import com.vicya.protocol.packet.PacketCodeC;
import com.vicya.protocol.packet.impl.LoginRequestPacket;
import com.vicya.protocol.packet.impl.LoginResponsePacket;
import com.vicya.protocol.packet.impl.MessageRequestPacket;
import com.vicya.protocol.packet.impl.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class ServerHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        ByteBuf byteBuf = (ByteBuf) msg;

        //解码
        Packet packet = PacketCodeC.decode(byteBuf);

        if (packet instanceof LoginRequestPacket loginRequestPacket)
        {
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            if (valid(loginRequestPacket))
            {
                loginResponsePacket.setSuccess(true);
            }else
            {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
            }
            ByteBuf responseByteBuf = PacketCodeC.encode(loginResponsePacket);
            Thread.sleep(10000);
            ctx.channel().writeAndFlush(responseByteBuf);
        } else if (packet instanceof MessageRequestPacket messageRequestPacket)
        {
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

            MessageResponsePacket responsePacket = new MessageResponsePacket();
            responsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf responseByteBuf = PacketCodeC.encode(responsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket)
    {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.println("客户端已断开连接");
        super.channelInactive(ctx);
    }
}
