package me.djaydenr.holopassporttsmp.events;

import me.djaydenr.holopassporttsmp.controller.PlayerController;
import me.djaydenr.holopassporttsmp.model.Player;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerJoin implements Listener {

    private PlayerController playerController;

    public PlayerJoin(PlayerController playerController) {
        this.playerController = playerController;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        registerPlayer(event.getPlayer());
    }

    private void registerPlayer(org.bukkit.entity.Player player) {
        if (playerController.getPlayer(player.getUniqueId().toString()).isEmpty()) {
            if (!playerController.createPlayer(new Player(player.getUniqueId().toString(), player.getDisplayName()))) {
                System.out.println("speler toevoegen aan de databank is mislukt");
            }
        } else {
            player.setDisplayName(playerController.getPlayer(player.getUniqueId().toString()).get().getNaam());
        }
    }

}

