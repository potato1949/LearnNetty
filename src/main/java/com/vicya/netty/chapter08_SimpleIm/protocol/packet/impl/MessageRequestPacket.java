package com.vicya.netty.chapter08_SimpleIm.protocol.packet.impl;

import com.vicya.netty.chapter08_SimpleIm.protocol.packet.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.vicya.netty.chapter08_SimpleIm.protocol.packet.Command.MESSAGE_REQUEST;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessageRequestPacket
    extends Packet
{
    private String message;

    @Override
    public Byte getCommand()
    {
        return MESSAGE_REQUEST;
    }
}
