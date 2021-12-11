package me.djaydenr.holopassporttsmp.controller;

import me.djaydenr.holopassporttsmp.dao.PlayerDAO;
import me.djaydenr.holopassporttsmp.model.Player;

import java.util.Optional;

public class PlayerController {

    private PlayerDAO playerDAO;

    public PlayerController(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public boolean createPlayer(Player player) {
        if (player != null &&
                player.getId().trim().length() > 0 &&
                player.getNaam().trim().length() > 0) {
            return playerDAO.insertPlayer(player);
        }
        return false;
    }

    public Optional<Player> getPlayer(String id) {
        if (id.trim().length() > 0) {
            return playerDAO.getPlayer(id);
        }
        return Optional.empty();
    }

    public boolean updatePlayer(Player player, String id) {
        if (id.trim().length() > 0) {
            Optional<Player> p = playerDAO.getPlayer(id);
            if (p.isPresent()) {
                Player pl = p.get();
                if (player.getId() != null && player.getId().trim().length() > 0) {
                    pl.setId(player.getId());
                }
                if (player.getNaam() != null && player.getNaam().trim().length() > 0) {
                    pl.setNaam(player.getNaam());
                }
                if (player.getGeslacht() != null && player.getGeslacht().trim().length() > 0) {
                    pl.setGeslacht(player.getGeslacht());
                }
                if (player.getLeeftijd() > 0) {
                    pl.setLeeftijd(player.getLeeftijd());
                }
                return playerDAO.updatePlayer(pl, id);
            }
        }
        return false;
    }

    public boolean deletePlayer(String id) {
        if (id.trim().length() > 0) {
            return playerDAO.deletePlayer(id);
        }
        return false;
    }

}
