package Fakehalla.Menu;

import Fakehalla.Game.Game;
import Fakehalla.Game.HashElement;
import Fakehalla.Game.PlayerScore;
import Fakehalla.Settings.Settings;
import Fakehalla.Settings.SettingsLoader;
import Fakehalla.Settings.SettingsSaver;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class Launcher { //TODO Change launcher tu menu, use only one stage
    private Stage stage;
    private Scene defaultScene;
    private Scene settingsScene;
    private Scene playerSelect;
    private Settings settings;

    public Launcher(Stage primaryStage) throws IOException, ClassNotFoundException {
        settings = new SettingsLoader().loadSettings("settings.txt");
        stage = primaryStage;
        stage.setTitle("Supa Smash Your Brothers: test técnico");
        defaultScene = generateLauncherScene();
        settingsScene = generateSettingsScene();
        playerSelect = generatePlayerSelectionScene();
        //defaultScene.setOnMouseExited(event -> System.out.println("pa"));
        stage.setScene(defaultScene);
    }


    public void run() {
        stage.show();
    }


    private void runGame() throws IOException, ClassNotFoundException {
        Game game = new Game("Supa Smash Your Brothers", settings.isFullscreen());
        game.start();
        stage.close();
    }
    private void runSettings() {
        stage.setScene(settingsScene);
    }

    private void runScoreboard() throws FileNotFoundException {
        stage.setScene(generateScoreboardScene());
    }

    private void runCredits() throws FileNotFoundException {
        stage.setScene(generateCreditsScene());
    }

    private void runPlayerSelectionScene(){
        stage.setScene(playerSelect);
    }

    private Scene generateCreditsScene() throws FileNotFoundException {

        GridPane gridPane = new GridPane();
        int count = 0;
        //Nombre
        Label first = new Label("Antonio");
        first.setStyle("-fx-font-size: 3em;");
        gridPane.add(first, 1, 0);
        gridPane.setHalignment(first, HPos.CENTER);
        Label sec = new Label("Madurell");
        sec.setStyle("-fx-font-size: 3em;");
        gridPane.add(sec, 2, 0);
        Label zirst = new Label("Autor:");
        zirst.setStyle("-fx-font-size: 3em;");
        gridPane.setHalignment(zirst, HPos.RIGHT);
        gridPane.add(zirst, 0, 0);
        gridPane.setVgap(2);
        gridPane.setHgap(14);

        //Curso
        Label first1 = new Label("2");
        first1.setStyle("-fx-font-size: 3em;");
        gridPane.add(first1, 1, 1);
        gridPane.setHalignment(first1, HPos.CENTER);

        Label sec1 = new Label("DAM");
        sec1.setStyle("-fx-font-size: 3em;");
        gridPane.add(sec1, 2, 1);
        Label zirst1 = new Label("Curso:");
        zirst1.setStyle("-fx-font-size: 3em;");
        gridPane.setHalignment(zirst1, HPos.RIGHT);
        gridPane.add(zirst1, 0, 1);
        gridPane.setVgap(2);
        gridPane.setHgap(14);


        //Centro
        Label first2 = new Label("Castellar");
        first2.setStyle("-fx-font-size: 3em;");
        gridPane.add(first2, 1, 2);
        gridPane.setHalignment(first2, HPos.CENTER);

        Label sec2 = new Label("Puig");
        sec2.setStyle("-fx-font-size: 3em;");
        gridPane.add(sec2, 2, 2);
        Label zirst2 = new Label("Institut");
        zirst2.setStyle("-fx-font-size: 3em;");
        gridPane.setHalignment(zirst2, HPos.RIGHT);
        gridPane.add(zirst2, 0, 2);
        gridPane.setVgap(2);
        gridPane.setHgap(14);

        //Centro
        Label first3 = new Label("https://github.com/Joe5K/fake-brawlhalla");
       gridPane.add(first3, 1, 5);

        Label sec3 = new Label(" ");
        gridPane.add(sec3, 1, 3);
        Label zirst3 = new Label("Agradecimiento y fuente de la versión original de: ");
        gridPane.setHalignment(zirst3, HPos.LEFT);
       gridPane.add(zirst3, 1, 4);

        gridPane.setVgap(2);
       gridPane.setHgap(14);

        Button button = new Button("Atrás");
        button.setStyle("-fx-font-size: 2em;");
        button.setMinWidth(200);
        button.setOnAction(e-> stage.setScene(defaultScene));
        gridPane.add(button, 2, count+10,3,1);
        gridPane.setAlignment(Pos.CENTER);
        return new Scene(gridPane,800,600);
    } //just long code generating javafx scene

    private Scene generateScoreboardScene() throws FileNotFoundException {
        PlayerScore ps = new PlayerScore("src\\resources\\score.txt");
        LinkedList<HashElement> scoreMap = ps.getScoreMap();
        GridPane gridPane = new GridPane();
        int count = 0;
        Label first = new Label("Nombre de Jugador");
        first.setStyle("-fx-font-size: 3em;");
        gridPane.add(first, 1, 0);
        Label sec = new Label("Victorias");
        sec.setStyle("-fx-font-size: 3em;");
        gridPane.add(sec, 2, 0);
        Label zirst = new Label("#");
        zirst.setStyle("-fx-font-size: 3em;");
        gridPane.setHalignment(zirst, HPos.RIGHT);
        gridPane.add(zirst, 0, 0);
        gridPane.setVgap(2);
        gridPane.setHgap(14);
        for(HashElement hashElement : scoreMap) {
            Label name = new Label(hashElement.getName());
            name.setStyle("-fx-font-size: 2em; -fx-border-width: 2;");
            gridPane.setHalignment(name, HPos.CENTER);
            gridPane.add(name, 1, count+1);
            Label score = new Label(Integer.toString(hashElement.getScore()));
            score.setStyle("-fx-font-size: 2em; -fx-border-width: 2;");
            gridPane.add(score, 2, count+1);
            if(count<3) {
                ImageView medal = new ImageView(new Image(new FileInputStream("src/resources/medal" + count + ".png")));
                medal.setFitWidth(32);
                gridPane.setMinWidth(32);
                Label num = new Label(count+1 + ".");
                num.setStyle("-fx-font-size: 2em; -fx-border-width: 2;");
                gridPane.setHalignment(num, HPos.RIGHT);
                gridPane.add(new HBox(medal, num), 0, count + 1);
            }
            else {
                Label num = new Label(count+1 + ".");
                num.setStyle("-fx-font-size: 2em; -fx-border-width: 2;");
                gridPane.setHalignment(num, HPos.RIGHT);
                gridPane.add(num, 0, count + 1);
            }
            if(++count==9)
                break;
        }
        Button button = new Button("Atrás");
        button.setStyle("-fx-font-size: 2em;");
        button.setMinWidth(200);
        button.setOnAction(e-> stage.setScene(defaultScene));
        gridPane.add(button, 1, count+1,3,1);
        gridPane.setAlignment(Pos.CENTER);
        return new Scene(gridPane,800,600);
    } //just long code generating javafx scene

    private Scene generateLauncherScene() throws FileNotFoundException {
        Button[] buttons = new Button[5];


        buttons[0] = new Button("Jugar");
        buttons[0].setOnAction(event -> {
            runPlayerSelectionScene();
        });

        buttons[1] = new Button("Ajustes");
        buttons[1].setOnAction(event -> {
            runSettings();
        });

        buttons[2] = new Button("Historial de victorias");
        buttons[2].setOnAction(event -> {
            try {
                runScoreboard();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        //Nuevo boton
        buttons[3] = new Button("Créditos");
        buttons[3].setOnAction(event -> {
            try {
                runCredits();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        //Nuevo boton

        buttons[4] = new Button("Salir del juego");
        buttons[4].setOnAction(event -> {
            Platform.exit();
        });

        for (Button i : buttons) {
            i.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            Rectangle but = new Rectangle();
            but.setWidth(500);
            but.setHeight(80);
            but.setArcHeight(30);
            but.setArcWidth(30);
            i.setShape(but);
            i.setStyle("-fx-font-size: 3em; -fx-border-color: black; -fx-border-width: 2;");
            i.setMinWidth(400);
            i.onMouseEnteredProperty().set(event -> i.setBackground(new Background(new BackgroundFill(Color.MEDIUMPURPLE, CornerRadii.EMPTY, Insets.EMPTY))));
            i.onMouseExitedProperty().set(event -> i.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))));
        }

        Label authors = new Label("©Amadurell 1997-2022");
        authors.setTextFill(Color.BLACK);

        Image image = new Image(new FileInputStream("src/resources/Logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setFitWidth(600);
     imageView.setPreserveRatio(true);

        VBox vbox = new VBox(buttons);
        vbox.setSpacing(30);
        vbox.setAlignment(Pos.CENTER);

        BorderPane borderpane = new BorderPane();
        borderpane.setCenter(vbox);
        borderpane.setTop(imageView);
        borderpane.setBottom(authors);
        borderpane.setAlignment(imageView, Pos.CENTER);
        borderpane.setAlignment(authors, Pos.BOTTOM_RIGHT);

        borderpane.setStyle("-fx-background-color: #cacaca;");
        return (new Scene(borderpane, 800, 600));
    } //just long code generating javafx scene

    private Scene generateSettingsScene() {

        Text[] player1Texts = new Text[4];
        player1Texts[0] = new Text("Jugador Nº1 SALTAR:");
        player1Texts[1] = new Text("Jugador Nº1 IZQUIERDA:");
        player1Texts[3] = new Text("Jugador Nº1 DISPARO:");
        player1Texts[2] = new Text("Jugador Nº1 DERECHA:");

        Button[] player1Buttons = new Button[4];
        player1Buttons[0] = new Button(settings.getPlayer1Jump().toString());
        player1Buttons[1] = new Button(settings.getPlayer1Left().toString());
        player1Buttons[3] = new Button(settings.getPlayer1Shoot().toString());
        player1Buttons[2] = new Button(settings.getPlayer1Right().toString());

        player1Buttons[0].setOnAction(e-> settingsScene.setOnKeyPressed(ek -> {settings.setPlayer1Jump(ek.getCode()); player1Buttons[0].setText(settings.getPlayer1Jump().toString());}));
        player1Buttons[1].setOnAction(e-> settingsScene.setOnKeyPressed(ek -> {settings.setPlayer1Left(ek.getCode()); player1Buttons[1].setText(settings.getPlayer1Left().toString());}));
        player1Buttons[3].setOnAction(e-> settingsScene.setOnKeyPressed(ek -> {settings.setPlayer1Shoot(ek.getCode()); player1Buttons[3].setText(settings.getPlayer1Shoot().toString());}));
        player1Buttons[2].setOnAction(e-> settingsScene.setOnKeyPressed(ek -> {settings.setPlayer1Right(ek.getCode()); player1Buttons[2].setText(settings.getPlayer1Right().toString());}));

        Text[] player2Texts = new Text[4];
        player2Texts[0] = new Text("Jugador Nº2 SALTAR:");
        player2Texts[1] = new Text("Jugador Nº2 IZQUIERDA:");
        player2Texts[3] = new Text("Jugador Nº2 DISPARO:");
        player2Texts[2] = new Text("Jugador Nº2 DERECHA:");

        Button[] player2Buttons = new Button[4];
        player2Buttons[0] = new Button(settings.getPlayer2Jump().toString());
        player2Buttons[1] = new Button(settings.getPlayer2Left().toString());
        player2Buttons[3] = new Button(settings.getPlayer2Shoot().toString());
        player2Buttons[2] = new Button(settings.getPlayer2Right().toString());

        player2Buttons[0].setOnAction(e-> settingsScene.setOnKeyPressed(ek -> {settings.setPlayer2Jump(ek.getCode()); player2Buttons[0].setText(settings.getPlayer2Jump().toString());}));
        player2Buttons[1].setOnAction(e-> settingsScene.setOnKeyPressed(ek -> {settings.setPlayer2Left(ek.getCode()); player2Buttons[1].setText(settings.getPlayer2Left().toString());}));
        player2Buttons[3].setOnAction(e-> settingsScene.setOnKeyPressed(ek -> {settings.setPlayer2Shoot(ek.getCode()); player2Buttons[3].setText(settings.getPlayer2Shoot().toString());}));
        player2Buttons[2].setOnAction(e-> settingsScene.setOnKeyPressed(ek -> {settings.setPlayer2Right(ek.getCode()); player2Buttons[2].setText(settings.getPlayer2Right().toString());}));

        Text[] texts = new Text[3];
        texts[0] = new Text(" Resolución: ");
        texts[1] = new Text(" Pantalla completa: ");
        texts[2] = new Text(" Musica y\n Efectos de sonidos: ");
        //Text textSound = new Text("Sound:");

        /*TextField[] textFields = new TextField[2];
        textFields[0] = new TextField(Integer.toString(settings.getWidth()));
        textFields[1] = new TextField(Integer.toString(settings.getHeight()));*/

        final ComboBox resolutionsComboBox = new ComboBox();
        resolutionsComboBox.getItems().addAll(
                "854x480",
                "1024x576",
                "1280x720",
                "1366x768",
                "1600x900",
                "1920x1080",
                "2560x1440",
                "3840x2160"
        );
        resolutionsComboBox.setValue(settings.getWidth()+"x"+settings.getHeight());

        CheckBox checkboxFullscreen = new CheckBox();
        checkboxFullscreen.setSelected(settings.isFullscreen());
        CheckBox checkboxsound = new CheckBox();
        checkboxsound.setSelected(settings.isSound());

        Button[] buttons = new Button[2];
        buttons[0] = new Button("Guardar");
        buttons[0].setOnAction(e -> {

            String resolution = resolutionsComboBox.getValue().toString();
            int posX = resolution.indexOf("x");
            int width = Integer.parseInt(resolution.substring(0, posX));
            int height = Integer.parseInt(resolution.substring(posX+1));

            settings.setResolution(width, height ,checkboxFullscreen.isSelected());
            settings.setSound(checkboxsound.isSelected());
            SettingsSaver settingsSaver = new SettingsSaver();
            try {
                settingsSaver.saveSettings("settings.txt", settings);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            stage.setScene(defaultScene);
        });

        buttons[0].setStyle("-fx-font-size: 1.5em;");
        resolutionsComboBox.setStyle("-fx-font-size: 1.5em;");

        buttons[1] = new Button("Cerrar");
        buttons[1].setStyle("-fx-font-size: 1.5em;");
        buttons[1].setOnAction(e-> stage.setScene(defaultScene));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        for(int i = 0;i<4;i++){
            player1Texts[i].setStyle("-fx-font-size: 2em;");
            player1Buttons[i].setStyle("-fx-font-size: 1.5em;");
            player1Buttons[i].setMaxWidth(140);
            player1Buttons[i].setMinWidth(140);

            player2Texts[i].setStyle("-fx-font-size: 2em;");
            player2Buttons[i].setStyle("-fx-font-size: 1.5em;");
            player2Buttons[i].setMaxWidth(140);
            player2Buttons[i].setMinWidth(140);

            gridPane.add(player1Texts[i], 2, i);
            gridPane.add(player1Buttons[i], 3, i);

            gridPane.add(player2Texts[i], 2, i+4);
            gridPane.add(player2Buttons[i], 3, i+4);
        }
        Label label = new Label("Press the button and press key");
        label.setStyle("-fx-font-size: 0.8em;");
        gridPane.add(label, 3,8);
        for (int i = 0; i < 2; i++) {
            texts[i].setStyle("-fx-font-size: 2em;");
            gridPane.add(texts[i],0,i);
            gridPane.add(buttons[i], i,3);
        }
        gridPane.add(resolutionsComboBox, 1,0);
        //textSound.setStyle("-fx-font-size: 2em;");
        //gridPane.add(textSound,0,3);
        texts[2].setStyle("-fx-font-size: 2em;");
        checkboxFullscreen.setStyle("-fx-font-size: 2em;");
        gridPane.add(texts[2], 0, 2);
        gridPane.add(checkboxFullscreen, 1, 1);
        checkboxsound.setStyle("-fx-font-size: 2em;");
        gridPane.add(checkboxsound, 1, 2);
        return(new Scene(gridPane, 800,600));

    } //just long code generating javafx scene

    private Scene generatePlayerSelectionScene(){ //just long code generating javafx scene
        Text[] texts = new Text[4];

        texts[0] = new Text("Nombre Jugador Nº1: ");
        texts[1] = new Text("Nombre Jugador Nº2: ");
        texts[2] = new Text("Personaje de Jugador Nº1: ");
        texts[3] = new Text("Personaje de Jugador Nº2: ");

        TextField[] textFields = new TextField[2];
        textFields[0] = new TextField(settings.getPlayer1().getName());
        textFields[1] = new TextField(settings.getPlayer2().getName());

        ComboBox[] comboBoxes = new ComboBox[2];
        comboBoxes[0] = new ComboBox();
        comboBoxes[1] = new ComboBox();

        ImageView img1 = new ImageView(new Image("resources/PlayerAnimation/Player2/front.png"));
        ImageView img2 = new ImageView(new Image("resources/PlayerAnimation/Player1/front.png"));
        img1.setId(String.valueOf(1));
        img2.setId(String.valueOf(2));
        comboBoxes[0].setValue(img1);
        comboBoxes[1].setValue(img2);

        comboBoxes[0].setOnMouseClicked(event -> {
            ImageView temp = (ImageView) comboBoxes[0].getValue();
            comboBoxes[0].getItems().clear();
            for (int i = 0; i < 3; i++) {
                ImageView img = new ImageView(new Image("resources/PlayerAnimation/Player"+ (i + 1) +"/front.png"));
                img.setId(String.valueOf(i+1));
                comboBoxes[0].getItems().add(img);
            }
            comboBoxes[0].setValue(temp);
        });

        comboBoxes[1].setOnMouseClicked(event -> {
            ImageView temp = (ImageView) comboBoxes[1].getValue();
            comboBoxes[1].getItems().clear();
            for (int i = 0; i < 3; i++) {
                ImageView img = new ImageView(new Image("resources/PlayerAnimation/Player"+ (i + 1) +"/front.png"));
                img.setId(String.valueOf(i+1));
                comboBoxes[1].getItems().add(img);
            }
            comboBoxes[1].setValue(temp);
        });

        Button play = new Button("Jugar");
        play.setOnAction(e-> {
            settings.getPlayer1().setSkin("Player"+((ImageView) comboBoxes[0].getValue()).getId());
            settings.getPlayer2().setSkin("Player"+((ImageView) comboBoxes[1].getValue()).getId());
            settings.getPlayer1().setName(textFields[0].getText());
            settings.getPlayer2().setName(textFields[1].getText());
            try {
                new SettingsSaver().saveSettings("settings.txt", settings);
                runGame();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        });

        Button back = new Button("Atrás");
        back.setOnAction(e-> {
            this.playerSelect = generatePlayerSelectionScene();
            stage.setScene(defaultScene);});

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        for (int i = 0; i < 2; i++) {
            texts[i].setStyle("-fx-font-size: 1.5em;");
            texts[i+2].setStyle("-fx-font-size: 1.5em;");
            textFields[i].setStyle("-fx-font-size: 1.5em;");
            textFields[i].setMaxWidth(120);
            comboBoxes[i].setStyle("-fx-font-size: 1.5em;");
            comboBoxes[i].setMinWidth(120);
            gridPane.add(texts[i], 0,i);
            gridPane.add(textFields[i],1,i);
            gridPane.add(texts[i+2], i,2);
            gridPane.add(comboBoxes[i], i, 3);
        }

        play.setStyle("-fx-font-size: 1.5em;");
        play.setMinWidth(120);
        back.setStyle("-fx-font-size: 1.5em;");
        back.setMinWidth(120);
        gridPane.add(play, 0,4);
        gridPane.add(back, 1,4);

        return new Scene(gridPane, 800, 600);
    } //just long code generating javafx scene

}
