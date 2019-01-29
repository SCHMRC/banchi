package it.marco_schiavo.disposizione_banchi.Model;
import java.util.List;
import java.util.ArrayList;


public class Alunno {
	private int id;
	private String nome;
	private String cognome;
	private String sesso;
	private String comportamento;
	private int idAulaFK;
	private List<Alunno> amici;
	private List<Alunno> nemici;
		
	
	public Alunno(int id,String nome, String cognome, String sesso, String comportamento,int idAulaFK) {
		this.id=id;
		this.nome=nome;
		this.cognome=cognome;
		this.sesso=sesso;
		this.comportamento=comportamento;
		this.idAulaFK=idAulaFK;
	}
	
	public Alunno(String nome, String cognome, String sesso, String comportamento,int idAulaFK) {
		this.nome=nome;
		this.cognome=cognome;
		this.sesso=sesso;
		this.comportamento=comportamento;
		this.idAulaFK=idAulaFK;
	}
	


	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getCognome() {
		return cognome;
	}




	public void setCognome(String cognome) {
		this.cognome = cognome;
	}




	public String getSesso() {
		return sesso;
	}




	public void setSesso(String sesso) {
		this.sesso = sesso;
	}




	public String getComportamento() {
		return comportamento;
	}




	public void setComportamento(String comportamento) {
		this.comportamento = comportamento;
	}
	
	




	public int getIdAulaFK() {
		return idAulaFK;
	}




	public void setIdAulaFK(int idAulaFK) {
		this.idAulaFK = idAulaFK;
	}




	@Override
	public String toString() {
		return "Alunno [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", sesso=" + sesso + ", comportamento="
				+ comportamento + ", codice aula=" + idAulaFK + "]";
	}









//	public boolean addAmici(Alunno p) {
//		boolean amico=true;
//		
//		for(Alunno k : amici) {
//			if(k.getId()==p.getId()) {
//				amico=false;
//				return amico;
//			}
//		}
//		amici.add(p);
//		
//		return amico;
//		
//	}
//	
//	public boolean addNemici(Alunno p) {
//	boolean nemico=true;
//			
//			for(Alunno k : nemici) {
//				if(k.getId()==p.getId()) {
//					nemico=false;
//					return nemico;
//				}
//			}
//			nemici.add(p);
//			
//			return nemico;
//			
//	}
	
	
	
	

}
