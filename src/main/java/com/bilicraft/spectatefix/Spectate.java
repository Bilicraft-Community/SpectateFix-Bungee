package com.bilicraft.spectatefix;

import de.exceptionflug.protocolize.api.protocol.AbstractPacket;
import de.exceptionflug.protocolize.api.util.BufferUtil;
import io.netty.buffer.ByteBuf;
import net.md_5.bungee.protocol.ProtocolConstants;

import java.util.UUID;

public class Spectate extends AbstractPacket {
    public Spectate(UUID target) {
        this.target = target;
    }

    public Spectate(){
        target = null;
    }

    private UUID target;
    @Override
    public void read(final ByteBuf buf, final ProtocolConstants.Direction direction, final int protocolVersion) {
        target = readUUID(buf);
        BufferUtil.finishBuffer(this, buf, direction, protocolVersion);
    }

    public UUID getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }
}
