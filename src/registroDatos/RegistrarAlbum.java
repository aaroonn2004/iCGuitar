package registroDatos;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import clases.ICGuitar;
import clases.LenguajeTexto;
import interfaz.HomeScreen;
import interfaz.LogingFrame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import baseDatos.ICGuitarDatabase;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.beans.PropertyChangeEvent;
import java.util.Date;

public class RegistrarAlbum extends JFrame implements LenguajeTexto, ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txfNombreAlbum;
	private JTextField txfArtista;
	private JButton btnPortada;
	private static String imageFile = "";
	private JLabel lblFechaDePublicacin;
	private JLabel lblPortada;
	private JLabel lblNombreAlbum;
	private JLabel lblArtistaNombre;
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JDateChooser dateChooser;
	private JLabel lblView;
	private JComboBox<String> comboBox;
	private String idArtista;
	private String nombreArtista;

	/**
	 * Create the frame.
	 */
	public RegistrarAlbum() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeScreen.class.getResource(APP_ICON)));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 835, 650);
		setTitle(UTILITY_ALBUM_REGISTRAR);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTituloApp = new JLabel(TITULO_APP);
		lblTituloApp.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 26));
		lblTituloApp.setBounds(10, 0, 111, 38);
		contentPane.add(lblTituloApp);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-11, 48, 832, 2);
		contentPane.add(separator);
		
		btnCancelar = new JButton(REGISTRAR_ALBUM_CANCELAR);
		btnCancelar.setBounds(726, 582, 85, 21);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
		
		btnRegistrar = new JButton(REGISTRAR_ALBUM_REGISTRAR);
		btnRegistrar.setBounds(569, 582, 147, 21);
		btnRegistrar.addActionListener(this);
		contentPane.add(btnRegistrar);
		
		JLabel lblNombre = new JLabel(REGISTRAR_ALBUM_NOMBRE);
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNombre.setBounds(75, 163, 176, 21);
		contentPane.add(lblNombre);
		
		txfNombreAlbum = new JTextField();
		txfNombreAlbum.addActionListener(this);
		txfNombreAlbum.setBounds(75, 194, 203, 19);
		contentPane.add(txfNombreAlbum);
		txfNombreAlbum.setColumns(10);
		
		JLabel lblFecha = new JLabel(REGISTRAR_ALBUM_FECHA);
		lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblFecha.setBounds(75, 223, 176, 21);
		contentPane.add(lblFecha);
		
		JLabel lblArtista = new JLabel(REGISTRAR_ALBUM_ARTISTA);
		lblArtista.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblArtista.setBounds(75, 283, 287, 21);
		contentPane.add(lblArtista);
		
		txfArtista = new JTextField();
		txfArtista.addActionListener(this);
		txfArtista.setColumns(10);
		txfArtista.setBounds(75, 314, 203, 19);
		contentPane.add(txfArtista);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(75, 343, 203, 21);
		comboBox.addActionListener(this);
		contentPane.add(comboBox);
		
		btnPortada = new JButton(REGISTRAR_ALBUM_SELECCION_PORTADA);
		btnPortada.addActionListener(this);
		btnPortada.setBounds(75, 379, 203, 21);
		contentPane.add(btnPortada);
		
		lblPortada = new JLabel(REGISTRAR_ALBUM_PORTADA_PLACEHOLDER);
		lblPortada.setIcon(null);
		lblPortada.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPortada.setHorizontalAlignment(SwingConstants.CENTER);
		lblPortada.setBounds(413, 98, 398, 342);
		contentPane.add(lblPortada);
		
		lblNombreAlbum = new JLabel("<html><p>"+REGISTRAR_ALBUM_NOMBRE+"</p></html>");
		lblNombreAlbum.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNombreAlbum.setBounds(413, 450, 398, 29);
		contentPane.add(lblNombreAlbum);
		
		lblArtistaNombre = new JLabel("<html><p>"+REGISTRAR_ALBUM_ARTISTA+"</p></html>");
		lblArtistaNombre.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblArtistaNombre.setBounds(413, 512, 398, 29);
		contentPane.add(lblArtistaNombre);
		
		lblFechaDePublicacin = new JLabel("<html><p>"+REGISTRAR_ALBUM_FECHA+"</p></html>");
		lblFechaDePublicacin.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblFechaDePublicacin.setBounds(412, 481, 399, 29);
		contentPane.add(lblFechaDePublicacin);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(372, 96, 2, 476);
		contentPane.add(separator_1);
		
		dateChooser = new JDateChooser();
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			String fechaPub;
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getSource().equals(getDateChooser())) {
					if (evt.getPropertyName().equals("date")) {
						if (getDateChooser().getDate()!=null) {
				    		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
				    		fechaPub = String.valueOf(formatoFecha.format(getDateChooser().getDate()));
				    		fechaPub=fechaPub.replaceAll("-", "/");
				    		getLblFechaDePublicacin().setText("<html><p>" + REGISTRAR_ALBUM_FECHA + " " + fechaPub + "</p></html>");	    	
				    	}
					}
				}
			}
		});
		dateChooser.setBounds(75, 254, 203, 19);
		contentPane.add(dateChooser);
		
		lblView = new JLabel(REGISTRAR_PREVIEW);
		lblView.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblView.setBounds(413, 73, 176, 21);
		contentPane.add(lblView);
		
		// Añade listeners para los textos de cada elemento
		getTxfNombreAlbum().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				getLblNombreAlbum().setText("<html><p>" + REGISTRAR_ALBUM_NOMBRE + " " + getTxfNombreAlbum().getText() + "</p></html>");
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				getLblNombreAlbum().setText("<html><p>" + REGISTRAR_ALBUM_NOMBRE + " " + getTxfNombreAlbum().getText() + "</p></html>");
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				getLblNombreAlbum().setText("<html><p>" + REGISTRAR_ALBUM_NOMBRE + " " + getTxfNombreAlbum().getText() + "</p></html>");
			}
			
		});
		getTxfArtista().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				mostrarNombreCombobox();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				mostrarNombreCombobox();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				mostrarNombreCombobox();
			}
			
		});
	}
	public JButton getBtnPortada() {
		return btnPortada;
	}
	public JLabel getLblFechaDePublicacin() {
		return lblFechaDePublicacin;
	}
	public JLabel getLblPortada() {
		return lblPortada;
	}
	public JLabel getLblNombreAlbum() {
		return lblNombreAlbum;
	}
	public JLabel getLblArtistaNombre() {
		return lblArtistaNombre;
	}
	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}
	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	public JTextField getTxfNombreAlbum() {
		return txfNombreAlbum;
	}
	public JDateChooser getDateChooser() {
		return dateChooser;
	}
	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	public JTextField getTxfArtista() {
		return txfArtista;
	}
	
	protected void setIdArtista(String artista) {
		this.idArtista = artista;
	}
	
	protected String getIdArtista() {
		return this.idArtista;
	}
	
	protected void setNombreArtista(String artista) {
		this.nombreArtista = artista;
	}
	
	protected String getNombreArtista() {
		return this.nombreArtista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnPortada)) {
	        JFileChooser fileChooser = new JFileChooser();

	        FileNameExtensionFilter filter = new FileNameExtensionFilter(FILE_EXTENSION_IMAGE, "jpg", "png", "gif");
	        fileChooser.setFileFilter(filter);
	        int result = fileChooser.showOpenDialog(null);

	        // Comprobará que el archivo sea válido y guardará su ruta
	        if (result == JFileChooser.APPROVE_OPTION) {
	            java.io.File selectedFile = fileChooser.getSelectedFile();
	            System.out.println("Archivo: " + selectedFile.getAbsolutePath());
	            imageFile=selectedFile.getAbsolutePath();
	            try {
					ImageIcon imagen = new ImageIcon(selectedFile.toURI().toURL());
					Image img = imagen.getImage().getScaledInstance(getLblPortada().getWidth(), getLblPortada().getHeight(), Image.SCALE_FAST);
					imagen = new ImageIcon(img);
					getLblPortada().setIcon(imagen);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	        

	    }
		
		if (e.getSource().equals(getBtnRegistrar())) {
			// Registra el album con la información proporcionada
			if (getTxfArtista()==null || getTxfNombreAlbum()==null || imageFile==null) {
				return;
			} else {
				System.out.println("Titulo: "+getTxfNombreAlbum().getText());
				System.out.println("Fecha de publicación: "+getDateChooser().getDate());
				System.out.println("Artista: "+getComboBox().getSelectedItem());
				
				if (getTxfNombreAlbum().getText().equals("")  || getComboBox().getSelectedItem()==null || getDateChooser().getDate()==null || getDateChooser().getDate().toString().equals("d MMM y")) {
					JOptionPane.showMessageDialog(null, ERROR_REGISTRO_CAMPOS_BLANCO, ERROR_REGISTRO, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (getComboBox().getSelectedItem()==null) {
			        JOptionPane.showMessageDialog(null, ERROR_REGISTRO_ARTISTA_NO_ENCONTRADO, ERROR_REGISTRO, JOptionPane.ERROR_MESSAGE);
			        return;
			    }
				
				if (getDateChooser().toString().equals("d MMM y")) {
					JOptionPane.showMessageDialog(null, ERROR_REGISTRO_FECHA_INVALIDA, ERROR_REGISTRO, JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (imageFile=="") {
					JOptionPane.showMessageDialog(null, ERROR_REGISTRO_NO_PORTADA, ERROR_REGISTRO, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				registrarAlbum();
			}
		}
		if (e.getSource().equals(getComboBox())) {
			if (getComboBox().getSelectedItem()!=null) {
				getLblArtistaNombre().setText("<html><p>" + REGISTRAR_ALBUM_ARTISTA + " " + getComboBox().getSelectedItem() + "</p></html>");
			}
		}
		if (e.getSource().equals(getBtnCancelar())) {
			LogingFrame.getScreen().setEnabled(true);
			dispose();
		}
	}
	
	private void mostrarNombreCombobox() {
		String texto = getTxfArtista().getText().toLowerCase().trim();
	    getComboBox().removeAllItems();
		if (!texto.isEmpty()) {
			try {
				ResultSet resultados = ICGuitarDatabase.obtenerDatosArtista();
				
				while (resultados.next()) {
					String nombre = resultados.getString("nombre");
	                String idArtista = resultados.getString("idArtista");
	                String artistaCompleto = nombre + ";" + idArtista;
	                
	                if (!ICGuitar.getArtistas().contains(artistaCompleto)) {
	                	ICGuitar.getArtistas().add(artistaCompleto);
	                }
				}
				for (String nombre : ICGuitar.getArtistas()) {
					if (nombre.toLowerCase().contains(texto)) {
						String[] item = nombre.split(";");
						String nombreArtista = item[0];
						System.out.println(nombre + " id - " + item[1]);
						getComboBox().addItem(nombreArtista);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void registrarAlbum() {	
		File imagen = new File(imageFile);
		String nombreAlbum=getTxfNombreAlbum().getText();
		FileInputStream fis = null;
		Object artistaSeleccionado = getComboBox().getSelectedItem();
		Date fechaAlbum = getDateChooser().getDate();
	    java.sql.Date sqlFechaAlb = null;
	    sqlFechaAlb = new java.sql.Date(fechaAlbum.getTime());
		if (artistaSeleccionado != null) {
	        for (String nombre : ICGuitar.getArtistas()) {
	            String[] item = nombre.split(";");
	            if (artistaSeleccionado.equals(item[0])) {
	                setIdArtista(item[1]);
	                System.out.println(getIdArtista());
	                break;
	            }
	        }
	    }
		
		if (imageFile!=null && !imageFile.equals("")) {
			try {
				fis = new FileInputStream(imagen);			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, ERROR_SELECCION_IMAGEN, ERROR_REGISTRO, JOptionPane.ERROR_MESSAGE);
			}
		}

		ICGuitarDatabase.insertarAlbum(nombreAlbum, getIdArtista(), sqlFechaAlb, imagen, fis);
		JOptionPane.showMessageDialog(null, nombreAlbum, REGISTRO_EXITOSO, JOptionPane.INFORMATION_MESSAGE);
		LogingFrame.getScreen().setEnabled(true);
		dispose();
	}
}
