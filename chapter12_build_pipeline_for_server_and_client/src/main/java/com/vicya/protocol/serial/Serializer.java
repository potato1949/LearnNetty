package com.vicya.protocol.serial;

public interface Serializer
{
    /**
     * 获取序列化算法id
     */
    Byte getSerializerAlgorithm();

    /**
     * 序列化对象转为二进制数组
     */
    byte[] serialize(Object object);

    /**
     * 反序列化二进制数组为对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
