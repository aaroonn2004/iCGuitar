package interfaz;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
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

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Album extends JFrame implements LenguajeTexto, ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblArtista;
	private JLabel lblAlbum;
	private JLabel lblAlbumImagen;
	private JSeparator separator_2;
	private JLabel lblUsuario;
	private String idAlbum;
	private JButton btnSalir;
	private JButton btnMarcarFavorita;
	private JEditorPane editorPane;
	private JComboBox<String> cmbCanciones;
	private ArrayList<String> canciones;
	private JButton btnAceptar;
	private String idCancion;
	/**
	 * Create the frame.
	 */
	public Album(String idAlbumUsado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 837, 683);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DatosUsuario.class.getResource("/assets/images/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		contentPane = new JPanel();
		idAlbum = idAlbumUsado;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAlbumImagen = new JLabel("New label");
		lblAlbumImagen.setIcon(null);
		lblAlbumImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlbumImagen.setBounds(10, 10, 174, 140);
		contentPane.add(lblAlbumImagen);
		
		lblArtista = new JLabel(ARTISTA_CANCION);
		lblArtista.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblArtista.setBounds(194, 38, 317, 29);
		contentPane.add(lblArtista);
		
		lblAlbum = new JLabel(ALBUM_CANCION);
		lblAlbum.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblAlbum.setBounds(194, 10, 317, 29);
		contentPane.add(lblAlbum);
		
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
		
		btnMarcarFavorita = new JButton(FAV_MARCAR);
		btnMarcarFavorita.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnMarcarFavorita.setBounds(194, 129, 168, 21);
		btnMarcarFavorita.addActionListener(this);
		contentPane.add(btnMarcarFavorita);
		
		cmbCanciones = new JComboBox<String>();
		cmbCanciones.setBounds(194, 98, 168, 21);
		contentPane.add(cmbCanciones);
		
		btnAceptar = new JButton(FILE_CHOOSER_ACCEPT);
		btnAceptar.setBounds(603, 615, 85, 21);
		btnAceptar.addActionListener(this);
		contentPane.add(btnAceptar);
		
		try {
			try {
				cargarAlbum();
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
	public JLabel getLblAlbum() {
		return lblAlbum;
	}
	public JLabel getLblAlbumImagen() {
		return lblAlbumImagen;
	}
	public JButton getBtnSalir() {
		return btnSalir;
	}
	public String getIdAlbum() {
		return idAlbum;
	}
	public JEditorPane getEditorPane() {
		return editorPane;
	}
	public void setIdAlbum(String idAlbum) {
		this.idAlbum = idAlbum;
	}
	public JButton getBtnMarcarFavorita() {
		return btnMarcarFavorita;
	}
	public JComboBox<String> getCmbCanciones() {
		return cmbCanciones;
	}
	public String getIdCancion() {
		return idCancion;
	}
	public void setIdCancion(String idCancion) {
		this.idCancion = idCancion;
	}
	public JButton getBtnAceptar() {
		return btnAceptar;
	}
	public void setCanciones(ArrayList<String> canciones) {
		this.canciones = canciones;
	}
	public ArrayList<String> getCanciones() {
		return this.canciones;
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
				if (ICGuitarDatabase.esFavorita(getIdAlbum(), ICGuitar.getUsuarioIniciado(), "album")) {
					ICGuitarDatabase.desmarcarFavorita(ICGuitar.getUsuarioIniciado(), getIdAlbum(), "album");
					esFavorita();
				} else {
					ICGuitarDatabase.marcarFavorita(ICGuitar.getUsuarioIniciado(), getIdAlbum(), "album");
					esFavorita();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource().equals(getBtnAceptar())) {
			for (String str : getCanciones()) {
				if (str.contains(getCmbCanciones().getSelectedItem()+";"))
				setIdCancion(str.split(";")[1]);
			}
			Cancion cancion = new Cancion(getIdCancion());
			cancion.setVisible(true);
		}
	}
	private void esFavorita() throws SQLException {
		if (ICGuitarDatabase.esFavorita(getIdAlbum(), ICGuitar.getUsuarioIniciado(), "album")) {
			getBtnMarcarFavorita().setText(FAV_DESMARCAR);
		} else {
			getBtnMarcarFavorita().setText(FAV_MARCAR);
		}
	}
	private void cargarAlbum() throws SQLException, IOException {
		// Define las variables del método
		String idAlbum = getIdAlbum();
		String idArtista="";
		String cancionNombre="";
		ResultSet album = ICGuitarDatabase.obtenerAlbum(idAlbum);
		while(album.next()) {
			// Mientras el ResultSet tenga resultados, cambiará el label del Album a su nombre y cambiará el título de la ventana
			getLblAlbum().setText(getLblAlbum().getText()+" "+album.getString("nombre"));
			setTitle(getLblAlbum().getText());
			
			// Recogerá la imagen de la base de datos y la escalará al label
			ImageIcon imagen = new ImageIcon(ImageIO.read(album.getBinaryStream("fotoAlbum")));
			Image img = imagen.getImage().getScaledInstance(getLblAlbumImagen().getWidth(), getLblAlbumImagen().getHeight(), Image.SCALE_FAST);
			imagen = new ImageIcon(img);
			getLblAlbumImagen().setIcon(imagen);
			
			// Obtiene datos de los artistas y las canciones
			idArtista=album.getString("idArtistaAux");
			getLblArtista().setText(getLblArtista().getText()+" "+ICGuitarDatabase.obtenerNombreArtista(idArtista));
			setCanciones(ICGuitarDatabase.obtenerCancionesAlbum(idAlbum));
			
			// Dibuja con HTML de manera ordenada las canciones
			for (String cancion : getCanciones()) {
				cancionNombre+=("<li style='font-family: Arial, sans-serif; margin-bottom: 8px;'>" + cancion.split(";")[0] + ".</li>");
				getCmbCanciones().addItem(cancion.split(";")[0]);
			}
			if (canciones.isEmpty()) {
				getBtnAceptar().setEnabled(false);
			}
			getEditorPane().setText("<html><h3 style='font-family: Segoe UI; font-weight: 500;'>"+String.format(CANCIONES_ALBUM, album.getString("nombre"))+"</h3><ul>"+cancionNombre+"</ul></html>");
		}
		// Cerrar ResultSet
		album.close();
	}
}