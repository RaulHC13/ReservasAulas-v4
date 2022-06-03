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
	
	public void inicializar() {
		TFNombre.setText("");
		TFPuestos.setText("0");
		//Inicializa los puestos como 0 para prevenir excepciones.
	}	
	
	private Aula getAula() {
		//Recoge los datos de los campos de texto.
		Aula aula = null;
		String nombre = "";
		int puestos = 10;
		
		if (TFNombre.getText() == null || TFNombre.getText().isBlank()) {
			Dialogos.mostrarDialogoAdvertencia("AVISO", "No se ha introducido ningún nombre.");
			
		} else {
			 nombre = TFNombre.getText();			
		}
		try {
		
		if (Integer.parseInt(TFPuestos.getText()) > 50 || Integer.parseInt(TFPuestos.getText()) < 10) {
			Dialogos.mostrarDialogoAdvertencia("AVISO", "El número de puestos no es válido");
		} else {
			 puestos = Integer.parseInt(TFPuestos.getText());
		}
		} catch(NumberFormatException e) {
			Dialogos.mostrarDialogoAdvertencia("AVISO", "Se debe de insertar un número entero de puestos entre 10 y 50");
		}//En caso de que se introduzca algo que no es un numero entero en TFPuestos
		
		if (!nombre.isBlank()) {
			aula = new Aula(nombre,puestos);			
		}
		
		/*Aunque el numero de puestos no sea válido se crea un nuevo aula con puestos 10 por defecto,
		se comprueba TFPuestos al insertar y, si no es válido no se crea.*/
		
		return aula;
	}
	
	@FXML
	private void insertarAula() {
		Aula aula = null;
		try {
			
			aula = getAula();
			try {
				
			if (Integer.parseInt(TFPuestos.getText()) > 50 || Integer.parseInt(TFPuestos.getText()) < 10 || aula == null) {
				Dialogos.mostrarDialogoError("ERROR", "ERROR: No se ha podido insertar el aula.");
				//Hace la ultima comprobación antes de insertar.
				
			} else {
				controladorPrincipal.insertarAula(aula);
				Dialogos.mostrarDialogoInformacion(null, "Se ha insertado el aula.");	
			}	
			} catch(NumberFormatException e) {
				Dialogos.mostrarDialogoError("ERROR", "ERROR: No se ha podido insertar el aula.");
			}
			
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("ERROR", "ERROR: El aula ya existe.");
		}//El otro error que puede dar es en caso de que el aula ya exista, por lo que se maneja en el catch.
	}
}