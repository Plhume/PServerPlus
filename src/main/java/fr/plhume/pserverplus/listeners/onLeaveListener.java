package fr.plhume.pserverplus.listeners;

import fr.plhume.pserverplus.PServerPlus;
import fr.plhume.pserverplus.modules.PlayerModules;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class onLeaveListener implements Listener {

    private final PServerPlus plugin = PServerPlus.getInstance();

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        if (!PlayerModules.joinLeaveModule()) {
            return;
        }

        event.quitMessage(MiniMessage.miniMessage().deserialize(
                plugin.getPrefix() + Objects.requireNonNull(
                        plugin.getConfig().getString("messages.leave")
                ).replace("%player%", event.getPlayer().getName())
        ));
    }
}
