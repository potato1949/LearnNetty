package com.vicya.netty.chapter08_SimpleIm.core.client;

import io.netty.util.AttributeKey;

public interface Attributes
{
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
