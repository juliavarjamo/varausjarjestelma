public class Tilanvarausjarjestelma {
    public static void main(String[] args) {
        Varausmoottori varausmoottori = Varausmoottori.getInstanssi();
        varausmoottori.kaynnista();
    }
}