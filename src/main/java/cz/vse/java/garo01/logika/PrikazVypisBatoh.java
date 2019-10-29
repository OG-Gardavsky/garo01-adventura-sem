package cz.vse.java.garo01.logika;

/**
 *  Třída PrikazVypisBatohu implementuje pro hru příkaz vypiš_batoh.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Ondřej Gardavský
 *@version    pro školní rok 2018/2019
 */
public class PrikazVypisBatoh implements IPrikaz {

    private static final String NAZEV = "vypiš_batoh";
    private Batoh batoh;

    /**
     * konstruktor
     * @param batoh
     */
    public PrikazVypisBatoh(Batoh batoh) {
        this.batoh = batoh;
    }

    /**
     * metoda provede vypsání věcí v batohu
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return vrací věci obsažené v batohu
     */
    @Override
    public String provedPrikaz(String... parametry){
        return batoh.vypsatVeci();
    }


    @Override
    public String getNazev() {
        return NAZEV;
    }
}
