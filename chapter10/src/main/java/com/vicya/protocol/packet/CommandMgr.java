package com.vicya.protocol.packet;

import java.util.HashMap;
import java.util.Map;

public class CommandMgr
{
    public static final CommandMgr INSTANCE = new CommandMgr();

    private final Map<Byte, Class<? extends Packet>> commandMap = new HashMap<>();

    public CommandMgr()
    {
//        register(Command.LOGIN_REQUEST,);
    }

    /**
     * 注册序列化类
     */
    private void register(Byte command, Class<? extends Packet> packet)
    {
        commandMap.put(command, packet);
    }

    public Class<? extends Packet> getSerializer(Byte command)
    {
        return commandMap.get(command);
    }
}
