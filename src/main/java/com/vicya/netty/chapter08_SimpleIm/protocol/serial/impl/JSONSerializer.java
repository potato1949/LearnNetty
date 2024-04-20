package com.vicya.netty.chapter08_SimpleIm.protocol.serial.impl;

import com.alibaba.fastjson.JSON;
import com.vicya.netty.chapter08_SimpleIm.protocol.serial.Serializer;
import com.vicya.netty.chapter08_SimpleIm.protocol.serial.SerializerAlgorithm;

public class JSONSerializer implements Serializer
{
    @Override
    public byte getSerializerAlgorithm()
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
