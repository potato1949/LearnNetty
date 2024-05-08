package com.vicya.protocol;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;

public class PacketCodeC
{
    //[魔数:4字节][长度:4字节][信息]
    public static final int MAGIC_NUMBER = 0x12345678;

    public static ByteBuf encode(ByteBuf _byteBuf, SimpleProtocol _protocol)
    {
        byte[] bytes = JSON.toJSONBytes(_protocol);
        _byteBuf.writeInt(MAGIC_NUMBER);
        _byteBuf.writeInt(bytes.length);
        _byteBuf.writeBytes(bytes);
        return _byteBuf;
    }

    public static SimpleProtocol decode(ByteBuf _byteBuf)
    {
        _byteBuf.skipBytes(4);
        int length = _byteBuf.readInt();
        byte[] bytes = new byte[length];
        _byteBuf.readBytes(bytes);
        return JSON.parseObject(bytes, SimpleProtocol.class);
    }
}
