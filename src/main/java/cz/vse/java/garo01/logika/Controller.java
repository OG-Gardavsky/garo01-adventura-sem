package cz.vse.java.garo01.logika;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Controller {
    @FXML
    private Label odjetLabel;
    @FXML
    private VBox odjetVbox;
    @FXML
    private VBox seznamPredmetuVMistnosti;
    @FXML
    private VBox seznamPredmetuVBatohu;
    @FXML
    private Label sysHlaska;
    @FXML
    private Label jmenoLokace;
    @FXML
    private Label popisLokace;
    @FXML
    private VBox seznamVychodu;
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
        String hlaskaPrikazu =  hra.zpracujPrikaz("plížit " + prostor.getNazev());

        Image image = new Image(getClass().getResourceAsStream("/" + prostor.getNazev() + ".jpg"));
        obrazekLokace.setImage(image);

        jmenoLokace.setText(prostor.getNazev());
        popisLokace.setText(prostor.getPopis());

        if (!hra.konecHry()){
            pridejPredmety(prostor);
            pridejVychody(prostor);
            odjetController(prostor);
        } else {
            seznamVychodu.getChildren().clear();
            seznamPredmetuVBatohu.getChildren().clear();
            seznamPredmetuVMistnosti.getChildren().clear();
            sysHlaska.setText(hra.vratEpilog());
            popisLokace.setText(hlaskaPrikazu);
            //Platform.exit();
        }

    }
    

    private void pridejVychody(Prostor prostor) {
        seznamVychodu.getChildren().clear();
        for (Prostor p : prostor.getVychody()) {
            HBox vychod = new HBox();
            vychod.setSpacing(10);
            Label nazevProstoru = new Label(p.getNazev());

            vychod.getChildren().addAll( nazevProstoru);

            seznamVychodu.getChildren().add(vychod);
            vychod.setOnMouseClicked(event -> {
                zmenProstor(p);
            });
        }
    }
    
    private void odjetController(Prostor prostor){
//        if (prostor.getNazev().equals("stará_V3S")) {
        if (prostor.getNazev().equals("havarovaná_helikoptéra")) {
//            odjetVbox.setOpacity(0.7);
            //Label odjetTlacitko = new Label("Odjet");
//            odjetVbox.getChildren().add(odjetTlacitko);

            odjetLabel.setText("Odjet");
            odjetLabel.setOnMouseClicked(event -> {
                String hlaskaPrikaz = hra.zpracujPrikaz("odjet");
                System.out.println(hlaskaPrikaz);
                sysHlaska.setText(hlaskaPrikaz);
            });
        }
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
                String hlaskaPrikazu = hra.zpracujPrikaz("seber " + vec.getNazev());
                Boolean jePlny = false;

                if (hlaskaPrikazu.equals("batoh je plný, nelze nic přidat")){
                    jePlny = true;
                }
                if (jePlny == false){
                    sysHlaska.setText("");
                    seznamPredmetuVBatohu.getChildren().add(vecVBatohu);
                    seznamPredmetuVMistnosti.getChildren().remove(nazevVeci);
                }
                vecVBatohu.setOnMouseClicked(event1 -> {
                    hra.zpracujPrikaz("vyhoď "+vec.getNazev());
                    seznamPredmetuVBatohu.getChildren().remove(vecVBatohu);
                    pridejPredmetDoMistnosti(vec);
                });
            }
        });
    }



}