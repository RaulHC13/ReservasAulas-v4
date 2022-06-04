package org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica.controladores;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica.resources.Dialogos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorRealizarReserva {
	
	@FXML private TextField TFCorreo, TFNombre, TFHora;
	@FXML private DatePicker DPPermanencia;
	@FXML private Button BTRealizar;
	@FXML private RadioButton RBTramo, RBHora;
	@FXML private ComboBox<Tramo> CBTramo;
	private DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
	private static final String ER_CORREO = "[a-zñÑA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zñÑA-Z0-9](?:[a-zñÑA-Z0-9-]{0,61}[a-zñÑA-Z0-9])?(?:\\.[a-zñÑA-Z0-9](?:[a-zñÑA-Z0-9-]{0,61}[a-zñÑA-Z0-9])?)";
	private static final LocalTime HORA_INICIO = LocalTime.of(10, 00);
	private static final LocalTime HORA_FIN = LocalTime.of(22, 00);
	
	private ObservableList<Tramo> obsTramo = FXCollections.observableArrayList(Tramo.values());
		
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
		DPPermanencia.setValue(null);
		CBTramo.setItems(obsTramo);
		CBTramo.setDisable(true);
		TFHora.setDisable(true);
		RBTramo.setSelected(false);
		RBHora.setSelected(false);
		TFHora.setText("");
		CBTramo.setValue(null);
	}
	
	@FXML
	private void seleccionPermanencia(ActionEvent evento) {
		RadioButton RB = (RadioButton)evento.getSource();
		//Dependiendo del boton radial que se pulse, se activa la parte de permanencia pertinente:
		if (RB == RBTramo) { 		  //Si se tiene pulsado el boton de tramo, activa el ComboBox para elegir y ademas
			CBTramo.setDisable(false);//desactiva lo relaccionado con permanencia hora.
			TFHora.setDisable(true);
			TFHora.setText("");
		}
		else if (RB == RBHora) {
			CBTramo.setDisable(true);
			TFHora.setDisable(false);
			CBTramo.setValue(null);
		}
	}
	
	@FXML private void realizarReserva() {
		Reserva reserva = null;
		
		try {
			reserva = getReserva();
			if (reserva == null) {
				Dialogos.mostrarDialogoError("ERROR", "ERROR: No se ha podido insertar la reserva.");
			} else {
				controladorPrincipal.realizarReserva(reserva);
				Dialogos.mostrarDialogoInformacion(null, "Se ha insertado la reserva.");	
			}
		} catch (OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("ERROR", "ERROR: La reserva ya existe.");
		}
	}
	/*private String getNombre() {
		
		String nombre = "";
		
		try {
		
		if (TFNombre.getText() == null || TFNombre.getText().isBlank()) {
			Dialogos.mostrarDialogoAdvertencia("AVISO", "No se ha introducido ningún nombre.");
		} else {
			 nombre = TFNombre.getText();
		}
		} catch (NullPointerException e) {
		}
		return nombre;
	}*/
	
	/*private String getCorreo() {
		
		String correo = "";
		try {
		if (TFCorreo.getText() == null || TFCorreo.getText().isBlank() || !TFCorreo.getText().matches(ER_CORREO)) {
			Dialogos.mostrarDialogoAdvertencia("AVISO", "No se ha introducido un correo válido.");
		} else {
			 correo = TFCorreo.getText();			
		}
		} catch (NullPointerException e) {
		}
		return correo;
	}*/
	
	private LocalDate getFecha() {
		LocalDate fecha = null;
		fecha = DPPermanencia.getValue();
		return fecha;
	}
	
	private Tramo getTramo() {
		Tramo tramo = CBTramo.getValue();
		return tramo;
	}
	
	private LocalTime getHora() {
		LocalTime hora = null;
		try {//Convierte la string en un LocalTime y le pasa un formateador.
			hora = LocalTime.parse(TFHora.getText(), formatoHora);
		} catch (DateTimeException e) {
			
		}
		return hora;
	}
	
	private boolean horaValida() {//Metodo para comprobar que la hora introducida es mayor que la hora
		boolean bool = false;     //de apertura y menor que la hora de cierre.
		LocalTime hora = getHora();
		try {
			
		if (hora.isBefore(HORA_INICIO) || hora.isAfter(HORA_FIN) || hora.getMinute() != 0) {
			bool = false;
		} else {
			bool = true;
		}
		} catch (NullPointerException e) {
		}
		return bool;
	}
	
	private Reserva getReserva() {
		Reserva reserva = null;
		Aula aula = null;
		Profesor profesor = null;
		LocalDate fecha = null;
		Tramo tramo = null;
		LocalTime hora = null;
		Permanencia permanencia = null;
		//Una gran cantidad de comprobaciones para que no haya ningún tipo de error en el programa.
		if (TFNombre.getText() == null || TFNombre.getText().isBlank()) {
			Dialogos.mostrarDialogoAdvertencia("AVISO", "No se ha introducido ningún nombre.");
		} else {
			 aula = Aula.getAulaFicticia(TFNombre.getText());	
		}
		
		if (TFCorreo.getText() == null || TFCorreo.getText().isBlank() || !TFCorreo.getText().matches(ER_CORREO)) {
			Dialogos.mostrarDialogoAdvertencia("AVISO", "No se ha introducido ningún correo válido.");
		} else {
			 profesor = Profesor.getProfesorFicticio(TFCorreo.getText());			
		}
		
		if (DPPermanencia.getValue() == null || DPPermanencia.getValue().isBefore(LocalDate.now().plusMonths(1))) {
			//Compruebo que no se pueda insertar una fecha en este mes.
			Dialogos.mostrarDialogoAdvertencia("AVISO", "No se ha introducido una fecha válida.");
		} else {
			fecha = getFecha();
		}
		
		if (!RBTramo.isSelected() && !RBHora.isSelected()) {
			Dialogos.mostrarDialogoAdvertencia("AVISO", "No se ha seleccionado un tipo de permanencia.");
		}
		
		if (RBTramo.isSelected() && CBTramo.getValue() == null) {
			Dialogos.mostrarDialogoAdvertencia("AVISO", "No se ha introducido un tramo válido.");
			tramo = null;
		} else if (RBTramo.isSelected() && CBTramo.getValue() != null ) {
			tramo = getTramo();
		
		}
		
		if (RBHora.isSelected() && (TFHora.getText().isEmpty() ||!horaValida())) {
			Dialogos.mostrarDialogoAdvertencia("AVISO", "No se ha introducido una hora válida.");
			hora = null;
		} else {
				hora = getHora();
		}
		
		if (RBTramo.isSelected() && CBTramo.getValue() != null && tramo != null) {
				permanencia = new PermanenciaPorTramo(fecha, tramo);
			
		} else if (RBHora.isSelected() && (!TFHora.getText().isEmpty() && TFHora.getText() != null && hora != null)) {
			permanencia = new PermanenciaPorHora(fecha, hora);
		}
		/*Si pasa todas las comprobaciones crea una nueva reserva que se va a insertar pero si
		  no las pasa el metodo realizarReserva() lanzará un mensaje de error y no la insertará.*/
		if (aula != null && profesor != null && permanencia != null) {
			try {
			reserva = new Reserva(profesor, aula, permanencia);
			} catch (NullPointerException e) {
			}
		}
			return reserva;
	}
}