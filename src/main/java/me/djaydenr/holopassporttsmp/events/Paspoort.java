package me.djaydenr.holopassporttsmp.events;

import me.djaydenr.holopassporttsmp.utils.HologramManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;


public class Paspoort implements Listener {

    private final HologramManager hologramManager;

    public Paspoort(HologramManager hologramManager) {
        this.hologramManager = hologramManager;
    }

    @EventHandler
    private void onJoinPlayer(PlayerJoinEvent event) {
        addPassportInInventory(event.getPlayer());
    }

    @EventHandler
    private void onRespawnPlayer(PlayerRespawnEvent event) {
        addPassportInInventory(event.getPlayer());
    }

    @EventHandler
    private void onDeathPlayer(PlayerDeathEvent event) {
        event.getDrops().clear();
    }

    @EventHandler
    private void onPlayerDropItem(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType().equals(Material.NETHER_STAR)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        if (event != null) {
            Player p = event.getPlayer();
            if (p.getInventory().getItemInMainHand().getType().equals(Material.NETHER_STAR) &&
                    !hologramManager.playerHaveAlreadyActiveHologram(p) &&
            event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                hologramManager.showHologram(p);
            }
        }
    }

    @EventHandler
    public void onInventoryModify(InventoryClickEvent event) {
        if (event.getCurrentItem().getType().equals(Material.NETHER_STAR)) {
            event.setCancelled(true);
        }
    }

    private void addPassportInInventory(Player player) {
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR);
        if (player.getInventory().getItem(8) == null) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName("Paspoort");
            itemStack.setItemMeta(itemMeta);
            player.getInventory().setItem(8, itemStack);
        }
    }

}
