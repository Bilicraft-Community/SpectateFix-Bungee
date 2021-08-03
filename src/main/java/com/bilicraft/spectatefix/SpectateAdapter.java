package com.bilicraft.spectatefix;

import com.google.gson.Gson;
import de.exceptionflug.protocolize.api.event.PacketReceiveEvent;
import de.exceptionflug.protocolize.api.event.PacketSendEvent;
import de.exceptionflug.protocolize.api.handler.PacketAdapter;
import de.exceptionflug.protocolize.api.protocol.ProtocolAPI;
import de.exceptionflug.protocolize.api.protocol.Stream;
import de.exceptionflug.protocolize.api.util.ProtocolVersions;
import net.md_5.bungee.protocol.Protocol;
import net.md_5.bungee.protocol.ProtocolConstants;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SpectateAdapter extends PacketAdapter<Spectate> {
    private final Gson gson = new Gson();

    public SpectateAdapter() {
        super(Stream.UPSTREAM, Spectate.class);
    }

    public void register(){
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int i = 0; i <= ProtocolVersions.MINECRAFT_LATEST; i++) {
            mapping.put(i,0x2D);
        }
        ProtocolAPI.getPacketRegistration().registerPacket(Protocol.GAME, ProtocolConstants.Direction.TO_SERVER,Spectate.class,mapping);
        ProtocolAPI.getEventManager().registerListener(this);
    }

    public void unregister(){
        ProtocolAPI.getEventManager().unregisterListener(this);
    }

    @Override
    public void receive(PacketReceiveEvent<Spectate> event){
        event.getPlayer().getServer().sendData("spectatefix:tp",gson.toJson(new MessagePacket(event.getPlayer().getUniqueId(),event.getPacket().getTarget())).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void send(PacketSendEvent<Spectate> event) {

    }
}
