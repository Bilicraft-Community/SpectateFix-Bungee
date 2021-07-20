package com.bilicraft.spectatefix;

import de.exceptionflug.protocolize.api.protocol.ProtocolAPI;
import de.exceptionflug.protocolize.api.util.ProtocolVersions;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.protocol.Protocol;
import net.md_5.bungee.protocol.ProtocolConstants;

import java.util.HashMap;
import java.util.Map;

public final class SpectateFix extends Plugin {
    private final SpectateAdapter listener = new SpectateAdapter();

    @Override
    public void onEnable() {
        // Plugin startup logic
        Map<Integer, Integer> mapping = new HashMap<>();
        mapping.put(ProtocolVersions.MINECRAFT_LATEST,0x2D);
        ProtocolAPI.getPacketRegistration().registerPacket(Protocol.GAME, ProtocolConstants.Direction.TO_SERVER,Spectate.class,mapping);
        ProtocolAPI.getEventManager().registerListener(listener);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ProtocolAPI.getEventManager().unregisterListener(listener);
    }
}
