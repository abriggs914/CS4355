package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Controller {

    // Identifying labels
    @FXML public Label nEqualsLabel;
    @FXML public Label computeNLabel;
    @FXML public Label setPublicKeyELabel;
    @FXML public Label calculatePrivateKeyDLabel;
    @FXML public Label inputStringLabel;
    @FXML public Label encryptCLabel;
    @FXML public Label decryptMLabel;
    @FXML public Label cEqualsLabel;
    @FXML public Label mEqualsLabel2;
    @FXML public Label mEqualsLabel1;
    @FXML public Label dEqualsLabel;
    @FXML public Label eEqualsLabel;
    @FXML public Label pEqualsLabel;
    @FXML public Label qEqualsLabel;
    @FXML public Label generatePrimesLabel1;
    @FXML public Label generatePrimesLabel2;
    @FXML public Label resetFieldsLabel;

    // Interaction buttons
    @FXML public Button generatePrimesButton;
    @FXML public Button computeNButton;
    @FXML public Button calculateDButton;
    @FXML public Button encryptButton;
    @FXML public Button decryptButton;
    @FXML public Button resetButton;

    // Results reporting windows
    @FXML public TextField pEqualsResultTextField;
    @FXML public TextField qEqualsResultTextField;
    @FXML public TextField cEqualsResultTextField;
    @FXML public TextField mEqualsResultTextField1;
    @FXML public TextField mEqualsResultTextField2;
    @FXML public TextField dEqualsResultTextField;
    @FXML public TextField nEqualsResultTextField;
    @FXML public static TextField eEqualsResultTextField;

//    Controller() {
//        eEqualsResultTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
//            // assert that e is a valid number
//            System.out.println("type: " + newValue.getClass());
//            Main.model.setE(Integer.parseInt(newValue));
//        }));
//    }

    @FXML
    public void genPrimesButtonClicked(MouseEvent mouseEvent) {
        System.out.println("gen primes clicked");
        int p = Main.model.generatePrimeNumber();
        int q = Main.model.generatePrimeNumber();

        if (mouseEvent.isControlDown()) {
            p = 11;
            q = 13;
            System.out.println("TESTING:\n\tp : " + p + "\n\tq : " + q + "\n");
        }
        String pResultCurrText = Integer.toString(p);
        String qResultCurrText = Integer.toString(q);
        Main.model.setP(new BigInteger(Integer.toString(p)));
        Main.model.setQ(new BigInteger(Integer.toString(q)));
        pIsNormal();
        qIsNormal();
        pEqualsResultTextField.setText(pResultCurrText);
        qEqualsResultTextField.setText(qResultCurrText);
    }

    public void computeNButtonClicked(MouseEvent mouseEvent) {
        System.out.println("compute n button clicked");
        BigInteger n = Main.model.computeN();
        BigInteger zero = new BigInteger("0");
        if (n.compareTo(zero) == 0) {
            if (Main.model.getP().compareTo(zero) <= 0) {
                pNeedsToBeSelected();
            }
            if (Main.model.getQ().compareTo(zero) <= 0) {
                qNeedsToBeSelected();
            }
        }
        else {
            pIsNormal();
            qIsNormal();
        }
        String nResultCurrText = n.toString();
        Main.model.setN(n);
        Main.model.setPhiN();
        nEqualsResultTextField.setText(nResultCurrText);
    }

    public void calculateDButtonClicked(MouseEvent mouseEvent) {
        System.out.println("calculate d button clicked");
        BigInteger d = Main.model.calculateD();
        boolean greaterThanZero = d.compareTo(new BigInteger("0")) > 0;
        boolean eIsInt = checkInt(Main.model.getE().toString());
        if (greaterThanZero && eIsInt) {
            eIsNormal();
            nIsNormal();
            String dResultCurrText = d.toString();
            Main.model.setD(d);
            dEqualsResultTextField.setText(dResultCurrText);
        }
        else {
            if (!eIsInt) {
                eNeedsToBeSelected();
            }
            if (!checkInt(Main.model.getN().toString())) {
                nNeedsToBeSelected();
            }
        }
    }

    private final Charset UTF_8 = Charset.forName("UTF-8");

    public void encryptButtonClicked(MouseEvent mouseEvent) {
        System.out.println("encrypt message button clicked");
        String m1CurrText = mEqualsResultTextField1.getText();
        if (m1CurrText.length() > 0) {
            BigInteger m1;
            if (checkInt(m1CurrText)) {
                m1 = new BigInteger(m1CurrText);
            }
            else{
                m1 = stringToBigInteger(m1CurrText);
            }

            BigInteger encM1 = Main.model.encrypt(m1);
            System.out.println("encM1: " + encM1);
            Main.model.setC(encM1);
            cEqualsResultTextField.setText(encM1.toString());
        }
        else {
            m1NeedsToBeSelected();
        }
    }

    public BigInteger stringToBigInteger(String num) {
        byte[] bA = num.getBytes(UTF_8);
        StringBuilder number = new StringBuilder();
        boolean first = true;
        for (Byte b : bA) {
            StringBuilder s = new StringBuilder(Integer.toBinaryString(b));
            for (int i = 8; i - s.length() >= 0; i--) {
                if (!first) {
                    s.insert(0, "0");
                }
                first = false;
            }
//            System.out.println("\ts: " + s);
            number.append(s);
        }
        System.out.println("number: {" + number + "}");
        return binaryToBigInteger(number.toString());
    }

    public BigInteger binaryToBigInteger(String number) {
        BigInteger res = BigInteger.ZERO;
        int power = number.length() - 1;
        for (int i = 0; i < number.length(); i++){
            int val = number.charAt(i) - 48;
//            System.out.println("val: " + val);
            if (val == 1) {
                BigInteger toAdd = new BigInteger(Integer.toString(val));
                toAdd = new BigInteger("2").pow(power - i);
                res = res.add(toAdd);
            }
        }
        System.out.println("decimal number: " + res);
        return res;
    }

    public void decryptButtonClicked(MouseEvent mouseEvent) {
        System.out.println("decrypt message button clicked");

        String cCurrText = cEqualsResultTextField.getText();
        if (checkInt(cCurrText)) {
            mEqualsResultTextField2.setText("processing... this may take a moment.");
            BigInteger decC = Main.model.decrypt();
            cIsNormal();
            if (checkInt(mEqualsResultTextField1.getText())) {
                mEqualsResultTextField2.setText(decC.toString());
            }
            else {
                mEqualsResultTextField2.setText(bigIntegerToString(decC));
            }
        }
        else {
            cNeedsToBeSelected();
        }
        System.out.println("decrypt handled.");
    }

    private String bigIntegerToString(BigInteger decC) {
        String binary = decC.toString(2);
        String res = null;
        res = new String(decC.toString().getBytes(), 0, decC.toString().length(), StandardCharsets.US_ASCII);
        char nextChar;
        System.out.println("binaryA: " + binary);
        for (int i = 0; i + binary.length() < 8; i++) {
            binary = "0" + binary;
        }
        System.out.println("binaryB: " + binary);

        StringBuilder decrypted = new StringBuilder();
        for(int i = 0; i <= binary.length()-8; i += 9) {
            nextChar = (char)Integer.parseInt(binary.substring(i, i+8), 2);
            decrypted.append(nextChar);
        }
        System.out.println("bigIntegerToString: " + res);
        System.out.println("decrypted: " + decrypted);
        return decrypted.toString();
    }

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
        String enteredText = eEqualsResultTextField.getText();
        Main.model.setWorkingE(enteredText);
        boolean textIsInt = checkInt(enteredText);
        boolean error = false;
        if (textIsInt && Main.model.checkPrime(new BigInteger(enteredText))) {
            boolean coprimePhiN = Main.model.gcd(new BigInteger(enteredText), Main.model.getPhiN()).compareTo(new BigInteger("1")) == 0;
            if (coprimePhiN) {
                BigInteger e = new BigInteger(enteredText);
                Main.model.setE(new BigInteger(e.toString()));
                eIsNormal();
            }
            else {
                error = true;
            }
        }
        else {
            error = true;
        }
        if (error) {
            System.out.println("Value enter for \"e\" is not an integer.");
            eNeedsToBeSelected();
        }
    }

    private void pNeedsToBeSelected() {
//        System.out.println("pNeedsToBeSelected:");
        pEqualsResultTextField.setStyle("-fx-control-inner-background: #FFAAAA;");
    }

    private void pIsNormal() {
//        System.out.println("pIsNormal:");
        pEqualsResultTextField.setStyle("-fx-control-inner-background: white;");
    }

    private void qNeedsToBeSelected() {
//        System.out.println("qNeedsToBeSelected:");
        qEqualsResultTextField.setStyle("-fx-control-inner-background: #FFAAAA;");
    }

    private void qIsNormal() {
//        System.out.println("qIsNormal:");
        qEqualsResultTextField.setStyle("-fx-control-inner-background: white;");
    }

    private void m1NeedsToBeSelected() {
//        System.out.println("m1NeedsToBeSelected:");
        mEqualsResultTextField1.setStyle("-fx-control-inner-background: #FFAAAA;");
    }

    private void m1IsNormal() {
//        System.out.println("m1IsNormal:");
        mEqualsResultTextField1.setStyle("-fx-control-inner-background: white;");
    }

    public static void eNeedsToBeSelected() {
//        System.out.println("eNeedsToBeSelected:");
        eEqualsResultTextField.setStyle("-fx-control-inner-background: #FFAAAA;");
    }

    private void cNeedsToBeSelected() {
//        System.out.println("cNeedsToBeSelected:");
        cEqualsResultTextField.setStyle("-fx-control-inner-background: #FFAAAA;");
    }

    private void cIsNormal() {
//        System.out.println("cIsNormal:");
        cEqualsResultTextField.setStyle("-fx-control-inner-background: white;");
    }

    public void eIsNormal() {
//        System.out.println("eIsNormal:");
        eEqualsResultTextField.setStyle("-fx-control-inner-background: white;");
    }

    private void nNeedsToBeSelected() {
//        System.out.println("nIsNormal:");
        nEqualsResultTextField.setStyle("-fx-control-inner-background: #FFAAAA;");
    }

    public void nIsNormal() {
//        System.out.println("nIsNormal:");
        nEqualsResultTextField.setStyle("-fx-control-inner-background: white;");
    }

    public void init() {
        GridPane gp = (GridPane) Main.view.getChildren().get(0);
        pEqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[0];
        qEqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[1];
        cEqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[2];
        mEqualsResultTextField1 = (TextField) gp.lookupAll("TextField").toArray()[3];
        mEqualsResultTextField2 = (TextField) gp.lookupAll("TextField").toArray()[4];
        dEqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[5];
        nEqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[6];
        eEqualsResultTextField = (TextField) gp.lookupAll("TextField").toArray()[7];
    }

    public void resetFields(MouseEvent mouseEvent) {
        System.out.println("CLEARING");
        pEqualsResultTextField.clear();
        qEqualsResultTextField.clear();
        cEqualsResultTextField.clear();
        mEqualsResultTextField1.clear();
        mEqualsResultTextField2.clear();
        dEqualsResultTextField.clear();
        nEqualsResultTextField.clear();
        eEqualsResultTextField.clear();
        pIsNormal();
        qIsNormal();
        cIsNormal();
        m1IsNormal();
        nIsNormal();
        eIsNormal();
    }
}
