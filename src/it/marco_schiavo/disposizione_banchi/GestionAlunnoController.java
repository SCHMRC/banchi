package it.marco_schiavo.disposizione_banchi;

/**
 * Sample Skeleton for 'GestioneAlunno.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import it.marco_schiavo.disposizione_banchi.DAO.DisposizioneDAO;
import it.marco_schiavo.disposizione_banchi.Model.Alunno;
import it.marco_schiavo.disposizione_banchi.Model.Classe;
import it.marco_schiavo.disposizione_banchi.Model.ClasseStringConverter;
import it.marco_schiavo.disposizione_banchi.Model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestionAlunnoController {
	private Model model;
	private ObservableList<Alunno> viewAlunno = FXCollections.observableArrayList();

	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="idtavola"
    private TableView<Alunno> idtavola; // Value injected by FXMLLoader

    @FXML // fx:id="idclm"
    private TableColumn<Alunno, Integer> idclm; // Value injected by FXMLLoader

    @FXML // fx:id="nomeclm"
    private TableColumn<Alunno, String> nomeclm; // Value injected by FXMLLoader

    @FXML // fx:id="cognomeclm"
    private TableColumn<Alunno, String> cognomeclm; // Value injected by FXMLLoader

    @FXML // fx:id="sessoclm"
    private TableColumn<Alunno, String> sessoclm; // Value injected by FXMLLoader

    @FXML // fx:id="comportamentoclm"
    private TableColumn<Alunno, String> comportamentoclm; // Value injected by FXMLLoader

    @FXML // fx:id="classeclm"
    private TableColumn<Alunno, Integer> classeclm; // Value injected by FXMLLoader

    @FXML // fx:id="sezioneclm"
    private TableColumn<Alunno, String> sezioneclm; // Value injected by FXMLLoader

    @FXML // fx:id="nometxt"
    private TextField nometxt; // Value injected by FXMLLoader

    @FXML // fx:id="cognometxt"
    private TextField cognometxt; // Value injected by FXMLLoader

    @FXML // fx:id="idtxt"
    private TextField idtxt; // Value injected by FXMLLoader

    @FXML // fx:id="m_sex"
    private RadioButton m_sex; // Value injected by FXMLLoader

    @FXML // fx:id="sesso"
    private ToggleGroup sesso; // Value injected by FXMLLoader

    @FXML // fx:id="f_sex"
    private RadioButton f_sex; // Value injected by FXMLLoader

    @FXML // fx:id="tranquillo"
    private RadioButton tranquillo; // Value injected by FXMLLoader

    @FXML // fx:id="comportamento"
    private ToggleGroup comportamento; // Value injected by FXMLLoader

    @FXML // fx:id="vivace"
    private RadioButton vivace; // Value injected by FXMLLoader

    @FXML // fx:id="criminale"
    private RadioButton criminale; // Value injected by FXMLLoader

    @FXML // fx:id="choicebox"
    private ChoiceBox<Classe> choicebox; // Value injected by FXMLLoader

    @FXML // fx:id="sezionetxt"
    private TextField sezionetxt; // Value injected by FXMLLoader

    @FXML
    void btnAggiungi(ActionEvent event) {



    	String sezione = sezionetxt.getText();
    	int classe = choicebox.getSelectionModel().getSelectedItem().getValore();


    	RadioButton sex = (RadioButton)sesso.getSelectedToggle();
    	String s = sex.getText();
    	switch (s) {
    	
    	case "Maschio" : s="M";
    			break;
    	case "Femmina" : s="F";
    			break;
    	}
    	
    	RadioButton comp = (RadioButton)comportamento.getSelectedToggle();
    	String c = comp.getText();
    	
    	if (c.equalsIgnoreCase("Molto Vivace"))
    		c="criminale";
    	
    	int id_aula = model.getIdAula(classe, sezione);
    	Alunno p = new Alunno (nometxt.getText(),cognometxt.getText(),s,c,id_aula);
    	model.inserisciAlunno(p);

    }

    @FXML
    void btnCerca(ActionEvent event) {
    	viewAlunno.clear();

    	
    	if (!sezionetxt.getText().equals("")) {
	    	int classe = choicebox.getSelectionModel().getSelectedItem().getValore();
	    	int id_classe = DisposizioneDAO.classeid(classe, sezionetxt.getText());
	    	for (Alunno alunno : model.getAlunniClasse(id_classe)) {
	    		viewAlunno.add(alunno);
	    	}
	    	
	    	idtavola.setItems(viewAlunno);
	    	
	    	idclm.setCellValueFactory(
	    			new PropertyValueFactory<Alunno, Integer>("id")//nome parametro della colonna
	    			);
	    	nomeclm.setCellValueFactory(
	    			new PropertyValueFactory<Alunno, String>("nome")//nome parametro della colonna
	    			);
	    	cognomeclm.setCellValueFactory(
	    			new PropertyValueFactory<Alunno, String>("cognome")//nome parametro della colonna
	    			);
	    	sessoclm.setCellValueFactory(
	    			new PropertyValueFactory<Alunno, String>("sesso")//nome parametro della colonna
	    			);
	    	comportamentoclm.setCellValueFactory(
	    			new PropertyValueFactory<Alunno, String>("comportamento")//nome parametro della colonna
	    			);
	    	classeclm.setCellValueFactory(
	    			new PropertyValueFactory<Alunno, Integer>("classe")//nome parametro della colonna
	    			);
	    	sezioneclm.setCellValueFactory(
	    			new PropertyValueFactory<Alunno, String>("sezione")//nome parametro della colonna
	    			);
    	} else {
	    	for (Alunno alunno : model.readAll()) {
	    		viewAlunno.add(alunno);
	    	}
	    	
	    	idtavola.setItems(viewAlunno);
	    	
	    	idclm.setCellValueFactory(
	    			new PropertyValueFactory<Alunno, Integer>("id")//nome parametro della colonna
	    			);
	    	nomeclm.setCellValueFactory(
	    			new PropertyValueFactory<Alunno, String>("nome")//nome parametro della colonna
	    			);
	    	cognomeclm.setCellValueFactory(
	    			new PropertyValueFactory<Alunno, String>("cognome")//nome parametro della colonna
	    			);
	    	sessoclm.setCellValueFactory(
	    			new PropertyValueFactory<Alunno, String>("sesso")//nome parametro della colonna
	    			);
	    	comportamentoclm.setCellValueFactory(
	    			new PropertyValueFactory<Alunno, String>("comportamento")//nome parametro della colonna
	    			);
	    	classeclm.setCellValueFactory(
	    			new PropertyValueFactory<Alunno, Integer>("classe")//nome parametro della colonna
	    			);
	    	sezioneclm.setCellValueFactory(
	    			new PropertyValueFactory<Alunno, String>("sezione")//nome parametro della colonna
	    			);
    	}
    	
    	
    

    }

    @FXML
    void btnElimina(ActionEvent event) {
    	
    	int k = Integer.parseInt(idtxt.getText()) ;
    	model.rimuoviAlunno(k);
    	

    }

    @FXML
    void btnModifica(ActionEvent event) {
    	int k = Integer.parseInt(idtxt.getText());


    	String sezione = sezionetxt.getText();
    	int classe = choicebox.getSelectionModel().getSelectedItem().getValore();


    	RadioButton sex = (RadioButton)sesso.getSelectedToggle();
    	String s = sex.getText();
    	switch (s) {
    	
    	case "Maschio" : s="M";
    			break;
    	case "Femmina" : s="F";
    			break;
    	}
    	
    	RadioButton comp = (RadioButton)comportamento.getSelectedToggle();
    	String c = comp.getText();
    	
    	if (c.equalsIgnoreCase("Molto Vivace"))
    		c="criminale";
    	
    	int id_aula = model.getIdAula(classe, sezione);
    	Alunno p = new Alunno (nometxt.getText(),cognometxt.getText(),s,c,id_aula);
    	
    	model.aggiornaAlunno(k, p);

    }

    @FXML
    void btnRiassegnaId(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	Classe def = new Classe("prima",1);
    	choicebox.getItems().addAll(
    			def ,
    			new Classe("seconda",2),
    			new Classe("terza",3)
    			);
    	choicebox.setValue (def);
    	choicebox.setConverter(new ClasseStringConverter());
    	
    	
        assert idclm != null : "fx:id=\"idclm\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert nomeclm != null : "fx:id=\"nomeclm\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert cognomeclm != null : "fx:id=\"cognomeclm\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert sessoclm != null : "fx:id=\"sessoclm\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert comportamentoclm != null : "fx:id=\"comportamentoclm\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert classeclm != null : "fx:id=\"classeclm\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert sezioneclm != null : "fx:id=\"sezioneclm\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert nometxt != null : "fx:id=\"nometxt\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert cognometxt != null : "fx:id=\"cognometxt\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert idtxt != null : "fx:id=\"idtxt\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert m_sex != null : "fx:id=\"m_sex\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert sesso != null : "fx:id=\"sesso\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert f_sex != null : "fx:id=\"f_sex\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert tranquillo != null : "fx:id=\"tranquillo\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert comportamento != null : "fx:id=\"comportamento\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert vivace != null : "fx:id=\"vivace\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert criminale != null : "fx:id=\"criminale\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert choicebox != null : "fx:id=\"choicebox\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";
        assert sezionetxt != null : "fx:id=\"sezionetxt\" was not injected: check your FXML file 'GestioneAlunno.fxml'.";

    }
    public void setModel(Model model) {
    	this.model=model;
    }
    
}
