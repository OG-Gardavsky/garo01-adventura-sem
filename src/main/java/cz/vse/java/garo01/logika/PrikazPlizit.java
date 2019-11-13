package cz.vse.java.garo01.logika;

/**
 *  Třída PrikazPlizit implementuje pro hru příkaz plížit.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Ondřej Gardavský
 *@version    pro školní rok 2018/2019
 */
public class PrikazPlizit implements IPrikaz {
    private static final String NAZEV = "plížit";
    private HerniPlan plan;
    private Hra hra;

    /**
     * Konstruktor třídy
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazPlizit(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    /**
     * metoda je podobná metodě v příkazu jdi, ale umožňuje projít lokace které metodě jdi nejsou umožněny
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Zadali jste pouze “plížit“ bez určení místa. Kam se chcete plizit";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan
                .getAktualniProstor()
                .vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud plížit nedá!";
        } else if (sousedniProstor.getNazev().equals("propast")) {
            hra.setKonecHry(true);
            //return sousedniProstor.getPopis();
            return sousedniProstor.getPopis();
        }
        else if (sousedniProstor.getNazev().equals("vysokohorská_cesta")) {
            plan.setAktualniProstor(sousedniProstor);
            return "proplížili jste se kolem povstalců, proti, kterým bojujete, máte štěstí, že si vás nevšimli, jsou ve značné početní převaze" + "\n" + sousedniProstor.popisVychodu() + "\n" + sousedniProstor.getVeci();
        }
        else if (sousedniProstor.getNazev().equals("horské_údolí")){
            plan.setAktualniProstor(sousedniProstor);
            return "proplížili jste se kolem rozzuřeného medvěda, který vypadá hladově a setkání s ním byste nepřežili" + "\n" + sousedniProstor.popisVychodu();
        }
        else if (sousedniProstor.getNazev().equals("poušť") && !plan.getBatoh().obsahujeVec("čutora_vody")){
            hra.setKonecHry(true);
            return "Vydali jste se do pouště bez vody, po pár kilometrech úmorného vedra umíráte na žízeň";
        }
        else {
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis(); 
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev () {
        return NAZEV;
    }
}
