package com.vicya.netty.chapter08_SimpleIm.protocol.packet;

import com.vicya.netty.chapter08_SimpleIm.protocol.serial.Serializer;
import com.vicya.netty.chapter08_SimpleIm.protocol.serial.SerializerAlgorithm;
import com.vicya.netty.chapter08_SimpleIm.protocol.serial.SerializerMgr;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class PacketCodeC
{
    /**
     * 自定义协议：
     *      [魔数:4字节] [版本号:1字节] [序列化算法:1字节] [指令:1字节] [数据长度:4字节] [数据:N字节]
     */

    private static final int MAGIC_NUMBER = 0x12345678;

    public static ByteBuf encode(Packet packet)
    {
        //1.创建ByteBuf对象
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        //获取序列化算法
        Serializer serializer = SerializerMgr.INSTANCE.getSerializer(SerializerAlgorithm.JSON);
        //2.序列化Java对象
        byte[] bytes = serializer.serialize(packet);

        //3.实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(serializer.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public static Packet decode(ByteBuf byteBuf)
    {
        //跳过魔数
        byteBuf.skipBytes(4);
        //跳过版本号
        byteBuf.skipBytes(1);
        //序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();
        //指令
        byte command = byteBuf.readByte();
        //数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = CommandMgr.INSTANCE.getPacket(command);
        Serializer serializer = SerializerMgr.INSTANCE.getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null)
        {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }
}
