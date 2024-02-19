import java.util.ArrayList;
import java.util.List;

public class Tila {
    // Attribuutit
    private String nimi;
    private int tilaId;
    private List<Varaus> varaukset;
    private List<String> varustus;

    // Rakentaja
    public Tila(String nimi, int tilaId) {
        this.nimi = nimi;
        this.tilaId = tilaId;
        this.varaukset = new ArrayList<>();
        this.varustus = new ArrayList<>();
    }

    // Getterit
    public String getNimi() {
        return nimi;
    }

    public int getTilaId() {
        return tilaId;
    }

    public List<Varaus> getVaraukset() {
        return varaukset;
    }

    public List<String> getVarustus() {
        return varustus;
    }

    // Metodit
    // Metodi tilan tietojen tulostamiseksi
    public void tulostaTiedot() {
        System.out.println("TilaID: " + tilaId);
        System.out.println("Varaukset: ");
        for (Varaus varaus : varaukset) {
            varaus.tulostaTiedot();
        }
        System.out.println("Tilan varusteet: ");
        for (String varuste : varustus) {
            System.out.println(" - " + varuste);
        }
    }

    // Metodi varauksen lisäämiselle 
    public void lisaaVaraus(Varaus varaus) {
        varaukset.add(varaus);
    }

    // Metodi varauksen poistamiselle
    public void poistaVaraus(Varaus varaus) {
        varaukset.remove(varaus);
    }

    // Metodit varustuksen lisäämiselle tilaan
    public void lisaaVarustus(String varuste) {
        varustus.add(varuste);
    }

    // Metodi varustuksen poistamiselle tilasta 
    public void poistaVarustus(String varuste) {
        varustus.remove(varuste);
    }
}