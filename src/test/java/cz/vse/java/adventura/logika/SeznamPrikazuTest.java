package cz.vse.java.adventura.logika;

import cz.vse.java.garo01.logika.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author    Luboš Pavlíček
 * @version   pro školní rok 2016/2017
 */
public class SeznamPrikazuTest
{
    private Hra hra;
    private HerniPlan herniPlan;
    private SeznamPrikazu seznamPrikazu;
    private Batoh batoh;

    private PrikazKonec prKonec;
    private PrikazJdi prJdi;
    private PrikazVyhodzBatohu prVyhodZBatohu;
    private PrikazOdjet prOdjet;
    private PrikazVypisBatoh prVypisBatoh;
    private PrikazPlizit prPlizit;
    private PrikazNapoveda prNapoveda;
    private PrikazSeber prSeber;
    
    @Before
    public void setUp() {
        hra = new Hra();
        herniPlan = new HerniPlan();
        seznamPrikazu = new SeznamPrikazu();
        batoh = new Batoh();

        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra.getHerniPlan(), hra);
        prVyhodZBatohu = new PrikazVyhodzBatohu(herniPlan);
        prOdjet = new PrikazOdjet(herniPlan, hra);
        prVypisBatoh = new PrikazVypisBatoh(batoh);
        prPlizit = new PrikazPlizit(herniPlan, hra);
        prKonec = new PrikazKonec(hra);
        prNapoveda = new PrikazNapoveda(seznamPrikazu);
        prSeber = new PrikazSeber(herniPlan);
    }

    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prVyhodZBatohu);
        seznPrikazu.vlozPrikaz(prOdjet);
        seznPrikazu.vlozPrikaz(prVypisBatoh);
        seznPrikazu.vlozPrikaz(prPlizit);
        seznPrikazu.vlozPrikaz(prSeber);
        seznPrikazu.vlozPrikaz(prNapoveda);

        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
        assertEquals(prNapoveda, seznPrikazu.vratPrikaz("nápověda"));
        assertEquals(prVyhodZBatohu, seznPrikazu.vratPrikaz("vyhoď"));
        assertEquals(prOdjet, seznPrikazu.vratPrikaz("odjet"));
        assertEquals(prVypisBatoh, seznPrikazu.vratPrikaz("vypiš_batoh"));
        assertEquals(prPlizit, seznPrikazu.vratPrikaz("plížit"));
        assertEquals(prSeber, seznPrikazu.vratPrikaz("seber"));
    }
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prVyhodZBatohu);
        seznPrikazu.vlozPrikaz(prOdjet);
        seznPrikazu.vlozPrikaz(prVypisBatoh);
        seznPrikazu.vlozPrikaz(prPlizit);
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prSeber);
        seznPrikazu.vlozPrikaz(prNapoveda);

        assertEquals(true, seznPrikazu.jePlatnyPrikaz("konec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("jdi"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("nápověda"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("vyhoď"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("odjet"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("vypiš_batoh"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("plížit"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("seber"));
    }
    
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prVyhodZBatohu);
        seznPrikazu.vlozPrikaz(prOdjet);
        seznPrikazu.vlozPrikaz(prVypisBatoh);
        seznPrikazu.vlozPrikaz(prPlizit);
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prSeber);
        seznPrikazu.vlozPrikaz(prNapoveda);

        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("jdi"));
        assertEquals(true, nazvy.contains("nápověda"));
        assertEquals(true, nazvy.contains("vyhoď"));
        assertEquals(true, nazvy.contains("odjet"));
        assertEquals(true, nazvy.contains("vypiš_batoh"));
        assertEquals(true, nazvy.contains("plížit"));
        assertEquals(true, nazvy.contains("seber"));

        assertEquals(false, nazvy.contains("Konec"));
        assertEquals(false, nazvy.contains("Jdi"));
        assertEquals(false, nazvy.contains("Nápověda"));
        assertEquals(false, nazvy.contains("Vyhoď"));
        assertEquals(false, nazvy.contains("Odjet"));
        assertEquals(false, nazvy.contains("Vypiš_batoh"));
        assertEquals(false, nazvy.contains("Plížit"));
        assertEquals(false, nazvy.contains("Seber"));
    }
    
}
