package com.vicya.core.client;

import io.netty.util.AttributeKey;

public interface Attributes
{
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
