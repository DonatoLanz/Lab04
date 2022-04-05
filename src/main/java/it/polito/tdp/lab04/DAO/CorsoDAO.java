package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				//System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);
                
				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
				
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String nomeC) {
		
		final String sql = " SELECT * "
				+ "FROM corso "
				+ "WHERE corso.nome=? ";
		
		Corso c = null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nomeC);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");
				
				c = new Corso(codins, crediti, nome, pd);
				
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			conn.close();
			
			return c;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(String nomeC) {
		
		final String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS "
				+ "FROM studente s, iscrizione i, corso c "
				+ "WHERE s.matricola=i.matricola AND i.codins=c.codins AND c.nome= ? ";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nomeC);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("CDS");
				
				Studente s = new Studente(matricola, cognome, nome, cds);
				studenti.add(s);
				
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			conn.close();
			
			return studenti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
		
	}
	
	
	
	public List<Corso> getCorsiByMatricolaStudente(int matricola) {
		
		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd " 
				+"FROM iscrizione i, corso c " 
				+"WHERE i.codins=c.codins AND i.matricola=? ";
	   
		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
    
				String codins = rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");
				
				Corso c = new Corso(codins, crediti, nome, pd);
				corsi.add(c);
				
				
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		
	}
	
	

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(int matricola, String codins) {
		
		boolean ex=false;
		
			final String sql = "INSERT INTO iscrizione "
					         + "VALUES (?,?) ";
				            	
			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, matricola);
				st.setString(2, codins);
			    ex = st.execute();
				
				conn.close();
				return ex;
				
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db", e);
			}
			
		
		// ritorna true se l'iscrizione e' avvenuta con successo
		
	}

}
