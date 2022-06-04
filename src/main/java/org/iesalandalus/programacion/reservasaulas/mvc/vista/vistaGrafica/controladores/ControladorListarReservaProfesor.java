package org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica.controladores;

import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica.resources.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorListarReservaProfesor {
	
	@FXML private TableView <Reserva> TVListarReservas;
	@FXML private TableColumn <Reserva, String> TCPermanencia, TCFecha, TCHora, TCAula, TCProfesor;
	@FXML private TextField TFCorreo;
	@FXML private Button BTListar;
	
	private ObservableList<Reserva> obsReservasProfesor = FXCollections.observableArrayList();
	private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String ER_CORREO = "[a-zñÑA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zñÑA-Z0-9](?:[a-zñÑA-Z0-9-]{0,61}[a-zñÑA-Z0-9])?(?:\\.[a-zñÑA-Z0-9](?:[a-zñÑA-Z0-9-]{0,61}[a-zñÑA-Z0-9])?)";

		
	private IControlador controladorPrincipal;
	
	public void setControladorPrincipal(IControlador controlador) {
		this.controladorPrincipal = controlador;
	}
	
	@FXML private Button BTSalir;
	@FXML
	private void salir(ActionEvent evento) {
		((Stage) BTSalir.getParent().getScene().getWindow()).close();
	}
	
	private Profesor getCorreo() {
		Profesor profesor = null;
		//Se crea un profesor ficticio a partir del correo
		try {
			profesor = Profesor.getProfesorFicticio(TFCorreo.getText()); 
		
		} catch(IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("ERROR", "No se ha introducido ningun correo válido.");
		} 
		return profesor;
	}
	
	private String getSPermanencia(Reserva reserva) {
		
		String permanencia = reserva.getPermanencia().toString();
		
		return permanencia;
	} /*Como la permanencia puede ser de 2 tipos y el metodo getReservasAulas no devuelve string, creo un metodo
	  getSPermanencia simplemente para pasar la permanencia a string utilizando su propio toString*/
	
	public void inicializar() {
		TFCorreo.setText("");
		TVListarReservas.setItems(null);
	}
	
	@FXML
	private void cargarListadoReservasProfesor() {
		
		/*Se crea el array list dentro de este metodo para que muestre reservas al darle al boton, no se cargan
		  al inicializar ya que hay que saber el nombre primero*/
		
		try {
		obsReservasProfesor = FXCollections.observableArrayList(controladorPrincipal.getReservasProfesor(getCorreo()));
		TVListarReservas.setItems(obsReservasProfesor);
		} catch (NullPointerException e) {
		}
		
		/*Se le dan valores a las columnas igual que en las otras listas pero como los metodos getReservasAula
		 y getReservasProfesor devuelven objetos aula y profesor, no hay que estar creando substrings 
		 y es mas sencillo, se utilizan los getters*/
		
		TCFecha.setCellValueFactory(fecha -> new SimpleStringProperty
				(formato.format(fecha.getValue().getPermanencia().getDia())));
		//Utilizo un DateTimeFormatter para que la fecha salga en un formato mas facil de leer
		
		TCHora.setCellValueFactory(hora -> new SimpleStringProperty
				(getSPermanencia(hora.getValue()).substring(45, 52)));
		//Utilizo getSPermanencia y creo una substring, desde la posicion 45 a la 52 del toString original.
		TCAula.setCellValueFactory(nombre -> new SimpleStringProperty
				(nombre.getValue().getAula().getNombre()));
		
		TCProfesor.setCellValueFactory(correo -> new SimpleStringProperty(
				(correo.getValue().getProfesor().getCorreo())));
		
		if (obsReservasProfesor.isEmpty() && !TFCorreo.getText().isBlank() && TFCorreo.getText().matches(ER_CORREO) ) {
			Dialogos.mostrarDialogoInformacion(null, "No existen reservas para este profesor.");
			/*Si se ha introducido un correo pero no existen reservas para ese profesor y 
			  es un correo valido (coincide con ER_CORREO), devuelve este mensaje
			  no lo devuelve si no se ha introducido ningun correo*/
		}
	}
}