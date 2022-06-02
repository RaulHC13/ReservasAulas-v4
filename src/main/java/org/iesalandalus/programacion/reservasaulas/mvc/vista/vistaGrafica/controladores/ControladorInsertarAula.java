package org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica.resources.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorInsertarAula {
	
	@FXML private Button BTInsertarAula;
	@FXML private TextField TFNombre,TFPuestos;
		
	private IControlador controladorPrincipal;
	
	public void setControladorPrincipal(IControlador controlador) {
		this.controladorPrincipal = controlador;
	}
	
	@FXML private Button BTSalir;
	@FXML
	private void salir(ActionEvent evento) {
		((Stage) BTSalir.getParent().getScene().getWindow()).close();
	}
	
		
	private Aula getAula() {
		
		Aula aula = null;
		String nombre = null;
		int puestos = 10;
		
		if (TFNombre.getText() == null || TFNombre.getText().isBlank()) {
			Dialogos.mostrarDialogoError("ERROR", "Error: No se ha introducido ningún nombre.");
		} else {
			 nombre = TFNombre.getText();			
		}
		if (Integer.parseInt(TFPuestos.getText()) > 50 || Integer.parseInt(TFPuestos.getText()) < 10) {
			Dialogos.mostrarDialogoError("ERROR", "Error: El número de puestos no es válido");
		} else {
			 puestos = Integer.parseInt(TFPuestos.getText());
		}
		aula = new Aula(nombre,puestos);
		
		return aula;
	}
	
	@FXML
	private void insertarAula() {
		Aula aula = null;
		try {
			aula = getAula();
			controladorPrincipal.insertarAula(aula);
			Dialogos.mostrarDialogoInformacion(null, "Se ha insertade el aula.");
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("ERROR", "Error: No se ha podido insertar el aula.");
		}
	}
}