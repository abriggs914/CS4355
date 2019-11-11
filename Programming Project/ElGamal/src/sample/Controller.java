package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML public Label pAndGLabel;
    @FXML public Label choosePrivateKeyLabel;
    @FXML public Label xEqualsLabel;
    @FXML public TextField xEqualsResultTextField;
    @FXML public Label mDecryptEqualsLabel;
    @FXML public Label c2EqualsLabel;
    @FXML public Label inputMEqualsLabel;
    @FXML public Button decryptButton;
    @FXML public Label c1EqualsLabel;
    @FXML public Label rEqualsLabel;
    @FXML public Button computec2Button;
    @FXML public Button computec1Button;
    @FXML public Button ComputeYButton;
    @FXML public TextField mDecryptResultTextField;
    @FXML public TextField c2EqualsResultTextField;
    @FXML public TextField c1EqualsResultTextField;
    @FXML public Label chooseRandomRLabel;
    @FXML public Label computeC1Label;
    @FXML public Label mDecrpytLabel;
    @FXML public Label computeC2Label;
    @FXML public Label decryptLabel;
    @FXML public TextField yEqualsResultTextField;
    @FXML public TextField inputMEqualsResultTextField;
    @FXML public TextField rEqualsResultTextField;
    @FXML public Label encryptLabel;
    @FXML public Label yEqualsLabel;
    @FXML public Label ComputeYLabel;
    @FXML public Label inputMLabel;

    public void init() {
    }

    public void computYButtonClicked(MouseEvent mouseEvent) {
        System.out.println("computeYButtonClicked");
    }

    public void computeC1ButtonClicked(MouseEvent mouseEvent) {
        System.out.println("computeC1ButtonClicked");
    }

    public void computeC2ButtonClicked(MouseEvent mouseEvent) {
        System.out.println("computeC2ButtonClicked");
    }

    public void decryptButtonClicked(MouseEvent mouseEvent) {
        System.out.println("decryptButtonClicked");
    }
}
