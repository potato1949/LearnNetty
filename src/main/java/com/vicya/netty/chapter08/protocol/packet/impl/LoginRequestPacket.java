package com.vicya.netty.chapter08.protocol.packet.impl;

import com.vicya.netty.chapter08.protocol.packet.Packet;
import lombok.Data;

import static com.vicya.netty.chapter08.protocol.packet.Command.LOGIN_REQUEST;

@Data
public class LoginRequestPacket extends Packet
{
    private String userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand()
    {
        return LOGIN_REQUEST;
    }
}
