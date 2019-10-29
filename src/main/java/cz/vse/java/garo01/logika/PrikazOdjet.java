package cz.vse.java.garo01.logika;


/**
 *  Třída PrikazOdjet implementuje pro hru příkaz odjet.
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Ondřej Gardavský
 *@version    pro školní rok 2018/2019
 */
public class PrikazOdjet implements IPrikaz {
    private static final String NAZEV = "odjet";
    private HerniPlan plan;
    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazOdjet(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }


    /**
     * metoda umožní odjet z finální destinace a tím ukončít hru v závislosti na potřebných položkách v inventáři
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (plan.getAktualniProstor().getNazev().equals("stará_V3S") && plan.getBatoh().obsahujeVec("kanistr_paliva") && plan.getBatoh().obsahujeVec("krabice_nářadí")){
            hra.setKonecHry(true);
            return "Starý dieselový motor se probral k životu a vy ujíždíte do bezpečí základny Krasnaja Skala";
        } else  if (plan.getAktualniProstor().getNazev().equals("stará_V3S") && (!plan.getBatoh().obsahujeVec("kanistr_paliva") || !plan.getBatoh().obsahujeVec("krabice_nářadí"))){
            return "Jste u vozidla, ale není v něm palivo a potřebujete nářadí na opravu";

        }
        return "Nenecházíte se v prostoru, kde by bylo nějaké vozidlo";
    }

            @Override
            public String getNazev () {
                return NAZEV;
            }
        }
