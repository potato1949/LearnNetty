package com.vicya.protocol.packet.impl;

import com.vicya.protocol.packet.Packet;
import lombok.*;

import static com.vicya.protocol.packet.Command.LOGIN_REQUEST;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequestPacket extends Packet
{
    private String userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand()
    {
        return LOGIN_REQUEST;
    }
}
