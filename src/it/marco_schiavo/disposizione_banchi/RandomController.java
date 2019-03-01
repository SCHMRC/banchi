/**
 * Sample Skeleton for 'Random.fxml' Controller Class
 */

package it.marco_schiavo.disposizione_banchi;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import it.marco_schiavo.disposizione_banchi.Model.Alunno;
import it.marco_schiavo.disposizione_banchi.Model.Classe;
import it.marco_schiavo.disposizione_banchi.Model.ClasseStringConverter;
import it.marco_schiavo.disposizione_banchi.Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RandomController {
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtdisplay"
    private TextArea txtdisplay; // Value injected by FXMLLoader

    @FXML // fx:id="txtrighe"
    private TextField txtrighe; // Value injected by FXMLLoader

    @FXML // fx:id="txtclm"
    private TextField txtclm; // Value injected by FXMLLoader

    @FXML // fx:id="checkvincoli"
    private CheckBox checkvincoli; // Value injected by FXMLLoader

    @FXML // fx:id="btncheck"
    private ChoiceBox<Classe> btncheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtsezione"
    private TextField txtsezione; // Value injected by FXMLLoader
    

    @FXML
    void handlecrea(ActionEvent event) {
    	txtdisplay.setEditable(false);
    	txtdisplay.clear();
    	
    	boolean check = checkvincoli.isSelected();
    	int classe = btncheck.getSelectionModel().getSelectedItem().getValore();
		String sezione = txtsezione.getText();
		int i = model.getIdAula(classe, sezione);
		HashMap<Alunno,Alunno> mappa = new HashMap<>();
		ArrayList<Alunno> lista = new ArrayList<>();
		
		
		
    	if (check==true) {
    		model.vincoli(i).clear();
    		mappa = model.vincoli(i);
    		for (Entry<Alunno,Alunno> entry : mappa.entrySet()) {
    			txtdisplay.appendText(entry.getValue().getNome() + " " + entry.getValue().getCognome()+ "\n");
    			txtdisplay.appendText(entry.getKey().getNome() + " " + entry.getKey().getCognome()+ "\n");
    			txtdisplay.appendText("**********************\n");
    		}
    	}else {
    		model.random_senza_vincoli(i).clear();
    		lista = (ArrayList<Alunno>)model.random_senza_vincoli(i);
    		for (Alunno alunno : lista) {
    			txtdisplay.appendText(alunno.getNome() + " " + alunno.getCognome()+ "\n");
    			txtdisplay.appendText(alunno.getNome() + " " + alunno.getCognome()+ "\n");
    			txtdisplay.appendText("**********************\n");
    		}
    		
    	}

    }

    @FXML
    void handlesalva(ActionEvent event) {
    	ArrayList<Alunno> mappa_ordinata = new ArrayList<>();
    	int classe =  btncheck.getSelectionModel().getSelectedItem().getValore();
    	String sezione = txtsezione.getText();
    	
    	String display = txtdisplay.getText();
    	
    	String[] diviso = display.split("\n");
    	
    	for (int i=0;i<diviso.length;i++) {
    		if (!diviso[i].equalsIgnoreCase("**********************")) {
    			String[] nome_cognome_diviso = diviso[i].split(" ");
    			for (int k=0;k<1;k++) {
    				String nome = nome_cognome_diviso[k];
    				String cognome = nome_cognome_diviso[k+1];
    				Alunno key = model.ricrea_alunno(classe, sezione, nome, cognome);
    				mappa_ordinata.add(key);
    			}
    		}
    	}

    	int id = model.getIdAula(classe, sezione);
    	model.salva_lista(mappa_ordinata, id);

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	txtdisplay.setEditable(false);
    	Classe prima = new Classe("prima",1);
    	btncheck.setValue(prima);
    	btncheck.getItems().addAll(
    			prima ,
    			new Classe("seconda",2),
    			new Classe("terza",3)
    			);
    	btncheck.setConverter(new ClasseStringConverter());
        assert txtdisplay != null : "fx:id=\"txtdisplay\" was not injected: check your FXML file 'Random.fxml'.";
        assert txtrighe != null : "fx:id=\"txtrighe\" was not injected: check your FXML file 'Random.fxml'.";
        assert txtclm != null : "fx:id=\"txtclm\" was not injected: check your FXML file 'Random.fxml'.";
        assert checkvincoli != null : "fx:id=\"checkvincoli\" was not injected: check your FXML file 'Random.fxml'.";
        assert btncheck != null : "fx:id=\"btncheck\" was not injected: check your FXML file 'Random.fxml'.";
        assert txtsezione != null : "fx:id=\"txtsezione\" was not injected: check your FXML file 'Random.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model=model;
    }
}
