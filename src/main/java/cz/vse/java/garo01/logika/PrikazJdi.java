package cz.vse.java.garo01.logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2016/2017
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazJdi(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    /**
     * Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     * existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     * (východ) není, vypíše se chybové hlášení.
     * pro specifické zamýšlené prostory vypíše konkrétní zprávu
     * @param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                  do kterého se má jít.
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Zadali jste pouze “jdi“ bez určení místa. Kam chcete jít";
        }

        String smer = parametry[0];

        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan
                .getAktualniProstor()
                .vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        } else {
            plan.setAktualniProstor(sousedniProstor);
            if (plan.getAktualniProstor().getNazev().equals("propast")) {
                hra.setKonecHry(true);
                return sousedniProstor.getPopis();

            } else if (plan.getAktualniProstor().getNazev().equals("vysokohorská_cesta")) {
                hra.setKonecHry(true);
                return "Šli jste po horské cestě a nejednou, jste se dostali pod palbu povstalců, a jedna kulka se stala vaší osudnou a po ní další, hra pro vás končí.";

            } else if (plan.getAktualniProstor().getNazev().equals("horské_údolí")) {
                hra.setKonecHry(true);
                return "vydali jste se do údolí, kde jste narazili na hladového a rozzuřeného medvěda, vycítil vaše zranění a setkání s ním se vám stalo osudným";

            } else if (plan.getAktualniProstor().getNazev().equals("poušť") && !plan.getBatoh().obsahujeVec("čutora_vody")){
                hra.setKonecHry(true);
                return "Vydali jste se do pouště bez vody, po pár kilometrech úmorného vedra umíráte na žízeň";

            } else {
                plan.setAktualniProstor(sousedniProstor);
                return sousedniProstor.dlouhyPopis();
            }
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
