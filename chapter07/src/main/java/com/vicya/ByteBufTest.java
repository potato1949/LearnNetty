package com.vicya;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufTest
{
    public static void main(String[] args)
    {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        System.out.println("allocate ByteBuf(9,100) buffer:" + buffer);

        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        System.out.println("writeBytes(1,2,3,4) buffer:" + buffer);

        buffer.writeInt(12);
        System.out.println("writeInt(12) buffer:" + buffer);

        buffer.writeBytes(new byte[]{5});
        System.out.println("writeBytes(5) buffer:" + buffer);

        buffer.writeBytes(new byte[]{6});
        System.out.println("writeBytes(6) buffer:" + buffer);

        System.out.println("getByte(3) return: " + buffer.getByte(3));
        System.out.println("getByte(3) buffer:" + buffer);

        System.out.println("setByte(3) return: " + buffer.setByte(buffer.readableBytes() + 1, 0));
        System.out.println("setByte(3) buffer:" + buffer);




    }
}
