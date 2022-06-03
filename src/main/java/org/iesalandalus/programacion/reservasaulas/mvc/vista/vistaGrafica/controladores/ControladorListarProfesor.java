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

public class ControladorListarProfesor{
	
	@FXML private TableView <String> tvProfesores;
    @FXML private TableColumn <String, String> nombreColumn,correoColumn,telefonoColumn; 
	
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
		ObservableList<String> obsProfesores = FXCollections.observableArrayList(controladorPrincipal.representarProfesores());
		tvProfesores.setItems(obsProfesores);
	}
	
	public void cargarListadoProfesores() {
		nombreColumn.setCellValueFactory(nombre -> new SimpleStringProperty
				(nombre.getValue().substring(nombre.getValue().indexOf("e: ") + 2,
						nombre.getValue().indexOf(", C"))));
		correoColumn.setCellValueFactory(correo -> new SimpleStringProperty
				(correo.getValue().substring(correo.getValue().indexOf("o: ") + 2,
						correo.getValue().indexOf(", T"))));
		telefonoColumn.setCellValueFactory(telefono -> new SimpleStringProperty
				(telefono.getValue().substring(telefono.getValue().indexOf("no: ") + 3,
						telefono.getValue().indexOf("]"))));
		
	}
}