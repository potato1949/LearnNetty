package com.vicya.protocol.packet;

import com.vicya.protocol.packet.impl.LoginRequestPacket;
import com.vicya.protocol.packet.impl.LoginResponsePacket;
import com.vicya.protocol.packet.impl.MessageRequestPacket;
import com.vicya.protocol.packet.impl.MessageResponsePacket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandMgr
{
    /**
     * 单例模式
     */
    public static final CommandMgr INSTANCE = new CommandMgr();

    public CommandMgr()
    {
        // 注册指令
        registerCommand(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        registerCommand(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        registerCommand(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        registerCommand(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
    }

    // 指令和数据包映射
    private final Map<Byte,Class<? extends Packet>> commandMaps = new ConcurrentHashMap<>();

    // 注册指令
    private void registerCommand(Byte command, Class<? extends Packet> packet){
        commandMaps.put(command, packet);
    }

    // 获取指令对应的数据包对象
    public Class<? extends Packet> getPacket(Byte command){
        return commandMaps.get(command);
    }
}
