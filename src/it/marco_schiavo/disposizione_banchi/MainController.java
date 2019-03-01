
/**
 * Sample Skeleton for 'Disposizione.fxml' Controller Class
 */

package it.marco_schiavo.disposizione_banchi;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML

    
    void handleClasse(ActionEvent event) {
    	Main.setIndice(1);
    	Main.newStart();

    }

    @FXML
    void handleRandom(ActionEvent event) {
    	Main.setIndice(3);
    	Main.newStart();

    }

    @FXML
    void handleStudente(ActionEvent event) {
    	Main.setIndice(2);
    	Main.newStart();

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
    
}
