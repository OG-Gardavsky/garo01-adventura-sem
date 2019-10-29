package cz.vse.java.garo01.logika;

import javafx.fxml.FXML;

public class Controller {

    @FXML
    private IHra hra;

    public void setHra(IHra hra){
        this.hra = hra;
        HerniPlan herniPlan = hra.getHerniPlan();
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
    }

}