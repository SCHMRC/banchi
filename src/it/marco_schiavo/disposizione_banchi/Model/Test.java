package it.marco_schiavo.disposizione_banchi.Model;
import java.util.Random;

import it.marco_schiavo.disposizione_banchi.Model.Alunno;


public class Test {
	
	public static void main(String[] args) {
		
//		Model model = new Model();
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
		
//		for (Alunno p : model.getAlunniClasse(2)) {
//		System.out.println(p.toString());
//		System.out.println("*****");
//	}
		
		
		int[] x= new int[20];
		Random random = new Random();
		boolean flag = false;
		
		int t = random.nextInt(10);
		
		for (int j=0;j<10;j++) {
			t=random.nextInt(10);
			do {
			for (int i=0;i<10;i++) {
				if(x[i]==t) {
					flag = true;
					break;
				}else flag=false;
			}}while (flag);
			if (!flag)
				x[j]=t;
			System.out.println(x[j]);
			
		}

		
	
		
		
		
		
		
		
		


		
	

	

			

		

		
		
		
		
	}

}
