package org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica.resources.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorEliminarProfesor {
	
	private static final String ER_CORREO = "[a-zñÑA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zñÑA-Z0-9](?:[a-zñÑA-Z0-9-]{0,61}[a-zñÑA-Z0-9])?(?:\\.[a-zñÑA-Z0-9](?:[a-zñÑA-Z0-9-]{0,61}[a-zñÑA-Z0-9])?)";

	@FXML private TextField TFCorreo;
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
		TFCorreo.setText("");
	}
	
	private String getCorreo() {
		String correo = "";
		
		if (TFCorreo.getText() == null || TFCorreo.getText().isBlank() || !TFCorreo.getText().matches(ER_CORREO)) {
			Dialogos.mostrarDialogoAdvertencia("AVISO", "No se ha introducido ningún correo válido.");
		} else {
			 correo = TFCorreo.getText();			
		}
		return correo;
	}
	
	@FXML
	private void eliminarProfesor() {
		String correo = getCorreo();
		
		try {
			if (correo.isBlank()) {
				Dialogos.mostrarDialogoError("ERROR", "No se ha podido eliminar el profesor.");	
			} else {
				Profesor profesor = Profesor.getProfesorFicticio(correo);
				controladorPrincipal.borrarProfesor(profesor);
				Dialogos.mostrarDialogoInformacion(null, "Se ha eliminado el profesor.");
			}

		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("ERROR", "ERROR: El profesor no existe.");	
		}
	}
}