package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.math.BigInteger;
import java.util.Arrays;

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
    @FXML public Button computeC2Button;
    @FXML public Button computeC1Button;
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

    private boolean checkInt(String text) {
        try {
            BigInteger i = new BigInteger(text);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    BigInteger toBigInteger(String x) {
        return new BigInteger(x);
    }

    public void init() {
        GridPane gp = Main.view.getGridPane();
        System.out.println(Arrays.toString(gp.lookupAll("TextField").toArray()));
        xEqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[0];
        yEqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[1];
        inputMEqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[2];
        rEqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[3];
        c1EqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[4];
        c2EqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[5];
        mDecryptResultTextField = (TextField) gp.lookupAll("TextField").toArray()[6];
    }

    public void setxEqualsResultTextField(String text) {
        xEqualsResultTextField.setText(text);
    }

    public void setyEqualsResultTextField(String text) {
        yEqualsResultTextField.setText(text);
    }

    public void setC1EqualsResultTextField(String text) {
        c1EqualsResultTextField.setText(text);
    }

    public void setC2EqualsResultTextField(String text) {
        c2EqualsResultTextField.setText(text);
    }

    public void setMDecryptResultTextField(String text) {
        mDecryptResultTextField.setText(text);
    }

    public void textChanged(KeyEvent inputMethodEvent) {
        String xText = xEqualsResultTextField.getText();
        String rText = rEqualsResultTextField.getText();
        String mText = inputMEqualsResultTextField.getText();
        boolean xIsInt = checkInt(xText);
        boolean rIsInt = checkInt(rText);
        boolean mIsInt = checkInt(mText);

        if (xIsInt) {
            BigInteger xVal = toBigInteger(xText);
            if (xVal.compareTo(BigInteger.ZERO) > 0) {
                Main.model.setX(xVal);
            }
            else {
                xIsInt = false;
            }
        }
        if (rIsInt) {
            BigInteger rVal = toBigInteger(rText);
            if (rVal.compareTo(BigInteger.ZERO) > 0) {
                Main.model.setR(rVal);
            }
            else {
                rIsInt = false;
            }
        }
        if (mIsInt) {
            BigInteger mVal = toBigInteger(mText);
            if (mVal.compareTo(BigInteger.ZERO) > 0) {
                Main.model.setM1(mVal);
            }
            else {
                mIsInt = false;
            }
        }

        if (xIsInt) {
            xIsNormal();
        } else if (!xText.equals("")) {
            xNeedsToBeSelected();
        }
        if (rIsInt) {
            rIsNormal();
        } else if (!rText.equals("")) {
            rNeedsToBeSelected();
        }
        if (mIsInt) {
            mIsNormal();
        } else if (!mText.equals("")) {
            mNeedsToBeSelected();
        }
    }

    public void computeYButtonClicked(MouseEvent mouseEvent) {
        System.out.println("computeYButtonClicked");
        boolean xIsInt = checkInt(xEqualsResultTextField.getText());
        if (xIsInt) {
            xIsNormal();
            yIsNormal();
            String yText = Main.model.computePublicKeyY();
            yEqualsResultTextField.setText(yText);
        }
        else {
            xNeedsToBeSelected();
        }
    }

    public void computeC1ButtonClicked(MouseEvent mouseEvent) {
        System.out.println("computeC1ButtonClicked");
        boolean rIsInt = checkInt(rEqualsResultTextField.getText());
        if (rIsInt) {
            rIsNormal();
            c1IsNormal();
            String c1Text = Main.model.computeC1();
            c1EqualsResultTextField.setText(c1Text);
        }
        else {
            rNeedsToBeSelected();
        }
    }

    public void computeC2ButtonClicked(MouseEvent mouseEvent) {
        System.out.println("computeC2ButtonClicked");
        boolean mIsInt = checkInt(inputMEqualsResultTextField.getText());
        boolean yIsInt = checkInt(yEqualsResultTextField.getText());
        boolean rIsInt = checkInt(rEqualsResultTextField.getText());

        if (mIsInt) {
            mIsNormal();
        }
        else {
            mNeedsToBeSelected();
        }
        if (yIsInt) {
            yIsNormal();
        }
        else {
            yNeedsToBeSelected();
        }
        if (rIsInt) {
            rIsNormal();
        }
        else {
            rNeedsToBeSelected();
        }

        if (mIsInt && yIsInt && rIsInt) {
            c2IsNormal();
            String c2Text = Main.model.computeC2();
            c2EqualsResultTextField.setText(c2Text);
        }
    }

    public void decryptButtonClicked(MouseEvent mouseEvent) {
        System.out.println("decryptButtonClicked");
        boolean c1IsInt = checkInt(c1EqualsResultTextField.getText());
        boolean c2IsInt = checkInt(c2EqualsResultTextField.getText());

        if (c1IsInt) {
            c1IsNormal();
        }
        else {
            c1NeedsToBeSelected();
        }
        if (c2IsInt) {
            c2IsNormal();
        }
        else {
            c2NeedsToBeSelected();
        }

        if (c1IsInt && c2IsInt) {
            String decryptedM = Main.model.decrypt();
            mDecryptResultTextField.setText(decryptedM);
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

    private void rNeedsToBeSelected() {
        System.out.println("rNeedsToBeSelected:");
        rEqualsResultTextField.setStyle("-fx-control-inner-background: #FFAAAA;");
    }
    private void rIsNormal() {
        System.out.println("rIsNormal:");
        rEqualsResultTextField.setStyle("-fx-control-inner-background: white;");
    }

    private void mNeedsToBeSelected() {
        System.out.println("mNeedsToBeSelected:");
        inputMEqualsResultTextField.setStyle("-fx-control-inner-background: #FFAAAA;");
    }
    private void mIsNormal() {
        System.out.println("mIsNormal:");
        inputMEqualsResultTextField.setStyle("-fx-control-inner-background: white;");
    }

    private void c1NeedsToBeSelected() {
        System.out.println("c1NeedsToBeSelected:");
        c1EqualsResultTextField.setStyle("-fx-control-inner-background: #FFAAAA;");
    }
    private void c1IsNormal() {
        System.out.println("c1IsNormal:");
        c1EqualsResultTextField.setStyle("-fx-control-inner-background: white;");
    }

    private void c2NeedsToBeSelected() {
        System.out.println("c2NeedsToBeSelected:");
        c2EqualsResultTextField.setStyle("-fx-control-inner-background: #FFAAAA;");
    }
    private void c2IsNormal() {
        System.out.println("c2IsNormal:");
        c2EqualsResultTextField.setStyle("-fx-control-inner-background: white;");
    }
}
