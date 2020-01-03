import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ControllerTheo {

   

	@FXML
    private TextField txtFileInput;

    @FXML
    private Button btnCalc;

    @FXML
    private TextField txtAns;

    @FXML
    private Text labelString;

    @FXML
    private TextField txtTextString;

    @FXML
    private Text InputTextFile;

    @FXML
    private Text IsDFAAccept;

    @FXML
    void Calculate(ActionEvent event) throws FileNotFoundException {
    	String fileName = txtFileInput.getText();
    	String testString = txtTextString.getText();
    	DFA dfa = new DFA(fileName, testString);
    	dfa.getFinal();
    	txtAns.setText(DFA.getAns());
    
    	
    }
}