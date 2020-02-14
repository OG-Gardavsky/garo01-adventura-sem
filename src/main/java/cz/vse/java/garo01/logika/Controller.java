package cz.vse.java.garo01.logika;


import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


/**
 * Tato třídá slouží k ovládání grafické verze jednoduché grafické adventury
 * toto rozšíření bylo vytvořeno v akademickém roce zimním semestru 2019 pro předmět 4IT115
 * část kódu je převzata ze cvičení od pana Růžičky
 * @author: Ondřej Gardavský
 */
public class Controller {
    @FXML
    private MenuItem uvitacipolozka;
    @FXML
    private MenuItem novaHra;
    @FXML
    private MenuItem napoveda;
    @FXML
    private MenuItem mapa;
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
    @FXML
    private Button odjetButton;

    private static final int SIRKA_IKONY = 48;
    private static final int VYSKA_IKONY = 32;


    public void setHra(IHra hra){
        this.hra = hra;
        seznamPredmetuVBatohu.getChildren().clear();
        HerniPlan herniPlan = hra.getHerniPlan();
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        zmenProstor(aktualniProstor);
        vypisUvitaciOkno();
    }

    /**
     * Metoda měmní prostor v grafickém rozhraní
     * @param prostor slouží k přepnutí do prostoru zadaného v parametru
     */
    private void zmenProstor(Prostor prostor){
        String hlaskaPrikazu =  hra.zpracujPrikaz("plížit " + prostor.getNazev());

        Image image = new Image(getClass().getResourceAsStream("/" + prostor.getNazev() + ".jpg"));
        obrazekLokace.setImage(image);

        jmenoLokace.setText(prostor.getNazev());
        popisLokace.setText(prostor.getPopis());
        sysHlaska.setText("");

        if (!prostor.getNazev().equals("stará_V3S")){
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
            stage.setAlwaysOnTop(true);
            stage.setTitle("Nápověda");
            StackPane root = new StackPane();

            WebView browser = new WebView();
            WebEngine webEngine = browser.getEngine();
            webEngine.load(getClass().getResource("/html/index.html").toString());
            root.getChildren().add(browser);

            stage.setScene(new Scene(root, 350, 200));
            stage.show();
        });


        mapa.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setAlwaysOnTop(true);
            stage.setTitle("Mapa hry");
            StackPane root = new StackPane();

            WebView browser = new WebView();
            WebEngine webEngine = browser.getEngine();
            webEngine.load(getClass().getResource("/html/mapa.html").toString());
            root.getChildren().add(browser);

            stage.setScene(new Scene(root, 650, 800));
            stage.show();
        });

        uvitacipolozka.setOnAction(event -> vypisUvitaciOkno());
    }

    /***
     * metoda přidá předměty do místnosti v grafickém rozhraní
     * @param prostor slouží určení východů na základě aktuálního prostoru
     */
    private void pridejVychody(Prostor prostor) {
        seznamVychodu.getChildren().clear();
        for (Prostor p : prostor.getVychody()) {

            HBox vychod = new HBox();
            vychod.setSpacing(15);
            vychod.setPadding(new Insets(10, 2, 10, 0));
            //Label nazevProstoru = new Label(p.getNazev());
            Button nazevProstoru = new Button();
            nazevProstoru.setText(p.getNazev());

            ImageView obrazekVchodu = new ImageView();
            Image vychodImage = new Image(getClass().getResourceAsStream("/" + p.getNazev() + ".jpg"));
            obrazekVchodu.setFitHeight(VYSKA_IKONY);
            obrazekVchodu.setFitWidth(SIRKA_IKONY);
            obrazekVchodu.setImage(vychodImage);

            vychod.getChildren().addAll(obrazekVchodu, nazevProstoru);

            seznamVychodu.getChildren().add(vychod);
            nazevProstoru.setOnMouseClicked(event -> zmenProstor(p));
        }
    }

    /**
     * Metoda umožní odjet z finální lokace a tím ukončít hru v grafickém rozhraní
     * @param prostor slouží k ověření, zda už hráč došel do finální lokace
     */
    private void odjetController(Prostor prostor){
        if (prostor.getNazev().equals("stará_V3S")) {
            odjetVbox.setVisible(true);

            odjetButton.setOnMouseClicked(event -> {
                String hlaskaPrikazu = hra.zpracujPrikaz("odjet");

                if (hra.konecHry()){
                    ukonciHru(hlaskaPrikazu);
                } else {
                    sysHlaska.setText(hlaskaPrikazu);
                }
            });
        }
    }

    /**
     * Metoda pomocí metody pridejPredmetDoMistnosti přidá předměty do místnosti v grafickém rozhraní
     * @param prostor slouží k určení jaké předměty se mají přidat
     */
    private void pridejPredmety(Prostor prostor) {
        seznamPredmetuVMistnosti.getChildren().clear();

        for (Vec vec : prostor.getVeci()) {
            pridejPredmetDoMistnosti(vec);
        }
    }

    /**
     * Metoda přidá věci do místnosti
     * @param vec slouží k určení jaká věc má být přidána
     */
    private void pridejPredmetDoMistnosti(Vec vec) {
        //Label nazevVeci = new Label(vec.getNazev());
        //nazevVeci.getStyleClass().add("/styly.css");
        Button klikaciPolozka = new Button();
        klikaciPolozka.setText(vec.getNazev());

        HBox vecBox = new HBox();
        vecBox.setSpacing(15);
        vecBox.setPadding(new Insets(2, 2, 10, 0));

        ImageView obrazekVeci = new ImageView();
        Image vychodImage =  new Image(getClass().getResourceAsStream("/predmety/" + vec.getNazev() + ".jpg"));
        obrazekVeci.setFitHeight(VYSKA_IKONY);
        obrazekVeci.setFitWidth(SIRKA_IKONY);
        obrazekVeci.setImage(vychodImage);
        vecBox.getChildren().addAll(obrazekVeci, klikaciPolozka);
        seznamPredmetuVMistnosti.getChildren().add(vecBox);
        
        klikaciPolozka.setOnMouseClicked(event -> {
            if (vec.isPrenositelna()) {

                String hlaskaPrikazu = hra.zpracujPrikaz("seber " + vec.getNazev());

                boolean jePlny = false;

                if (hlaskaPrikazu.equals("batoh je plný, nelze nic přidat")) {
                    jePlny = true;
                    sysHlaska.setText(hlaskaPrikazu);
                }
                if (!jePlny) {
                    seznamPredmetuVBatohu.getChildren().add(vecBox);
                    seznamPredmetuVMistnosti.getChildren().remove(vecBox);

                    klikaciPolozka.setOnMouseClicked(event1 -> {
                        sysHlaska.setText("");
                        hra.zpracujPrikaz("vyhoď " + vec.getNazev());
                        seznamPredmetuVBatohu.getChildren().remove(vecBox);
                        pridejPredmetDoMistnosti(vec);
                    });
                }
            }
        });
    }

    /**
     * metoda vrací uvítací okno s krátkým popisem hry
     */
    private void vypisUvitaciOkno(){
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.setTitle("Uvítací okno");
        StackPane root = new StackPane();

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(getClass().getResource("/html/uvitani.html").toString());
        root.getChildren().add(browser);

        stage.setScene(new Scene(root, 640, 360));
        stage.show();
    }

    /**
     * metoda ukončí hru z pohledu grafické nádstavby
     * @param hlaskaPrikazu slouží k určení jaká hláška se vrátí v ukončení
     */
    private void ukonciHru(String hlaskaPrikazu){
        seznamVychodu.getChildren().clear();
        seznamPredmetuVBatohu.getChildren().clear();
        seznamPredmetuVMistnosti.getChildren().clear();
        sysHlaska.setText(hra.vratEpilog());
        popisLokace.setText(hlaskaPrikazu);
    }



}