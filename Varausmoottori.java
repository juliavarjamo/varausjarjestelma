import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Luokka tilanvarausjärjestelmän moottorille. 
 * Pyörittää tilanvarausjärjestelmän toimintoja. 
 */
public class Varausmoottori {
    private static volatile Varausmoottori instanssi;

    private List<Asiakas> asiakkaat;
    private List<Varaus> varaukset;
    private List<Tila> tilat;
    private Scanner lukija;

    private Varausmoottori() {
        asiakkaat = new ArrayList<>();
        varaukset = new ArrayList<>();
        tilat = new ArrayList<>();
        lukija = new Scanner(System.in);
    }

    /**
     * Singleton-pattern varmistaa, että luokasta on vain yksi instanssi. 
     * 
     * @return instanssi varausmoottorista.
     */
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

    /**
     * Ohjelman runko, kysyy käyttäjältä toiminnon ja kutsuu 
     * metodeita toiminnon mukaan.
     */
    public void kaynnista() {
        try {
            while (true) {
                System.out.println("1 - tee uusi varaus");
                System.out.println("2 - poista varaus");
                System.out.println("3 - tulosta varaukset");
                System.out.println("4 - tulosta asiakkaat");
                System.out.println("5 - tulosta tilat");
                System.out.println("6 - lisää varusteita tilaan");
                System.out.println("7 - lopeta");
                System.out.print("Syötä toiminto: ");
                
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
                        tulostaVaraukset();
                        break;
                    case 4:
                        tulostaAsiakkaat();
                        break;
                    case 5:
                        tulostaTilat();
                        break;
                    case 6:
                        hallitseVarustuksia();
                        break;
                    case 7: 
                        System.out.println("Lopetetaan.");
                        lukija.close();
                        return;
                    default:
                        System.out.println("Virheellinen syöte! Syötä luku 1, 2, 3 tai 4.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Virheellinen syöte! Syötä luku 1, 2, 3 tai 4.");
            lukija.nextLine(); 
            kaynnista();
        }
    }

    /**
     * Luo uuden varauksen ja lisää sen kaikkiin varauksiin sekä
     * asiakkaiden ja tilojen varauksiin.
     */
    private void teeVaraus() {
        try {
            Asiakas asiakas = haeAsiakas();
            Tila tila = haeTila();

            System.out.println("Syötä alkuaika tasatunnein:");
            int alkuaika = lukija.nextInt();

            System.out.println("Syötä loppuaika tasatunnein:");
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
        } catch (InputMismatchException e) {
            System.out.println("Virheellinen syöte! Syötä ajat lukuina välillä 00 - 23.");
            lukija.nextLine(); 
            teeVaraus();
        }
    }

    /**
     * Poistaa varauksen kaikkialta, mihin se on tallennettu. 
     */
    private void poistaVaraus() {
        try {
            System.out.println("Syötä poistettavan varauksen numero:");
            int id = lukija.nextInt();

            for (int i = 0; i < varaukset.size(); i++) {
                Varaus varaus = varaukset.get(i);
                if (varaus.getVarausId() == id) {
                    Asiakas asiakas = varaus.getAsiakas();
                    asiakas.poistaVaraus(varaus);

                    Tila tila = varaus.getTila();
                    tila.poistaVaraus(varaus);

                    varaukset.remove(i);
                    System.out.println("Varaus poistettu onnistuneesti.");
                    return;
                }
            }
            System.out.println("Varausnumerolla ei löytynyt varausta.");
        } catch (InputMismatchException e) {
            System.out.println("Virheellinen syöte! Syötä varausnumero lukuna.");
            lukija.nextLine(); 
            poistaVaraus();
        }
    }

    /**
     * Tulostaa kaikki varaukset.
     */
    private void tulostaVaraukset() {
        System.out.println("Varaukset:");

        if (varaukset.size() == 0) {
            System.out.println("Ei varauksia.");
            return;
        }

        for (Varaus varaus : varaukset) {
            varaus.tulostaTiedot();
        }
    }


    /**
     * Tulostaa asiakkaiden tiedot.
     */
    private void tulostaAsiakkaat() {
        System.out.println("Asiakkaat:");

        if (asiakkaat.size() == 0) {
            System.out.println("Ei asiakkaita.");
            return;
        }

        for (Asiakas asiakas : asiakkaat) {
            asiakas.tulostaTiedot();
        }
    }

    /**
     * Tulostaa tilojen tiedot. 
     */
    private void tulostaTilat() {
        System.out.println("Tilat:");

        if (tilat.size() == 0) {
            System.out.println("Ei tiloja.");
            return;
        }

        for (Tila tila : tilat) {
            tila.tulostaTiedot();
        }
    }

    /**
     * Lisää varusteita tilaan. 
     */
    private void hallitseVarustuksia() {
        try {
            Tila tila = haeTila();
            System.out.println("Syötä lisättävä varustus:");
            String lisattavaVaruste = lukija.nextLine();
            tila.lisaaVarustus(lisattavaVaruste);
        } catch (InputMismatchException e) {
            System.out.println("Virheellinen syöte! Syötä toiminto L tai P.");
            lukija.nextLine(); 
            hallitseVarustuksia();
        }    
    }

    /**
     * Ottaa asiakkaan tiedot ja tarkistaa, onko asiakas jo olemassa.
     * Lisää asiakkaan asiakaslistaan ja palauttaa asiakas-olion. 
     * 
     * @return asiakas. 
     */
    private Asiakas haeAsiakas() {
        System.out.println("Syötä nimi:");
        String nimi = lukija.nextLine();

        System.out.println("Syötä sähköposti:");
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

    /**
     * Ottaa tilan tiedot ja tarkistaa, onko tila jo olemassa.
     * Lisää tilan tilalistaan ja palauttaa tila-olion. 
     * 
     * @return tila. 
     */
    private Tila haeTila() {
        System.out.println("Syötä tilan nimi:");
        String nimi = lukija.nextLine();

        if (onkoTilaOlemassa(nimi) != null) {
            return onkoTilaOlemassa(nimi);
        }

        int id = tilat.size() + 1;

        Tila tila = new Tila(nimi, id);
        tilat.add(tila);

        return tila;
    }

    /**
     * Käy asiakaslistan läpi ja tarkastelee, onko asiakas jo olemassa.
     * 
     * @param nimi, asiakkaan nimi.
     * @param sahkoposti, asiakaan sähköposti. 
     * @return asiakas, jos löytyy, null, jos ei löydy. 
     */
    private Asiakas onkoAsiakasOlemassa(String nimi, String sahkoposti) {
        for (Asiakas asiakas : asiakkaat) {
            if (asiakas.getNimi().equals(nimi) && asiakas.getSahkoposti().equals(sahkoposti)) {
                return asiakas;
            }
        }
        return null;
    }

    /**
     * Käy tilalistan läpi ja tarkastaa, onko tila jo olemassa.
     * 
     * @param nimi, tilan nimi.
     * @return tila, jos löytyy, muuten palauttaa null. 
     */
    private Tila onkoTilaOlemassa(String nimi) {
        for (Tila tila : tilat) {
            if (tila.getNimi().equals(nimi)) {
                return tila;
            }
        }
        return null;
    }

    /**
     * Tarkastelee, onko tila varattuna jo haluttuun aikaan.
     * 
     * @param tila, tila-olio.
     * @param alkuaika, alkuaika tilan varaamiselle.
     * @param loppuaika, loppuaika tilan varaamiselle.
     * @return true, jos tila varattuna haluttuna aikana, false, jos tila vapaana. 
     */
    private boolean onkoTilaVarattuna(Tila tila, int alkuaika, int loppuaika) {
        for (Varaus varaus : tila.getVaraukset()) {
            if (varaus.getAlkuaika() < alkuaika && alkuaika < varaus.getLoppuaika() ||
                varaus.getAlkuaika() > alkuaika && loppuaika > varaus.getAlkuaika() ||
                varaus.getAlkuaika() < loppuaika && loppuaika < varaus.getLoppuaika() ||
                varaus.getAlkuaika() == alkuaika && loppuaika == varaus.getLoppuaika()) {
                return true;
            }
        }
        return false;
    }
}
