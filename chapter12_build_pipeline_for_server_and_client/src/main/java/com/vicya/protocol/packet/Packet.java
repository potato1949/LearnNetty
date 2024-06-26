package com.vicya.protocol.packet;

import lombok.Data;

@Data
public abstract class Packet
{
    private Byte version = 1;

    public abstract Byte getCommand();
}
