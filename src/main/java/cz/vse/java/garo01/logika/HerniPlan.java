package cz.vse.java.garo01.logika;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 *
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan {

    private cz.vse.java.garo01.logika.Prostor aktualniProstor;
    private Batoh batoh;


    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        batoh = new Batoh();
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor hello = new Prostor("havarovaná_helikoptéra","Havarovaná helikoptéra, která je výchozí lokací příběhu");
        Prostor horska_stezka = new Prostor("horská_stezka", "Horská stezka, která je jedinou cestou od helikoptéry");
        Prostor propast = new Prostor("propast", "Spadli jste do propasti, a tímto hru prohráli");
        Prostor vysoka_stezka = new Prostor("vysokohorská_cesta","vysokohorská_cesta na které se vyskytují povstalci");
        Prostor udoli = new Prostor("horské_údolí","Horské_údolí, ve kterém se nachází rozzuřený medvěd");
        Prostor horsky_pusmyk = new Prostor("horský_průsmyk","Horský_průsmyk, který se zdánlivě táhne do nekonečna");
        Prostor zalesneneUdoli = new Prostor("zalesněné_údolí","zalesněné údolí, kde uvidíte studánku");
        Prostor poust = new Prostor("poušť","poušť, na které je voda vzácná");
        Prostor staraV3s = new Prostor("stará_V3S","stará_V3S, která není pojízdná, ale vypadá, že by šla spravit");



        // přiřazují se průchody mezi prostory (sousedící prostory)

        hello.setVychod(horska_stezka);
        horska_stezka.setVychod(hello);
        horska_stezka.setVychod(propast);
        horska_stezka.setVychod(vysoka_stezka);
        horska_stezka.setVychod(udoli);

        vysoka_stezka.setVychod(horska_stezka);
        vysoka_stezka.setVychod(horsky_pusmyk);
        horsky_pusmyk.setVychod(vysoka_stezka);
        horsky_pusmyk.setVychod(poust);

        poust.setVychod(horsky_pusmyk);
        poust.setVychod(zalesneneUdoli);
        poust.setVychod(staraV3s);

        staraV3s.setVychod(poust);

        udoli.setVychod(horska_stezka);
        udoli.setVychod(zalesneneUdoli);
        zalesneneUdoli.setVychod(udoli);
        zalesneneUdoli.setVychod(poust);

        aktualniProstor = hello;  // hra začíná v helikoptéře
        //aktualniProstor = poust;

        hello.pridejVec(new Vec("cihlička_zlata", true));
        hello.pridejVec(new Vec("kanistr_paliva", true));
        hello.pridejVec(new Vec("ČZ_vz.58", true));
        hello.pridejVec(new Vec("nůž", true));
        hello.pridejVec(new Vec("sedačka", false));
        hello.pridejVec(new Vec("mrtvola", false));

        horska_stezka.pridejVec(new Vec("fragmenty_helikoptéry", false));
        horska_stezka.pridejVec(new Vec("čutora_vody", true));

        horsky_pusmyk.pridejVec(new Vec("krabice_nářadí", true));
        horsky_pusmyk.pridejVec(new Vec("velký kámen", false));

        zalesneneUdoli.pridejVec(new Vec("studánka", false));
        zalesneneUdoli.pridejVec(new Vec("cigarety", true));

        poust.pridejVec(new Vec("kusy_letadla", false));
        zalesneneUdoli.pridejVec(new Vec("krabice_nářadí", true));
    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Batoh getBatoh(){
        return batoh;
    }

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }

}