
/**
 * Sample Skeleton for 'Disposizione.fxml' Controller Class
 */

package it.marco_schiavo.disposizione_banchi;

import java.net.URL;
import java.util.ResourceBundle;

import it.marco_schiavo.disposizione_banchi.Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML

    
    void handleClasse(ActionEvent event) {
    	Main.setNumeroscena(1);

    }

    @FXML
    void handleRandom(ActionEvent event) {

    }

    @FXML
    void handleStudente(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
