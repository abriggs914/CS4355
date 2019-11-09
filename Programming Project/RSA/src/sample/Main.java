package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Pane view;
    public static Model model;
    public static Controller controller;

    @Override
    public void start(Stage primaryStage) {
        controller = new Controller();
        view = new View();
        model = new Model();
        controller.init();
        Scene scene = new Scene(view, 500, 375);
        primaryStage.setTitle("RSA Encryption/Decryption");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
