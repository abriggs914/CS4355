package sample;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

class View extends GridPane {

    public GridPane gridPane;

    View() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
            gridPane = fxmlLoader.load();
            this.getChildren().add(gridPane);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed loading FXML\nExiting Program");
            Platform.exit();
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
