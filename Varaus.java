public class Varaus {
    // Attribuutit
    private int varausId;
    private Tila tila;
    private Asiakas asiakas;
    private int alkuaika;
    private int loppuaika;

    // Rakentaja
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

    // Metodit
    // Metodi varauksen tietojen tulostamiseksi
    public void tulostaTiedot() {
        System.out.println("VarausID: " + varausId);
        System.out.println("TilaID: " + tila.getTilaId());
        System.out.println("Varaaja: " + asiakas.getNimi());
        System.out.println("Varattu: " + alkuaika + " - " + loppuaika);
    }
}