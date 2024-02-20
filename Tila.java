import java.util.ArrayList;
import java.util.List;

/**
 * Luokka tilanvarausjärjestelmän tiloille.
 */
public class Tila {
    private String nimi;
    private int tilaId;
    private List<Varaus> varaukset;
    private List<String> varustus;

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

    /**
     * Tulostaa tila-olion tiedot.
     */
    public void tulostaTiedot() {
        System.out.println("  TilaID: " + tilaId);
        System.out.println("    Varaukset:");
        for (Varaus varaus : varaukset) {
            System.out.println("      Aika: " + varaus.getAlkuaika() + " - " + varaus.getLoppuaika());
        }

        System.out.println("    Tilan varusteet:");
        for (String varuste : varustus) {
            System.out.println("      - " + varuste);
        }
    }

    /**
     * Lisää varauksen tilalle.
     * 
     * @param varaus.
     */
    public void lisaaVaraus(Varaus varaus) {
        varaukset.add(varaus);
    }


    /**
     * Poistaa varauksen tilalta.
     * 
     * @param varaus.
     */
    public void poistaVaraus(Varaus varaus) {
        for (int i = 0; i < varaukset.size(); i++) {
            Varaus v = varaukset.get(i);
            if (v == varaus) {
                varaukset.remove(i);
                return;
            }
        }
    }

    /**
     * Lisää varusteita tilalle.
     * 
     * @param varuste.
     */
    public void lisaaVarustus(String varuste) {
        varustus.add(varuste);
    }

    /**
     * Poistaa varusteen tilasta. 
     * 
     * @param varuste.
     */
    public void poistaVarustus(String varuste) {
        for (int i = 0; i < varustus.size(); i++) {
            if (varuste == varustus.get(i)) {
                varustus.remove(i);
                return;
            }
        }
    }
}