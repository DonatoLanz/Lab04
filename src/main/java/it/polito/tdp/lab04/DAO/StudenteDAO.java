package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	//data una matricola,restituice uno studente
	
	public Studente getStudenteByMatricola(int matricola) {
		
		final String sql = "SELECT * "
				         + "FROM studente s "
				         + "WHERE matricola= ? ";

	    Studente s=null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				s = new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("CDS"));
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			conn.close();
			
			return s;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
			
	}
	
	
	
	
}
