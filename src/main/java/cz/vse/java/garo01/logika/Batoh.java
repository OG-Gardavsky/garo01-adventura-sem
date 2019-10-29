package cz.vse.java.garo01.logika;

import java.util.ArrayList;
import java.util.List;

/**
 * Třída vytvářející batoh k přenášení předmětů ve hře
 * @author Ondřej Gardavský - pro předmět 4IT101
 */
public class Batoh {
    private static final int VELIKOST_BATOHU = 3;
    private List<Vec> veciBatohu;


    /**
     * Konstruktor
     */
    public Batoh(){
        veciBatohu = new ArrayList<Vec>();
    }

    /**
     * metoda ověření zda je v batohu místo
     * @return true když je místo, false, když místo není
     */
    public boolean jeMistoVBatohu(){
        if(VELIKOST_BATOHU > veciBatohu.size()){
            return true;
        } else {
            return false;
        }
    }

    /**
     * metoda přidá věc uvedenou v parametru
     * @param pridavanaVec
     * @return vrací true nebo false podle toho jestli se operace povedla
     */
    public boolean PridejVec(Vec pridavanaVec){
        return veciBatohu.add(pridavanaVec);
    }


    /**
     * metoda odebere věc z batohu
     * @param nazevVeci
     * @return
     */
    public Vec odeberZBatohu(String nazevVeci){
        Vec vecKOdebrani = null;
        for (Vec vec: veciBatohu){
            if (vec.getNazev().equals(nazevVeci)){
                vecKOdebrani=vec;
                break;
            }
        }
        if (vecKOdebrani != null){
            veciBatohu.remove(vecKOdebrani);
        }
        return vecKOdebrani;
    }

    /**
     * vypíše obsah batohu
     * @return vrací obsah batohu v podobě stringu
     */
    public String vypsatVeci(){
        String textKVraceni = "V batohu jsou: ";
        for (Vec vec: veciBatohu){
            textKVraceni = textKVraceni + vec.getNazev() + ", ";
        }
        return textKVraceni;

    }

    /**
     * ověří zdali se zadaná věc nachází v batohu, hledá se podle názvu věci
     * @param nazevVeci
     * @return vrací true, když se nachází, false, když ne
     */
    public boolean obsahujeVec(String nazevVeci){
        for (Vec vec: veciBatohu){
            if (vec.getNazev().equals(nazevVeci)){
                return true;
            }
        }
        return false;
    }

    

}
