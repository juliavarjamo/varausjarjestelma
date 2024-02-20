import java.util.ArrayList;
import java.util.List;

/**
 * Luokka tilanvarausjärjestelmän asiakkaille.
 */
public class Asiakas {
    private int asiakasId;
    private String nimi;
    private String sahkoposti;
    // Lista asiakkaan varauksista
    private List<Varaus> varaukset;

    public Asiakas(int asiakasId, String nimi, String sahkoposti) {
        this.asiakasId = asiakasId;
        this.nimi = nimi;
        this.sahkoposti = sahkoposti;
        this.varaukset = new ArrayList<>();
    }

    // Getterit
    public int getAsiakasId() {
        return asiakasId;
    }

    public String getNimi() {
        return nimi;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public List<Varaus> getVaraukset() {
        return varaukset;
    }

    /**
     * Tulostaa asiakas-olion tiedot.
     */
    public void tulostaTiedot() {
        System.out.println("  Asiakasnumero: " + asiakasId);
        System.out.println("    Nimi: " + nimi);
        System.out.println("    Sähköposti: " + sahkoposti);
        System.out.println("    Varaukset: ");

        for (Varaus varaus : varaukset) {
            System.out.println("      - " + varaus.getVarausId());
        }
    }

    /**
     * Lisää varauksen asiakkaan tietoihin.
     * 
     * @param varaus.
     */
    public void lisaaVaraus(Varaus varaus) {
        varaukset.add(varaus);
    }

    
    /**
     * Poistaa varauksen asiakkaan tiedoista. 
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
}