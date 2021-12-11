package me.djaydenr.holopassporttsmp.commands;

import me.djaydenr.holopassporttsmp.constant.Geslacht;
import me.djaydenr.holopassporttsmp.controller.PlayerController;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Setgeslacht implements CommandExecutor {

    private PlayerController playerController;

    public Setgeslacht(PlayerController playerController) {
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
                    if (args[1].equalsIgnoreCase(Geslacht.MAN.name())) {
                        p.setGeslacht(Geslacht.MAN.name());
                    } else if (args[1].equalsIgnoreCase(Geslacht.VROUW.name())) {
                        p.setGeslacht(Geslacht.VROUW.name());
                    } else if (args[1].equalsIgnoreCase(Geslacht.X.name())) {
                        p.setGeslacht(Geslacht.X.name());
                    } else {
                        player.sendMessage("Geen goed geslacht gekozen kies uit: man, vrouw, x");
                        return false;
                    }
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
