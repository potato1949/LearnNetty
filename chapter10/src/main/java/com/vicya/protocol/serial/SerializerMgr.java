package com.vicya.protocol.serial;

import com.vicya.protocol.serial.impl.FastJsonSerializer;

import java.util.HashMap;
import java.util.Map;

public class SerializerMgr
{
    public static final SerializerMgr INSTANCE = new SerializerMgr();

    private final Map<Byte, Serializer> serializerMap = new HashMap<>();

    public SerializerMgr()
    {
        register(new FastJsonSerializer());
    }

    /**
     * 注册序列化类
     */
    private void register(Serializer serializer)
    {
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public Serializer getSerializer(Byte serializerAlgorithm)
    {
        return serializerMap.get(serializerAlgorithm);
    }
}
