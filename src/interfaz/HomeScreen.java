package interfaz;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import baseDatos.ICGuitarDatabase;
import clases.ICGuitar;
import clases.LenguajeTexto;
import registroDatos.RegistrarAlbum;
import registroDatos.RegistrarArtista;
import registroDatos.RegistrarCancion;

import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class HomeScreen extends JFrame implements ActionListener, LenguajeTexto {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnAyuda;
	private JMenu mnArchivo;
	private JMenu mnOpciones;
	private JMenuItem mntmFav;
	private JMenuItem mntmTablatura;
	private JMenu mnCancion;
	private JMenuItem mntmMisCanciones;
	private JMenu mnÁlbum;
	private JMenuItem mntmAlbum;
	private JMenuItem mntmFavArtista;
	private JMenuItem mntmCerrarSesion;
	private JSeparator separator;
	private JMenuItem mntmBuscar;
	private JMenuItem mntmPagina;
	private JMenuItem mntmRegistrar;
	private JMenuItem mntmUsuario;
	private JMenuItem mntmSobre;
	private JLabel lblProfilePic;
	private JLabel lblDestacados;
	private JLabel lblUserName;
	private JEditorPane panelEditable;
	private JMenuItem mntmAlbumFav;
	/**
	 * Create the frame.
	 */
	public HomeScreen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeScreen.class.getResource(APP_ICON)));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 835, 650);
		setTitle(TITULO_APP);
		setResizable(false);
		ICGuitar.getLogin().setVisible(false);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArchivo = new JMenu(MENU_ARCHIVO);
		menuBar.add(mnArchivo);
		
		mnCancion = new JMenu(MENU_CANCION);
		mnArchivo.add(mnCancion);
		
		mntmTablatura = new JMenuItem(MENU_CANCION_SUBIR_TAB);
		mntmTablatura.addActionListener(this);
		mnCancion.add(mntmTablatura);
		
		mntmFav = new JMenuItem(MENU_CANCION_FAV);
		mntmFav.addActionListener(this);
		mnCancion.add(mntmFav);
		
		mntmMisCanciones = new JMenuItem(MENU_CANCION_SUBIDAS);
		mntmMisCanciones.addActionListener(this);
		mnCancion.add(mntmMisCanciones);
		
		mnÁlbum = new JMenu(MENU_ALBUM);
		mnArchivo.add(mnÁlbum);
		
		mntmAlbum = new JMenuItem(MENU_ALBUM_NUEVO);
		mntmAlbum.addActionListener(this);
		mnÁlbum.add(mntmAlbum);
		
		mntmAlbumFav = new JMenuItem(MENU_CUALQUIER_FAV);
		mntmAlbumFav.addActionListener(this);
		mnÁlbum.add(mntmAlbumFav);
		
		JMenu mnArtistas = new JMenu(MENU_ARTISTAS);
		mnArchivo.add(mnArtistas);
		
		mntmFavArtista = new JMenuItem(MENU_CUALQUIER_FAV);
		mntmFavArtista.addActionListener(this);
		mnArtistas.add(mntmFavArtista);
		
		mntmRegistrar = new JMenuItem(MENU_ARTISTA_REGISTRAR);
		mntmRegistrar.addActionListener(this);
		mnArtistas.add(mntmRegistrar);
		
		mntmBuscar = new JMenuItem(MENU_ARCHIVO_BUSCAR);
		mntmBuscar.addActionListener(this);
		mnArchivo.add(mntmBuscar);
		
		mnOpciones = new JMenu(MENU_OPCIONES);
		menuBar.add(mnOpciones);
		
		JMenu mnUsuario = new JMenu(MENU_USUARIO);
		mnOpciones.add(mnUsuario);
		
		mntmUsuario = new JMenuItem(MENU_USUARIO_MIS_DATOS);
		mntmUsuario.addActionListener(this);
		mnUsuario.add(mntmUsuario);
		
		mntmCerrarSesion = new JMenuItem(MENU_USUARIO_CERRAR_SESION);
		mntmCerrarSesion.addActionListener(this);
		mnUsuario.add(mntmCerrarSesion);
		
		mnAyuda = new JMenu(MENU_AYUDA);
		menuBar.add(mnAyuda);
		
		mntmPagina = new JMenuItem(MENU_AYUDA_ICGUITAR);
		mntmPagina.addActionListener(this);
		mnAyuda.add(mntmPagina);
		
		mntmSobre = new JMenuItem(MENU_AYUDA_ACERCA_DE);
		mntmSobre.addActionListener(this);
		mnAyuda.add(mntmSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTituloApp = new JLabel(TITULO_APP);
		lblTituloApp.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 26));
		lblTituloApp.setBounds(10, 0, 111, 38);
		contentPane.add(lblTituloApp);
		
		separator = new JSeparator();
		separator.setBounds(-11, 48, 832, 2);
		contentPane.add(separator);
		
		lblUserName = new JLabel("<html><h3>"+"@"+ICGuitar.getUsuarioIniciado()+"</h3></html>");
		lblUserName.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblUserName.setBounds(662, 26, 111, 18);
		contentPane.add(lblUserName);
		
		lblProfilePic = new JLabel("");
		lblProfilePic.setIcon(null);
		lblProfilePic.setBounds(779, 14, 32, 32);
		contentPane.add(lblProfilePic);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 60, 801, 521);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblDestacados = new JLabel("<html><h1><u>"+ARTISTAS_DESTACADOS+"</u></h1></html>");
		lblDestacados.setFont(new Font("Segoe UI Semilight", Font.BOLD, 18));
		lblDestacados.setBounds(0, 0, 791, 52);
		panel.add(lblDestacados);
		
		panelEditable = new JEditorPane();
		panelEditable.setEditable(false);
		panelEditable.setBounds(0, 52, 791, 469);
		panelEditable.setContentType("text/html");

		JScrollPane scrollPane = new JScrollPane(panelEditable);
		scrollPane.setViewportBorder(UIManager.getBorder("ScrollPane.border"));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 59, 791, 452);
		panel.add(scrollPane);	
		
		
		cargarArtistas();
		
		try {
			mostrarFotoPerfil();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JMenuItem getMntmCerrarSesion() {
		return mntmCerrarSesion;
	}
	public JMenuItem getMntmPagina() {
		return mntmPagina;
	}
	public JMenuItem getMntmRegistrarArtista() {
		return mntmRegistrar;
	}
	public JMenuItem getMntmUsuarioDatos() {
		return mntmUsuario;
	}
	public JMenuItem getMntmSobreApp() {
		return mntmSobre;
	}
	public JLabel getLblProfilePic() {
		return lblProfilePic;
	}
	public JMenu getMnAyuda() {
		return mnAyuda;
	}
	public JMenu getMnArchivo() {
		return mnArchivo;
	}
	public JMenu getMnOpciones() {
		return mnOpciones;
	}
	public JMenuItem getMntmFav() {
		return mntmFav;
	}
	public JMenuItem getMntmTablatura() {
		return mntmTablatura;
	}
	public JMenu getMnCanción() {
		return mnCancion;
	}
	public JMenuItem getMntmMisCanciones() {
		return mntmMisCanciones;
	}
	public JMenu getMnÁlbum() {
		return mnÁlbum;
	}
	public JMenuItem getMntmAlbum() {
		return mntmAlbum;
	}
	public JMenuItem getMntmFavArtista() {
		return mntmFavArtista;
	}
	public JLabel getLblUserName() {
		return lblUserName;
	}
	public JEditorPane getEp() {
		return panelEditable;
	}
	public JMenuItem getMntmBuscar() {
		return mntmBuscar;
	}
	public JMenuItem getMntmAlbumFav() {
		return mntmAlbumFav;
	}
	
	public void mostrarFotoPerfil() throws SQLException {
		try {
			ImageIcon imagen = new ImageIcon(ICGuitarDatabase.obtenerFotoPerfil(ICGuitar.getUsuarioIniciado()));
			Image img = imagen.getImage().getScaledInstance(getLblProfilePic().getWidth(), getLblProfilePic().getHeight(), Image.SCALE_FAST);
			imagen = new ImageIcon(img);
			getLblProfilePic().setIcon(imagen);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void cargarArtistas() {
	    final int MAX_ARTISTAS = 6;
	    String textoArtista = "<html><body style='background-color: #f0f0f0; margin=0; padding=0;'>";
	    ResultSet artistas;
	    try {
	        artistas = ICGuitarDatabase.obtenerDatosArtista();
	        int totalArtistas = 0;
	        while (artistas.next() && totalArtistas < MAX_ARTISTAS) {
	        	String nombreArtista = artistas.getString(1);
	            String idArtista = artistas.getString(2);
	            String bioArtista = ICGuitarDatabase.obtenerBioArtista(idArtista);
	            textoArtista += "<h2 style='font-size: 24px; color: #333; font-family: Arial, sans-serif; margin-bottom: 8px;'>" + nombreArtista + "</h2>" +
	                    "<p style='font-size: 16px; color: #666; font-family: Arial, sans-serif; line-height: 1.6;'>" + bioArtista + "</p>" +
	                    "<br/><hr style='border: 0; height: 1px; background-color: #ccc; margin-top: 20px;'/>";

	            totalArtistas++;
	            System.out.println(textoArtista);
	        }
	        if (totalArtistas == 0) {
	        	textoArtista+=("<p style='font-size: 16px; color: #666; font-family: Arial, sans-serif; line-height: 1.6;'><b>"+ARTISTAS_NO_ENCONTRADOS)+"</b></p>";
	        }
	        textoArtista+="</body></html>";
	        getEp().setText(textoArtista);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(getMntmCerrarSesion())) {
			if (ICGuitarDatabase.conectar()!=null) {
				ICGuitar.getLogin().setVisible(true);
	            dispose();
	    	} else {
	    		JOptionPane.showMessageDialog(null, ERROR_RED_DB, ERROR_RED, JOptionPane.ERROR_MESSAGE);
	    	}
		}
		if (e.getSource().equals(getMntmPagina())) {
			String url = "http://localhost/icguitar";
			try {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource().equals(getMntmAlbum())) {
			RegistrarAlbum registrar = new RegistrarAlbum();
			registrar.setVisible(true);
			setEnabled(false);
		}
		if (e.getSource().equals(getMntmAlbumFav())) {
			Favoritos albumFav = new Favoritos("album");
			albumFav.setVisible(true);
			setEnabled(false);
		}
		if (e.getSource().equals(getMntmFavArtista())) {
			Favoritos artistaFav = new Favoritos("artista");
			artistaFav.setVisible(true);
			setEnabled(false);
		}
		if (e.getSource().equals(getMntmFav())) {
			Favoritos cancionFav = new Favoritos("cancion");
			cancionFav.setVisible(true);
			setEnabled(false);
		}
		if (e.getSource().equals(getMntmRegistrarArtista())) {
			RegistrarArtista registrar = new RegistrarArtista();
			registrar.setVisible(true);
			setEnabled(false);
		}
		if (e.getSource().equals(getMntmUsuarioDatos())) {
			DatosUsuario datos = new DatosUsuario();
			datos.setVisible(true);
			setEnabled(false);
		}
		if (e.getSource().equals(getMntmSobreApp())) {
			JOptionPane.showMessageDialog(null, ACERCA_DE, MENU_AYUDA_ACERCA_DE, JOptionPane.INFORMATION_MESSAGE);
		}
		if (e.getSource().equals(getMntmTablatura())) {
			RegistrarCancion cancion = new RegistrarCancion();
			cancion.setVisible(true);
			setEnabled(false);
		}
		if (e.getSource().equals(getMntmBuscar())) {
			Buscar buscar = new Buscar();
			buscar.setVisible(true);
			setEnabled(false);
		}
		if (e.getSource().equals(getMntmMisCanciones())) {
			MisCanciones mis = new MisCanciones();
			mis.setVisible(true);
			setEnabled(false);
		}
	}
}
