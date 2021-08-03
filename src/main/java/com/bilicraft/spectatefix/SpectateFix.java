package com.bilicraft.spectatefix;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public final class SpectateFix extends Plugin implements Listener {
    private SpectateAdapter listener;
    private boolean loaded = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
       getProxy().getPluginManager().registerListener(this,this);
    }


    @EventHandler
    public void proxyInit(net.md_5.bungee.api.event.LoginEvent event){
        if(loaded){
            return;
        }
        listener = new SpectateAdapter();
        listener.register();
        loaded = true;
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
      listener.unregister();
    }
}
