package com.vicya.core.client.handler;

import com.vicya.protocol.packet.impl.LoginRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AutoLoginHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        // 让用户输入账号、密码
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名:");
        String username = reader.readLine();
        System.out.println("请输入密码:");
        String password = reader.readLine();
        // 构建登录请求包
        LoginRequestPacket loginRequestPacket = LoginRequestPacket.builder()
                .username(username)
                .password(password)
                .build();
        ctx.channel().writeAndFlush(loginRequestPacket);
        super.channelActive(ctx);
    }
}
