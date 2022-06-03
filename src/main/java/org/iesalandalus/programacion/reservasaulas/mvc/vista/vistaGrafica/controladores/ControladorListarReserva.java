package org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica.controladores;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControladorListarReserva {
	
	@FXML private TableView <String> TVListarReservas;
	@FXML private TableColumn <String, String> TCPermanencia, TCFecha, TCHora, TCAula, TCProfesor;
		
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
		ObservableList<String> obsReservas = FXCollections.observableArrayList(controladorPrincipal.representarReservas());
		TVListarReservas.setItems(obsReservas);
	}
	
	public void cargarListadoReservas() {
		TCFecha.setCellValueFactory(fecha -> new SimpleStringProperty
				(fecha.getValue().substring(fecha.getValue().indexOf("Dia = ") + 6,
						fecha.getValue().indexOf(", "))));
		TCHora.setCellValueFactory(hora -> new SimpleStringProperty
				(hora.getValue().substring(hora.getValue().indexOf(", ") + 8,
						hora.getValue().indexOf("]"))));
		TCAula.setCellValueFactory(aula -> new SimpleStringProperty
				(aula.getValue().substring(aula.getValue().indexOf("e = ") + 4,
						aula.getValue().indexOf("Pu"))));
		TCProfesor.setCellValueFactory(profesor -> new SimpleStringProperty
				(profesor.getValue().substring(profesor.getValue().indexOf("eo: ") + 3,
						profesor.getValue().indexOf("Tel") - 2)));
		
	}
}