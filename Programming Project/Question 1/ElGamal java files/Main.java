package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *  CS4355 Cryptanalysis Final Project
 *  Question 2 - part c) ii
 *  Dec.2/19
 *  Avery Briggs
 *  3471065
 *
 *  JavaFX application to demonstrate simple ElGamal
 *  encryption and decryption.
 */

public class Main extends Application {

    public static View view;
    public static Model model;
    public static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        view = new View();
        controller = new Controller();
        model = new Model();
        controller.init();

        primaryStage.setTitle("ElGamal Encryption/Decryption");
        primaryStage.setScene(new Scene(view, 500, 350));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
