package me.djaydenr.holopassporttsmp.dao;

import me.djaydenr.holopassporttsmp.model.Player;

import java.sql.*;
import java.util.Optional;

public class PlayerDAO {

    private Connection connection;

    public PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertPlayer(Player player) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PLAYER VALUES (?, ?, ?, ?)");
            ps.setString(1, player.getId());
            ps.setString(2, player.getNaam());
            ps.setInt(3, player.getLeeftijd());
            ps.setString(4, player.getGeslacht());
            int i = ps.executeUpdate();

            if(i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public Optional<Player> getPlayer(String id) {

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER WHERE player_id=\"" + id + "\"");

            if(rs.next())
            {
                Player player = new Player();

                player.setId( rs.getString("player_id") );
                player.setNaam( rs.getString("naam") );
                player.setLeeftijd( rs.getInt("leeftijd") );
                player.setGeslacht( rs.getString("geslacht") );

                return Optional.of(player);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    public boolean updatePlayer(Player player, String id) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE PLAYER SET naam = ?, leeftijd = ?, geslacht = ? WHERE player_id=\"" + id + "\"");
            ps.setString(1, player.getNaam());
            ps.setInt(2, player.getLeeftijd());
            ps.setString(3, player.getGeslacht());
            int i = ps.executeUpdate();

            if(i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean deletePlayer(String id) {
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM PLAYER WHERE player_id=\"" + id + "\"");

            if(i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

}
