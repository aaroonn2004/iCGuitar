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
import clases.LenguajeTexto;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class Buscar extends JFrame implements LenguajeTexto, ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txfBusqueda;
	private JRadioButton rdbtnCancion;
	private JRadioButton rdbtnAlbum;
	private JRadioButton rdbtnArtista;
	private JButton btnBuscar;
	private JButton btnSalir;
	private JComboBox<String> comboBox;
	private static ArrayList<String> resultados;
	private JButton btnAceptar;
	private String idObjetivo;

	/**
	 * Create the frame.
	 */
	public Buscar() {
		resultados = new ArrayList<String>();
		setTitle(MENU_ARCHIVO_BUSCAR);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DatosUsuario.class.getResource("/assets/images/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 591, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txfBusqueda = new JTextField();
		txfBusqueda.setBounds(21, 47, 451, 20);
		contentPane.add(txfBusqueda);
		txfBusqueda.setColumns(10);
		
		btnBuscar = new JButton(MENU_ARCHIVO_BUSCAR);
		btnBuscar.setBounds(482, 46, 85, 21);
		btnBuscar.addActionListener(this);
		contentPane.add(btnBuscar);
		
		btnSalir = new JButton(REGISTRAR_ALBUM_CANCELAR);
		btnSalir.setBounds(482, 222, 85, 21);
		btnSalir.addActionListener(this);
		contentPane.add(btnSalir);
		
		JLabel lblBuscar = new JLabel(BUSCAR_BUSQUEDA);
		lblBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblBuscar.setBounds(21, 24, 238, 13);
		contentPane.add(lblBuscar);
		
		rdbtnCancion = new JRadioButton(MENU_CANCION);
		rdbtnCancion.setSelected(true);
		rdbtnCancion.setBounds(21, 90, 103, 21);
		rdbtnCancion.addActionListener(this);
		contentPane.add(rdbtnCancion);
		
		rdbtnAlbum = new JRadioButton(MENU_ALBUM);
		rdbtnAlbum.setBounds(21, 124, 103, 21);
		rdbtnAlbum.addActionListener(this);
		contentPane.add(rdbtnAlbum);
		
		rdbtnArtista = new JRadioButton(MENU_ARTISTAS);
		rdbtnArtista.setBounds(21, 162, 103, 21);
		rdbtnArtista.addActionListener(this);
		contentPane.add(rdbtnArtista);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(126, 90, 346, 21);
		contentPane.add(comboBox);
		
		btnAceptar = new JButton(FILE_CHOOSER_ACCEPT);
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(387, 222, 85, 21);
		btnAceptar.addActionListener(this);
		contentPane.add(btnAceptar);
	}
	public JTextField getTxfBusqueda() {
		return txfBusqueda;
	}
	public JRadioButton getRdbtnCancion() {
		return rdbtnCancion;
	}
	public JRadioButton getRdbtnAlbum() {
		return rdbtnAlbum;
	}
	public JRadioButton getRdbtnArtista() {
		return rdbtnArtista;
	}
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	public JButton getBtnSalir() {
		return btnSalir;
	}
	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	public ArrayList<String> getResultados() {
		return resultados;
	}
	public JButton getBtnAceptar() {
		return btnAceptar;
	}
	public String getIdObjetivo() {
		return idObjetivo;
	}
	public void setIdObjetivo(String idObjetivo) {
		this.idObjetivo = idObjetivo;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(getBtnSalir())) {
			LogingFrame.getScreen().setEnabled(true);
			dispose();
		}
		if (e.getSource().equals(getBtnBuscar())) {
			try {
				buscarEnBaseDatos();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource().equals(getBtnAceptar())) {
			String filtro="";
			// Cada posición almacena el nombre;id;tipo, con este código guardo en el combobox solo el nombre, aislándo el id para utilizarlos posteriormente
			for (String st : getResultados()) {
				System.out.println("Probando");
				System.out.println(st.split(";")[1]);
				if (st.contains(getComboBox().getSelectedItem().toString().replace(" - Letra", "")+";")) {
					setIdObjetivo(st.split(";")[1]);
				} else if (st.contains(getComboBox().getSelectedItem().toString().replace(" - Acordes", "")+";")) {
					setIdObjetivo(st.split(";")[1]);
				} else if (st.contains(getComboBox().getSelectedItem().toString().replace(" - Tablatura", "")+";")) {
					setIdObjetivo(st.split(";")[1]);
				} 
			}
			if (getRdbtnAlbum().isSelected()) {
				filtro = "album";
			} else if (getRdbtnArtista().isSelected()) {
				filtro = "artista";
			} else if (getRdbtnCancion().isSelected()) {
				filtro = "cancion";
			}
			switch (filtro) {
				case "album":
					Album album = new Album(getIdObjetivo());
					album.setVisible(true);
					dispose();
					break;
				case "artista":
					Artista artista = new Artista(getIdObjetivo());
					artista.setVisible(true);
					dispose();
					break;
				case "cancion":
					System.out.println(getIdObjetivo());
					Cancion cancion = new Cancion(getIdObjetivo());
					cancion.setVisible(true);
					dispose();
					break;
			}		
		}
		
		// Obtiene el tipo de dato
		if (e.getSource().equals(getRdbtnAlbum())) {
			getRdbtnAlbum().setSelected(true);
			getRdbtnCancion().setSelected(false);
			getRdbtnArtista().setSelected(false);
		} else if (e.getSource().equals(getRdbtnCancion())) {
			getRdbtnAlbum().setSelected(false);
			getRdbtnCancion().setSelected(true);
			getRdbtnArtista().setSelected(false);
		} else if (e.getSource().equals(getRdbtnArtista())) {
			getRdbtnAlbum().setSelected(false);
			getRdbtnCancion().setSelected(false);
			getRdbtnArtista().setSelected(true);
		}
	}
	private void buscarEnBaseDatos() throws SQLException {
		String filtro = "";
		String busqueda = getTxfBusqueda().getText();
		ResultSet resultado = null;
		resultados.clear();
		
		if (busqueda.equals("")) {
			JOptionPane.showMessageDialog(null, BUSCAR_ERROR2, BUSCAR_ERROR1, JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (getRdbtnAlbum().isSelected()) {
			filtro = "album";
		} else if (getRdbtnArtista().isSelected()) {
			filtro = "artista";
		} else if (getRdbtnCancion().isSelected()) {
			filtro = "cancion";
		}
		switch (filtro) {
			case "album":
				resultado=ICGuitarDatabase.obtenerGlobal("album");
				while (resultado.next()) {
					resultados.add(resultado.getString("nombre")+";"+resultado.getString("idAlbum"));
				}
				break;
			case "artista":
				resultado=ICGuitarDatabase.obtenerGlobal("artista");
				while (resultado.next()) {
					resultados.add(resultado.getString("nombre")+";"+resultado.getString("idArtista"));
				}
				break;
			case "cancion":
				resultado=ICGuitarDatabase.obtenerGlobal("cancion");
				while (resultado.next()) {
					resultados.add(resultado.getString("nombre")+";"+resultado.getString("idCancion"));
				}
				break;
		}		
		mostrarNombreCombobox();
	}
	
	private void mostrarNombreCombobox() throws SQLException {
		String texto = getTxfBusqueda().getText().toLowerCase().trim();
		String idTipo = null;
		boolean encontrado=false;
		int tipo;
		
		// Obtiene los datos previamente insertados y usa el nombre en el combobox
	    getComboBox().removeAllItems();
		if (!texto.isEmpty()) {
			for (String nombre : getResultados()) {
				if (nombre.toLowerCase().contains(texto)) {
					String[] item = nombre.split(";");
					String nombreObjeto= item[0];
					System.out.println(nombre + " id - " + item[1]);
					if (idTipo!=null) {
						idTipo=ICGuitarDatabase.obtenerTipoDeCancion(item[1]);
						tipo=Integer.parseInt(idTipo);
						if (getRdbtnCancion().isSelected()) {
							nombreObjeto+=" - "+ICGuitarDatabase.obtenerTipoCancion(tipo);
							System.out.println(nombreObjeto);
						}
					}
					getComboBox().addItem(nombreObjeto);
					encontrado = true;
				}
			}
		}
		getBtnAceptar().setEnabled(encontrado);
		if (!encontrado) {
	        JOptionPane.showMessageDialog(null, BUSCAR_ERROR2, BUSCAR_ERROR1, JOptionPane.ERROR_MESSAGE);
	    }
	}
}
