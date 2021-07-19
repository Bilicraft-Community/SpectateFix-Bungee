package com.bilicraft.spectatefix;

import com.google.gson.Gson;
import de.exceptionflug.protocolize.api.event.PacketReceiveEvent;
import de.exceptionflug.protocolize.api.event.PacketSendEvent;
import de.exceptionflug.protocolize.api.handler.PacketAdapter;
import de.exceptionflug.protocolize.api.protocol.Stream;

import java.nio.charset.StandardCharsets;

public class SpectateAdapter extends PacketAdapter<Spectate> {
    private final Gson gson = new Gson();

    public SpectateAdapter() {
        super(Stream.UPSTREAM, Spectate.class);
    }

    @Override
    public void receive(PacketReceiveEvent<Spectate> event){
        event.getPlayer().getServer().sendData("spectatefix:tp",gson.toJson(new MessagePacket(event.getPlayer().getUniqueId(),event.getPacket().getTarget())).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void send(PacketSendEvent<Spectate> event) {

    }
}
