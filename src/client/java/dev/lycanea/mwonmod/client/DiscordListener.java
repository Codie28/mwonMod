package dev.lycanea.mwonmod.client;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.*;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;

public class DiscordListener implements IPCListener {
    private boolean connected = false;

    @Override
    public void onReady(IPCClient client) {
        connected = true;
    }

    @Override
    public void onDisconnect(IPCClient client, Throwable t) {
        connected = false;
        if (DiscordManager.enabled) {
            try {
                client.connect(DiscordBuild.ANY);
            } catch (NoDiscordClientException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isConnected() {
        return connected;
    }
}
