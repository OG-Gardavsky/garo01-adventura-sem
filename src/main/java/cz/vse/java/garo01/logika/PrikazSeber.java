package cz.vse.java.garo01.logika;

/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2016/2017
 */
public class PrikazSeber implements IPrikaz {
    private static final String NAZEV = "seber";
    private final HerniPlan herniPlan;

    /**
     * kontruktor
     * @param herniPlan
     */
    public PrikazSeber(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * příkaz přidá předmět do batohu
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím co mam sebrat.";
        }

        Prostor zvolenyProstor = herniPlan.getAktualniProstor();
        Batoh batoh = herniPlan.getBatoh();

        String nazevVeci = parametry[0];
        if (!herniPlan.getAktualniProstor().vecJeVProstoru(nazevVeci)) {
            return "Věc s názvem " + nazevVeci + " se v prostoru nenachází.";
        }
        if (zvolenyProstor.vyhledejVec(nazevVeci).isPrenositelna() == false){
            return "Věc nelze přenášet";
        }
        if (!batoh.jeMistoVBatohu()){
            return "batoh je plný, nelze nic přidat";
        }

        Vec vecKSebrani = zvolenyProstor.odstranVec(nazevVeci);
        batoh.PridejVec(vecKSebrani);
        return "sebrali jste: " + nazevVeci;
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