package com.vicya.protocol.packet.impl;

import com.vicya.protocol.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.vicya.protocol.packet.Command.LOGIN_RESPONSE;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponsePacket extends Packet
{
    private boolean isSuccess;
    private String reason;

    @Override
    public Byte getCommand()
    {
        return LOGIN_RESPONSE;
    }
}
