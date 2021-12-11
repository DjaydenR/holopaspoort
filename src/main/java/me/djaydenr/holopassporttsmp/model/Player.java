package me.djaydenr.holopassporttsmp.model;

public class Player {

    private String id;
    private String naam;
    private int leeftijd;
    private String geslacht;

    public Player() {
    }

    public Player(String id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public Player(String id, String naam, int leeftijd, String geslacht) {
        this.id = id;
        this.naam = naam;
        this.leeftijd = leeftijd;
        this.geslacht = geslacht;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getLeeftijd() {
        return leeftijd;
    }

    public void setLeeftijd(int leeftijd) {
        this.leeftijd = leeftijd;
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", naam='" + naam + '\'' +
                ", leeftijd=" + leeftijd +
                ", geslacht='" + geslacht + '\'' +
                '}';
    }

}
