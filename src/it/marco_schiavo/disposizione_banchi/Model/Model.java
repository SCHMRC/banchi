package it.marco_schiavo.disposizione_banchi.Model;

import java.util.ArrayList;
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
	 * @param id <- id della classe da riordinare
	 * @return
	 */
	public List<Alunno> random(int id){

		ArrayList<Alunno> listaOrdinata = new ArrayList<>();
		
		listaOrdinata = DisposizioneDAO.alunniClasse(id);
		

		//implementato l'algoritmo del casuale
		boolean flag = false;
		int[] k = new int[listaOrdinata.size()];	
		Random casual = new Random();
		int r = listaOrdinata.size();
		int t = casual.nextInt(r);
		int cont=0;
		for (int j=0;j<r;j++) {
			do {
				flag=false;
				//cerca il numero random all'interno del vettore
				for (int h=0;h<r;h++){
					//se il valore casuale è = a 0
					if (t==0) {
						if (cont==0) {
							flag=false;
							cont++;
							break;
						}
					}
					//se c'è nel vettore oppure t è 0 dammi un'altro numero casuale e metti a true
					if(k[h]==t) {//
						flag=true;
						t = casual.nextInt(r);//incrementa di 1 il numero massimo di valore da cercare
						break;
						}
					
					}
				//finchè il flag è true
				}while(flag);
			if (!flag) {
				k[j]=t;
				t=casual.nextInt(r);
				}
			}
		ArrayList<Alunno> lista = new ArrayList<>();
		for (int i=0;i<r;i++)
			lista.add(listaOrdinata.get(k[i]));
		
		return lista;
		
	}

	
	
	
	

}
