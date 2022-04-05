package it.polito.tdp.lab04.DAO;

import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		cdao.getTuttiICorsi();
		
		StudenteDAO sDao = new StudenteDAO();
		Studente s = sDao.getStudenteByMatricola(146101);
		System.out.println(s.getNome());
		
		CorsoDAO c = new CorsoDAO();
		List<Corso> corsi = c.getCorsiByMatricolaStudente(146101);
		
		for(Corso cc : corsi) {
			System.out.println(cc.getNome());
		}
	}

}
