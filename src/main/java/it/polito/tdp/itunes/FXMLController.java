/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.itunes;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.itunes.model.Dettaglio;
import it.polito.tdp.itunes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdiacenze"
    private Button btnAdiacenze; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="cmbA1"
    private ComboBox<String> cmbA1; // Value injected by FXMLLoader

    @FXML // fx:id="cmbA2"
    private ComboBox<?> cmbA2; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="txtX"
    private TextField txtX; // Value injected by FXMLLoader

    @FXML
    void doCalcolaAdiacenze(ActionEvent event) {
    	
    	String albumT = cmbA1.getValue();
    	
    	if(albumT==null) {
      		txtResult.appendText("Selezionare un album.\n");
      		return ;
      	}
    	
    	List<Dettaglio> dettagli = new LinkedList<>();
    	
    	dettagli.addAll(this.model.calcolaAdiacenze(albumT));
    	
    	for(Dettaglio d : dettagli) {
    		txtResult.appendText(d.getAlbum().getTitle()+"-> Bilancio: "+d.getBilancio()+"\n");
    	}
    	
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	cmbA1.getItems().clear();
    	
    	  String td = txtN.getText() ;
      	
      	if(td.equals("")) {
      		txtResult.setText("Inserire una durata.\n");
      		return ;
      	}
      	
      	int durata = 0 ;

      	try {
  	    	durata = Integer.parseInt(td) ;
      	} catch(NumberFormatException e) {
      		txtResult.setText("Inserire un valore numerico come durata.\n");
      		return ;
      	}
      	
//     	creazione grafo
     	this.model.creaGrafo(durata);
     	
     	
//     	stampa grafo
     	this.txtResult.setText("Grafo creato.\n");
     	this.txtResult.appendText("Ci sono " + this.model.nVertici() + " vertici\n");
     	this.txtResult.appendText("Ci sono " + this.model.nArchi() + " archi\n\n");
     	
     	cmbA1.getItems().addAll(this.model.getVerticiName());
     	btnAdiacenze.setDisable(false);
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdiacenze != null : "fx:id=\"btnAdiacenze\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA1 != null : "fx:id=\"cmbA1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbA2 != null : "fx:id=\"cmbA2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX != null : "fx:id=\"txtX\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    
    public void setModel(Model model) {
    	this.model = model;
    	
    	btnAdiacenze.setDisable(true);
    	btnPercorso.setDisable(true);
    }
}
