package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public View view;
    public Model model;

    @Override
    public void start(Stage primaryStage) {

        view = new View();
        model = new Model();

        primaryStage.setTitle("Diffie-Hellman Key Exchange");
        primaryStage.setScene(new Scene(view, 500, 375));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
