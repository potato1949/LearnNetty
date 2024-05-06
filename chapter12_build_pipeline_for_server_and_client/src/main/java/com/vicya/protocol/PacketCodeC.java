package com.vicya.protocol;

import com.vicya.protocol.packet.CommandMgr;
import com.vicya.protocol.packet.Packet;
import com.vicya.protocol.serial.Serializer;
import com.vicya.protocol.serial.SerializerAlgorithm;
import com.vicya.protocol.serial.SerializerMgr;
import io.netty.buffer.ByteBuf;

public class PacketCodeC
{
    /**
     * 自定义协议：
     *      [魔数:4字节] [版本号:1字节] [序列化算法:1字节] [指令:1字节] [数据长度:4字节] [数据:N字节]
     */

    private static final int MAGIC_NUMBER = 0x12345678;

    /**
     * 序列化
     * @param _packet
     */
    public static ByteBuf encode(ByteBuf _byteBuf, Packet _packet)
    {
        //获取序列化算法
        Serializer serializer = SerializerMgr.INSTANCE.getSerializer(SerializerAlgorithm.JSON);
        if (serializer == null)
            return null;

        //序列化
        byte[] bytes = serializer.serialize(_packet);

        //实际编码过程
        _byteBuf.writeInt(MAGIC_NUMBER);
        _byteBuf.writeByte(_packet.getVersion());
        _byteBuf.writeByte(serializer.getSerializerAlgorithm());
        _byteBuf.writeByte(_packet.getCommand());
        _byteBuf.writeInt(bytes.length);
        _byteBuf.writeBytes(bytes);

        return _byteBuf;
    }

    /**
     * 反序列化
     * @param _byteBuf
     */
    public static Packet decode(ByteBuf _byteBuf)
    {
        //实际编码过程
        _byteBuf.skipBytes(4);
        _byteBuf.skipBytes(1);

        byte serializeAlgorithm = _byteBuf.readByte();
        byte command = _byteBuf.readByte();
        int length = _byteBuf.readInt();
        byte[] bytes = new byte[length];
        _byteBuf.readBytes(bytes);

        //获取序列化算法
        Serializer serializer = SerializerMgr.INSTANCE.getSerializer(serializeAlgorithm);
        if (serializer == null)
            return null;

        //获取数据包类型
        Class<? extends Packet> commandClazz = CommandMgr.INSTANCE.getCommand(command);
        if (commandClazz == null)
            return null;

        return serializer.deserialize(commandClazz, bytes);
    }
}
