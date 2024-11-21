package com.vicya.protocol;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;

/**
 * PacketCodeC类提供了将SimpleProtocol对象编码和解码为ByteBuf的方法，
 * 使用自定义协议格式。
 */
public class PacketCodeC
{
    // 用于标识协议的魔数（4字节）
    public static final int MAGIC_NUMBER = 0x12345678;

    /**
     * 将SimpleProtocol对象编码为ByteBuf。
     *
     * @param _byteBuf 要写入编码数据的ByteBuf。
     * @param _protocol 要编码的SimpleProtocol对象。
     * @return 包含编码数据的ByteBuf。
     */
    public static ByteBuf encode(ByteBuf _byteBuf, SimpleProtocol _protocol)
    {
        byte[] bytes = JSON.toJSONBytes(_protocol);
        _byteBuf.writeInt(MAGIC_NUMBER); // 写入魔数
        _byteBuf.writeInt(bytes.length); // 写入序列化数据的长度
        _byteBuf.writeBytes(bytes); // 写入序列化数据
        return _byteBuf;
    }

    /**
     * 将ByteBuf解码为SimpleProtocol对象。
     *
     * @param _byteBuf 包含编码数据的ByteBuf。
     * @return 解码后的SimpleProtocol对象。
     */
    public static SimpleProtocol decode(ByteBuf _byteBuf)
    {
        _byteBuf.skipBytes(4); // 跳过魔数
        int length = _byteBuf.readInt(); // 读取序列化数据的长度
        byte[] bytes = new byte[length];
        _byteBuf.readBytes(bytes); // 读取序列化数据
        return JSON.parseObject(bytes, SimpleProtocol.class); // 将数据反序列化为SimpleProtocol对象
    }
}