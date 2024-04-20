package com.vicya.protocol.serial;

import com.vicya.protocol.serial.impl.JSONSerializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SerializerMgr
{
    /**
     * 单例模式
     */
    public static final SerializerMgr INSTANCE = new SerializerMgr();

    public SerializerMgr()
    {
        // 注册序列化算法和序列化器
        registerSerializer(SerializerAlgorithm.JSON, new JSONSerializer());
    }

    // 序列化算法和序列化器的映射
    private final Map<Byte, Serializer> serializerMaps = new ConcurrentHashMap<>();

    private void registerSerializer(Byte serializerAlgorithm, Serializer serializer){
        serializerMaps.put(serializerAlgorithm, serializer);
    }

    public Serializer getSerializer(Byte serializerAlgorithm){
        return serializerMaps.get(serializerAlgorithm);
    }

}
