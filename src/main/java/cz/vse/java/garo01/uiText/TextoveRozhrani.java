package cz.vse.java.garo01.uiText;


import java.io.*;
import java.util.Scanner;
import cz.vse.java.garo01.logika.IHra;
/**
 *  Class TextoveRozhrani
 * 
 *  Toto je uživatelského rozhraní aplikace Adventura
 *  Tato třída vytváří instanci třídy Hra, která představuje logiku aplikace.
 *  Čte vstup zadaný uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na konzoli.
 *  
 *  
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */

public class TextoveRozhrani {
    private IHra hra;

    /**
     *  Vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    /**
     *  Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     *  příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     *  hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() {
        System.out.println(hra.vratUvitani());

        // základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }

        System.out.println(hra.vratEpilog());
        hra.konecHry();
    }

    /**
     *  Metoda přečte příkaz z příkazového řádku
     *
     *@return    Vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

    /**
     * metoda sloužící k automatickému hraní z externího souboru
     * @param jmenoSouboru
     */
    public void hrajZeSouboru( String jmenoSouboru) {
        try (BufferedReader cteni = new BufferedReader(new FileReader(jmenoSouboru));
             PrintWriter zapis = new PrintWriter(new FileWriter("vystup.txt")))
        {
            System.out.println(hra.vratUvitani());
            zapis.println(hra.vratUvitani());
            String radek = cteni.readLine();

            while (radek != null && hra.konecHry() != true)
            {
                System.out.println("> " + radek);
                String vystup = hra.zpracujPrikaz(radek);
                zapis.println("-->" + radek);
                System.out.println(vystup);
                zapis.println(vystup);

                radek = cteni.readLine();
            }

            System.out.println(hra.vratEpilog());
            hra.konecHry();

          //uprava hlaseni
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Soubor nenalezen");
            File file = new File(jmenoSouboru);
            System.out.println("Hledano na: " + file.getAbsolutePath());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println("čtení ze souboru ukončeno");
        }

    }

}
