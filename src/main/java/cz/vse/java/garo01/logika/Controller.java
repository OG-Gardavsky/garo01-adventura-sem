package cz.vse.java.garo01.logika;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Controller {

    @FXML
    private VBox seznamJit;
    @FXML
    private VBox seznamPlizit;
    private IHra hra;
    @FXML
    private ImageView obrazekLokace;

    public void setHra(IHra hra){
        this.hra = hra;
        HerniPlan herniPlan = hra.getHerniPlan();
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        zmenProstor(aktualniProstor);
    }

    private void zmenProstor(Prostor prostor){
        Image image = new Image(getClass().getResourceAsStream("/" + prostor.getNazev() + ".jpg"));
        obrazekLokace.setImage(image);

    }



}