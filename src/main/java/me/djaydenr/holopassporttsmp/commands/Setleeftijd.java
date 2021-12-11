package me.djaydenr.holopassporttsmp.commands;

import me.djaydenr.holopassporttsmp.constant.Geslacht;
import me.djaydenr.holopassporttsmp.controller.PlayerController;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class Setleeftijd implements CommandExecutor {

    private PlayerController playerController;

    public Setleeftijd(PlayerController playerController) {
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
                    try {
                        if (Integer.parseInt(args[1]) > 0 && Integer.parseInt(args[1]) < 99) {
                            p.setLeeftijd(Integer.parseInt(args[1]));
                        }
                    } catch (Exception e) {
                        player.sendMessage("Geef een getal in tussen 1 en 99");
                        return false;
                    }
                    playerController.updatePlayer(p, player.getUniqueId().toString());
                    return true;
                } else {
                    player.sendMessage("De gekozen player is niet aanwezig");
                }
            }

        }
        return false;
    }
}