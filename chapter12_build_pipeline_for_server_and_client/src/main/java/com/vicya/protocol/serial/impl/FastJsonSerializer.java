package com.vicya.protocol.serial.impl;

import com.alibaba.fastjson.JSON;
import com.vicya.protocol.serial.Serializer;
import com.vicya.protocol.serial.SerializerAlgorithm;

public class FastJsonSerializer implements Serializer
{
    @Override
    public Byte getSerializerAlgorithm()
    {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object)
    {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes)
    {
        return JSON.parseObject(bytes, clazz);
    }
}
