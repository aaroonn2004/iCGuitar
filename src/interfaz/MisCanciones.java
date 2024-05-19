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

public class MisCanciones extends JFrame implements LenguajeTexto, ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JEditorPane editorPane;
	private JComboBox<String> comboBoxMisCanciones;
	private JButton btnAceptar;
	private JButton btnSalir;
	private String id;
	private ArrayList<String> canciones=new ArrayList<String>();

	/**
	 * Create the frame.
	 */
	public MisCanciones() {
		String favorito=null;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 522);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DatosUsuario.class.getResource("/assets/images/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle(String.format(MIS_CANCIONES, ICGuitar.getUsuarioIniciado()));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMisCanciones = new JLabel(String.format(MIS_CANCIONES, ICGuitar.getUsuarioIniciado()));
		lblMisCanciones.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblMisCanciones.setBounds(10, 0, 537, 38);
		contentPane.add(lblMisCanciones);
		
		editorPane = new JEditorPane();
		editorPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		
		JScrollPane scrollPane = new JScrollPane(editorPane);
		scrollPane.setBounds(27, 48, 507, 369);
		contentPane.add(scrollPane);
		
		comboBoxMisCanciones = new JComboBox<String>();
		comboBoxMisCanciones.setBounds(27, 427, 507, 21);
		contentPane.add(comboBoxMisCanciones);
		
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
		return comboBoxMisCanciones;
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(getBtnSalir())) {
			LogingFrame.getScreen().setEnabled(true);
			dispose();
		}
		if (e.getSource().equals(getBtnAceptar())) {
			for (String cancion : getCanciones()) {
				if (cancion.contains(";"+getComboBoxFavoritos().getSelectedItem().toString().replace(" - Tablatura", "").replace(" - Acordes", "").replace(" - Letra", ""))) {
					setId(cancion.split(";")[0]);
				}
			}
			Cancion cancion = new Cancion(getId());
			cancion.setVisible(true);
			dispose();
		}
	}
	private void cargarFavoritos(String favorito) throws SQLException {
		// Carga la información relevante a canciones subidas por el usuario
		ResultSet datos;
		String lista="";
		datos=ICGuitarDatabase.obtenerCanciones();
		while(datos.next()) {
			if (ICGuitarDatabase.esFavorita(datos.getString("idCancion"), ICGuitar.getUsuarioIniciado(), "misCanciones")) {
				getCanciones().add(datos.getString("idCancion")+";"+datos.getString("nombre")+";"+ICGuitarDatabase.obtenerTipoCancion(Integer.parseInt(ICGuitarDatabase.obtenerTipoDeCancion(datos.getString("idCancion")))));
			}
		}
		for (String cancion : getCanciones()) {
			lista+="<li style='font-family: segoe ui; font-size: 18px;'>"+cancion.split(";")[1]+"</li>";
			getComboBoxFavoritos().addItem(cancion.split(";")[1]+" - "+cancion.split(";")[2]);
		}
		getEditorPane().setText("<html>"+lista+"</html>");
		
		if (getCanciones().isEmpty()) {
			getEditorPane().setText(NO_CONTENIDO);
		}
	}
}
