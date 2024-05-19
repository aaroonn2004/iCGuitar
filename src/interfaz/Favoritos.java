package interfaz;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.ICGuitarDatabase;
import clases.ICGuitar;
import clases.LenguajeTexto;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JButton;

public class Favoritos extends JFrame implements LenguajeTexto, ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JEditorPane editorPane;
	private JComboBox<String> comboBoxFavoritos;
	private JButton btnAceptar;
	private JButton btnSalir;
	private String id;
	private String tipo;
	private ArrayList<String> canciones=new ArrayList<String>();
	private ArrayList<String> albumes=new ArrayList<String>();
	private ArrayList<String> artistas=new ArrayList<String>();

	/**
	 * Create the frame.
	 */
	public Favoritos(String tipo) {
		String favorito=null;
		setTipo(tipo);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 522);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DatosUsuario.class.getResource("/assets/images/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		switch(getTipo()) {
			case "cancion": 
				favorito=String.format(FAV_CANCIONES, ICGuitar.getUsuarioIniciado());
				break;
			case "album": 
				favorito=String.format(FAV_ALBUM, ICGuitar.getUsuarioIniciado());
				break;
			case "artista": 
				favorito=String.format(FAV_ARTISTA, ICGuitar.getUsuarioIniciado());
				break;
		}
		setTitle(favorito);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFavorito = new JLabel(favorito);
		lblFavorito.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblFavorito.setBounds(10, 0, 537, 38);
		contentPane.add(lblFavorito);
		
		editorPane = new JEditorPane();
		editorPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		
		JScrollPane scrollPane = new JScrollPane(editorPane);
		scrollPane.setBounds(27, 48, 507, 369);
		contentPane.add(scrollPane);
		
		comboBoxFavoritos = new JComboBox<String>();
		comboBoxFavoritos.setBounds(27, 427, 507, 21);
		contentPane.add(comboBoxFavoritos);
		
		btnSalir = new JButton(FILE_CHOOSER_CANCELAR);
		btnSalir.setBounds(449, 458, 85, 21);
		btnSalir.addActionListener(this);
		contentPane.add(btnSalir);
		
		btnAceptar = new JButton(FILE_CHOOSER_ACCEPT);
		btnAceptar.setBounds(354, 458, 85, 21);
		btnAceptar.addActionListener(this);
		contentPane.add(btnAceptar);
		
		try {
			cargarFavoritos(favorito);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public JComboBox<String> getComboBoxFavoritos() {
		return comboBoxFavoritos;
	}
	public JButton getBtnAceptar() {
		return btnAceptar;
	}
	public JButton getBtnSalir() {
		return btnSalir;
	}
	public JEditorPane getEditorPane() {
		return editorPane;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<String> getCanciones() {
		return canciones;
	}
	public void setCanciones(ArrayList<String> canciones) {
		this.canciones = canciones;
	}
	public ArrayList<String> getAlbumes() {
		return albumes;
	}
	public void setAlbumes(ArrayList<String> albumes) {
		this.albumes = albumes;
	}
	public ArrayList<String> getArtistas() {
		return artistas;
	}
	public void setArtistas(ArrayList<String> artistas) {
		this.artistas = artistas;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(getBtnSalir())) {
			LogingFrame.getScreen().setEnabled(true);
			dispose();
		}
		if (e.getSource().equals(getBtnAceptar())) {
			// Carga el tipo, su información y lanza una instancion de cada objeto en función a el item seleccionado.
			switch (getTipo()) {
			case "cancion":
				for (String cancion : getCanciones()) {
					if (cancion.contains(";"+getComboBoxFavoritos().getSelectedItem().toString().replace(" - Tablatura", "").replace(" - Acordes", "").replace(" - Letra", ""))) {
						setId(cancion.split(";")[0]);
					}
				}
				Cancion cancion = new Cancion(getId());
				cancion.setVisible(true);
				break;
			case "artista":
				for (String artista : getArtistas()) {
					if (artista.contains(";"+getComboBoxFavoritos().getSelectedItem())) {
						setId(artista.split(";")[0]);
					}
				}
				Artista artista = new Artista(getId());
				artista.setVisible(true);
				break;
			case "album":
				for (String album : getAlbumes()) {
					if (album.contains(";"+getComboBoxFavoritos().getSelectedItem())) {
						setId(album.split(";")[0]);
					}
				}
				Album album = new Album(getId());
				album.setVisible(true);
				break;
			}
			dispose();
		}
	}
	private void cargarFavoritos(String favorito) throws SQLException {
		// Carga datos en función al tipo de dato, y sus objetos
		ResultSet datos;
		String lista="";
		switch (getTipo()) {
			case "cancion":
				datos=ICGuitarDatabase.obtenerCanciones();
				while(datos.next()) {
					if (ICGuitarDatabase.esFavorita(datos.getString("idCancion"), ICGuitar.getUsuarioIniciado(), "cancion")) {
						getCanciones().add(datos.getString("idCancion")+";"+datos.getString("nombre")+";"+ICGuitarDatabase.obtenerTipoCancion(Integer.parseInt(ICGuitarDatabase.obtenerTipoDeCancion(datos.getString("idCancion")))));
					}
				}
				for (String cancion : getCanciones()) {
					lista+="<li style='font-family: segoe ui; font-size: 18px;'>"+cancion.split(";")[1]+"</li>";
					getComboBoxFavoritos().addItem(cancion.split(";")[1]+" - "+cancion.split(";")[2]);
				}
				getEditorPane().setText("<html>"+lista+"</html>");
				if (getCanciones().isEmpty()) {
					getBtnAceptar().setEnabled(false);
					getEditorPane().setText(NO_FAV);
				}
				break;
			case "album":
				datos=ICGuitarDatabase.obtenerAlbumes();
				while(datos.next()) {
					if (ICGuitarDatabase.esFavorita(datos.getString("idAlbum"), ICGuitar.getUsuarioIniciado(), "album")) {
						getAlbumes().add(datos.getString("idAlbum")+";"+datos.getString("nombre"));
					}
				}
				for (String album : getAlbumes()) {
					lista+="<li style='font-family: segoe ui; font-size: 18px;'>"+album.split(";")[1]+"</li>";
					getComboBoxFavoritos().addItem(album.split(";")[1]);
				}
				getEditorPane().setText("<html>"+lista+"</html>");
				if (getAlbumes().isEmpty()) {
					getBtnAceptar().setEnabled(false);
					getEditorPane().setText(NO_FAV);
				}
				break;
			case "artista":
				datos=ICGuitarDatabase.obtenerDatosArtista();
				while(datos.next()) {
					if (ICGuitarDatabase.esFavorita(datos.getString("idArtista"), ICGuitar.getUsuarioIniciado(), "artista")) {
						getArtistas().add(datos.getString("idArtista")+";"+datos.getString("nombre"));
					}
				}
				for (String artista : getArtistas()) {
					lista+="<li style='font-family: segoe ui; font-size: 18px;'>"+artista.split(";")[1]+"</li>";
					getComboBoxFavoritos().addItem(artista.split(";")[1]);
				}
				getEditorPane().setText("<html>"+lista+"</html>");
				if (getArtistas().isEmpty()) {
					getBtnAceptar().setEnabled(false);
					getEditorPane().setText(NO_FAV);
				}
				break;
		}
	}
}
