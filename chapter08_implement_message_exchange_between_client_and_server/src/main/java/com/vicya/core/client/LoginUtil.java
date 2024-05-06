package com.vicya.core.client;

import io.netty.channel.Channel;

public class LoginUtil
{
    public static void markAsLogin(Channel _channel)
    {
        _channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel _channel)
    {
        return _channel.attr(Attributes.LOGIN).get() != null;
    }
}
