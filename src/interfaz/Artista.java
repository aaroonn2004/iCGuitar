package interfaz;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.ICGuitar;
import clases.LenguajeTexto;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

import baseDatos.ICGuitarDatabase;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JButton;

public class Artista extends JFrame implements LenguajeTexto, ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblArtista;
	private JLabel lblArtistaImagen;
	private JSeparator separator_2;
	private JLabel lblUsuario;
	private String idArtista;
	private JButton btnSalir;
	private JEditorPane editorPane;
	private JScrollPane scrollPaneBio;
	private JEditorPane editorPanelArtista;
	private JButton btnMarcarFavorita;
	/**
	 * Create the frame.
	 */
	public Artista(String idArtistaUsado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 837, 683);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DatosUsuario.class.getResource("/assets/images/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		contentPane = new JPanel();
		idArtista = idArtistaUsado;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblArtistaImagen = new JLabel("New label");
		lblArtistaImagen.setIcon(null);
		lblArtistaImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtistaImagen.setBounds(10, 10, 174, 174);
		contentPane.add(lblArtistaImagen);
		
		lblArtista = new JLabel(ARTISTA_CANCION);
		lblArtista.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblArtista.setBounds(194, 10, 317, 29);
		contentPane.add(lblArtista);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(10, 194, 791, 2);
		contentPane.add(separator_2);
		
		lblUsuario = new JLabel();
		lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblUsuario.setBounds(20, 160, 342, 24);
		contentPane.add(lblUsuario);
		
		btnSalir = new JButton(DATOS_USUARIO_SALIR);
		btnSalir.setBounds(698, 615, 85, 21);
		btnSalir.addActionListener(this);
		contentPane.add(btnSalir);
		
		editorPane = new JEditorPane();
		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		editorPane.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new JScrollPane(editorPane);
		scrollPane.setBounds(20, 206, 764, 399);
		scrollPane.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		contentPane.add(scrollPane);
		
		editorPanelArtista = new JEditorPane();
		editorPanelArtista.setContentType("text/html");
		editorPanelArtista.setEditable(false);
		
		scrollPaneBio = new JScrollPane(editorPanelArtista);
		scrollPaneBio.setBounds(194, 38, 588, 146);
		contentPane.add(scrollPaneBio);
		
		btnMarcarFavorita = new JButton(FAV_MARCAR);
		btnMarcarFavorita.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnMarcarFavorita.setBounds(517, 615, 168, 21);
		btnMarcarFavorita.addActionListener(this);
		contentPane.add(btnMarcarFavorita);
		
		try {
			try {
				cargarArtista();
				esFavorita();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public JLabel getLblArtista() {
		return lblArtista;
	}
	public JLabel getLblArtistaImagen() {
		return lblArtistaImagen;
	}
	public JButton getBtnSalir() {
		return btnSalir;
	}
	public String getIdArtista() {
		return idArtista;
	}
	public JEditorPane getEditorPane() {
		return editorPane;
	}
	public void setIdArtista(String Artista) {
		this.idArtista = Artista;
	}
	public JEditorPane getEditorPaneArtista() {
		return editorPanelArtista;
	}
	public JButton getBtnMarcarFavorita() {
		return btnMarcarFavorita;
	}
	@SuppressWarnings({ })
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(getBtnSalir())) {
			LogingFrame.getScreen().setEnabled(true);
			dispose();
		}
		if (e.getSource().equals(getBtnMarcarFavorita())) {
			try {
				if (ICGuitarDatabase.esFavorita(getIdArtista(), ICGuitar.getUsuarioIniciado(), "artista")) {
					ICGuitarDatabase.desmarcarFavorita(ICGuitar.getUsuarioIniciado(), getIdArtista(), "artista");
					esFavorita();
				} else {
					ICGuitarDatabase.marcarFavorita(ICGuitar.getUsuarioIniciado(), getIdArtista(), "artista");
					esFavorita();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private void esFavorita() throws SQLException {
		if (ICGuitarDatabase.esFavorita(getIdArtista(), ICGuitar.getUsuarioIniciado(), "artista")) {
			getBtnMarcarFavorita().setText(FAV_DESMARCAR);
		} else {
			getBtnMarcarFavorita().setText(FAV_MARCAR);
		}
	}
	private void cargarArtista() throws SQLException, IOException {
		// Carga las variables del método
	    String idArtista = getIdArtista();
	    String albumesCanciones = "";
	    ArrayList<String> albumes;
	    String bioArtista = ICGuitarDatabase.obtenerBioArtista(idArtista);
	    String nombreArtista = ICGuitarDatabase.obtenerNombreArtista(idArtista);
	    
	    // Carga la información del artista
	    getEditorPaneArtista().setText("<html><p style='font-family: Segoe UI;'>" + bioArtista + "</p></html>");
	    
	    // Imagen
	    BufferedImage fotoArtista = ICGuitarDatabase.obtenerFotoArtista(idArtista);
	    getLblArtista().setText(ARTISTA_CANCION + " " + nombreArtista);
	    setTitle(ARTISTA_CANCION + " " + nombreArtista);
	    ImageIcon imagen = new ImageIcon(fotoArtista);
	    Image img = imagen.getImage().getScaledInstance(getLblArtistaImagen().getWidth(), getLblArtistaImagen().getHeight(), Image.SCALE_FAST);
	    imagen = new ImageIcon(img);
	    getLblArtistaImagen().setIcon(imagen);
	    
	    // Obtener información de los álbumes y sus canciones
	    albumes = ICGuitarDatabase.obtenerAlbumesArtista(idArtista);
	    for (String album : albumes) {
	        String id = album.split(";")[0];
	        String nombre = album.split(";")[1];
	        ArrayList<String> cancionesAlbum = ICGuitarDatabase.obtenerCancionesAlbum(id);
	        albumesCanciones += "<tr><th colspan='2' style='font-family: Segoe UI Black, Segoe UI; font-style: italic; font-weight: bold; font-size: larger; border: 1px solid black; padding: 5px;'>" + nombre + "</th></tr>";
	        for (String cancion : cancionesAlbum) {
	            albumesCanciones += "<tr><td style='border: 1px solid black; padding: 5px;'></td><td style='font-family: Segoe UI; font-size: small; border: 1px solid black; padding: 5px;'>" + cancion.split(";")[0] + "</td></tr>";
	        }
	    }
	    getEditorPane().setText("<html><h1 style='font-family: Segoe UI Black; text-align: center; text-decoration: underline;'>Repertorio de " + nombreArtista + ":</h1><table style='text-align: center;'><tbody>" + albumesCanciones + "</tbody></table></html>");
	}
}
