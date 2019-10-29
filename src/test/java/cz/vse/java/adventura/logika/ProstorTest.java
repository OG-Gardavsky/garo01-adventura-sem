package cz.vse.java.adventura.logika;


import cz.vse.java.garo01.logika.Prostor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková
 * @version   pro skolní rok 2016/2017
 */
public class ProstorTest
{
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
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {
        Prostor hello = new Prostor("havarovaná_helikoptéra","Havarovaná helikoptéra, která je výchozí lokací příběhu");
        Prostor horska_stezka = new Prostor("horská_stezka", "Horská stezka, která je jedinou cestou od helikoptéry");
        Prostor propast = new Prostor("propast", "Spadli jste do propasti, a tímto hru prohráli");
        Prostor vysoka_stezka = new Prostor("vysokohorská_cesta","vysokohorská_cesta na které se vyskytují povstalci");
        Prostor udoli = new Prostor("horské_údolí","Horské_údolí, ve kterém se nachází rozzuřený medvěd");
        Prostor horsky_pusmyk = new Prostor("horský_průsmyk","Horský_průsmyk, který se zdánlivě táhne do nekonečna");
        Prostor zalesneneUdoli = new Prostor("zalesněné_údolí","zalesněné údolí, kde uvidíte studánku");
        Prostor poust = new Prostor("poušť","poušť, na které je voda vzácná");
        Prostor staraV3s = new Prostor("stará_V3S","stará_V3S, která není pojízdná, ale vypadá, že by šla spravit");

        {   hello.setVychod(horska_stezka);
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
        }

        assertEquals(horska_stezka, hello.vratSousedniProstor("horská_stezka"));
        assertEquals(horska_stezka, vysoka_stezka.vratSousedniProstor("horská_stezka"));
        assertEquals(horska_stezka, udoli.vratSousedniProstor("horská_stezka"));

        assertEquals(propast, horska_stezka.vratSousedniProstor("propast"));
        assertEquals(hello, horska_stezka.vratSousedniProstor("havarovaná_helikoptéra"));
        assertEquals(vysoka_stezka, horska_stezka.vratSousedniProstor("vysokohorská_cesta"));
        assertEquals(udoli, horska_stezka.vratSousedniProstor("horské_údolí"));

        assertEquals(horska_stezka, udoli.vratSousedniProstor("horská_stezka"));
        assertEquals(zalesneneUdoli, udoli.vratSousedniProstor("zalesněné_údolí"));

        assertEquals(udoli, zalesneneUdoli.vratSousedniProstor("horské_údolí"));
        assertEquals(poust, zalesneneUdoli.vratSousedniProstor("poušť"));

        assertEquals(horska_stezka, vysoka_stezka.vratSousedniProstor("horská_stezka"));
        assertEquals(horsky_pusmyk, vysoka_stezka.vratSousedniProstor("horský_průsmyk"));

        assertEquals(vysoka_stezka, horsky_pusmyk.vratSousedniProstor("vysokohorská_cesta"));
        assertEquals(poust, horsky_pusmyk.vratSousedniProstor("poušť"));

        assertEquals(zalesneneUdoli, poust.vratSousedniProstor("zalesněné_údolí"));
        assertEquals(horsky_pusmyk, poust.vratSousedniProstor("horský_průsmyk"));
        assertEquals(staraV3s, poust.vratSousedniProstor("stará_V3S"));

        assertEquals(poust, staraV3s.vratSousedniProstor("poušť"));
        
    }

}
