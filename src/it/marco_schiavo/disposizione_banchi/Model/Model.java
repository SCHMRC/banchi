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
	
	/**
	 * 
	 * @param id <- id della classe
	 * @return
	 */
	public List<Alunno> random(int id){

		ArrayList<Alunno> lista = new ArrayList<>();
		HashMap<Integer,Integer> random = new HashMap<>();
		
		int i = 1;
		for (Alunno alunno : DisposizioneDAO.alunniClasse(id)) {
			random.put(alunno.getId(),i++);
		}

		//implementato l'algoritmo del casuale
		boolean flag = false;
		int[] k = new int[random.size()];
		Random casual = new Random();
		int r = random.size();
		int t = casual.nextInt(r);
		for (int j=0;j<random.size();j++) {
			do {
				flag=false;
				//cerca il numero random all'interno del vettore
				for (int h=0;h<random.size();h++){
					//se c'è nel vettore oppure t è 0 dammi un'altro numero casuale e metti a true
					if(t==0||k[h]==t) {//
						flag=true;
						t = casual.nextInt(random.size()+1);//incrementa di 1 il numero massimo di valore da cercare
						break;
						}
					}
				//finchè il flag è true
				}while(flag);
			if (!flag) {
				k[j]=t;
				t=casual.nextInt(random.size()+1);
				}
			}
		i = 0;
		for (Alunno alunno : DisposizioneDAO.alunniClasse(id)) {
			random.put(alunno.getId(),k[i++]);
		}
		
		
		
		
		
		
		return lista;
		
	}

	
	
	
	

}
