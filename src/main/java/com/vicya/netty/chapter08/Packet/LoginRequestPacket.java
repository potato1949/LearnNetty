package com.vicya.netty.chapter08.Packet;

import static com.vicya.netty.chapter08.Packet.Command.LOGIN_REQUEST;

public class LoginRequestPacket extends Packet
{
    private Integer userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand()
    {
        return LOGIN_REQUEST;
    }
}
