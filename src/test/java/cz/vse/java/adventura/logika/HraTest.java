package cz.vse.java.adventura.logika;

import cz.vse.java.garo01.logika.Hra;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("havarovaná_helikoptéra", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber kanistr_paliva");
        hra1.zpracujPrikaz("jdi horská_stezka");
        assertEquals(false, hra1.konecHry());
        assertEquals("horská_stezka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber čutora_vody");
        hra1.zpracujPrikaz("plížit horské_údolí");
        assertEquals(false, hra1.konecHry());
        assertEquals("horské_údolí", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi zalesněné_údolí");
        hra1.zpracujPrikaz("seber krabice_nářadí");
        assertEquals(false, hra1.konecHry());
        assertEquals("zalesněné_údolí", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi poušť");
        assertEquals(false, hra1.konecHry());
        assertEquals("poušť", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi stará_V3S");
        assertEquals(false, hra1.konecHry());
        assertEquals("stará_V3S", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("odjet");
        assertEquals(true, hra1.konecHry());
        System.out.println("test proběhl úspěšně");
    }
}
