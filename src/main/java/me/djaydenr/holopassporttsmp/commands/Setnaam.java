package me.djaydenr.holopassporttsmp.commands;

import me.djaydenr.holopassporttsmp.controller.PlayerController;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class Setnaam implements CommandExecutor {

    private Plugin plugin;
    private PlayerController playerController;

    public Setnaam(Plugin plugin, PlayerController playerController) {
        this.plugin = plugin;
        this.playerController = playerController;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 2 && args[0].trim().length() > 0 && args[1].trim().length() > 0) {

                me.djaydenr.holopassporttsmp.model.Player p = new me.djaydenr.holopassporttsmp.model.Player();

                Player foundPlayer = Bukkit.getPlayer(args[0]);
                if (foundPlayer != null) {
                    p.setNaam(args[1]);
                    player.setDisplayName(args[1]);
                    playerController.updatePlayer(p, foundPlayer.getUniqueId().toString());
                    return true;
                } else {
                    player.sendMessage("De gekozen player is niet aanwezig");
                }

            }

        }
        return false;
    }
}