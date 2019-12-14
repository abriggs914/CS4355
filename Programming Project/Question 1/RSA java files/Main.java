package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *  CS4355 Cryptanalysis Final Project
 *  Question 2 - part c) i
 *  Dec.2/19
 *  Avery Briggs
 *  3471065
 *
 *  JavaFX application to demonstrate simple RSA
 *  encryption and decryption. String input works
 *  sometimes, it seems mostly for really short
 *  words.
 */

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
        Scene scene = new Scene(view, 500, 400);
        primaryStage.setTitle("RSA Encryption/Decryption");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
