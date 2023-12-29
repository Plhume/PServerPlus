package fr.plhume.pserverplus;

import fr.plhume.pserverplus.commands.FlyCommands;
import fr.plhume.pserverplus.listeners.onJoinListener;
import fr.plhume.pserverplus.listeners.onLeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class PServerPlus extends JavaPlugin {

    private static PServerPlus instance;

    @Override
    public void onEnable() {
        getLogger().info("PServerPlus by Plhume");

        saveDefaultConfig();
        instance = this;

        registerCommands();
        registerListeners();

        getLogger().info("Plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled!");
    }

    public static PServerPlus getInstance() {
        return instance;
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommands());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new onJoinListener(), this);
        getServer().getPluginManager().registerEvents(new onLeaveListener(), this);
    }

    public String getPrefix() {
        return Objects.requireNonNull(getConfig().getString("prefix"));
    }
}
