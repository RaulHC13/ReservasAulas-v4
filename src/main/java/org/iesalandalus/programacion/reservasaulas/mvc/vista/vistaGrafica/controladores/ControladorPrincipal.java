package org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica.controladores;

import java.io.IOException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica.resources.Dialogos;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica.resources.LocalizadorRecursos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorPrincipal {

	
	@FXML private Button BTSalirPrincipal,BTInsertarAula,BTEliminarAula,BTListarAula,BTConsultarDisponibilidad;
	@FXML private Button BTInsertarProfesor,BTEliminarProfesor,BTListarProfesor; 
	@FXML private Button BTRealizarReserva,BTAnularReserva,BTListarReserva,BTListarReservaAula,BTListarReservaProfesor;
	@FXML private MenuItem MICerrar, MISorpresa;
	
	private Stage insertarAulaStage, eliminarAulaStage, listarAulaStage, consultarDisponibilidadStage;
	private Stage insertarProfesorStage, eliminarProfesorStage, listarProfesorStage;
	private Stage realizarReservaStage, anularReservaStage, listarReservaStage, 
	listarReservaAulaStage, listarReservaProfesorStage;
	
	private ControladorInsertarAula controllerInsertarAula;
	private ControladorEliminarAula controllerEliminarAula;
	private ControladorListarAula controllerListarAula;
	private ControladorConsultarDisponibilidad controllerConsultarDisponibilidad;
	private ControladorInsertarProfesor controllerInsertarProfesor;
	private ControladorEliminarProfesor controllerEliminarProfesor;
	private ControladorListarProfesor controllerListarProfesor;
	private ControladorRealizarReserva controllerRealizarReserva;
	private ControladorAnularReserva controllerAnularReserva;
	private ControladorListarReserva controllerListarReserva;
	private ControladorListarReservaAula controllerListarReservaAula;
	private ControladorListarReservaProfesor controllerListarReservaProfesor;

	private IControlador controladorPrincipal;
	
	public void setControladorPrincipal(IControlador controlador) {
		this.controladorPrincipal = controlador;
	}
	
	@FXML
	public void abrirInsertarAula() throws IOException {
		crearVentanaInsertarAula();
		insertarAulaStage.showAndWait();
	}
	
	@FXML 
	public void abrirEliminarAula() throws IOException {
		crearVentanaEliminarAula();
		eliminarAulaStage.showAndWait();
	}
	
	@FXML
	public void abrirListarAula() throws IOException {			
		crearVentanaListarAula();
		listarAulaStage.showAndWait();
	}
	
	@FXML
	public void abrirConsultarDisponibilidad() throws IOException {
		crearVentanaConsultarDisponibilidad();
		consultarDisponibilidadStage.showAndWait();
	}
	
	@FXML
	public void abrirInsertarProfesor() throws IOException {
		crearVentanaInsertarProfesor();
		insertarProfesorStage.showAndWait();
	}
	
	@FXML
	public void abrirEliminarProfesor() throws IOException {
		crearVentanaEliminarProfesor();
		eliminarProfesorStage.showAndWait();
	}
	
	@FXML
	public void abrirListarProfesor() throws IOException {
		crearVentanaListarProfesor();
		listarProfesorStage.showAndWait();
	}
	
	@FXML
	public void abrirRealizarReserva() throws IOException {
		crearVentanaRealizarReserva();
		realizarReservaStage.showAndWait();
	}
	
	@FXML
	public void abrirAnularReserva() throws IOException {
		crearVentanaAnularReserva();
		anularReservaStage.showAndWait();
	}
	
	@FXML
	public void abrirListarReserva() throws IOException {
		crearVentanaListarReserva();
		listarReservaStage.showAndWait();
	}
	
	@FXML
	public void abrirListarReservaAula() throws IOException {
		crearVentanaListarReservaAula();
		listarReservaAulaStage.showAndWait();
	}
	
	@FXML
	public void abrirListarReservaProfesor() throws IOException {
		crearVentanaListarReservaProfesor();
		listarReservaProfesorStage.showAndWait();
		
	}
	
	@FXML
	private void salir(ActionEvent event) {

		if (Dialogos.mostrarDialogoConfirmacion("Salir del programa", "¿Seguro que quieres salir?", null)) {
			controladorPrincipal.terminar();
			System.exit(0);
		}
	}
	
	@FXML
	private void sorpresa(ActionEvent evento) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Sorpresa!!!");
		alerta.setContentText("No hay ninguna sorpresa");
		alerta.setHeaderText(null);
		alerta.show();
	}
	
	private void crearVentanaInsertarAula() throws IOException {
		if (insertarAulaStage == null) {
			insertarAulaStage = new Stage();
			FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/InsertarAula.fxml"));
			AnchorPane raiz = loader.load();
			controllerInsertarAula = loader.getController();
			controllerInsertarAula.setControladorPrincipal(controladorPrincipal);
			controllerInsertarAula.inicializar();
			
			Scene scene = new Scene(raiz,500,400);
			insertarAulaStage.setTitle("Insertar aulas");
			insertarAulaStage.initModality(Modality.APPLICATION_MODAL);
			insertarAulaStage.setScene(scene);
		} else {
			controllerInsertarAula.inicializar();
		} /*Se inicializa si el escenario no es nulo para que al volver a abrir tras cerrar 
			la ventana no aparezca lo que se habia escrito antes*/
	}
	
	private void crearVentanaEliminarAula() throws IOException {
		if (eliminarAulaStage == null) {
			eliminarAulaStage = new Stage();
			FXMLLoader loader2 = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/EliminarAula.fxml"));
			AnchorPane raiz2 = loader2.load();
			controllerEliminarAula = loader2.getController();
			controllerEliminarAula.setControladorPrincipal(controladorPrincipal);
			controllerEliminarAula.inicializar();
			
			Scene scene2 = new Scene(raiz2,500,400);
			eliminarAulaStage.setTitle("Eliminar aulas");
			eliminarAulaStage.initModality(Modality.APPLICATION_MODAL);
			eliminarAulaStage.setScene(scene2);
		}
		else {
			controllerEliminarAula.inicializar();
		}
	}
	
	private void crearVentanaListarAula() throws IOException {
		if (listarAulaStage == null) {
			listarAulaStage = new Stage();
			FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ListarAula.fxml"));
			AnchorPane raiz = loader.load();
			controllerListarAula = loader.getController();
			controllerListarAula.setControladorPrincipal(controladorPrincipal);
			controllerListarAula.inicializar();
			controllerListarAula.cargarListadoAulas();
			
			Scene scene = new Scene(raiz,500,400);
			listarAulaStage.setTitle("Listar aulas");
			listarAulaStage.initModality(Modality.APPLICATION_MODAL);
			listarAulaStage.setScene(scene);
		}
		else {
			controllerListarAula.inicializar();
			controllerListarAula.cargarListadoAulas();
		}
	}
	
	private void crearVentanaConsultarDisponibilidad() throws IOException {
		if (consultarDisponibilidadStage == null) {
			consultarDisponibilidadStage = new Stage();
			FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ConsultarDisponibilidad.fxml"));
			AnchorPane raiz = loader.load();
			controllerConsultarDisponibilidad = loader.getController();
			controllerConsultarDisponibilidad.setControladorPrincipal(controladorPrincipal);
			
			Scene scene = new Scene(raiz,500,400);
			consultarDisponibilidadStage.setTitle("Consultar disponibilidad");
			consultarDisponibilidadStage.initModality(Modality.APPLICATION_MODAL);
			consultarDisponibilidadStage.setScene(scene);
		}
	}
	
	private void crearVentanaInsertarProfesor() throws IOException {
		if (insertarProfesorStage == null) {
			insertarProfesorStage = new Stage();
			FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/InsertarProfesor.fxml"));
			AnchorPane raiz = loader.load();
			controllerInsertarProfesor = loader.getController();
			controllerInsertarProfesor.setControladorPrincipal(controladorPrincipal);
			controllerInsertarProfesor.inicializar();
			
			Scene scene = new Scene(raiz,500,400);
			insertarProfesorStage.setTitle("Insertar profesor");
			insertarProfesorStage.initModality(Modality.APPLICATION_MODAL);
			insertarProfesorStage.setScene(scene);
		} else {
			controllerInsertarProfesor.inicializar();
		}
		
	}
	
	private void crearVentanaEliminarProfesor() throws IOException {
		if (eliminarProfesorStage == null) {
			eliminarProfesorStage = new Stage();
			FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/EliminarProfesor.fxml"));
			AnchorPane raiz = loader.load();
			controllerEliminarProfesor = loader.getController();
			controllerEliminarProfesor.setControladorPrincipal(controladorPrincipal);
			controllerEliminarProfesor.inicializar();
			
			Scene scene = new Scene(raiz,500,400);
			eliminarProfesorStage.setTitle("Eliminar profesor");
			eliminarProfesorStage.initModality(Modality.APPLICATION_MODAL);
			eliminarProfesorStage.setScene(scene);
		}
		else {
			controllerEliminarProfesor.inicializar();
		}
	}
		private void crearVentanaListarProfesor() throws IOException {
			if (listarProfesorStage == null) {
				listarProfesorStage = new Stage();
				FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ListarProfesor.fxml"));
				AnchorPane raiz = loader.load();
				controllerListarProfesor = loader.getController();
				controllerListarProfesor.setControladorPrincipal(controladorPrincipal);
				controllerListarProfesor.inicializar();
				controllerListarProfesor.cargarListadoProfesores();
				
				Scene scene = new Scene(raiz,500,400);
				listarProfesorStage.setTitle("Listar profesor");
				listarProfesorStage.initModality(Modality.APPLICATION_MODAL);
				listarProfesorStage.setScene(scene);
			} else {
				controllerListarProfesor.inicializar();
				controllerListarProfesor.cargarListadoProfesores();
			}
	}
		private void crearVentanaRealizarReserva() throws IOException {
			if (realizarReservaStage == null) {
				realizarReservaStage = new Stage();
				FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/RealizarReserva.fxml"));
				AnchorPane raiz = loader.load();
				controllerRealizarReserva = loader.getController();
				controllerRealizarReserva.setControladorPrincipal(controladorPrincipal);
				controllerRealizarReserva.inicializar();
				
				Scene scene = new Scene(raiz,500,400);
				realizarReservaStage.setTitle("Realizar reserva");
				realizarReservaStage.initModality(Modality.APPLICATION_MODAL);
				realizarReservaStage.setScene(scene);
			}
			else {
				controllerRealizarReserva.inicializar();
			}
	}
		private void crearVentanaAnularReserva() throws IOException {
			if (anularReservaStage == null) {
				anularReservaStage = new Stage();
				FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/AnularReserva.fxml"));
				AnchorPane raiz = loader.load();
				controllerAnularReserva = loader.getController();
				controllerAnularReserva.setControladorPrincipal(controladorPrincipal);
				
				Scene scene = new Scene(raiz,500,400);
				anularReservaStage.setTitle("Anular reserva");
				anularReservaStage.initModality(Modality.APPLICATION_MODAL);
				anularReservaStage.setScene(scene);
			}
	}
		private void crearVentanaListarReserva() throws IOException {
			if (listarReservaStage == null) {
				listarReservaStage = new Stage();
				FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ListarReserva.fxml"));
				AnchorPane raiz = loader.load();
				controllerListarReserva = loader.getController();
				controllerListarReserva.setControladorPrincipal(controladorPrincipal);
				controllerListarReserva.inicializar();
				controllerListarReserva.cargarListadoReservas();
				
				Scene scene = new Scene(raiz,500,400);
				listarReservaStage.setTitle("Listar reservas");
				listarReservaStage.initModality(Modality.APPLICATION_MODAL);
				listarReservaStage.setScene(scene);
			} else {
				controllerListarReserva.inicializar();
				controllerListarReserva.cargarListadoReservas();
			}
	}
		private void crearVentanaListarReservaAula() throws IOException {
			if (listarReservaAulaStage == null) {
				listarReservaAulaStage = new Stage();
				FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ListarReservaAula.fxml"));
				AnchorPane raiz = loader.load();
				controllerListarReservaAula = loader.getController();
				controllerListarReservaAula.setControladorPrincipal(controladorPrincipal);
				controllerListarReservaAula.inicializar();
				
				Scene scene = new Scene(raiz,500,400);
				listarReservaAulaStage.setTitle("Listar reservas por aula");
				listarReservaAulaStage.initModality(Modality.APPLICATION_MODAL);
				listarReservaAulaStage.setScene(scene);
			}
			else {
				controllerListarReservaAula.inicializar();
			}
	}
		private void crearVentanaListarReservaProfesor() throws IOException {
			if (listarReservaProfesorStage == null) {
				listarReservaProfesorStage = new Stage();
				FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/ListarReservaProfesor.fxml"));
				AnchorPane raiz = loader.load();
				controllerListarReservaProfesor = loader.getController();
				controllerListarReservaProfesor.setControladorPrincipal(controladorPrincipal);
				controllerListarReservaProfesor.inicializar();
				
				Scene scene = new Scene(raiz,500,400);
				listarReservaProfesorStage.setTitle("Listar reservas por profesor");
				listarReservaProfesorStage.initModality(Modality.APPLICATION_MODAL);
				listarReservaProfesorStage.setScene(scene);
			} else {
				controllerListarReservaProfesor.inicializar();
			}
	}
	}