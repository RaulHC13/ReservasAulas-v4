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

public class ControladorEliminarAula {
		
	@FXML private TextField TFNombre;
	@FXML private Button BTEliminar;
	
	private IControlador controladorPrincipal;
	
	public void setControladorPrincipal(IControlador controlador) {
		this.controladorPrincipal = controlador;
	}
	
	@FXML private Button BTSalir;
	@FXML
	private void salir(ActionEvent evento) {
		((Stage) BTSalir.getParent().getScene().getWindow()).close();
	}
	
	public void inicializar() {
		TFNombre.setText("");
	}	
	
	private String getNombre() {
		
		String nombre = "";
		
		if (TFNombre.getText() == null || TFNombre.getText().isBlank()) {
			Dialogos.mostrarDialogoAdvertencia("AVISO", "No se ha introducido ningún nombre.");
		} else {
			 nombre = TFNombre.getText();			
		}
		return nombre;
	}
	
	@FXML
	private void eliminarAula() {
		
		String nombre = getNombre();
		try {			
			if (nombre.isEmpty()) {
				Dialogos.mostrarDialogoError("ERROR", "No se ha podido eliminar el aula.");				
			} else {				
				Aula aulaBorrar = Aula.getAulaFicticia(nombre);
				controladorPrincipal.borrarAula(aulaBorrar);
				Dialogos.mostrarDialogoInformacion(nombre, "Se ha eliminado el aula.");
			}
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("ERROR", "ERROR: El aula no existe.");	
		}//Se maneja este error en el catch
	}
}