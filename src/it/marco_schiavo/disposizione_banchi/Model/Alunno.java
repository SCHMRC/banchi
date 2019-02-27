package it.marco_schiavo.disposizione_banchi.Model;


public class Alunno {
	private int id;
	private String nome;
	private String cognome;
	private String sesso;
	private String comportamento;
	private int idAulaFK;
	private int classe;
	private String sezione;

		
	
	public Alunno () {
		this.nome = "banco";
		this.cognome = "vuoto";
		this.comportamento="tranquillo";
		
	}
	
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
	
	public Alunno(int id,String nome,String cognome,String sesso,String comportamento,int classe,String sezione) {
		this.id=id;
		this.nome=nome;
		this.cognome=cognome;
		this.sesso=sesso;
		this.comportamento=comportamento;
		this.classe=classe;
		this.sezione=sezione;
	}
	
	public int getClasse() {
		return this.classe;
	}
	public String getSezione() {
		return this.sezione;
	}
	
	public void setClasse(int classe) {
		this.classe=classe;
	}
	
	public void setSezione(String sezione) {
		this.sezione=sezione;
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
		return id + " " + getCognome() + " " + getNome() + ", sesso=" + sesso + ", comportamento="
				+ comportamento;
	}
	
	
	

}
