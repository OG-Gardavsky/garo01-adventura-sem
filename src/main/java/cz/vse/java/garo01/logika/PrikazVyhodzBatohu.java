package cz.vse.java.garo01.logika;

/**
 *  Třída PrikazVyhodzBatohu implementuje pro hru příkaz vyhoď.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Ondřej Gardavský
 *@version    pro školní rok 2018/2019
 */
public class PrikazVyhodzBatohu implements IPrikaz {
    private static final String NAZEV = "vyhoď";
    private final HerniPlan herniPlan;

    /**
     * kontruktor
     * @param herniPlan
     */
    public PrikazVyhodzBatohu(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * příkaz odebere danou vec z batohu
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím co mam vyhodit.";
        }

        Prostor zvolenyProstor = herniPlan.getAktualniProstor();
        Batoh batoh = herniPlan.getBatoh();

        String nazevVeci = parametry[0];
        if (!batoh.obsahujeVec(nazevVeci)) {
            return "Věc s názvem " + nazevVeci + " není v batohu";
        }
        if (batoh.obsahujeVec(nazevVeci)){

            Vec odebiranaVec = batoh.odeberZBatohu(nazevVeci);
            zvolenyProstor.pridejVec(odebiranaVec);
        }


        return "vyhodili jste z batohu: " + nazevVeci;
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}