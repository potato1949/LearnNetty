package com.vicya.netty.chapter08_SimpleIm.protocol.packet.impl;

import com.vicya.netty.chapter08_SimpleIm.protocol.packet.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.vicya.netty.chapter08_SimpleIm.protocol.packet.Command.LOGIN_RESPONSE;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends Packet
{
    private boolean isSuccess;
    private String reason;

    @Override
    public Byte getCommand()
    {
        return LOGIN_RESPONSE;
    }
}
