package com.vicya.protocol.packet.impl;

import com.vicya.protocol.packet.Packet;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.vicya.protocol.packet.Command.MESSAGE_RESPONSE;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessageResponsePacket
    extends Packet
{
    private String message;

    @Override
    public Byte getCommand()
    {
        return MESSAGE_RESPONSE;
    }
}
