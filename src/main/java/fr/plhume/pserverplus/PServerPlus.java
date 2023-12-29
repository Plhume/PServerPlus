package fr.plhume.pserverplus;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

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

    public String getPrefix() {
        return Objects.requireNonNull(getConfig().getString("prefix"));
    }
}
