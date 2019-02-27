package it.marco_schiavo.disposizione_banchi.Model;

public class Classe {
	private String nome;
	private int valore;
	
	public Classe(String nome,int valore) {
		this.nome=nome;
		this.valore=valore;
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getValore() {
		return valore;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}

	@Override
	public String toString() {
		return getNome();
	}
	

}
