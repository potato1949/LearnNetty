package com.vicya.netty.chapter08.Packet;

import lombok.Data;

@Data
public abstract class Packet
{
    private Byte version = 1;

    public abstract Byte getCommand();
}
