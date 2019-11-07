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
    private VBox seznamJit;
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
        System.out.println(" ");
        System.out.println(hra.konecHry());

        Image image = new Image(getClass().getResourceAsStream("/" + prostor.getNazev() + ".jpg"));
        obrazekLokace.setImage(image);

        jmenoLokace.setText(prostor.getNazev());
        popisLokace.setText(prostor.getPopis());

        pridejPredmety(prostor);
        pridejVychody(prostor);

        if (hra.konecHry()){
            popisLokace.setText(hlaskaPrikazu);
            Platform.exit();
        }
    }
    

    private void pridejVychody(Prostor prostor) {
        seznamJit.getChildren().clear();
System.out.println(prostor.getNazev());
System.out.println(hra.konecHry());
        for (Prostor p : prostor.getVychody()) {
            HBox vychod = new HBox();
            vychod.setSpacing(10);
            Label nazevProstoru = new Label(p.getNazev());

            vychod.getChildren().addAll( nazevProstoru);

            seznamJit.getChildren().add(vychod);
            vychod.setOnMouseClicked(event -> {
//                System.out.println(hra.konecHry());
                zmenProstor(p);
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
        //System.out.println(hra.zpracujPrikaz("vypiš_batoh"));
                } else {
                   // sysHlaska.setText(hlaskaPrikazu);
                }

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