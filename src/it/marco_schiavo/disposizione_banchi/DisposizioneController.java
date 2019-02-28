/**
 * Sample Skeleton for 'Disposizione.fxml' Controller Class
 */

package it.marco_schiavo.disposizione_banchi;

import java.net.URL;
import java.util.ResourceBundle;

import it.marco_schiavo.disposizione_banchi.Model.Aula;
import it.marco_schiavo.disposizione_banchi.Model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DisposizioneController {
	
	private Model model;
	private ObservableList<Aula> collezione = FXCollections.observableArrayList();


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtDisplay"
    private TableView<Aula> txtDisplay; // Value injected by FXMLLoader

    @FXML // fx:id="classe"
    private TableColumn<Aula, Integer> classe; // Value injected by FXMLLoader

    @FXML // fx:id="sezione"
    private TableColumn<Aula, String> sezione; // Value injected by FXMLLoader

    @FXML // fx:id="id"
    private TableColumn<Aula, Integer> id; // Value injected by FXMLLoader
    
    @FXML // fx:id="clumnNumeroAlunni"
    private TableColumn<Aula, Integer> alunni; // Value injected by FXMLLoader

    @FXML // fx:id="txtidclasse"
    private TextField txtidclasse; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtclasse"
    private TextField txtclasse; // Value injected by FXMLLoader

    @FXML // fx:id="txtsezione"
    private TextField txtsezione; // Value injected by FXMLLoader
    
    @FXML
    private TextArea txtMessage;



    @FXML
    void handelAggiungi(ActionEvent event) {
    	
    	txtMessage.setDisable(false);
    	Integer id = Integer.parseInt(txtidclasse.getText());
      	Integer classe = Integer.parseInt(txtclasse.getText());
    	String sezione = txtsezione.getText();
    	boolean ok = model.creaAula(id, classe, sezione);
    	
    	if (ok) {
    		txtMessage.setText(String.format("Aula %d%s è stata creata", classe,sezione));
    	}else {
    		txtMessage.setText("Aula non creata"); 
    	}
    }

    @FXML
    void handelElimina(ActionEvent event) {
    	txtMessage.setDisable(false);
    	Integer classe = Integer.parseInt(txtclasse.getText());
    	String sezione = txtsezione.getText();
    	boolean ok = false;
    	for (Aula aula : model.aule()) {
    		if (aula.getClasse() == classe && aula.getSezione().equalsIgnoreCase(sezione)) {
    			model.removeAula(aula.getAulaId());
    			txtMessage.setText(String.format("La classe %d%s è stata eliminata", aula.getClasse(),aula.getSezione()));
    			ok=true;
    			break;
    		}
    	}
    	
    	if (ok==false) {
    		txtMessage.setText(String.format("La classe %d%s non esiste", classe,sezione));
    		
    	}
    }


    @FXML
    void handelTest(ActionEvent event) {
    	collezione.clear();
   
    	
    	for (Aula aula : model.getnumeroAlunni()) {
    		collezione.add(aula);
    	}
    	
    	txtDisplay.setItems(collezione);
    	id.setCellValueFactory(
    			new PropertyValueFactory<Aula, Integer>("aulaId")//nome parametro della colonna
    			);
    	classe.setCellValueFactory(
    			new PropertyValueFactory<Aula, Integer>("classe")
    			);
    	sezione.setCellValueFactory(
    			new PropertyValueFactory<Aula, String>("sezione")
    			);
    	alunni.setCellValueFactory(
    			new PropertyValueFactory<Aula, Integer>("numero")
    			);

    }
 
    @FXML
    void handleModifica(ActionEvent event) {
    	txtMessage.setDisable(false);
    	Integer id = Integer.parseInt(txtidclasse.getText());
      	Integer classe = Integer.parseInt(txtclasse.getText());
    	String sezione = txtsezione.getText();
    	boolean ok = model.updateAula(id, classe, sezione);
    	
    	if (ok) {
    		txtMessage.setText(String.format("Aula %d%s è stata aggiornata", classe,sezione));
    	}else {
    		txtMessage.setText("Aula non aggiornata"); 
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtDisplay != null : "fx:id=\"txtDisplay\" was not injected: check your FXML file 'Disposizione.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'Disposizione.fxml'.";
        assert classe != null : "fx:id=\"classe\" was not injected: check your FXML file 'Disposizione.fxml'.";
        assert sezione != null : "fx:id=\"sezione\" was not injected: check your FXML file 'Disposizione.fxml'.";
   
        assert alunni != null : "fx:id=\"alunni\" was not injected: check your FXML file 'Disposizione.fxml'.";
        assert txtidclasse != null : "fx:id=\"txtidclasse\" was not injected: check your FXML file 'Disposizione.fxml'.";
        assert txtclasse != null : "fx:id=\"txtclasse\" was not injected: check your FXML file 'Disposizione.fxml'.";
        assert txtsezione != null : "fx:id=\"txtsezione\" was not injected: check your FXML file 'Disposizione.fxml'.";
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'Disposizione.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
}
