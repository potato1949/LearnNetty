package com.vicya.netty.chapter08_SimpleIm.core.client;

import com.vicya.netty.chapter08_SimpleIm.protocol.packet.Packet;
import com.vicya.netty.chapter08_SimpleIm.protocol.packet.PacketCodeC;
import com.vicya.netty.chapter08_SimpleIm.protocol.packet.impl.LoginRequestPacket;
import com.vicya.netty.chapter08_SimpleIm.protocol.packet.impl.LoginResponsePacket;
import com.vicya.netty.chapter08_SimpleIm.protocol.packet.impl.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ClientHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.println(new Date() + ": 客户端开始登陆");

        //创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("flash");
        loginRequestPacket.setPassword("pwd");

        //编码
        ByteBuf byteBuf = PacketCodeC.encode(loginRequestPacket);

        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodeC.decode(byteBuf);
        if (packet instanceof LoginResponsePacket loginResponsePacket)
        {
            if (loginResponsePacket.isSuccess())
            {
                System.out.println(new Date() + ": 客户端登陆成功");

                LoginUtil.markAsLogin(ctx.channel());
            } else
            {
                System.out.println(new Date() + ": 客户端登陆失败，原因：" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket messageResponsePacket)
        {
            System.out.println(new Date() + ": 客户端收到消息: " + messageResponsePacket.getMessage());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception
    {
        // 重连的间隔时间
        final int RECONNECT_DELAY = 5;
        System.out.println(new Date() + ": 连接断开，" + RECONNECT_DELAY + "秒后尝试重连");
        final EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(() ->
        {
            System.out.println(new Date() + ": 开始重连");
            // 你的重连逻辑，例如创建一个新的Bootstrap来重新连接服务器

        }, RECONNECT_DELAY, TimeUnit.SECONDS);
    }
}
