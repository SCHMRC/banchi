package it.marco_schiavo.disposizione_banchi.Model;
import java.util.HashMap;
import java.util.List;
import it.marco_schiavo.disposizione_banchi.DAO.DisposizioneDAO;

public class Model {
	private Service service;

	public Model() {
		service = new Service();
	}
	
	public boolean inserisciAlunno(Alunno p) {
		return DisposizioneDAO.createAlunno(p);
	}
	
	public List<Alunno> getAll(){
		return DisposizioneDAO.getAll();
	}
	
	public boolean aggiornaAlunno(int k,Alunno p) {
		return DisposizioneDAO.updateDB(k, p);
	}
	
	public boolean rimuoviAlunno(int k) {
		return DisposizioneDAO.removeDB(k);
	}
	
	public boolean riassegnaId() {
		return DisposizioneDAO.sortId();
	}
	
	public boolean creaAula(int id,int classe,String sezione) {
		return DisposizioneDAO.addAula(id,classe,sezione);
	}
	
	public List<Aula> aule(){
		return DisposizioneDAO.aule();
	}
	
	public boolean removeAula(int id) {
		return DisposizioneDAO.deleteAula(id);
	}
	
	public boolean updateAula(int id,int classe,String sezione) {
		return DisposizioneDAO.updateAula(id, classe, sezione);
	}
	
	public List<Alunno> getAlunniClasse(int id){
		return DisposizioneDAO.alunniClasse(id);
	}

	public HashMap<Alunno, Alunno> vincoli(int i) {
		HashMap<Alunno, Alunno> mappa = service.vincoli(i);
		return mappa;
	}
	
	public boolean salva(HashMap<Alunno,Alunno> mappa,int id) {
		return service.salva(mappa,id);
	}
	


}
