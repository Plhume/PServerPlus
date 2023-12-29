package fr.plhume.pserverplus;

import org.bukkit.plugin.java.JavaPlugin;

public final class PServerPlus extends JavaPlugin {

    private static PServerPlus instance;

    @Override
    public void onEnable() {
        getLogger().info("PServerPlus by Plhume");

        saveDefaultConfig();
        instance = this;

        getLogger().info("Plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled!");
    }

    public static PServerPlus getInstance() {
        return instance;
    }
    }
}
