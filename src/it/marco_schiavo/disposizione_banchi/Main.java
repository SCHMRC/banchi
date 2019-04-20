package it.marco_schiavo.disposizione_banchi;
	
import java.util.ArrayList;


import it.marco_schiavo.disposizione_banchi.Model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private static int indice;
	private static ArrayList<Stage> listaFinestra = new ArrayList<>();
	private static ArrayList<Scene> listaScene = new ArrayList<>();

	@Override
	public void start(Stage finestra) {
		
		try {
			Model model = new Model();

			FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("main.fxml"));
			FXMLLoader loaderDisposizione = new FXMLLoader(getClass().getResource("Disposizione.fxml"));
			FXMLLoader loaderAlunni = new FXMLLoader(getClass().getResource("GestioneAlunno.fxml"));
			FXMLLoader loaderRandom = new FXMLLoader(getClass().getResource("Random.fxml"));

			BorderPane rootBorder = (BorderPane)loaderMain.load();
			BorderPane rootDisposizione = (BorderPane)loaderDisposizione.load();
			BorderPane rootAlunni = (BorderPane)loaderAlunni.load();
			BorderPane rootRandom = (BorderPane)loaderRandom.load();

			DisposizioneController controller = loaderDisposizione.getController();
			controller.setModel(model);
			GestionAlunnoController alunnoController = loaderAlunni.getController();
			alunnoController.setModel(model);
			RandomController randomController = loaderRandom.getController();
			randomController.setModel(model);
			

			//inserisco le mie scene nella lista
			listaScene.add(new Scene(rootBorder));
			listaScene.add(new Scene(rootDisposizione));
			listaScene.add(new Scene(rootAlunni));
			listaScene.add(new Scene(rootRandom));

			Stage mainStage = new Stage();
			Stage firstStage = new Stage();
			Stage secondStage = new Stage();
			Stage thirdStage = new Stage();
			
			listaFinestra.add(mainStage);
			listaFinestra.add(firstStage);
			listaFinestra.add(secondStage);
			listaFinestra.add(thirdStage);
			
			
			
			
			finestra = listaFinestra.get(indice);
			finestra.setScene(listaScene.get(getIndice()));
			finestra.setTitle("Disposizione Alunni");
			finestra.setResizable(false);
			finestra.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void newStart() {
		Main main = new Main();
		main.start(listaFinestra.get(getIndice()));
	}
	
	public static void setIndice(int index) {
		indice = index;
	}
	
	public static int getIndice() {
		return indice;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
