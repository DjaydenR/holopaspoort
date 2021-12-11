package me.djaydenr.holopassporttsmp.model;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;

public class HologramPack {

    private Hologram hologram;
    private Player player;
    private final LocalDateTime localDateTime;

    public HologramPack(Hologram hologram, Player player) {
        this.hologram = hologram;
        this.player = player;
        this.localDateTime = LocalDateTime.now();
    }

    public Hologram getHologram() {
        return hologram;
    }

    public void setHologram(Hologram hologram) {
        this.hologram = hologram;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public String toString() {
        return "HologramPack{" +
                "hologram=" + hologram +
                ", player=" + player +
                '}';
    }

}
