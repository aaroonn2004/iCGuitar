package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import interfaz.*;
import registroDatos.RegisterFrame;
import baseDatos.*;

public class ICGuitar extends JFrame implements LenguajeTexto {

	/**
	 *
	 */
	
	// Define las variables principales y/o estructuras de datos de la aplicación
	private static final long serialVersionUID = 1L;
	private static String usuarioIniciado = "";
	private static interfaz.LogingFrame login;
	private static registroDatos.RegisterFrame register;
	private static ArrayList<String> artistas = new ArrayList<String>();
	private static ArrayList<String> albumes = new ArrayList<String>();
	private static ArrayList<String> dificultades = new ArrayList<String>();

	public static void main(String args[]) {
		
		// Crea instancias de las ventanas de logueo y registro
		setRegister(new RegisterFrame());
		setLogin(new LogingFrame());
		
		// Carga las dificultades de la base de datos en su ArrayList
		cargarDificultad();
	 
		// Comprueba si se puede conectar a la base de datos, en caso contrario lanza un error y se cierra
		if (ICGuitarDatabase.conectar() != null) {
			getLogin().setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, ERROR_RED_DB, ERROR_RED, JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	public static void cargarDificultad() {
		try {
			ResultSet dificultad = ICGuitarDatabase.obtenerDificultades();
			while (dificultad.next()) {
				dificultades.add(dificultad.getString("dificultad") + ";" + dificultad.getString("idDificultad"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static registroDatos.RegisterFrame getRegister() {
		return register;
	}

	public static void setRegister(registroDatos.RegisterFrame register) {
		ICGuitar.register = register;
	}

	public static String getUsuarioIniciado() {
		return usuarioIniciado;
	}

	public static void setUsuarioIniciado(String usuarioIniciado) {
		ICGuitar.usuarioIniciado = usuarioIniciado;
	}

	public static interfaz.LogingFrame getLogin() {
		return login;
	}

	public static void setLogin(interfaz.LogingFrame login) {
		ICGuitar.login = login;
	}

	public static ArrayList<String> getArtistas() {
		return artistas;
	}

	public static void setArtistas(ArrayList<String> artistas) {
		ICGuitar.artistas = artistas;
	}

	public static ArrayList<String> getAlbumes() {
		return albumes;
	}

	public static void setAlbumes(ArrayList<String> albumes) {
		ICGuitar.albumes = albumes;
	}

	public static ArrayList<String> getDificultad() {
		return dificultades;
	}

	public static void setDificultad(ArrayList<String> dificultades) {
		ICGuitar.dificultades = dificultades;
	}
}