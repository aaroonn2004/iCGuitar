package interfaz;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import baseDatos.ICGuitarDatabase;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JButton;

public class Cancion extends JFrame implements LenguajeTexto, ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCancionTitulo;
	private JEditorPane editorPane;
	private JEditorPane editorPaneArtista;
	private JLabel lblArtista;
	private JLabel lblAlbum;
	private JLabel lblArtistaImagen;
	private JLabel lblAlbumImagen;
	private JSeparator separator_2;
	private JLabel lblDificultad;
	private JLabel lblUsuario;
	private JTextArea txaComentario;
	private JButton btnEnviarComentario;
	private String idCancion;
	private JScrollPane scpComentarios;
	private JEditorPane jepComentarios;
	private JButton btnVideo;
	private String url = "";
	private JButton btnMarcarFavorita;
	private JButton btnSalir;
	private JLabel lblTotalComentarios;
	private JLabel lblGenero;
	private JLabel lblTipoCancion;

	/**
	 * Create the frame.
	 */
	public Cancion(String idCancionUsada) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 837, 726);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DatosUsuario.class.getResource("/assets/images/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		contentPane = new JPanel();
		idCancion = idCancionUsada;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCancionTitulo = new JLabel(TITULO_CANCION);
		lblCancionTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblCancionTitulo.setBounds(496, 153, 317, 29);
		contentPane.add(lblCancionTitulo);
		
		lblAlbumImagen = new JLabel("New label");
		lblAlbumImagen.setIcon(null);
		lblAlbumImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlbumImagen.setBounds(577, 21, 147, 133);
		contentPane.add(lblAlbumImagen);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(484, 21, 2, 615);
		contentPane.add(separator);
		
		lblArtista = new JLabel(ARTISTA_CANCION);
		lblArtista.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblArtista.setBounds(496, 231, 317, 29);
		contentPane.add(lblArtista);
		
		lblAlbum = new JLabel(ALBUM_CANCION);
		lblAlbum.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblAlbum.setBounds(496, 192, 317, 29);
		contentPane.add(lblAlbum);
		
		lblArtistaImagen = new JLabel("New label");
		lblArtistaImagen.setIcon(null);
		lblArtistaImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtistaImagen.setBounds(616, 395, 94, 80);
		contentPane.add(lblArtistaImagen);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(484, 372, 329, 2);
		contentPane.add(separator_1);
		
		editorPaneArtista = new JEditorPane();
		editorPaneArtista.setContentType("text/html");
		editorPaneArtista.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		editorPaneArtista.setEditable(false);
		editorPaneArtista.setBounds(521, 498, 262, 127);
		
		JScrollPane scrollPaneArtista = new JScrollPane(editorPaneArtista);
		scrollPaneArtista.setBounds(496, 485, 301, 151);
		contentPane.add(scrollPaneArtista);
		
		editorPane = new JEditorPane();
		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		editorPane.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		contentPane.add(editorPane);
		
		JScrollPane scrPaneCancion = new JScrollPane(editorPane);
		scrPaneCancion.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrPaneCancion.setBounds(10, 21, 463, 305);
		contentPane.add(scrPaneCancion);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(0, 372, 486, 2);
		contentPane.add(separator_2);
		
		lblDificultad = new JLabel(DIFICULTAD_CANCION);
		lblDificultad.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDificultad.setBounds(496, 270, 317, 29);
		contentPane.add(lblDificultad);
		
		lblUsuario = new JLabel();
		lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblUsuario.setBounds(10, 0, 342, 24);
		contentPane.add(lblUsuario);
		
		txaComentario = new JTextArea();
		txaComentario.setLineWrap(true);
		txaComentario.setBounds(10, 395, 329, 34);
		contentPane.add(txaComentario);
		
		btnEnviarComentario = new JButton(COMENTARIO_CANCION);
		btnEnviarComentario.setBounds(349, 395, 125, 34);
		btnEnviarComentario.addActionListener(this);
		contentPane.add(btnEnviarComentario);
		
		btnMarcarFavorita = new JButton(FAVORITA_CANCION);
		btnMarcarFavorita.setBounds(260, 350, 214, 21);
		btnMarcarFavorita.addActionListener(this);
		contentPane.add(btnMarcarFavorita);
		
		jepComentarios = new JEditorPane();
		jepComentarios.setContentType("text/html");
		jepComentarios.setEditable(false);
		jepComentarios.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		scpComentarios = new JScrollPane(jepComentarios);
		scpComentarios.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		scpComentarios.setBounds(10, 463, 463, 173);
		contentPane.add(scpComentarios);
		
		btnVideo = new JButton(CLIP_CANCION);
		btnVideo.setBounds(10, 350, 214, 21);
		btnVideo.addActionListener(this);
		contentPane.add(btnVideo);
		
		btnSalir = new JButton(DATOS_USUARIO_SALIR);
		btnSalir.setBounds(712, 658, 85, 21);
		btnSalir.addActionListener(this);
		contentPane.add(btnSalir);
		
		lblTotalComentarios = new JLabel(TOTAL_COMENTARIOS);
		lblTotalComentarios.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTotalComentarios.setBounds(10, 429, 464, 34);
		contentPane.add(lblTotalComentarios);
		
		lblGenero = new JLabel(CANCIONES_GENERO);
		lblGenero.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblGenero.setBounds(496, 309, 317, 29);
		contentPane.add(lblGenero);
		
		lblTipoCancion = new JLabel("Género: ");
		lblTipoCancion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTipoCancion.setBounds(10, 320, 317, 29);
		contentPane.add(lblTipoCancion);
		
		try {
			try {
				esFavorita();
				cargarCancion(idCancionUsada);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JLabel getLblCancionTitulo() {
		return lblCancionTitulo;
	}
	public JEditorPane getEditorPane() {
		return editorPane;
	}
	public JEditorPane getEditorPaneArtista() {
		return editorPaneArtista;
	}
	public JLabel getLblArtista() {
		return lblArtista;
	}
	public JLabel getLblAlbum() {
		return lblAlbum;
	}
	public JLabel getLblArtistaImagen() {
		return lblArtistaImagen;
	}
	public JLabel getLblAlbumImagen() {
		return lblAlbumImagen;
	}
	public JLabel getLblDificultad() {
		return lblDificultad;
	}
	public JEditorPane getJepComentario() {
		return jepComentarios;
	}
	public JTextArea getTxaComentario() {
		return txaComentario;
	}
	public JButton getBtnEnviarComentario() {
		return btnEnviarComentario;
	}
	public String getIdCancionUsada() {
		return idCancion;
	}
	public JButton getBtnVideo() {
		return btnVideo;
	}
	public JButton getBtnMarcarFavorita() {
		return btnMarcarFavorita;
	}
	public JButton getBtnSalir() {
		return btnSalir;
	}
	public JLabel getLblTotalComentarios() {
		return lblTotalComentarios;
	}
	public JLabel getLblGenero() {
		return lblGenero;
	}
	@SuppressWarnings({ "deprecation" })
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(getBtnEnviarComentario())) {
			if (!getTxaComentario().getText().equals("")) {
				ICGuitarDatabase.comentar(ICGuitar.getUsuarioIniciado(), getIdCancionUsada(), getTxaComentario().getText().trim());
				getTxaComentario().setText("");
				try {
					recargarComentarios(getIdCancionUsada());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource().equals(getBtnVideo())) {
			try {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource().equals(getBtnSalir())) {
			LogingFrame.getScreen().setEnabled(true);
			dispose();
		}
		if (e.getSource().equals(getBtnMarcarFavorita())) {
			try {
				if (ICGuitarDatabase.esFavorita(getIdCancionUsada(), ICGuitar.getUsuarioIniciado(), "cancion")) {
					ICGuitarDatabase.desmarcarFavorita(ICGuitar.getUsuarioIniciado(), getIdCancionUsada(), "cancion");
					esFavorita();
				} else {
					ICGuitarDatabase.marcarFavorita(ICGuitar.getUsuarioIniciado(), getIdCancionUsada(), "cancion");
					esFavorita();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private void cargarCancion(String idCancionUsada) throws SQLException, IOException {
		// Declaración de variables
		String id = idCancionUsada;
		String idAlbum = "";
		String idArtista="";
		String dificultad;
		
		// Carga información relevante a las canciones y sus álbumes
		ResultSet cancion = ICGuitarDatabase.obtenerCancion(id);
		ResultSet idAlbumCancion = ICGuitarDatabase.obtenerAlbumCancion(id);
		while(idAlbumCancion.next()) {
			idAlbum=idAlbumCancion.getString("idAlbumAux");
		}
		ResultSet album = ICGuitarDatabase.obtenerAlbum(idAlbum);
		while(album.next()) {
			getLblAlbum().setText(getLblAlbum().getText()+" "+album.getString("nombre"));
			ImageIcon imagen = new ImageIcon(ImageIO.read(album.getBinaryStream("fotoAlbum")));
			Image img = imagen.getImage().getScaledInstance(getLblAlbumImagen().getWidth(), getLblAlbumImagen().getHeight(), Image.SCALE_FAST);
			imagen = new ImageIcon(img);
			getLblAlbumImagen().setIcon(imagen);
			idArtista=album.getString("idArtistaAux");
		}
		
		// Muestra la información con HTML
		while(cancion.next()) {
			getLblCancionTitulo().setText(getLblCancionTitulo().getText()+" "+cancion.getString("nombre"));
			setTitle(TITULO_APP+" - "+cancion.getString("nombre"));
			dificultad = cancion.getString("idDificultadAux");
			getEditorPane().setText("<html><p style='font-family: segoe ui; font-size:16px; color: black;'>" + cancion.getString("cancion") + "</p></html>");
			getLblTipoCancion().setText(ICGuitarDatabase.obtenerTipoCancion(cancion.getInt("idTipoAux")));
			for (String df : ICGuitar.getDificultad()) {
				if (df.contains(";"+dificultad)) {
					dificultad=df.split(";")[0];
					getLblDificultad().setText(getLblDificultad().getText()+" "+dificultad);
					break;
				}
			}
			getLblTotalComentarios().setText(TOTAL_COMENTARIOS+cancion.getInt("totalComentarios"));
			url=cancion.getString("videoCancion");
		}
		
		// Recarga los comentarios y carga la información
		recargarComentarios(id);
		String bioArtista = ICGuitarDatabase.obtenerBioArtista(idArtista);
		BufferedImage fotoArtista = ICGuitarDatabase.obtenerFotoArtista(idArtista);
		ImageIcon imagen = new ImageIcon(fotoArtista);
		Image img = imagen.getImage().getScaledInstance(getLblArtistaImagen().getWidth(), getLblArtistaImagen().getHeight(), Image.SCALE_FAST);
		imagen = new ImageIcon(img);
		getLblArtistaImagen().setIcon(imagen);
		getEditorPaneArtista().setText("<html><h2 style='font-family: Segoe UI; font-weight: 500;'>"+ICGuitarDatabase.obtenerNombreArtista(idArtista)+"</h2><p style='font-family: Segoe UI;'>"+bioArtista+"</p></html>");
		getLblArtista().setText(getLblArtista().getText()+" "+ICGuitarDatabase.obtenerNombreArtista(idArtista));
		String nickname = ICGuitarDatabase.obtenerUsuarioCancion(id);
		getLblGenero().setText(getLblGenero().getText()+ICGuitarDatabase.obtenerGeneroCancion(id));
		lblUsuario.setText(String.format(USUARIO_CANCION, nickname));
		// Cerrar ResultSet
		cancion.close();
		album.close();
		idAlbumCancion.close();
	}
	
	private void recargarComentarios(String id) throws SQLException {
		String textoComentarios = "";
		ResultSet comentarios = ICGuitarDatabase.obtenerComentarios(id);
		ResultSet cancion = ICGuitarDatabase.obtenerCancion(id);
		while(comentarios.next()) {
			textoComentarios += "<p style='font-family: Segoe UI;'>"+comentarios.getString(3)+"</p><h4 style='font-family: Segoe UI; font-weight: 500;'>@"+comentarios.getString(1)+"</h4><hr>";
		}
		while(cancion.next()) {
			getLblTotalComentarios().setText(TOTAL_COMENTARIOS+cancion.getInt("totalComentarios"));
		}
		getJepComentario().setText(textoComentarios);
	}
	
	private void esFavorita() throws SQLException {
		if (ICGuitarDatabase.esFavorita(getIdCancionUsada(), ICGuitar.getUsuarioIniciado(), "cancion")) {
			getBtnMarcarFavorita().setText(DESMARCAR_CANCION);
		} else {
			getBtnMarcarFavorita().setText(FAVORITA_CANCION);
		}
	}
	public JLabel getLblTipoCancion() {
		return lblTipoCancion;
	}
}
