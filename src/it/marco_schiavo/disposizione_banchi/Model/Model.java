package it.marco_schiavo.disposizione_banchi.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import it.marco_schiavo.disposizione_banchi.DAO.DisposizioneDAO;

public class Model {
	
	
	
	public Model() {
		super();
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
	
	public List<Alunno> random(int id){

		ArrayList<Alunno> lista = new ArrayList<>();
		HashMap<Integer,Integer> random = new HashMap<>();
		int i = 0;
		boolean flag = false;
		
		for (Alunno alunno : DisposizioneDAO.alunniClasse(id)) {
			random.put(alunno.getId(), i++);
		}
		//TODO: implementare l'algoritmo del casuale
		int[] k = new int[random.size()];
		Random casual = new Random(random.size());
		
		for (i=0;i<random.size();i++) {
			int r = casual.nextInt();
			
			do {
				for (int j=0;j<random.size();j++) {
					if(k[j]==r) {
						flag=true;
						r=casual.nextInt();
					}
				}
				if (!flag) {
					k[i] = r;
				}
			}while (flag || );

		}
		
		
		
		return lista;
		
	}

	
	
	
	

}
