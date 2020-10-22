package net.learn2develop.jelovnikaplikacija;

import java.util.ArrayList;
import java.util.List;
// Klasa JeloProvider je treci korak posle klase Jelo i JeloAdapter, gde moramo odrediti unos svih podataka za nasa jela.
public class JeloProvider {
    //A. Kreiramo staticke metode
    private static List<Jelo> jela = null;
    private static List<Jelo> jelaByKategorija = null;
    private static List<String> kategorije = null;
//B. Poziv na metode u Provider-u uvek mora biti  static void init (), kako bismo unutar te metode pozvali List metodu Jelo i dali
    //konkretne opise sto ce jedna applikacija da sadrzi. init () vrsi inicijalizaciju liste, ako je puna - nista ne radi.
// Kad proveri da je prazna, on popunjava listu.
    private static void init() {
        if (jela == null) {
            jela = new ArrayList<>();
            jela.add(new Jelo(
                    0, "jelo1.j pg", "Sarma", "Sarma sa kupusom", "Glavno jelo",
                    "Mleveno meso i kupus"
                    , 300, 499.99));
            jela.add(new Jelo(
                    1, "jelo2.jpg", "Palacinke", "Palacinka sa kremom i plazmom", "Desert",
                    "Jaja , brasno , mleko , voda , krem , plazma", 450, 149.99));
            jela.add(new Jelo(
                    2, "jelo3.jpg", "Salata", "Salata od kupusa", "Dodatak jelu",
                    "Kupus , sirce , so", 40, 99.99));
            jela.add(new Jelo(
                    3, "jelo4.jpg", "Meze", "Lako jelo pre glavnog jela", "Predjelo",
                    "Kulen , slanina , salama , sir", 150, 199.99));
            jela.add(new Jelo(
                    3, "jelo5.jpg", "Meze1", "Lako jelo pre glavnog jela", "Predjelo",
                    "Kulen , slanina , salama , sir", 150, 199.99));
        }
    }
// C. Pozivamo ispis svih jela u fijoci Jela i unutar fijoke Kategorije
    public static List<Jelo> getAllJela() {
        init();
        return jela;
    }
// D. Pozivamo ispis svih kategorije jela za fragment listu Kategorije u samoj app.
    public static List<String> getKategorije(){
        init();

        kategorije = new ArrayList<>();
         //Ovde ce nam ispisati kategoriju jela po imenima kategorije (Glavno jelo, dezert, itd.), te ce
        //pozvati static List<Jelo> jela
        for (int i = 0; i < jela.size(); i++) {
            //pozivom na .getKategorija vec se pozivamo na public String getKategorija(), koju smo prvo odredili u klasi Jelo!
            if (!kategorije.contains(jela.get(i).getKategorija()))
                kategorije.add(jela.get(i).getKategorija());
        }

        return kategorije;
    }
// E. Ispih svih kategorisanih jela po id-ju unutar jedne kategorije
    public static List<Jelo> getAllJelaByKategorija(String kategorije) {
        init();

        jelaByKategorija = new ArrayList<>();

        for (int i = 0; i < jela.size(); i++) {
            //Ovo nam ispisuje jela po odredjenim kategorijama (na primer Dezert ima dva jela) - drugi prozor
           if (getJeloById(i).getKategorija().equals(kategorije))
                jelaByKategorija.add(jela.get(i));
        }

        return jelaByKategorija;
    }
// getJelobyID mora prvo da se uradi, kako bismo pozvali metodu List<Jelo> (E). Ova metoda nam vraca
    //nazive jela po njihovoj jedinstvoj numeraciji (id)
    public static Jelo getJeloById(int id) {
        init();

        if (id >= 0 && id < jela.size()) {
            return jela.get(id);
        }
        return null;
    }
}

