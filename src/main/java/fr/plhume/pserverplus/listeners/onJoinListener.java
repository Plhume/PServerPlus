package fr.plhume.pserverplus.listeners;

import fr.plhume.pserverplus.PServerPlus;
import fr.plhume.pserverplus.modules.PlayerModules;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class onJoinListener implements Listener {

    private final PServerPlus plugin = PServerPlus.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!PlayerModules.joinLeaveModule()) {
            return;
        }

        if (!event.getPlayer().hasPlayedBefore()) {
            if (!PlayerModules.joinLeaveModule()) {
                return;
            }

            event.joinMessage(MiniMessage.miniMessage().deserialize(
                    plugin.getPrefix() + Objects.requireNonNull(
                            plugin.getConfig().getString("messages.first-join")
                    ).replace("%player%", event.getPlayer().getName())
            ));
            return;
        }

        event.joinMessage(MiniMessage.miniMessage().deserialize(
                plugin.getPrefix() + Objects.requireNonNull(
                        plugin.getConfig().getString("messages.join")
                ).replace("%player%", event.getPlayer().getName())
        ));
    }
}
