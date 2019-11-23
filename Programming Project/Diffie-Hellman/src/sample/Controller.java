package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {

    public Label pAndGLabel;
    public Label xEqualsLabel;
    public Label chooseRandomXLabel;
    public Label ComputeXLabel;
    public Label XEqualsLabel;
    public Label chooseRandomYLabel;
    public Label computeYLabel;
    public Label calcSessionKeyLabel;
    public Label inputMEqualsLabel;
    public Label c1EqualsLabel;
    public Label k2EqualsLabel;
    public Label k1EqualsLabel;

    public TextField XEqualsResultTextField;
    public TextField yEqualsTextField;
    public TextField YEqualsResultTextField;
    public TextField c2EqualsResultTextField;
    public TextField mDecryptResultTextField;
    public TextField xEqualsResultTextField;

    public Button ComputeXButton;
    public Button computeYButton;
    public Button calcK1Button;
    public Button calcK2Button;

    public void computeXButtonClicked(MouseEvent mouseEvent) {
        System.out.println("computeXButtonClicked");
    }

    public void computeYButtonClicked(MouseEvent mouseEvent) {
        System.out.println("computeYButtonClicked");
    }

    public void calcK1ButtonClicked(MouseEvent mouseEvent) {
        System.out.println("calcK1ButtonClicked");
    }

    public void calcK2ButtonClicked(MouseEvent mouseEvent) {
        System.out.println("calcK2ButtonClicked");
    }
}
