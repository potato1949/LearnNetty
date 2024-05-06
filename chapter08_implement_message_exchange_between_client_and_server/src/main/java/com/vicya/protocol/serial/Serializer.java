package com.vicya.protocol.serial;

public interface Serializer
{
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
