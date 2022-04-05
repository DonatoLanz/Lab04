package it.polito.tdp.lab04;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbCorsi;

    @FXML
    private TextField txtCogn;

    @FXML
    private TextField txtMatr;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void doCerca(ActionEvent event) {

    	String matricola = txtMatr.getText();
    	Studente s = this.model.getStudenteByMatricola(Integer.parseInt(matricola));
    	
    	if(s != null) {
    		List<Corso> listaCorsiSt = this.model.getCorsiByMatricolaStudente(Integer.parseInt(matricola));
    		if(listaCorsiSt.contains(this.model.getCorso(cmbCorsi.getValue()))==true) {
    			txtRisultato.setText("Lo studente è iscritto a questo corso \n");
    		}
    		else {
    			txtRisultato.setText("Lo studente non è iscritto a questo corso \n");
    		}
    	}
    	else {
    		txtRisultato.setText("Matricola non valida!");
    	}
    }
    
    @FXML
    void doCercaCorsi(ActionEvent event) {

    	String matricola = txtMatr.getText();
    	
    	Studente s = this.model.getStudenteByMatricola(Integer.parseInt(matricola));
    	
    	if(s != null) {
    		for(Corso c : this.model.getCorsiByMatricolaStudente(Integer.parseInt(matricola))) {
    			txtRisultato.appendText(c.getCodins()+" "+c.getCrediti()+" "+c.getNome()+" "+c.getPd()+"\n");
    		}
    	}
    	else {
    		txtRisultato.setText("Matricola non valida");
    	}
    	
    	
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {

    	String nomeCorso = cmbCorsi.getValue();
    	
    	if(nomeCorso.equals(" ")==false) {
    	List<Studente> listaSt = this.model.getStudentiIscrittiAlCorso(nomeCorso);
    	for(Studente s : listaSt) {
    		txtRisultato.appendText(""+s.getMatricola()+" "+s.getNome()+" "+s.getCognome()+" "+s.getCDS()+"\n");
    	}
       }
    	else {
    		txtRisultato.setText("Seleziona un corso");
    	}
    	
    }

    @FXML
    void doCompletamento(ActionEvent event) {

    	Studente s = this.model.getStudenteByMatricola(Integer.parseInt(txtMatr.getText()));
    	if(s!=null) {
    	txtNome.setText(s.getNome());
    	txtCogn.setText(s.getCognome());
    	}else {
    		txtRisultato.setText("Inserisci Matricola Valida \n");
    	}
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    	int matricola;
    	Corso c = this.model.getCorso(cmbCorsi.getValue());
    	
    	try {
    	 matricola = Integer.parseInt(txtMatr.getText());
    	} catch(NumberFormatException nfe) {
    		//nfe.printStackTrace();
    		txtRisultato.setText("Inserisci una matricola valida! \n");
    		return;
    	}
    	
    	if(txtMatr.getText().length()!=6) {
    		txtRisultato.setText("Inserisci una matricola valida! \n");
    		return;
    	}
    
    	Studente s = this.model.getStudenteByMatricola(matricola);
    	List<Corso> listaCorsiSt = this.model.getCorsiByMatricolaStudente(matricola); 
    	if(s!=null && cmbCorsi.getValue().compareTo(" ")!=0) {
    		if(listaCorsiSt.contains(c)==false) {
    			this.model.inscriviStudenteACorso(matricola, c.getCodins());
        		txtRisultato.setText("Studente iscritto con successo! \n");
    		}
    		else {
    			txtRisultato.setText("Studente gia' iscritto al corso selezionato \n");
    		}
    		
    	}
    	else {
    		txtRisultato.setText("Studente non presente nel DataBase \n");
    	}
    	
    	
    }

    @FXML
    void doReset(ActionEvent event) {
   	  txtCogn.clear();
   	  txtMatr.clear();
   	  txtNome.clear();
   	  txtRisultato.clear();
    }

     
    @FXML
    void initialize() {
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCogn != null : "fx:id=\"txtCogn\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatr != null : "fx:id=\"txtMatr\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";


    }

    public void setModel(Model model) {
    	this.model = model;
    
    	for(Corso c : this.model.getTuttiICorsi()) {
        	cmbCorsi.getItems().add(c.getNome());
        }
    	
    	cmbCorsi.getItems().add(" ");
    }
    
}
