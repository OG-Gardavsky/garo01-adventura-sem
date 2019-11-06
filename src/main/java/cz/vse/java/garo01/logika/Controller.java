package cz.vse.java.garo01.logika;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Controller {

    @FXML
    private VBox seznamPredmetuVMistnosti;
    @FXML
    private VBox seznamPredmetuVBatohu;
    @FXML
    private Label jmenoLokace;
    @FXML
    private Label popisLokace;
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

        hra.zpracujPrikaz("jdi " + prostor.getNazev());

        Image image = new Image(getClass().getResourceAsStream("/" + prostor.getNazev() + ".jpg"));
        obrazekLokace.setImage(image);

        jmenoLokace.setText(prostor.getNazev());
        popisLokace.setText(prostor.getPopis());

        pridejPredmety(prostor);
    }


    private void pridejPredmety(Prostor prostor) {
        seznamPredmetuVMistnosti.getChildren().clear();

        for (Vec vec : prostor.getVeci()) {
            pridejPredmetDoMistnosti(vec);
        }
    }

    private void pridejPredmetDoMistnosti(Vec vec) {
        Label nazevVeci = new Label(vec.getNazev());
        seznamPredmetuVMistnosti.getChildren().add(nazevVeci);
        nazevVeci.setOnMouseClicked(event -> {
            if (vec.isPrenositelna()) {
                hra.zpracujPrikaz("seber " + vec.getNazev());
                Label vecVBatohu = new Label(vec.getNazev());
                seznamPredmetuVBatohu.getChildren().add(vecVBatohu);
                seznamPredmetuVMistnosti.getChildren().remove(nazevVeci);
                System.out.println(hra.zpracujPrikaz("vypiš_batoh"));

                vecVBatohu.setOnMouseClicked(event1 -> {
                    hra.zpracujPrikaz("vyhoď "+vec.getNazev());
                    seznamPredmetuVBatohu.getChildren().remove(vecVBatohu);
                    System.out.println(hra.zpracujPrikaz("vypiš_batoh"));
                    pridejPredmetDoMistnosti(vec);
                });
            }
        });
    }



}