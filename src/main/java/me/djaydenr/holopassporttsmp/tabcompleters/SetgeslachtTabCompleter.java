package me.djaydenr.holopassporttsmp.tabcompleters;

import me.djaydenr.holopassporttsmp.constant.Geslacht;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SetgeslachtTabCompleter implements TabCompleter {

    @Nullable
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            return Bukkit.getServer()
                    .getOnlinePlayers()
                    .stream()
                    .map(Player::getDisplayName)
                    .collect(Collectors.toList());
        }
        if (args.length == 2) {
            return Arrays.asList(Geslacht.MAN.name(), Geslacht.VROUW.name(), Geslacht.X.name());
        }
        return Arrays.asList();
    }
}
