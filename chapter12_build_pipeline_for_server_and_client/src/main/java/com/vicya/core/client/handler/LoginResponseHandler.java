package com.vicya.core.client.handler;

import com.vicya.core.client.LoginUtil;
import com.vicya.protocol.packet.impl.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket>
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception
    {
        if (loginResponsePacket.isSuccess())
        {
            System.out.println(new Date() + ": 客户端登陆成功");

            LoginUtil.markAsLogin(channelHandlerContext.channel());
        } else
        {
            System.out.println(new Date() + ": 客户端登陆失败，原因：" + loginResponsePacket.getReason());
        }
    }
}
