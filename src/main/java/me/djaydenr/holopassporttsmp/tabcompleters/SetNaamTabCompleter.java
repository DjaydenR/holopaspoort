package me.djaydenr.holopassporttsmp.tabcompleters;

import me.djaydenr.holopassporttsmp.constant.Geslacht;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SetNaamTabCompleter implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return Bukkit.getServer()
                    .getOnlinePlayers()
                    .stream()
                    .map(Player::getDisplayName)
                    .collect(Collectors.toList());
        }
        return Arrays.asList();
    }
}
