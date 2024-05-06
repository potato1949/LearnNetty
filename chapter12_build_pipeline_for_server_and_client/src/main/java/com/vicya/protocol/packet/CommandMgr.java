package com.vicya.protocol.packet;

import com.vicya.protocol.packet.impl.LoginRequestPacket;
import com.vicya.protocol.packet.impl.LoginResponsePacket;
import com.vicya.protocol.packet.impl.MessageRequestPacket;
import com.vicya.protocol.packet.impl.MessageResponsePacket;

import java.util.HashMap;
import java.util.Map;

public class CommandMgr
{
    public static final CommandMgr INSTANCE = new CommandMgr();

    private final Map<Byte, Class<? extends Packet>> commandMap = new HashMap<>();

    public CommandMgr()
    {
        register(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        register(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        register(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        register(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
    }

    /**
     * 注册序列化类
     */
    private void register(Byte command, Class<? extends Packet> packet)
    {
        commandMap.put(command, packet);
    }

    public Class<? extends Packet> getCommand(Byte command)
    {
        return commandMap.get(command);
    }
}
