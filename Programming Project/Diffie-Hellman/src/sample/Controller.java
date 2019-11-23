package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.math.BigInteger;
import java.util.Arrays;

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
    public TextField yEqualsResultTextField;
    public TextField YEqualsResultTextField;
    public TextField k1EqualsResultTextField;
    public TextField k2EqualsResultTextField;
    public TextField xEqualsResultTextField;

    public Button computeXButton;
    public Button computeYButton;
    public Button calcK1Button;
    public Button calcK2Button;

//    Controller () {
//    }

    private boolean checkInt(String text) {
        try {
            int i = Integer.parseInt(text);
            return true;
        }
        catch (Exception e) {
            try{
                BigInteger i = new BigInteger(text);
            }
            catch (Exception b) {
                return false;
            }
            return true;
        }
    }

    public void textChanged(KeyEvent inputMethodEvent) {
        System.out.println("CHANGING TEXT : " + inputMethodEvent.getCharacter());
        String xText = xEqualsResultTextField.getText();
        String yText = yEqualsResultTextField.getText();
        boolean xIsInt = checkInt(xText);
        boolean yIsInt = checkInt(yText);
        if (xIsInt) {
            int xVal = Integer.parseInt(xText);
            if (xVal > 0) {
                // x is valid
                System.out.println("setting x: " + xVal);
                Main.model.setX(xVal);
            } else {
                xIsInt = false;
            }
        }
        if (yIsInt) {
            int yVal = Integer.parseInt(yText);
            if (yVal > 0) {
                // y is valid
                System.out.println("setting y: " + yVal);
                Main.model.setY(yVal);
            } else {
                yIsInt = false;
            }
        }

        if (xIsInt) {
            xIsNormal();
        } else if (!xText.equals("")) {
            xNeedsToBeSelected();
        }
        if (yIsInt) {
            yIsNormal();
        } else if (!yText.equals("")) {
            yNeedsToBeSelected();
        }
    }

    public void init() {
        GridPane gp = (GridPane) Main.view.getChildren().get(0);
        System.out.println("TextFields: {\n" + Arrays.toString(gp.lookupAll("TextField").toArray()) + "\n}");
        XEqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[1];
        YEqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[3];
        xEqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[0];
        yEqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[2];
        k1EqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[4];
        k2EqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[5];
    }

    public void setxEqualsResultTextField(String text) {
        xEqualsResultTextField.setText(text);
    }

    public void setyEqualsResultTextField(String text) {
        yEqualsResultTextField.setText(text);
    }

    public void setXEqualsResultTextField(String text) {
        XEqualsResultTextField.setText(text);
    }

    public void setYEqualsResultTextField(String text) {
        YEqualsResultTextField.setText(text);
    }

    public void setK1EqualsResultTextField(String text) {
        k1EqualsResultTextField.setText(text);
    }

    public void setK2EqualsResultTextField(String text) {
        k2EqualsResultTextField.setText(text);
    }

    public void computeXButtonClicked(MouseEvent mouseEvent) {
        System.out.println("computeXButtonClicked");
        boolean xIsInt = checkInt(xEqualsResultTextField.getText());
        if (xIsInt) {
            xIsNormal();
            bigXIsNormal();
            String bigX = Main.model.computeBigX();
            XEqualsResultTextField.setText(bigX);
        }
        else {
            xNeedsToBeSelected();
        }
    }

    public void computeYButtonClicked(MouseEvent mouseEvent) {
        System.out.println("computeYButtonClicked");
        boolean yIsInt = checkInt(yEqualsResultTextField.getText());
        if (yIsInt) {
            yIsNormal();
            bigYIsNormal();
            String bigY = Main.model.computeBigY();
            YEqualsResultTextField.setText(bigY);
        }
        else {
            yNeedsToBeSelected();
        }
    }

    public void calcK1ButtonClicked(MouseEvent mouseEvent) {
        System.out.println("calcK1ButtonClicked");
        boolean xIsInt = checkInt(xEqualsResultTextField.getText());
        boolean yIsInt = checkInt(yEqualsResultTextField.getText());
        boolean bigXIsInt = checkInt(XEqualsResultTextField.getText());
        boolean bigYIsInt = checkInt(YEqualsResultTextField.getText());
        if (!xIsInt) {
            xNeedsToBeSelected();
        }
        else {
            xIsNormal();
        }
        if (!yIsInt) {
            yNeedsToBeSelected();
        }
        else {
            yIsNormal();
        }
        if (!bigYIsInt) {
            bigYNeedsToBeSelected();
        }
        else {
            bigYIsNormal();
        }
        if (xIsInt && yIsInt && bigYIsInt) {
            bigYIsNormal();
            xIsNormal();
            yIsNormal();
            String k1 = Main.model.computeSessionKey1();
            k1EqualsResultTextField.setText(k1);
        }
    }

    public void calcK2ButtonClicked(MouseEvent mouseEvent) {
        System.out.println("calcK2ButtonClicked");
        boolean xIsInt = checkInt(xEqualsResultTextField.getText());
        boolean yIsInt = checkInt(yEqualsResultTextField.getText());
        boolean bigXIsInt = checkInt(XEqualsResultTextField.getText());
        boolean bigYIsInt = checkInt(YEqualsResultTextField.getText());
        if (!xIsInt) {
            xNeedsToBeSelected();
        }
        else {
            xIsNormal();
        }
        if (!yIsInt) {
            yNeedsToBeSelected();
        }
        else {
            yIsNormal();
        }
        if (!bigXIsInt) {
            bigXNeedsToBeSelected();
        }
        else {
            bigXIsNormal();
        }
        if (xIsInt && yIsInt && bigXIsInt) {
            bigXIsNormal();
            xIsNormal();
            yIsNormal();
            String k2 = Main.model.computeSessionKey2();
            k2EqualsResultTextField.setText(k2);
        }
    }

    private void xNeedsToBeSelected() {
        System.out.println("xNeedsToBeSelected:");
        xEqualsResultTextField.setStyle("-fx-control-inner-background: #FFAAAA;");
    }

    private void xIsNormal() {
        System.out.println("xIsNormal:");
        xEqualsResultTextField.setStyle("-fx-control-inner-background: white;");
    }

    private void yNeedsToBeSelected() {
        System.out.println("yNeedsToBeSelected:");
        yEqualsResultTextField.setStyle("-fx-control-inner-background: #FFAAAA;");
    }

    private void yIsNormal() {
        System.out.println("yIsNormal:");
        yEqualsResultTextField.setStyle("-fx-control-inner-background: white;");
    }

    private void bigXNeedsToBeSelected() {
        System.out.println("bigXNeedsToBeSelected:");
        XEqualsResultTextField.setStyle("-fx-control-inner-background: #FFAAAA;");
    }

    private void bigXIsNormal() {
        System.out.println("bigXIsNormal:");
        XEqualsResultTextField.setStyle("-fx-control-inner-background: white;");
    }

    private void bigYNeedsToBeSelected() {
        System.out.println("bigYNeedsToBeSelected:");
        YEqualsResultTextField.setStyle("-fx-control-inner-background: #FFAAAA;");
    }

    private void bigYIsNormal() {
        System.out.println("bigYIsNormal:");
        YEqualsResultTextField.setStyle("-fx-control-inner-background: white;");
    }
}
