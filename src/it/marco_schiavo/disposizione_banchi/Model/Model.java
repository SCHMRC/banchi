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
	
	private int[] serviceRandom(ArrayList<Alunno> listaOrdinata) {
		
		//implementato l'algoritmo del casuale
				boolean flag = false;
				int[] k;
				if (listaOrdinata.size()!=0) {
					k = new int[listaOrdinata.size()];}
				else return k = new int[0];
				Random casual = new Random();//mi restituisce un numero casuale da 0 a N compreso
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
								t = casual.nextInt(r);//dammi un'altro numero casuale
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
				return k;
		
		
	}
	
	private ArrayList<Alunno> serviceList(ArrayList<Alunno> listaOrdinata){
		
		int[] k = serviceRandom(listaOrdinata);


		ArrayList<Alunno> lista = new ArrayList<>();
		for (int i=0;i<listaOrdinata.size();i++)
			lista.add(listaOrdinata.get(k[i]));
		return lista;
		
		
	}
	/**
	 * 
	 * @param id <- id della classe da riordinare
	 * @return lista della classe casuale
	 */
	public List<Alunno> random(int id){

		ArrayList<Alunno> listaOrdinata = new ArrayList<>();
		
		listaOrdinata = DisposizioneDAO.alunniClasse(id);
		
		int[] k = serviceRandom(listaOrdinata);


		ArrayList<Alunno> lista = new ArrayList<>();
		for (int i=0;i<listaOrdinata.size();i++)
			lista.add(listaOrdinata.get(k[i]));
		return lista;
		
	}
	
	public HashMap<Alunno,Alunno> vincoli(int id){
		
		//implementare eventuali vincoli di ordinamento random
		ArrayList<Alunno> lista = DisposizioneDAO.alunniClasse(id);
		ArrayList<Alunno> tranquilli = new ArrayList<>();
		ArrayList<Alunno> vivaci = new ArrayList<>();
		ArrayList<Alunno> criminali = new ArrayList<>();
		
		for (Alunno alunno : lista) {
			switch (alunno.getComportamento()) {
				case "tranquillo": {
					tranquilli.add(alunno);
					break;
				}
				case "vivace": {
					vivaci.add(alunno);
					break;
				}
				case "criminale": {
					criminali.add(alunno);
					break;
				}
			}
		}
		
			ArrayList<Alunno> serviceV = serviceList(vivaci);
			ArrayList<Alunno> serviceT = serviceList(tranquilli);
			ArrayList<Alunno> serviceC = serviceList(criminali);
		

	    HashMap<Alunno,Alunno> mappa = new HashMap<>();
	    
	    do {
	    if (serviceV.size()!=0 && serviceT.size()!=0 && serviceC.size()!=0) {
	    	do {
	    	for (int i=0; ;i++) {
	    	mappa.put(serviceV.get(i),serviceT.get(i));
	    	mappa.put(serviceC.get(i),serviceT.get(i));}
	    	}while (serviceV.size()==0 || serviceT.size()==0 || serviceC.size()==0);
	    }
	    //TODO: implementare le condizioni delle liste
	    if (serviceV.size()!=0 && serviceT.size()!=0 && serviceC.size()==0) {
	    	boolean flag = false;
	    	do {
	    		flag = false;
		    	for (int i=0; ;i++) {
		    		try {
		    			mappa.put(serviceV.get(i),serviceT.get(i));
		    			serviceV.remove(i);
		    			serviceT.remove(i);
		    			i--;
		    		}catch (IndexOutOfBoundsException e) {
		    			flag = true;
		    			break;
		    		}

		    	}
		    	}while (!flag);
	    	
	    }
	    if (serviceV.size()!=0 && serviceT.size()==0 && serviceC.size()!=0) {
	    	boolean flag = false;
	    	do {
	    		flag = false;
		    	for (int i=0; ;i++) {
		    		try {
		    			mappa.put(serviceV.get(i),serviceC.get(i));
		    		}catch (IndexOutOfBoundsException e) {
		    			flag = true;
		    			break;
		    		}

		    	}
		    	}while (!flag);
	    }
	    if (serviceV.size()!=0 && serviceT.size()==0 && serviceC.size()==0) {
	    	boolean flag = false;
	    	do {
	    		flag = false;
		    	for (int i=0; ;i++) {
		    		try {
		    			mappa.put(serviceV.get(i),serviceV.get(i++));
		    		}catch (IndexOutOfBoundsException e) {
		    			flag = true;
		    			break;
		    		}

		    	}
		    	}while (!flag);
	    }
	    if (serviceV.size()==0 && serviceT.size()!=0 && serviceC.size()!=0) {
	    	boolean flag = false;
	    	do {
	    		flag = false;
		    	for (int i=0; ;i++) {
		    		try {
		    			mappa.put(serviceT.get(i),serviceC.get(i));
		    		}catch (IndexOutOfBoundsException e) {
		    			flag = true;
		    			break;
		    		}

		    	}
		    	}while (!flag);
	    	
	    }
	    }while (serviceV.size()==0 && serviceT.size()==0 && serviceC.size()==0);

	
		
		
		return mappa;
	}

	
	
	
	

}
