package it.marco_schiavo.disposizione_banchi.Model;

import it.marco_schiavo.disposizione_banchi.Model.Alunno;


public class Test {
	
	public static void main(String[] args) {
		
		Model model = new Model();
//		Alunno j = new Alunno("Cocco","Pero","M","vivace",2);
//		System.out.println(model.aggiornaAlunno(5, j));
//		System.out.println(model.inserisciAlunno(j));
//		System.out.println(model.rimuoviAlunno(7));
//		System.out.println(model.riassegnaId());
		
//		for (Alunno p : model.getAll()) {
//			System.out.println(p.toString());
//			System.out.println("*****");
//		}
//		System.out.println(model.creaAula(7, 3, "B"));
//		System.out.println(model.removeAula(7));
		
//		System.out.println(model.updateAula(6, 3, "B"));
		
		for (Alunno p : model.getAlunniClasse(2)) {
		System.out.println(p.toString());
		System.out.println("*****");
	}
		System.out.println("--------");
		for (Alunno p : model.random(2)) {
		System.out.println(p.toString());
		System.out.println("*****");
	}

		
		


	}

}
