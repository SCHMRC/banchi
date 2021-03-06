package it.marco_schiavo.disposizione_banchi.Model;
import java.util.ArrayList;

public class Aula {
	private int aulaId;
	private int classe;
	private String sezione;
	private int numero;
	private ArrayList<Alunno> alunni;
	
	public Aula (int aulaId,int classe,String sezione) {
		this.aulaId = aulaId;
		this.setClasse(classe);
		this.sezione=sezione;
		this.alunni=new ArrayList<>();
	}
	
	public Aula (int aulaId,int classe,String sezione,int numero) {
		this.aulaId = aulaId;
		this.setClasse(classe);
		this.sezione=sezione;
		this.numero=numero;
	}

	public int getAulaId() {
		return aulaId;
	}

	public void setAulaId(int aulaId) {
		this.aulaId = aulaId;
	}

	public int getClasse() {
		return classe;
	}

	public void setClasse(int classe) {
		if (classe>=1||classe<=3)
			this.classe = classe;
	}

	public String getSezione() {
		return sezione;
	}

	public void setSezione(String sezione) {
		this.sezione = sezione;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public ArrayList<Alunno> getAlunni() {
		return alunni;
	}

	public void setAlunni(ArrayList<Alunno> alunni) {
		this.alunni = alunni;
	}

	@Override
	public String toString() {
		return aulaId + " classe=" + classe + " sezione=" + sezione + ", alunni=" + numero;
	}
	
	

}
