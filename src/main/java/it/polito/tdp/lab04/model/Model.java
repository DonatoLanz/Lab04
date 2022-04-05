package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model() {
		this.corsoDao = new CorsoDAO();
		this.studenteDao = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi() {
		return corsoDao.getTuttiICorsi();
	}
	
	public Studente getStudenteByMatricola(int matricola) {
		return studenteDao.getStudenteByMatricola(matricola);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(String nomeC) {
		return corsoDao.getStudentiIscrittiAlCorso(nomeC);
	}
	
	public List<Corso> getCorsiByMatricolaStudente(int matricola) {
		return corsoDao.getCorsiByMatricolaStudente(matricola);
	}
	
	public Corso getCorso(String nomeC) {
		return corsoDao.getCorso(nomeC);
	}
	
	public boolean inscriviStudenteACorso(int matricola, String codins) {
		return corsoDao.inscriviStudenteACorso(matricola, codins);
	}
}
