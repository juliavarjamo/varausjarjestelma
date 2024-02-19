import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Varausmoottori {
    // Attribuutit
    private static volatile Varausmoottori instanssi;
    private List<Asiakas> asiakkaat;
    private List<Varaus> varaukset;
    private List<Tila> tilat;
    private Scanner lukija;

    // Rakentaja
    private Varausmoottori() {
        asiakkaat = new ArrayList<>();
        varaukset = new ArrayList<>();
        tilat = new ArrayList<>();
        lukija = new Scanner(System.in);
    }

    // Metodit
    public static Varausmoottori getInstanssi() {
        if (instanssi == null) {
            synchronized(Varausmoottori.class) {
                if (instanssi == null) {
                    instanssi = new Varausmoottori();
                }
            }
        }
        return instanssi;
    }

    public void kaynnista() {
        while (true) {
            System.out.println("1 - tee uusi varaus");
            System.out.println("2 - poista varaus");
            System.out.println("3 - lopeta");
            
            int toiminto = lukija.nextInt();
            lukija.nextLine();

            switch (toiminto) {
                case 1:
                    teeVaraus();
                    break;
                case 2:
                    poistaVaraus();
                    break;
                case 3: 
                    System.out.println("Lopetetaan.");
                    lukija.close();
                    return;
                default:
                    System.out.println("Virheellinen syöte! Syötä luku 1, 2 tai 3.");
            }
        }
    }

    public void teeVaraus() {
        Asiakas asiakas = haeAsiakas();
        Tila tila = haeTila();

        System.out.println("Syötä alkuaika tasatunnein: ");
        int alkuaika = lukija.nextInt();

        System.out.println("Syötä loppuaika tasatunnein: ");
        int loppuaika = lukija.nextInt();

        if (onkoTilaVarattuna(tila, alkuaika, loppuaika)) {
            System.out.println("Varausta ei voi tehdä, tila on varattu haluttuna aikana.");
            return;
        }

        int varausId = varaukset.size() + 1;

        Varaus varaus = new Varaus(varausId, tila, asiakas, alkuaika, loppuaika);

        varaukset.add(varaus);
        asiakas.lisaaVaraus(varaus);
        tila.lisaaVaraus(varaus);
    }

    public void poistaVaraus() {
        System.out.println("Syötä poistettavan varauksen id: ");
        int id = lukija.nextInt();

        for (Varaus varaus : varaukset) {
            if (varaus.getVarausId() == id) {
                Asiakas asiakas = varaus.getAsiakas();
                asiakas.poistaVaraus(varaus);
                Tila tila = varaus.getTila();
                tila.poistaVaraus(varaus);
                varaukset.remove(varaus);
            }
        }
    }

    private Asiakas haeAsiakas() {
        System.out.println("Syötä nimi: ");
        String nimi = lukija.nextLine();

        System.out.println("Syötä sähköposti: ");
        String sahkoposti = lukija.nextLine();

        // Jos asiakas on jo olemassa
        if (onkoAsiakasOlemassa(nimi, sahkoposti) != null) {
            return onkoAsiakasOlemassa(nimi, sahkoposti);
        } 

        int id = asiakkaat.size() + 1;

        Asiakas asiakas = new Asiakas(id, nimi, sahkoposti);
        asiakkaat.add(asiakas);

        return asiakas;
    }

    private Tila haeTila() {
        System.out.println("Syötä tilan nimi: ");
        String nimi = lukija.nextLine();

        if (onkoTilaOlemassa(nimi) != null) {
            return onkoTilaOlemassa(nimi);
        }

        int id = tilat.size() + 1;

        Tila tila = new Tila(nimi, id);
        tilat.add(tila);

        return tila;
    }

    private Asiakas onkoAsiakasOlemassa(String nimi, String sahkoposti) {
        for (Asiakas asiakas : asiakkaat) {
            if (asiakas.getNimi().equals(nimi) && asiakas.getSahkoposti().equals(sahkoposti)) {
                return asiakas;
            }
        }
        return null;
    }

    private Tila onkoTilaOlemassa(String nimi) {
        for (Tila tila : tilat) {
            if (tila.getNimi().equals(nimi)) {
                return tila;
            }
        }
        return null;
    }

    private boolean onkoTilaVarattuna(Tila tila, int alkuaika, int loppuaika) {
        for (Varaus varaus : tila.getVaraukset()) {
            if (varaus.getAlkuaika() <= alkuaika && alkuaika < varaus.getLoppuaika()) {
                return true;
            }
        }
        return false;
    }
}
