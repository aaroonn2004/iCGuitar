package registroDatos;

import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import baseDatos.ICGuitarDatabase;
import clases.ICGuitar;
import clases.LenguajeTexto;
import interfaz.HomeScreen;
import interfaz.LogingFrame;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class RegistrarCancion extends JFrame implements LenguajeTexto, ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txfTitulo;
	private JTextField textField;
	private JTextField txfAlbum;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBoxAlbum;
	private JTextField txfVideoclip;
	private JComboBox<String> cmbDificultad;
	private JButton btnSubir;
	private JButton btnCancelar;
	private JEditorPane panelEditable;
	private JButton btnAnadirTab;
	private JComboBox<String> comboBoxGenero;
	private ArrayList<String> generos;
	private JRadioButton rdbtnAcorde;
	private JRadioButton rdbtnLetra;
	private JRadioButton rdbtnTab;
	private JLabel lblCancion;

	public RegistrarCancion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeScreen.class.getResource(APP_ICON)));
		setResizable(false);
		setTitle(REGISTRAR_CANCION);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 691);
		getContentPane().setLayout(null);
		
		panelEditable = new JEditorPane();
		panelEditable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panelEditable.setBounds(0, 52, 791, 469);
		panelEditable.setContentType("text/html");
		panelEditable.setText("<html><p style=\"font-size: 11px; font-family: 'Segoe UI'; text-align:'center';\"><b>"+LETRA_BLANK+"</b></p></html>");
		
		JScrollPane scrollPane = new JScrollPane(panelEditable);
		scrollPane.setViewportBorder(UIManager.getBorder("ScrollPane.border"));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 100, 632, 304);
		getContentPane().add(scrollPane);	
		
		JLabel lblTablatura = new JLabel("<html><p>"+REGISTRAR_CANCION_DATOS+"</p></html>");
		lblTablatura.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblTablatura.setBounds(10, 397, 555, 36);
		getContentPane().add(lblTablatura);
		
		lblCancion = new JLabel("<html><h1>"+REGISTRAR_CANCION_LETRA+"</h1></html>");
		lblCancion.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblCancion.setBounds(10, 27, 235, 36);
		getContentPane().add(lblCancion);
		
		JLabel lblTab = new JLabel("<html><p>"+TEMP_LETRA+"</p></html>");
		lblTab.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblTab.setBounds(10, 58, 632, 36);
		getContentPane().add(lblTab);
		
		JLabel lblTitulo = new JLabel(REGISTRAR_CANCION_DATOS_TITULO);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblTitulo.setBounds(21, 443, 110, 20);
		getContentPane().add(lblTitulo);
		
		txfTitulo = new JTextField();
		txfTitulo.setBounds(140, 447, 110, 19);
		getContentPane().add(txfTitulo);
		txfTitulo.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 433, 632, 2);
		getContentPane().add(separator);
		
		JLabel lblArtista = new JLabel(REGISTRAR_CANCION_DATOS_ARTISTA);
		lblArtista.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblArtista.setBounds(21, 475, 110, 21);
		getContentPane().add(lblArtista);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(140, 479, 110, 19);
		textField.addActionListener(this);
		getContentPane().add(textField);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(this);
		comboBox.setBounds(269, 478, 110, 21);
		getContentPane().add(comboBox);
		
		JLabel lblAlbum = new JLabel(REGISTRAR_CANCION_DATOS_ALBUM);
		lblAlbum.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblAlbum.setBounds(21, 507, 110, 21);
		getContentPane().add(lblAlbum);
		
		txfAlbum = new JTextField();
		txfAlbum.setColumns(10);
		txfAlbum.setBounds(140, 511, 110, 19);
		txfAlbum.addActionListener(this);
		getContentPane().add(txfAlbum);
		
		comboBoxAlbum = new JComboBox<String>();
		comboBoxAlbum.setBounds(269, 510, 110, 21);
		getContentPane().add(comboBoxAlbum);
		
		JLabel lblVideoclip = new JLabel(REGISTRAR_CANCION_DATOS_VIDEO);
		lblVideoclip.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblVideoclip.setBounds(21, 538, 110, 21);
		getContentPane().add(lblVideoclip);
		
		txfVideoclip = new JTextField();
		txfVideoclip.setColumns(10);
		txfVideoclip.setBounds(140, 542, 110, 19);
		getContentPane().add(txfVideoclip);
		
		JLabel lblDificultad = new JLabel(REGISTRAR_CANCION_DATOS_DIFICULTAD);
		lblDificultad.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblDificultad.setBounds(21, 569, 110, 21);
		getContentPane().add(lblDificultad);
		
		cmbDificultad = new JComboBox<String>();
		cmbDificultad.setBounds(139, 572, 111, 21);
		getContentPane().add(cmbDificultad);
		
		btnCancelar = new JButton(REGISTRAR_CANCION_DATOS_CANCELAR);
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(557, 623, 85, 21);
		getContentPane().add(btnCancelar);
		
		btnSubir = new JButton(REGISTRAR_CANCION_DATOS_SUBIR);
		btnSubir.addActionListener(this);
		btnSubir.setBounds(462, 623, 85, 21);
		getContentPane().add(btnSubir);
		
		btnAnadirTab = new JButton(ANNADIR_TAB);
		btnAnadirTab.setEnabled(false);
		btnAnadirTab.setBounds(479, 407, 163, 21);
		btnAnadirTab.addActionListener(this);
		getContentPane().add(btnAnadirTab);
		
		comboBoxGenero = new JComboBox<String>();
		comboBoxGenero.setBounds(140, 603, 110, 21);
		getContentPane().add(comboBoxGenero);
		
		JLabel lblGenero = new JLabel(CANCIONES_GENERO);
		lblGenero.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblGenero.setBounds(21, 600, 110, 21);
		getContentPane().add(lblGenero);
		
		rdbtnTab = new JRadioButton(OPC_TAB);
		rdbtnTab.setBounds(269, 541, 187, 21);
		rdbtnTab.addActionListener(this);
		getContentPane().add(rdbtnTab);
		
		rdbtnAcorde = new JRadioButton(OPC_ACORD);
		rdbtnAcorde.setBounds(269, 572, 187, 21);
		rdbtnAcorde.addActionListener(this);
		getContentPane().add(rdbtnAcorde);
		
		rdbtnLetra = new JRadioButton(OPC_LETRA);
		rdbtnLetra.addActionListener(this);
		rdbtnLetra.setBounds(269, 603, 187, 21);
		rdbtnLetra.setSelected(true);
		getContentPane().add(rdbtnLetra);
		mostrarNombreComboboxGenero();
		
		// Añade listeners
		
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
		
		getTxfAlbum().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				mostrarNombreComboboxAlbum();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				mostrarNombreComboboxAlbum();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				mostrarNombreComboboxAlbum();
			}
			
		});
		
		cargarDificultades();
	}
	
	public JTextField getTxfArtista() {
		return textField;
	}
	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	public JTextField getTxfAlbum() {
		return txfAlbum;
	}
	public JComboBox<String> getComboBoxAlbum() {
		return comboBoxAlbum;
	}
	public JComboBox<String> getComboBoxGenero() {
		return comboBoxGenero;
	}
	public JComboBox<String> getCmbDificultad() {
		return cmbDificultad;
	}
	public ArrayList<String> getGeneros() {
		return generos;
	}
	public void setGeneros(ArrayList<String> generos) {
		this.generos = generos;
	}
	public JButton getBtnSubir() {
		return btnSubir;
	}
	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	public JTextField getTxfVideoclip() {
		return txfVideoclip;
	}
	public JTextField getTxfTitulo() {
		return txfTitulo;
	}
	public JEditorPane getPanelEditable() {
		return panelEditable;
	}
	public JButton getBtnAnnadirTab() {
		return btnAnadirTab;
	}
	public JRadioButton getRdbtnAcorde() {
		return rdbtnAcorde;
	}
	public JRadioButton getRdbtnLetra() {
		return rdbtnLetra;
	}
	public JRadioButton getRdbtnTab() {
		return rdbtnTab;
	}
	public JLabel getLblCancion() {
		return lblCancion;
	}
	
	public void cargarDificultades() {
		for (String dif : ICGuitar.getDificultad()) {
			getCmbDificultad().addItem(dif.split(";")[0]);
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
	
	private void mostrarNombreComboboxAlbum() {
		String texto = getTxfAlbum().getText().toLowerCase().trim();
	    getComboBoxAlbum().removeAllItems();
		if (!texto.isEmpty()) {
			try {
				ResultSet resultados = ICGuitarDatabase.obtenerDatosAlbum();
				
				while (resultados.next()) {
					String nombre = resultados.getString("nombre");
	                String idAlbum = resultados.getString("idAlbum");
	                String albumCompleto = nombre + ";" + idAlbum;
	                
	                if (!ICGuitar.getAlbumes().contains(albumCompleto)) {
	                	ICGuitar.getAlbumes().add(albumCompleto);
	                }
				}
				for (String nombreAlbum : ICGuitar.getAlbumes()) {
					if (nombreAlbum.toLowerCase().contains(texto)) {
						String[] item = nombreAlbum.split(";");
						String nombre=item[0];
						System.out.println(nombreAlbum + " id - " + item[1]);
						getComboBoxAlbum().addItem(nombre);
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void mostrarNombreComboboxGenero() {
		try {
			setGeneros(ICGuitarDatabase.obtenerGeneros());
				
			for (String genero : getGeneros()) {
					getComboBoxGenero().addItem(genero.split(";")[1]);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void registrarCancion() {
		Object album = getComboBoxAlbum().getSelectedItem();
		Object artista = getComboBox().getSelectedItem();
		Object dificultad = getCmbDificultad().getSelectedItem();
		Object genero = getComboBoxGenero().getSelectedItem();
		String nombreCancion = getTxfTitulo().getText();
		String urlClip = getTxfVideoclip().getText();
		String idArtistaAux;
		String idGenero = null;
		String idDificultadAux = null;
		String idAlbumAux = null;
		String tab = getPanelEditable().getText();
		System.out.println(genero);
		
		// Registra cada cancion controlando errores e iterando en cada arraylist separando el id del nombre, etc
		
		if (album==null || artista == null || dificultad == null || nombreCancion == null || nombreCancion == "" || urlClip == null || urlClip=="") {
			JOptionPane.showMessageDialog(null, ERROR_REQUIRED_FIELDS, REGISTRAR_CANCION_DATOS_ERROR, JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		for (String str : ICGuitar.getArtistas()) {
			if (str.contains(artista+";")) {
				System.out.println(str);
				idArtistaAux=str.split(";")[1];
				System.out.println(idArtistaAux);
			}
		}
		
		for (String str : ICGuitar.getAlbumes()) {
			if (str.contains(album+";")) {
				System.out.println(str);
				idAlbumAux=str.split(";")[1];
				System.out.println(idAlbumAux);
			}
		}
		
		for (String str : ICGuitar.getDificultad()) {
			if (str.contains(dificultad+";")) {
				System.out.println(str);
				idDificultadAux=str.split(";")[1];
				System.out.println(idDificultadAux);
			}
		}
		
		for (String str : getGeneros()) {
			if (str.contains(";"+genero)) {
				System.out.println(str);
				idGenero=str.split(";")[0];
				System.out.println(idGenero);
			}
		}
		
		if (getRdbtnTab().isSelected()) {
			if (ICGuitarDatabase.insertarCancion(idGenero, nombreCancion, idDificultadAux, urlClip, idAlbumAux, 1, tab)) {
				JOptionPane.showMessageDialog(null, String.format(REGISTRAR_CANCION_DATOS_REGISTRO, nombreCancion), REGISTRAR_CANCION_DATOS_REGISTRADA, JOptionPane.INFORMATION_MESSAGE);
				LogingFrame.getScreen().setEnabled(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, REGISTRAR_CANCION_DATOS_ERRORA, REGISTRAR_CANCION_DATOS_ERROR, JOptionPane.ERROR_MESSAGE);
			}
		} else if (getRdbtnAcorde().isSelected()){
			if (ICGuitarDatabase.insertarCancion(idGenero, nombreCancion, idDificultadAux, urlClip, idAlbumAux, 2, tab)) {
				JOptionPane.showMessageDialog(null, String.format(REGISTRAR_CANCION_DATOS_REGISTRO, nombreCancion), REGISTRAR_CANCION_DATOS_REGISTRADA, JOptionPane.INFORMATION_MESSAGE);
				LogingFrame.getScreen().setEnabled(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, REGISTRAR_CANCION_DATOS_ERRORA, REGISTRAR_CANCION_DATOS_ERROR, JOptionPane.ERROR_MESSAGE);
			}
		} else if (getRdbtnLetra().isSelected()) {
			if (ICGuitarDatabase.insertarCancion(idGenero, nombreCancion, idDificultadAux, urlClip, idAlbumAux, 3, tab)) {
				JOptionPane.showMessageDialog(null, String.format(REGISTRAR_CANCION_DATOS_REGISTRO, nombreCancion), REGISTRAR_CANCION_DATOS_REGISTRADA, JOptionPane.INFORMATION_MESSAGE);
				LogingFrame.getScreen().setEnabled(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, REGISTRAR_CANCION_DATOS_ERRORA, REGISTRAR_CANCION_DATOS_ERROR, JOptionPane.ERROR_MESSAGE);
			}
		}		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(getBtnCancelar())) {
			LogingFrame.getScreen().setEnabled(true);
			dispose();
		}
		if (e.getSource().equals(getBtnSubir())) {
			registrarCancion();
		}
		if (e.getSource().equals(getBtnAnnadirTab())) {
			getPanelEditable().setText("<html><p style=\"font-size: 11px; font-family: 'Segoe UI'; text-align:'center';\"><b>"+TAB_BLANK+"</b></p><p style='text-align:center;'>" + TABLATURA_BLANK + "</p></html>");
			System.out.println(getPanelEditable().getText());
		}
		if (e.getSource().equals(getRdbtnLetra())) {
			getRdbtnLetra().setSelected(true);
			getRdbtnTab().setSelected(false);
			getRdbtnAcorde().setSelected(false);
			getBtnAnnadirTab().setEnabled(false);
			getPanelEditable().setText("<html><p style=\"font-size: 11px; font-family: 'Segoe UI'; text-align:'center';\"><b>"+LETRA_BLANK+"</b></p></html>");
			getLblCancion().setText("<html><h1>"+REGISTRAR_CANCION_LETRA+"</h1></html>");
		} else if (e.getSource().equals(getRdbtnAcorde())) {
			getRdbtnLetra().setSelected(false);
			getRdbtnTab().setSelected(false);
			getRdbtnAcorde().setSelected(true);
			getBtnAnnadirTab().setEnabled(false);
			getLblCancion().setText("<html><h1>"+REGISTRAR_CANCION_ACORDES+"</h1></html>");
			getPanelEditable().setText("<html><p style=\"font-size: 11px; font-family: 'Segoe UI'; text-align:'center';\"><b>"+ACORDES_BLANK+"</b></p></html>");
		} else if (e.getSource().equals(getRdbtnTab())) {
			getRdbtnLetra().setSelected(false);
			getRdbtnTab().setSelected(true);
			getRdbtnAcorde().setSelected(false);
			getBtnAnnadirTab().setEnabled(true);
			getLblCancion().setText("<html><h1>"+REGISTRAR_CANCION_TAB+"</h1></html>");
			getPanelEditable().setText("<html><p style=\"font-size: 11px; font-family: 'Segoe UI'; text-align:'center';\"><b>"+TAB_BLANK+"</b></p></html>");
		}
	}
}
