package me.djaydenr.holopassporttsmp.utils;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.angeschossen.lands.api.integration.LandsIntegration;
import me.angeschossen.lands.api.player.LandPlayer;
import me.djaydenr.holopassporttsmp.constant.Constants;
import me.djaydenr.holopassporttsmp.controller.PlayerController;
import me.djaydenr.holopassporttsmp.model.HologramPack;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.time.LocalDateTime;
import java.util.*;

public class HologramManager {

    private final List<HologramPack> hologramPacks;
    private final Plugin plugin;
    private final PlayerController playerController;
    private final LandsIntegration landsIntegration;

    public HologramManager(Plugin plugin, PlayerController playerController, LandsIntegration landsIntegration) {
        this.hologramPacks = new ArrayList<>();
        this.plugin = plugin;
        this.playerController = playerController;
        this.landsIntegration = landsIntegration;
    }

    public void showHologram(Player player) {
        Hologram hologram = null;
        Block block = player.getTargetBlockExact(Constants.MAX_DISTANCE_TARGET_BLOCK);
        if (block == null) {
            player.sendMessage(Constants.ERR_NO_BLOCK_IN_RANGE);
        } else {
            Location location = block.getLocation();
            location.setY(location.getY() + Constants.DISTANCE_HOLOGRAM);
            hologram = HologramsAPI.createHologram(plugin, location);

            String textFeelds = plugin.getConfig().getString(Constants.TXT_HOLOGRAM_FEELDS);
            textFeelds = textFeelds.replace("{player}", player.getDisplayName());
            Optional<me.djaydenr.holopassporttsmp.model.Player> optionalPlayer = playerController.getPlayer(player.getUniqueId().toString());
            if (optionalPlayer.isPresent()) {
                textFeelds = textFeelds.replace("{leeftijd}", optionalPlayer.get().getLeeftijd() != 0 ? optionalPlayer.get().getLeeftijd() + "" : "");
                textFeelds = textFeelds.replace("{geslacht}", optionalPlayer.get().getGeslacht() != null ? optionalPlayer.get().getGeslacht() : "");
                textFeelds = textFeelds.replace("{land}", getLandFromPlayer(player));
            }
            List<String> textFeeldArr = new ArrayList<>();
            try {
                textFeeldArr = List.of(textFeelds.split(Constants.CHAR_SPLIT_COMMA));
            } catch (NullPointerException npe) {
                System.out.println(Constants.ERR_HOLOGRAM_FEELDS);
            }

            for (String textFeeld : textFeeldArr) {
                hologram.appendTextLine(textFeeld);
            }
        }
        hologramPacks.add(new HologramPack(hologram, player));
    }

    public void startCheckHologramsForDelete() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Optional<HologramPack> hologramPackOptional = hologramPacks
                                .stream().filter(hp -> hp.getLocalDateTime().isBefore(LocalDateTime.now().plusSeconds(15)))
                                .findFirst();
                        if (hologramPackOptional.isPresent()) {
                            hologramPacks.remove(hologramPackOptional.get());
                            hologramPackOptional.get().getHologram().delete();
                        }
                    }
                }, 0, 15 * 1000);
            }
        }).start();

    }

    public boolean playerHaveAlreadyActiveHologram(Player player) {
        return hologramPacks
                .stream().anyMatch(hp -> hp.getPlayer().getUniqueId().toString()
                        .equals(player.getUniqueId().toString()));
    }

    public void deleteAllActiveHolograms() {
        for (HologramPack hologramPack : hologramPacks) {
            hologramPacks.remove(hologramPack);
            hologramPack.getHologram().delete();
        }
    }

    private String getLandFromPlayer(Player p) {
        LandPlayer lp = landsIntegration.getLandPlayer(p.getUniqueId());
        String landnaam = "";
        if (lp != null) {
            if (lp.getLands().stream().findFirst().isPresent()) {
               landnaam = lp.getLands().stream().findFirst().get().getName();
            }
        }
        return landnaam;
    }

}




