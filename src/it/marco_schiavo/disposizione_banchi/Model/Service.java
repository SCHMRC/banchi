package it.marco_schiavo.disposizione_banchi.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

import it.marco_schiavo.disposizione_banchi.DAO.DisposizioneDAO;

public class Service {
	private HashMap<Alunno,Alunno> mappa2;
	private String nomefile;
	private File file;


	
	public Service() {
		this.mappa2 = new HashMap<>();
	}
	
	public String getNomefile(int id) {
		return DisposizioneDAO.classe_sezione(id);
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
	
	/**
	 * Associa alle chiavi  casuali un alunno
	 * mi restituisce una lista random senza vincoli
	 * @param listaOrdinata
	 * @return ArrayList<Alunno>
	 */
	
	private ArrayList<Alunno> serviceList(ArrayList<Alunno> listaOrdinata){
		
		int[] k = serviceRandom(listaOrdinata);


		ArrayList<Alunno> lista = new ArrayList<>();
		for (int i=0;i<listaOrdinata.size();i++)
			lista.add(listaOrdinata.get(k[i]));
		return lista;
		
		
	}
	/**
	 * @param id <- id della classe da riordinare
	 * @return List<Alunno> lista della classe casuale
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
	
	/**
	 * crea una mappa contenente una chiave e un valore che soddisfa un vincolo sul comportamento
	 *per le classi dispari verrà assegnato un banco vuoto ad un alunno
	 * @param id ->id della classe 
	 * @return -> mappa <Alunno,Alunno> 
	 */
	
	
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
	    mappa = ricorsione(serviceV,serviceT,serviceC);
	    
	    //implementare controllo lista non ripetuta
	    ArrayList<Integer> controllo = new ArrayList<>();
	    for (Entry<Alunno,Alunno> entry : mappa.entrySet()) {
	    	controllo.add(entry.getKey().getId());
	    	controllo.add(entry.getValue().getId());
	    }
	    //risolvere il problema sulla verifa dell'eistenza del file
	    file = new File(getNomefile(id)+".txt");
	    if (file.exists()) {
	    	try {
	    		ArrayList<Integer> listafile = new ArrayList<>();
				FileReader filer = new FileReader(file);
				BufferedReader filebr = new BufferedReader(filer);
				String riga;
				String divisione = "************";
				riga = filebr.readLine();
				do {
				while (!riga.equalsIgnoreCase(divisione)) {
					String[] numerostr = riga.split(" ");
					int x = Integer.parseInt(numerostr[0]);
					listafile.add(x);
					riga = filebr.readLine();
				};
				
				if (controllo.equals(listafile)) {
					vincoli(id);
				}
				listafile.clear();
				}while ((riga = filebr.readLine()) != null);
				filebr.close();
				filer.close();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    }
	    
	    
	    
	    

		return mappa;
	}
	
	/**
	 * inserisce il valore che ho ottenuto come risultato dal metodo ricorsione() 
	 * all'interno della variabile d'istanza mappa2
	 * @param mappa risultato dell'elaborazione ottenuto da ricorsione()
	 */
	public void insertRisultMappa(HashMap<Alunno,Alunno> mappa){
		for (Entry<Alunno, Alunno> entry : mappa.entrySet()) {
		    mappa2.put(entry.getKey(), entry.getValue());
		  }
	}
	
	/**
	 * metodo ricorsivo che mi restituisce ogni singola combinazione del calcolo combinatorio
	 * @param serviceV <- lista alunno vivace
	 * @param serviceT <- lista alunno tranquillo
	 * @param serviceC <- lista alunno criminale
	 * @return mi restituisce un singolo elemento di una mappa che verrà inserito nella variabile d'istanza mappa2 con il metodo
	 * insertRisultMappa()
	 */
	public HashMap<Alunno,Alunno> ricorsione(ArrayList<Alunno> serviceV,ArrayList<Alunno> serviceT,ArrayList<Alunno> serviceC){
		
		Alunno vuoto = new Alunno();
		HashMap<Alunno,Alunno> mappa = new HashMap<>();
		int i = 0;
	
		
		if (serviceV.size()==0 && serviceT.size()==0 && serviceC.size()==0) {
			return mappa2;		
		}
		
		/*-------*/
		
		if (serviceV.size()==0)
			try {
				if (serviceC.size()==0) {
					mappa.put(serviceT.get(i),serviceT.get(i+1));
					serviceT.remove(i);
					serviceT.remove(i);
				}
				else if (serviceT.size()==0) {
					mappa.put(serviceC.get(i),serviceC.get(i+1));
					serviceC.remove(i);
					serviceC.remove(i);
				}else {mappa.put(serviceT.get(i),serviceC.get(i));
				serviceT.remove(i);
    			serviceC.remove(i);}
    		}catch (IndexOutOfBoundsException e) {
    			if (serviceT.size()==1) {
    			mappa.put(serviceT.get(i),vuoto);
    			serviceT.remove(i);
    			}
    			else if (serviceC.size()==1) {
        		mappa.put(serviceC.get(i),vuoto);
    			serviceC.remove(i);
    			}
    			
    			insertRisultMappa(mappa);
    			return ricorsione(serviceV,serviceT,serviceC);
    		}
		
		else if (serviceT.size()==0) {
			try {
				if (serviceC.size()==0) {
					mappa.put(serviceV.get(i),serviceV.get(i+1));
					serviceV.remove(i);
					serviceV.remove(i);
				}
				else if (serviceV.size()==0) {
					mappa.put(serviceC.get(i),serviceC.get(i+1));
					serviceC.remove(i);
					serviceC.remove(i);
				}else 	{mappa.put(serviceC.get(i),serviceV.get(i));
    			serviceC.remove(i);
    			serviceV.remove(i);}
    		}catch (IndexOutOfBoundsException e) {
    			if (serviceV.size()==1) {
        			mappa.put(serviceV.get(i),vuoto);
        			serviceV.remove(i);
    			}
        			else if (serviceC.size()==1) {
            		mappa.put(serviceC.get(i),vuoto);
            		serviceC.remove(i);
        			}
        			
        			insertRisultMappa(mappa);
        			return ricorsione(serviceV,serviceT,serviceC);
    			}
			}
		
		else if (serviceC.size()==0) {
			try {
				if (serviceT.size()==0) {
					mappa.put(serviceV.get(i),serviceV.get(i+1));
					serviceV.remove(i);
					serviceV.remove(i);
				}
				else if (serviceV.size()==0) {
					mappa.put(serviceT.get(i),serviceT.get(i+1));
					serviceT.remove(i);
					serviceT.remove(i);
				}	
				else {mappa.put(serviceT.get(i),serviceV.get(i));
    			serviceT.remove(i);
    			serviceV.remove(i);}
    		}catch (IndexOutOfBoundsException e) {
    			if (serviceV.size()==1) {
        			mappa.put(serviceV.get(i),vuoto);
        			serviceV.remove(i);
    			}
        			else if (serviceT.size()==1) {
            		mappa.put(serviceT.get(i),vuoto);
            		serviceT.remove(i);
        			}
        			
        			insertRisultMappa(mappa);
        			return ricorsione(serviceV,serviceT,serviceC);
    			}
			}
		
		else {mappa.put(serviceV.get(i),serviceT.get(i));
		      mappa.put(serviceC.get(i),serviceT.get(i+1));
		      serviceV.remove(i);
		      serviceC.remove(i);
		      serviceT.remove(i);
		      serviceT.remove(i);
		}
		
		insertRisultMappa(mappa);
		
		return ricorsione(serviceV,serviceT,serviceC);
	}

	/**
	 * 
	 * @param mappa
	 * @param id
	 * @return
	 */
	public boolean salva(HashMap<Alunno,Alunno> mappa,int id) {
		

		ArrayList<String> lista = new ArrayList<>();
		boolean ok=true;
		for (Entry<Alunno, Alunno> entry : mappa.entrySet()) {
			String key = entry.getKey().toString();
			lista.add(key);
			String value = entry.getValue().toString();
			lista.add(value);
		}
		String divisione = "************";
		lista.add(divisione);
		try {
			File file = new File(getNomefile(id)+".txt");
			if (!file.exists())
				file.createNewFile();
			FileReader filer = new FileReader(file);
			BufferedReader filebr = new BufferedReader(filer);
			String stringa;
			while ((stringa= filebr.readLine()) != null)
				lista.add(stringa);
			
			
			
			filebr.close();
			filer.close();
			
			FileWriter filew = new FileWriter(file);
			BufferedWriter filebw = new BufferedWriter(filew);
			
			for (String riga : lista) {
				filebw.write(riga + "\n");
			}
			
			filebw.close();
			filew.close();
		} catch (IOException e) {
			return ok=false;
		}
		
		return ok;
		
		
	}
	
	public boolean salva_lista(ArrayList<Alunno> lista_alunni,int id) {

		boolean ok=true;
		ArrayList<String> lista = new ArrayList<>();
		for (Alunno alunno : lista_alunni) {
			lista.add(alunno.toString());
		}
		String divisione = "************";
		lista.add(divisione);
		
		try {
			File file = new File(getNomefile(id)+".txt");
			if (!file.exists())
				file.createNewFile();
			
			FileReader filer = new FileReader(file);
			BufferedReader filebr = new BufferedReader(filer);
			String stringa;
			while ((stringa= filebr.readLine()) != null)
				lista.add(stringa);
			
			
			
			filebr.close();
			filer.close();
			
			FileWriter filew = new FileWriter(file);
			BufferedWriter filebw = new BufferedWriter(filew);
			
			for (String riga : lista) {
				filebw.write(riga + "\n");
			}
			
			filebw.close();
			filew.close();
		} catch (IOException e) {
			return ok=false;
		}
		
		return ok;
		
		
	}
	
	public String setNome_file(int id) {
		return getNomefile(id);
	}
	
	public String getNomeFile() {
		return nomefile;
	}
}
