package cz.vse.java.garo01.logika;

/**
 * Tato třídá slouží k ovládání grafické verze jednoduché grafické adventury
 * toto rozšíření bylo vytvořeno v akademickém roce zimním semestru 2019 pro předmět 4IT115
 * část kódu je převzata ze cvičení od pana Růžičky
 * @author: Ondřej Gardavský
 */


import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Controller {
    @FXML
    private MenuItem testHTml;
    @FXML
    private MenuItem uvitacipolozka;
    @FXML
    private MenuItem novaHra;
    @FXML
    private MenuItem napoveda;
    @FXML
    private MenuItem mapa;
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

    public static final int SIRKA_IKONY = 45;
    public static final int VYSKA_IKONY = 30;


    public void setHra(IHra hra){
        this.hra = hra;
        seznamPredmetuVBatohu.getChildren().clear();
        HerniPlan herniPlan = hra.getHerniPlan();
        odjetLabel.setText("");
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        zmenProstor(aktualniProstor);
        vypisUvitaciOkno();
    }

    /**
     * Metoda měmní prostor v grafickém rozhraní
     * @param prostor
     */
    private void zmenProstor(Prostor prostor){
        String hlaskaPrikazu =  hra.zpracujPrikaz("plížit " + prostor.getNazev());

        Image image = new Image(getClass().getResourceAsStream("/" + prostor.getNazev() + ".jpg"));
        obrazekLokace.setImage(image);

        jmenoLokace.setText(prostor.getNazev());
        popisLokace.setText(prostor.getPopis());
        sysHlaska.setText("");

        if (!prostor.getNazev().equals("stará_V3S")){
            odjetLabel.setText("");
            odjetVbox.setVisible(false);
        }
        if (!hra.konecHry()){
            pridejPredmety(prostor);
            pridejVychody(prostor);
            menuListener();
            odjetController(prostor);
        } else {
            ukonciHru(hlaskaPrikazu);
        }

    }

    /**
     * metoda obstarává kliknutí na jednotlivá tlačítka v menu
     */
    private void menuListener(){
        novaHra.setOnAction( event -> {
            Hra novaHra = new Hra();
            setHra(novaHra);
        });

        napoveda.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setTitle("Nápověda");
            StackPane root = new StackPane();
            String textNapovedy = hra.zpracujPrikaz("nápověda");
            Label labelNapovedy = new Label(textNapovedy);
            root.getChildren().add(labelNapovedy);
            stage.setScene(new Scene(root, 854, 480));
            stage.show();
        });

//        testHTml.setOnAction( event -> {
//            Stage stage = new Stage();
//            stage.setTitle("Nápověda");
//            StackPane root = new StackPane();
//
//
//        });

        mapa.setOnAction(event -> {
            Stage stage = new Stage();
            Image image = new Image(getClass().getResourceAsStream("/mapa.png"));
            ImageView mapa = new ImageView();
            mapa.setImage(image);

            stage.show();
        });

        uvitacipolozka.setOnAction(event -> {
            vypisUvitaciOkno();
        });
    }

    /***
     * metoda přidá předměty do místnosti v grafickém rozhraní
     * @param prostor
     */
    private void pridejVychody(Prostor prostor) {
        seznamVychodu.getChildren().clear();
        for (Prostor p : prostor.getVychody()) {

            HBox vychod = new HBox();
            vychod.setSpacing(15);
            vychod.setPadding(new Insets(10, 2, 10, 0));
            Label nazevProstoru = new Label(p.getNazev());

            ImageView obrazekVchodu = new ImageView();
            Image vychodImage = new Image(getClass().getClassLoader().getResourceAsStream("\\" + p.getNazev() + ".jpg"));
            obrazekVchodu.setFitHeight(VYSKA_IKONY);
            obrazekVchodu.setFitWidth(SIRKA_IKONY);
            obrazekVchodu.setImage(vychodImage);

            vychod.getChildren().addAll(obrazekVchodu, nazevProstoru);

            seznamVychodu.getChildren().add(vychod);
            vychod.setOnMouseClicked(event -> {
                zmenProstor(p);
            });
        }
    }

    /**
     * Metoda umožní odjet z finální lokace a tím ukončít hru v grafickém rozhraní
     * @param prostor
     */
    private void odjetController(Prostor prostor){
        if (prostor.getNazev().equals("stará_V3S")) {
            odjetLabel.setText("Odjet");
            odjetVbox.setVisible(true);

            odjetLabel.setOnMouseClicked(event -> {
                String hlaskaPrikazu = hra.zpracujPrikaz("odjet");
                System.out.println(hra.konecHry());
                if (hra.konecHry() == true){
                    ukonciHru(hlaskaPrikazu);
                } else {
                    sysHlaska.setText(hlaskaPrikazu);
                }
            });
        }
    }

    /**
     * Metoda pomocí metody pridejPredmetDoMistnosti přidá předměty do místnosti v grafickém rozhraní
     * @param prostor
     */
    private void pridejPredmety(Prostor prostor) {
        seznamPredmetuVMistnosti.getChildren().clear();

        for (Vec vec : prostor.getVeci()) {
            pridejPredmetDoMistnosti(vec);
        }
    }

    /**
     * Metoda přidá věci do místnosti
     * @param vec
     */
    private void pridejPredmetDoMistnosti(Vec vec) {
        Label nazevVeci = new Label(vec.getNazev());
//        seznamPredmetuVMistnosti.getChildren().add(nazevVeci);

        HBox vecBox = new HBox();
        vecBox.setSpacing(15);
        vecBox.setPadding(new Insets(2, 2, 10, 0));

        ImageView obrazekVeci = new ImageView();

//        Image vychodImage = new Image(getClass().getResourceAsStream("/" + vec.getNazev() + ".jpg"));
        Image vychodImage =  new Image(getClass().getResourceAsStream("/" + vec.getNazev() + ".jpg"));
        obrazekVeci.setFitHeight(VYSKA_IKONY);
        obrazekVeci.setFitWidth(SIRKA_IKONY);
        obrazekVeci.setImage(vychodImage);

        vecBox.getChildren().addAll(obrazekVeci, nazevVeci);

        seznamPredmetuVMistnosti.getChildren().add(vecBox);

        
        vecBox.setOnMouseClicked(event -> {
            if (vec.isPrenositelna()) {

                String hlaskaPrikazu = hra.zpracujPrikaz("seber " + vec.getNazev());
                HBox batohBox = vecBox;

                Boolean jePlny = false;

                if (hlaskaPrikazu.equals("batoh je plný, nelze nic přidat")){
                    jePlny = true;
                    sysHlaska.setText(hlaskaPrikazu);
                }
                if (jePlny == false){
                    seznamPredmetuVBatohu.getChildren().add(batohBox);
                    seznamPredmetuVMistnosti.getChildren().remove(batohBox);
                }
                batohBox.setOnMouseClicked(event1 -> {
                    sysHlaska.setText("");
                    hra.zpracujPrikaz("vyhoď "+vec.getNazev());
                    seznamPredmetuVBatohu.getChildren().remove(batohBox);
                    pridejPredmetDoMistnosti(vec);
                });
            }
        });
    }

    private void vypisUvitaciOkno(){
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.setTitle("Uvítání");
        StackPane root = new StackPane();
        Label labelUvitani = new Label(hra.vratUvitani());
        root.getChildren().add(labelUvitani);
        stage.setScene(new Scene(root, 854, 360));
        stage.show();
    }

    /**
     * metoda ukončí hru z pohledu grafické nádstavby
     * @param hlaskaPrikazu
     */
    private void ukonciHru(String hlaskaPrikazu){
        seznamVychodu.getChildren().clear();
        seznamPredmetuVBatohu.getChildren().clear();
        seznamPredmetuVMistnosti.getChildren().clear();
        sysHlaska.setText(hra.vratEpilog());
        popisLokace.setText(hlaskaPrikazu);
    }



}