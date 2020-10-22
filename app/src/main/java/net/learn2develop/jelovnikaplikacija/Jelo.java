package net.learn2develop.jelovnikaplikacija;
// Jelo klasa je drugi korak posle Main Activity. Ona se mora kreirati, kako bismo kreirali posle JeloAdapter, Jelo Provider i List Fragment
public class Jelo {
    private int id, kalorijskaVrednost;
    private String slikaUrl, naziv, opis, kategorija, sastojci;
    double cena;


    public Jelo() {
        id = 0;
        kalorijskaVrednost = 0;
        slikaUrl = "";
        naziv = "";
        opis = "";
        kategorija = "";
        sastojci = "";
        cena = 0;
    }

    public Jelo(int id, String slikaUrl, String naziv, String opis, String kategorija, String sastojci, int kalorijskaVrednost, double cena) {
        this.id = id;
        this.kalorijskaVrednost = kalorijskaVrednost;
        this.slikaUrl = slikaUrl;
        this.naziv = naziv;
        this.opis = opis;
        this.kategorija = kategorija;
        this.sastojci = sastojci;
        this.cena = cena;
    }

    public int getId() {
        return id;
    }

    public int getKalorijskaVrednost() {
        return kalorijskaVrednost;
    }

    public String getSlikaUrl() {
        return slikaUrl;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public String getKategorija() {
        return kategorija;
    }

    public String getSastojci() {
        return sastojci;
    }

    public double getCena() {
        return cena;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKalorijskaVrednost(int kalorijskaVrednost) {
        this.kalorijskaVrednost = kalorijskaVrednost;
    }

    public void setSlikaUrl(String slikaUrl) {
        this.slikaUrl = slikaUrl;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public void setSastojci(String sastojci) {
        this.sastojci = sastojci;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Jelo{" +
                "kalorijskaVrednost=" + kalorijskaVrednost +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", kategorija='" + kategorija + '\'' +
                ", sastojci='" + sastojci + '\'' +
                ", cena=" + cena +
                '}';
    }
}

