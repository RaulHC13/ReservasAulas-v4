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

public class ControladorListarAula {
	
	@FXML private TableView <String> TVListaAulas;
	@FXML private TableColumn <String, String> TCNombre, TCPuestos;
	
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
		ObservableList<String> obsAula = FXCollections.observableArrayList(controladorPrincipal.representarAulas());
		//Se crea un ObservableList para guardar las Strings que devuelve representarAulas
		TVListaAulas.setItems(obsAula);
		/*Se inicializa el TableView dandole como valor/es la ObservableList creada,
		estas strings las formatearan las columnas en cargarListadoAulas*/
	}
	
	public void cargarListadoAulas() {
		
		/*Para darle valor a las columnas se utiliza setCellValueFactory.
		  Seria más facil si el método representarAulas devolviese valores en si pero devuelve strings.
		  
		  Se pueden utilizar los metodos substring e indexOf, substring para crear una string a partir de otra 
		  (lo que devuelve representarAulas) y dentro de esa substring se utiliza 
		  indexOf para seleccionar solo la parte que interesa insertar en la substring, 
		  en este caso para nombre, indexOf empieza en "= " que es lo que va antes del valor nombre,
		  se le suma 1 para que no incluya el "= " y como segundo parametro de substring se añade donde acaba la
		  string, en este caso en "P" que es donde empieza "Puestos"
		  
		  Para la columna TCPuestos se empieza por "]" que es el final y se le resta 3 para que incluya 
		  los 2 numeros de puestos, acaba en "]".
		  
		  Se puede no utilizar indexOf y directamente introducir la posicion en substring(), pero asi se limitaria
		  la longitud que se puede mostrar.
		  */
		
		TCNombre.setCellValueFactory(nombre -> new SimpleStringProperty
				(nombre.getValue().substring(nombre.getValue().indexOf("= ") + 1, //Donde empieza
				nombre.getValue().indexOf("Pues")))); //Donde acaba
		
		TCPuestos.setCellValueFactory(puestos -> new SimpleStringProperty
				(puestos.getValue().substring(puestos.getValue().indexOf("]") -3, 
				puestos.getValue().indexOf("]"))));
	}
}