package cz.vse.java.garo01.logika;

/**
 *  Třída Věc poisuje věci pro hru
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Ondřej Gardavský
 *@version    pro školní rok 2018/2019
 */
public class Vec {

    private String nazev;
    private final boolean prenositelna;

    /**
     * kontruktor třídy
     * @param nazev
     * @param prenositelna
     */
    public Vec(String nazev, boolean prenositelna) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
    }

    /**
     * getter názvu věci
     * @return
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * setter názvu věci
     * @param nazev
     */
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    /**
     * metoda ověří jestli je věc přenositelná
     * @return
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }


}
