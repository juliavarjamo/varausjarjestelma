/**
 * Luokka tilanvarausjärjestelmän varauksille.
 */
public class Varaus {
    private int varausId;
    private Tila tila;
    private Asiakas asiakas;
    private int alkuaika;
    private int loppuaika;

    public Varaus(int varausId, Tila tila, Asiakas asiakas, int alkuaika, int loppuaika) {
        this.varausId = varausId;
        this.tila = tila;
        this.asiakas = asiakas;
        this.alkuaika = alkuaika;
        this.loppuaika = loppuaika;
    }

    // Getterit
    public int getVarausId() {
        return varausId;
    }

    public Tila getTila() {
        return tila;
    }

    public Asiakas getAsiakas() {
        return asiakas;
    }

    public int getAlkuaika() {
        return alkuaika;
    }

    public int getLoppuaika() {
        return loppuaika;
    }

    /**
     * Tulostaa varaus-olion tiedot.
     */
    public void tulostaTiedot() {
        System.out.println("  Varausnumero: " + varausId);
        System.out.println("    Asiakas: " + asiakas.getNimi());
        System.out.println("    Tila: " + tila.getNimi());
        System.out.println("    Aika: " + alkuaika + " - " + loppuaika);
    }
}