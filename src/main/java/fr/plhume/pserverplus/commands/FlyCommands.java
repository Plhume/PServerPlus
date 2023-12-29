package fr.plhume.pserverplus.commands;

import fr.plhume.pserverplus.PServerPlus;
import fr.plhume.pserverplus.modules.PlayerModules;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FlyCommands implements CommandExecutor, TabExecutor {

    private final PServerPlus plugin = new PServerPlus();

    private final String prefix = plugin.getPrefix();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String msg, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (!PlayerModules.flyModule()) {
                sender.sendMessage(MiniMessage.miniMessage().deserialize(
                        Objects.requireNonNull(
                                plugin.getConfig().getString("messages.module-disabled")
                        )
                ));
                return true;
            }

            if (sender instanceof Player player) {
                if (args.length == 0) {
                    if (player.hasPermission("pserverplus.fly") || player.hasPermission("pserverplus.admin")) {
                        if (player.isFlying()) {
                            player.setFlying(false);
                            player.setAllowFlight(false);
                            player.sendMessage(MiniMessage.miniMessage().deserialize(
                                    prefix + plugin.getConfig().getString("messages.fly-disabled")
                            ));
                        } else {
                            player.setFlying(true);
                            player.setAllowFlight(true);
                            player.sendMessage(MiniMessage.miniMessage().deserialize(
                                    prefix + plugin.getConfig().getString("messages.fly-enabled")
                            ));
                        }

                        return true;
                    } else {
                        player.sendMessage(MiniMessage.miniMessage().deserialize(
                                prefix + plugin.getConfig().getString("messages.no-permission")
                        ));
                    }

                    return true;
                }
            } else {
                sender.sendMessage(MiniMessage.miniMessage().deserialize(
                        prefix + plugin.getConfig().getString("messages.no-console")
                ));
                return true;
            }

            if (args.length == 1) {
                Player target = Bukkit.getServer().getPlayer(args[0]);

                if (target == null) {
                    sender.sendMessage(MiniMessage.miniMessage().deserialize(
                            prefix + (plugin.getConfig().getString("messages.player-not-found")).replace("%player%", args[0])
                    ));

                    return true;
                }

                if (target.isFlying()) {
                    target.setFlying(false);
                    target.setAllowFlight(false);
                    target.sendMessage(MiniMessage.miniMessage().deserialize(
                            prefix + plugin.getConfig().getString("messages.fly-disabled")
                    ));
                    sender.sendMessage(MiniMessage.miniMessage().deserialize(
                            prefix + (plugin.getConfig().getString("messages.fly-disabled-other")).replace("%player%", target.getName())
                    ));
                } else {
                    target.setFlying(true);
                    target.setAllowFlight(true);
                    target.sendMessage(MiniMessage.miniMessage().deserialize(
                            prefix + plugin.getConfig().getString("messages.fly-enabled")
                    ));
                    sender.sendMessage(MiniMessage.miniMessage().deserialize(
                            prefix + (plugin.getConfig().getString("messages.fly-enabled-other")).replace("%player%", target.getName())
                    ));
                }

                return true;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String msg, @NotNull String[] args) {
        List<String> rList = new ArrayList<>();

        if (args.length == 0) {
            rList.add("fly");
            return rList;
        }

        if (cmd.getName().equalsIgnoreCase("fly")) {
            if (args.length == 1) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    rList.add(player.getName());
                }
                return rList;
            }
        }

        return rList;
    }
}
