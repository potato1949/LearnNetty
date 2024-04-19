package com.vicya.netty.chapter08.protocol.serial;

public interface Serializer
{
    /**
     * JSON序列化
     */
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * Java 对象转换成二进制数据
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制数据转换成Java对象
     * @param clazz
     * @param bytes
     * @return
     * @param <T>
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
