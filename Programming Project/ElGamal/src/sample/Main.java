package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public View view;
    public static Model model;
    public static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        view = new View();
        controller = new Controller();
        model = new Model();
        controller.init();

        primaryStage.setTitle("ElGamal Encryption/Decryption");
        primaryStage.setScene(new Scene(view, 500, 375));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
