package fr.plhume.pserverplus;

import org.bukkit.plugin.java.JavaPlugin;

public final class PServerPlus extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("PServerPlus by Plhume");


        getLogger().info("Plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled!");
    }
    }
}
