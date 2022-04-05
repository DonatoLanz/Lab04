package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		/*
		 * 	Write here your test model
		 */

		for(Corso c : model.getTuttiICorsi()) {
			System.out.println(c.getNome());
		}
		
	Studente s = model.getStudenteByMatricola(146101);
		System.out.println(s.getNome());
	
		for(Corso c : model.getCorsiByMatricolaStudente(146101)) {
			System.out.println(c.getNome());
		}
	}

}
