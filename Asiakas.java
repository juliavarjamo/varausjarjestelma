import java.util.ArrayList;
import java.util.List;

public class Asiakas {
    // Attribuutit
    private int asiakasId;
    private String nimi;
    private String sahkoposti;
    private List<Varaus> varaukset;

    // Rakentaja
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

    // Metodit
    // Metodi asiakkaan tietojen tulostamiseksi
    public void tulostaTiedot() {
        System.out.println("AsiakasID: " + asiakasId);
        System.out.println("Nimi: " + nimi);
        System.out.println("Sähköposti: " + sahkoposti);
        System.out.println("Varaukset: ");
        for (Varaus varaus : varaukset) {
            varaus.tulostaTiedot();
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
}