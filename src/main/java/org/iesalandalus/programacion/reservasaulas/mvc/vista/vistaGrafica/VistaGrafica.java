package org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica.controladores.ControladorPrincipal;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.vistaGrafica.resources.LocalizadorRecursos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VistaGrafica extends Application implements IVista{
	
	private static IControlador controladorPrograma;
	
	@Override
	public void start(Stage escenarioPrincipal) {
		try {
			FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/Principal.fxml"));
			AnchorPane raiz = loader.load();
			
			ControladorPrincipal controllerP = loader.getController();
			controllerP.setControladorPrincipal(controladorPrograma);
			
			Scene escena = new Scene(raiz, 800, 600);
			escena.getStylesheets().add(LocalizadorRecursos.class.getResource("estilos/estilosPrincipal.css").toExternalForm());
			escenarioPrincipal.setTitle("Ventana principal");
			escenarioPrincipal.setScene(escena);
			escenarioPrincipal.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void setControlador(IControlador controlador) {
		controladorPrograma = controlador;
	}

	@Override
	public void comenzar() {
		launch(this.getClass());
	}

	@Override
	public void salir() {
		controladorPrograma.terminar();
	}

}