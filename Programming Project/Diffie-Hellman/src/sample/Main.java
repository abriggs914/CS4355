package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static View view;
    public static Model model;
    public static Controller controller;

    @Override
    public void start(Stage primaryStage) {

        controller = new Controller();
        view = new View();
        model = new Model();
        controller.init();

        primaryStage.setTitle("Diffie-Hellman Key Exchange");
        primaryStage.setScene(new Scene(view, 505, 375));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
