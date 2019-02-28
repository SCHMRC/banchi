package it.marco_schiavo.disposizione_banchi.Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import it.marco_schiavo.disposizione_banchi.DAO.DisposizioneDAO;

public class Model {
	private Service service;

	public Model() {
		service = new Service();
	}
	/**
	 * aggiungi alunno 
	 * @param p oggetto di tipo alunno
	 * @return true operazione andata a buon fine
	 */
	public boolean inserisciAlunno(Alunno p) {
		return DisposizioneDAO.createAlunno(p);
	}
	/**
	 * richiedo la lista di tutti gli alunni presenti nel DB
	 * @return una lista <Alunno>
	 */
	public List<Alunno> getAll(){
		return DisposizioneDAO.getAll();
	}
	public List<Alunno> readAll(){
		return DisposizioneDAO.readAll();
	}
	
	/**
	 * aggiorna alunno
	 * @param k id della classe da inserire
	 * @param p oggetto di tipo alunno
	 * @return true operazione andata a buon fine
	 */
	public boolean aggiornaAlunno(int k,Alunno p) {
		return DisposizioneDAO.updateDB(k, p);
	}
	/**
	 * cancellazione alunno
	 * @param k id dell'alunno
	 * @return true eliminazione andata a buon fine
	 */
	public boolean rimuoviAlunno(int k) {
		return DisposizioneDAO.removeDB(k);
	}
	/**
	 * riassegna id degli alunni e li ordina in modo continuato
	 * @return true se il riordino è andato a buon fine
	 */
	public boolean riassegnaId() {
		return DisposizioneDAO.sortId();
	}
	/**
	 * crea una nuova aula
	 * @param id assegna un identificativo alla classe
	 * @param classe un intero da inserire tra 1 2 e 3
	 * @param sezione impostare la sezione
	 * @return
	 */
	public boolean creaAula(int id,int classe,String sezione) {
		return DisposizioneDAO.addAula(id,classe,sezione);
	}
	/**
	 * lista di tutte le aule del DB
	 * @return una lista di Aula
	 */
	public List<Aula> aule(){
		return DisposizioneDAO.aule();
	}
	/**
	 * rimuove l'aula
	 * @param id dell'aula da eliminare
	 * @return
	 */
	public boolean removeAula(int id) {
		return DisposizioneDAO.deleteAula(id);
	}
	/**
	 * aggiorna aula
	 * @param id aula da aggiornare
	 * @param classe aggiornamento nuova classe
	 * @param sezione aggiornamento nuova sezione
	 * @return
	 */
	public boolean updateAula(int id,int classe,String sezione) {
		return DisposizioneDAO.updateAula(id, classe, sezione);
	}
	/**
	 * richiedo gli alunni di una determinata classe al DB
	 * @param id <- identificatore della classe
	 * @return una lista di tipo Alunno
	 */
	public List<Alunno> getAlunniClasse(int id){
		return DisposizioneDAO.alunniClasse(id);
	}
	/**
	 * metodo che mi assegna i posti nell'aula che rispetta vincoli caratteriali
	 * @param i <- identificatore della classe
	 * @return
	 */
	public HashMap<Alunno, Alunno> vincoli(int i) {
		HashMap<Alunno, Alunno> mappa = service.vincoli(i);
		return mappa;
	}
	/**
	 * salva in file esterno
	 * @param mappa
	 * @param id
	 * @return
	 */
	public boolean salva(HashMap<Alunno,Alunno> mappa,int id) {
		return service.salva(mappa,id);
	}
	
	public List<Aula> getnumeroAlunni() {
		return DisposizioneDAO.getnumero_alunni();
	}
	
	public int getIdAula(int classe,String sezione) {
		int k=-1;
		ArrayList<Aula> lista = (ArrayList<Aula>)aule();
		for (Aula aula : lista) {
			if (aula.getClasse()==classe && aula.getSezione().equalsIgnoreCase(sezione))
				k=aula.getAulaId();
		}
		
		return k;
	
		
	}
	

	


}
