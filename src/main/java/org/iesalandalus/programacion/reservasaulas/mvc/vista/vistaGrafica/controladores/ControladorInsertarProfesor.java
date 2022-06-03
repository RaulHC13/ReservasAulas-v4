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

public class ControladorInsertarProfesor {
	
	private static final String ER_TELEFONO = "([6|9][0-9]{8})";
	//Expresion regular para validar que el número de telefono es válido.
	private static final String ER_CORREO = "[a-zñÑA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zñÑA-Z0-9](?:[a-zñÑA-Z0-9-]{0,61}[a-zñÑA-Z0-9])?(?:\\.[a-zñÑA-Z0-9](?:[a-zñÑA-Z0-9-]{0,61}[a-zñÑA-Z0-9])?)";
	//ER para el correo
	
	@FXML private TextField TFNombre, TFCorreo, TFTelefono;
	@FXML private Button BTInsertar;
	
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
		TFCorreo.setText("");
		TFTelefono.setText("");
	}
	
	private Profesor getProfesor() {
		
		Profesor profesor = null;
		String nombre = "";
		String correo = "";
		String telefono = null;

		if (TFNombre.getText() == null || TFNombre.getText().isBlank()) {
			Dialogos.mostrarDialogoAdvertencia("AVISO", "No se ha introducido ningún nombre.");
		} else {
			 nombre = formateaNombre(TFNombre.getText());//Utilizo el metodo para formatear el nombre.	
		}
		
		if (TFCorreo.getText() == null || TFCorreo.getText().isBlank() || !TFCorreo.getText().matches(ER_CORREO)) {
			Dialogos.mostrarDialogoAdvertencia("AVISO", "No se ha introducido ningún correo válido.");
		} else {
			 correo = TFCorreo.getText();			
		}
		
		if (!TFTelefono.getText().isBlank() && !(TFTelefono.getText() == null) && !TFTelefono.getText().matches(ER_TELEFONO)) {	
			
			Dialogos.mostrarDialogoAdvertencia("AVISO", "El número de telefono introducido no es válido.");
			telefono = null;
			
		} else if (!TFTelefono.getText().isBlank() && !(TFTelefono.getText() == null) && TFTelefono.getText().matches(ER_TELEFONO)) {
			telefono = TFTelefono.getText();
			
		}//Si el Nº de telefono introducido no es válido se le da null y crea un profesor sin telefono.
		
			
		if (telefono == null && !nombre.isBlank() && !correo.isBlank()) {
			profesor = new Profesor(nombre,correo);
		} else if (telefono != null && !nombre.isBlank() && !correo.isBlank()) {
			profesor = new Profesor(nombre,correo,telefono);
		}
		
		return profesor;
	}
	
	@FXML
	private void insertarProfesor() {
		Profesor profesorInsertar = null;
		try {
			profesorInsertar = getProfesor();
			if (profesorInsertar == null) {
				Dialogos.mostrarDialogoError("ERROR", "ERROR: No se ha podido insertar el profesor.");
			} else  {
				controladorPrincipal.insertarProfesor(profesorInsertar);
				Dialogos.mostrarDialogoInformacion(null, "Se ha insertado el profesor.");	
			}
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("ERROR", "ERROR: El profesor ya existe.");
		}
	}
	
	private String formateaNombre(String nombre) {
		
		char[] caracter = nombre.toCharArray(); //Se crea un array a partir de la string nombre
		caracter[0] = Character.toUpperCase(caracter[0]); //Se convierte la primera letra a mayuscula
		
		for (int i = 0; i < caracter.length -1; i++) {
			
			if(caracter[i] == ' ' || caracter[i] == '.')//Si una iteración es espacio o punto, la siguiente iteración sera mayuscula
				caracter[i+1] = Character.toUpperCase(caracter[i+1]);
		}
		String salida = new String(caracter);//A partir del array caracter se crea una string salida
		
		return salida.trim();//Para eliminar espacios se utiliza trim()
	}
}