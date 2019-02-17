package it.marco_schiavo.disposizione_banchi;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	//creo una variabile statica di tipo AnchorPane in modo da appenderci altri layout(è un layout che consente di posizionare il contenuto a una distanza specifica dai lati)
	private static AnchorPane anchor;
	// la lista statica contiene tutte le pagine fxml in questo caso di tipo BorderPane
	private static ArrayList<BorderPane> listascene = new ArrayList<>();
	//Mi permette di tenere memoria della pagina inizialmente vale 0 che stò visualizzando in modo che,successivamente, io possa identificarla rimuoverla e sostituirla
	private static int numeroscena;

	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("anchor.fxml"));
			FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("main.fxml"));
			FXMLLoader loaderDisposizione = new FXMLLoader(getClass().getResource("Disposizione.fxml"));
			
			anchor = (AnchorPane)loader.load();
			BorderPane rootBorder = (BorderPane)loaderMain.load();
			BorderPane rootDisposizione = (BorderPane)loaderDisposizione.load();

			//inserisco le mie scene nella lista
			listascene.add(rootBorder);
			listascene.add(rootDisposizione);
			
			
			//aggiungo al mio master layout la scena che voglio visualizzare la prima volta che eseguo il programma
			anchor.getChildren().add(listascene.get(0));

			
			Scene scene = new Scene(anchor,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Questo metodo mi consente di settare la scena che voglio successivamente visualizzare
	 * all'interno di ogni controller della scena inserirò il numero del file FXML che voglio visualizzare
	 * 		1)Rimuove la scena corrente (con il numeroscena attuale)
	 * 		2)La sostituisce con il numeroscena che passo nel controller specifico
	 * 		3)Tiene memoria dell'indice della scena che sto visualizzando in modo che alla successiva chiamata io possa rimuoverlo
	 * @param i <- indice del della lista che voglio visualizzare
	 */
	public static void setNumeroscena(int i) {
		anchor.getChildren().remove(listascene.get(numeroscena));
		anchor.getChildren().add(listascene.get(i));
		numeroscena = i;
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
