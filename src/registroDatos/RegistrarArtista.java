package registroDatos;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import clases.LenguajeTexto;
import interfaz.HomeScreen;
import interfaz.LogingFrame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import baseDatos.ICGuitarDatabase;

import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.net.MalformedURLException;
import java.sql.SQLException;

import javax.swing.JTextArea;

public class RegistrarArtista extends JFrame implements LenguajeTexto, ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txfNombre;
	private JButton btnPortada;
	private static String imageFile = "";
	private JLabel lblPortada;
	private JLabel lblNombreArtista;
	private JLabel lblBio2;
	private JButton btnRegistrar;
	private JButton btnCancelar;
	private JLabel lblView;
	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public RegistrarArtista() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeScreen.class.getResource(APP_ICON)));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 835, 650);
		setTitle(UTILITY_ARTISTA_REGISTRAR);
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

		btnCancelar = new JButton(FILE_CHOOSER_CANCELAR);
		btnCancelar.setBounds(726, 582, 85, 21);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);

		btnRegistrar = new JButton(MENU_ARTISTA_REGISTRAR);
		btnRegistrar.setBounds(569, 582, 147, 21);
		btnRegistrar.addActionListener(this);
		contentPane.add(btnRegistrar);

		JLabel lblNombre = new JLabel(REGISTRAR_ARTISTA_NOMBRE);
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNombre.setBounds(75, 163, 176, 21);
		contentPane.add(lblNombre);

		txfNombre = new JTextField();
		txfNombre.addActionListener(this);
		txfNombre.setBounds(75, 194, 203, 19);
		contentPane.add(txfNombre);
		txfNombre.setColumns(10);

		JLabel lblBio = new JLabel(REGISTRAR_ARTISTA_BIOGRAFIA);
		lblBio.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblBio.setBounds(75, 259, 287, 21);
		contentPane.add(lblBio);

		btnPortada = new JButton(REGISTRAR_ALBUM_SELECCION_PORTADA);
		btnPortada.addActionListener(this);
		btnPortada.setBounds(75, 467, 203, 21);
		contentPane.add(btnPortada);

		lblPortada = new JLabel(REGISTRAR_ALBUM_PORTADA_PLACEHOLDER);
		lblPortada.setIcon(null);
		lblPortada.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPortada.setHorizontalAlignment(SwingConstants.CENTER);
		lblPortada.setBounds(425, 136, 317, 266);
		contentPane.add(lblPortada);

		lblNombreArtista = new JLabel("<html><p>" + REGISTRAR_ARTISTA_NOMBRE + "</p></html>");
		lblNombreArtista.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNombreArtista.setBounds(413, 450, 398, 21);
		contentPane.add(lblNombreArtista);

		lblBio2 = new JLabel("<html><p>" + REGISTRAR_ARTISTA_BIOGRAFIA + "</p></html>");
		lblBio2.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblBio2.setBounds(413, 512, 398, 21);
		contentPane.add(lblBio2);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(372, 96, 2, 476);
		contentPane.add(separator_1);

		lblView = new JLabel(REGISTRAR_PREVIEW);
		lblView.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblView.setBounds(413, 73, 176, 21);
		contentPane.add(lblView);

		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(75, 290, 203, 51);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setSize(203, 167);
		scrollPane.setLocation(75, 290);
		scrollPane.setViewportBorder(UIManager.getBorder("ScrollPane.border"));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textArea.setBounds(75, 290, 203, 51);
		contentPane.add(scrollPane);

		// Añade listeners para cada elemento de texto
		getTxfNombre().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				getLblNombre().setText(
						"<html><p>" + REGISTRAR_ARTISTA_NOMBRE + " " + getTxfNombre().getText() + "</p></html>");
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				getLblNombre().setText(
						"<html><p>" + REGISTRAR_ARTISTA_NOMBRE + " " + getTxfNombre().getText() + "</p></html>");
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				getLblNombre().setText(
						"<html><p>" + REGISTRAR_ARTISTA_NOMBRE + " " + getTxfNombre().getText() + "</p></html>");
			}

		});
		getTextArea().getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				getLblBio2().setText(
						"<html><p>" + REGISTRAR_ARTISTA_BIOGRAFIA + " " + getTextArea().getText() + "</p></html>");
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				getLblBio2().setText(
						"<html><p>" + REGISTRAR_ARTISTA_BIOGRAFIA + " " + getTextArea().getText() + "</p></html>");
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				getLblBio2().setText(
						"<html><p>" + REGISTRAR_ARTISTA_BIOGRAFIA + " " + getTextArea().getText() + "</p></html>");
			}

		});
	}

	public JButton getBtnPortada() {
		return btnPortada;
	}
	public JLabel getLblPortada() {
		return lblPortada;
	}

	public JLabel getLblNombre() {
		return lblNombreArtista;
	}

	public JLabel getLblBio2() {
		return lblBio2;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JTextField getTxfNombre() {
		return txfNombre;
	}
	public JTextArea getTextArea() {
		return textArea;
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
				imageFile = selectedFile.getAbsolutePath();
				try {
					ImageIcon imagen = new ImageIcon(selectedFile.toURI().toURL());
					Image img = imagen.getImage().getScaledInstance(getLblPortada().getWidth(),
							getLblPortada().getHeight(), Image.SCALE_FAST);
					imagen = new ImageIcon(img);
					getLblPortada().setIcon(imagen);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource().equals(getBtnRegistrar())) {
			if (getTxfNombre() == null || imageFile == null) {
				return;
			} else {
				System.out.println("Titulo: " + getTxfNombre().getText());
				System.out.println("Bio: " + getTextArea().getText());
				registrarArtista();
			}
		}
		if (e.getSource().equals(getBtnCancelar())) {
			LogingFrame.getScreen().setEnabled(true);
			dispose();
		}
	}

	private void registrarArtista() {
		// Registra el artista
		String[] campos = new String[2];
		campos[0] = getTxfNombre().getText();
		campos[1] = getTextArea().getText();

		if (campos[0].equals("") || campos[0] == null || campos[1] == null || campos[1].equals("")) {
			JOptionPane.showMessageDialog(null, ERROR_REGISTRO_CAMPOS_BLANCO, ERROR_REGISTRO_ARTISTA,
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (imageFile == "") {
			JOptionPane.showMessageDialog(null, ERROR_REGISTRO_NO_PORTADA, ERROR_REGISTRO_ARTISTA,
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		ICGuitarDatabase.insertarDatosDB("INSERT INTO artista (nombre, biografia) VALUES(?,?)", campos);
		FileInputStream fis = null;
		File imagen = new File(imageFile);
		if (imageFile != null && !imageFile.equals("")) {
			try {
				fis = new FileInputStream(imagen);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		try {
			ICGuitarDatabase.insertarImagenDB("UPDATE Artista SET fotoArtista = ? WHERE idArtista = ?", imagen, fis,
					ICGuitarDatabase.obtenerIdArtista(getTxfNombre().getText(), getTextArea().getText()));
			JOptionPane.showMessageDialog(null, campos[0], ARTISTA_EGISTRO_EXITOSO, JOptionPane.INFORMATION_MESSAGE);
			LogingFrame.getScreen().setEnabled(true);
			LogingFrame.getScreen().cargarArtistas();
			dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}