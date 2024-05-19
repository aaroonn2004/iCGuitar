package interfaz;

import java.awt.Toolkit;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import baseDatos.ICGuitarDatabase;
import clases.ICGuitar;
import clases.LenguajeTexto;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatosUsuario extends JFrame implements ActionListener, LenguajeTexto {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txfName;
	private JTextField txfApellidos;
	private JTextField txfUsername;
	private JPasswordField psfOld;
	private JPasswordField psfNew;
	private JPasswordField psfConfirm;
	private JButton btnSalir;
	private JButton btnAplicar;
	private JLabel lblFechaNac;
	private JLabel lblFechaCreacion;
	private JCheckBox chckbxEditable;
	private JLabel lblFotoPerfil;
	private JSeparator separator_2;
	private JLabel lblView;
	private JButton btnCambiarFoto;
	private String imageFile = "";
	private boolean isEditable=false;

	/**
	 * Create the frame.
	 */
	public DatosUsuario() {
		setType(Type.UTILITY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DatosUsuario.class.getResource("/assets/images/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 441);
		setTitle(TITULO_APP);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("<html><u>"+DATOS_USUARIO_TITULO+"</u></html>");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTitulo.setBounds(10, 10, 142, 22);
		contentPane.add(lblTitulo);
		
		txfName = new JTextField();
		txfName.setEnabled(false);
		txfName.setBounds(132, 42, 107, 19);
		contentPane.add(txfName);
		txfName.setColumns(10);
		
		JLabel lblNombre = new JLabel("<html><p>"+RF_NAME+"</p></html>");
		lblNombre.setBounds(20, 45, 86, 13);
		contentPane.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("<html><p>"+RF_SURNAME+"</p></html>");
		lblApellidos.setBounds(20, 74, 86, 13);
		contentPane.add(lblApellidos);
		
		txfApellidos = new JTextField();
		txfApellidos.setEnabled(false);
		txfApellidos.setColumns(10);
		txfApellidos.setBounds(132, 71, 107, 19);
		contentPane.add(txfApellidos);
		
		JLabel lblUsername = new JLabel("<html><p>"+LF_USERNAME+"</p></html>");
		lblUsername.setBounds(20, 86, 107, 49);
		contentPane.add(lblUsername);
		
		txfUsername = new JTextField();
		txfUsername.setEnabled(false);
		txfUsername.setColumns(10);
		txfUsername.setBounds(132, 100, 107, 19);
		contentPane.add(txfUsername);
		
		lblFechaNac = new JLabel(RF_DATE);
		lblFechaNac.setBounds(20, 212, 209, 19);
		contentPane.add(lblFechaNac);
		
		JLabel lblPasswOld = new JLabel("<html><p>"+DATOS_USUARIO_OLD+"</p></html>");
		lblPasswOld.setBounds(20, 120, 107, 49);
		contentPane.add(lblPasswOld);
		
		psfOld = new JPasswordField();
		psfOld.setEnabled(false);
		psfOld.setBounds(132, 129, 107, 19);
		contentPane.add(psfOld);
		
		JLabel lblPasswNew = new JLabel("<html><p>"+DATOS_USUARIO_NEW+"</p></html>");
		lblPasswNew.setBounds(20, 145, 107, 49);
		contentPane.add(lblPasswNew);
		
		psfNew = new JPasswordField();
		psfNew.setEnabled(false);
		psfNew.setBounds(132, 157, 107, 19);
		contentPane.add(psfNew);
		
		JLabel lblPasswNewConf = new JLabel("<html><p>"+LF_CONFIRM_PASSWORD+"</p></html>");
		lblPasswNewConf.setBounds(20, 179, 107, 38);
		contentPane.add(lblPasswNewConf);
		
		psfConfirm = new JPasswordField();
		psfConfirm.setEnabled(false);
		psfConfirm.setBounds(132, 188, 108, 19);
		contentPane.add(psfConfirm);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(260, 27, 0, 221);
		contentPane.add(separator);
		
		lblFotoPerfil = new JLabel(REGISTRAR_ALBUM_PORTADA_PLACEHOLDER);
		lblFotoPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotoPerfil.setBounds(270, 73, 158, 158);
		contentPane.add(lblFotoPerfil);
		
		btnSalir = new JButton(DATOS_USUARIO_SALIR);
		btnSalir.addActionListener(this);
		btnSalir.setBounds(343, 373, 85, 21);
		contentPane.add(btnSalir);
		
		btnAplicar = new JButton(DATOS_USUARIO_APLICAR);
		btnAplicar.setEnabled(false);
		btnAplicar.setBounds(248, 373, 85, 21);
		btnAplicar.addActionListener(this);
		contentPane.add(btnAplicar);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 272, 431, 2);
		contentPane.add(separator_1);
		
		chckbxEditable = new JCheckBox(DATOS_USUARIO_EDIT);
		chckbxEditable.setBounds(10, 342, 234, 21);
		chckbxEditable.addActionListener(this);
		contentPane.add(chckbxEditable);
		
		lblFechaCreacion = new JLabel(DATOS_USUARIO_CREACION);
		lblFechaCreacion.setBounds(20, 243, 230, 19);
		contentPane.add(lblFechaCreacion);
		
		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(249, 30, 1, 218);
		contentPane.add(separator_2);
		
		lblView = new JLabel(REGISTRAR_PREVIEW);
		lblView.setBounds(260, 45, 142, 13);
		contentPane.add(lblView);
		
		btnCambiarFoto = new JButton(RF_AVATAR);
		btnCambiarFoto.setEnabled(false);
		btnCambiarFoto.setBounds(270, 242, 158, 21);
		btnCambiarFoto.addActionListener(this);
		contentPane.add(btnCambiarFoto);
		
		JLabel lblInfo = new JLabel("<html><p>"+DATOS_USUARIO_AVISO+"</p></html>");
		lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(10, 307, 418, 29);
		contentPane.add(lblInfo);
		
		cargarInformacion();
	}
	
	public JButton getBtnSalir() {
		return btnSalir;
	}
	public JButton getBtnAplicar() {
		return btnAplicar;
	}
	public JLabel getLblFechaNac() {
		return lblFechaNac;
	}
	public JTextField getTxfApellidos() {
		return txfApellidos;
	}
	public JLabel getLblFechaCreacion() {
		return lblFechaCreacion;
	}
	public JPasswordField getPsfOld() {
		return psfOld;
	}
	public JTextField getTxfUserName() {
		return txfUsername;
	}
	public JPasswordField getPsfConfirm() {
		return psfConfirm;
	}
	public JTextField getTxfName() {
		return txfName;
	}
	public JPasswordField getPsfNew() {
		return psfNew;
	}
	public JCheckBox getChckbxEditable() {
		return chckbxEditable;
	}
	public JLabel getLblFotoPerfil() {
		return lblFotoPerfil;
	}
	public JButton getBtnCambiarFoto() {
		return btnCambiarFoto;
	}
	private boolean getIsEditable() {
		return isEditable;
	}
	private void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}
	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	
	private void cargarInformacion() {
		try {
			// Carga la imagen del avatar
			ImageIcon imagen = new ImageIcon(ICGuitarDatabase.obtenerFotoPerfil(ICGuitar.getUsuarioIniciado()));
			Image img = imagen.getImage().getScaledInstance(getLblFotoPerfil().getWidth(), getLblFotoPerfil().getHeight(), Image.SCALE_FAST);
			imagen = new ImageIcon(img);
			getLblFotoPerfil().setIcon(imagen);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet user = null;
		try {
			// Obtiene el usuario y sus datos
			user = ICGuitarDatabase.obtenerUsuario(ICGuitar.getUsuarioIniciado());
			while (user.next()) {
				Date fechaCreacion = new Date(user.getDate(4).getTime());
	            Date fechaActual = new Date(System.currentTimeMillis());
	            LocalDate fechaNac = new Date(user.getDate(5).getTime()).toLocalDate();
	            fechaNac.format(DateTimeFormatter.ISO_LOCAL_DATE);
	            long milisegundosEntreAmbas = fechaActual.getTime() - fechaCreacion.getTime();
	            int diasSiendoMiembro = (int) (milisegundosEntreAmbas / (1000 * 60 * 60 * 24));
				getTxfName().setText(user.getString(2));
				getTxfApellidos().setText(user.getString(3));
				getTxfUserName().setText(user.getString(1));
				getLblFechaCreacion().setText(getLblFechaCreacion().getText()+" "+String.format(DATOS_USUARIO_MIEMBRO, diasSiendoMiembro));
				getLblFechaNac().setText(getLblFechaNac().getText()+" "+fechaNac);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "deprecation" })
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(getBtnSalir())) {
			LogingFrame.getScreen().setEnabled(true);
			dispose();
		}
		if (e.getSource().equals(getChckbxEditable())) {
			// Comprueba que los campos sean editables o no
			boolean isEditable = getChckbxEditable().isSelected();
	        setIsEditable(isEditable);
	        System.out.println(getIsEditable());
			getTxfUserName().setEnabled(getIsEditable());
	        getTxfName().setEnabled(getIsEditable());
	        getTxfApellidos().setEnabled(getIsEditable());
	        getPsfConfirm().setEnabled(getIsEditable());
	        getPsfNew().setEnabled(getIsEditable());
	        getPsfOld().setEnabled(getIsEditable());
	        getBtnAplicar().setEnabled(getIsEditable());
	        getBtnCambiarFoto().setEnabled(getIsEditable());
		}
		if (e.getSource().equals(getBtnCambiarFoto())) {
			JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle(RF_AVATAR);
	        fileChooser.setApproveButtonText(FILE_CHOOSER_ACCEPT);
	        FileNameExtensionFilter filter = new FileNameExtensionFilter(FILE_EXTENSION_IMAGE, "jpg", "png", "gif");
	        fileChooser.setFileFilter(filter);
	        int result = fileChooser.showOpenDialog(contentPane);
	        
	        // Comprobará que el archivo sea válido y guardará su ruta
	        if (result == JFileChooser.APPROVE_OPTION) {
	            java.io.File selectedFile = fileChooser.getSelectedFile();
	            System.out.println("Archivo: " + selectedFile.getAbsolutePath());
	            setImageFile(selectedFile.getAbsolutePath());
	            File imagen = new File(getImageFile());
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(imagen);
					if (!getImageFile().equals("")) {
						ICGuitarDatabase.insertarImagenDB("UPDATE Usuario set fotoPerfil=? where nickname=?", imagen, fis, ICGuitar.getUsuarioIniciado());
						JOptionPane.showMessageDialog(null, String.format(DATOS_USUARIO_AVATAR, ICGuitar.getUsuarioIniciado()), DATOS_ACTUALIZADOS, JOptionPane.INFORMATION_MESSAGE);
						LogingFrame.getScreen().setEnabled(true);
						LogingFrame.getScreen().mostrarFotoPerfil();
						dispose();
					}
				} catch (FileNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
		}
		if (e.getSource().equals(getBtnAplicar())) {
			// Maneja los datos ingresados y hace control de errores.
			String currentPassword = null;
			try {
				currentPassword = ICGuitarDatabase.obtenerContrasena(ICGuitar.getUsuarioIniciado());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (getTxfName().getText().equals("") || getTxfApellidos().getText().equals("") || getTxfUserName().getText().equals("") || getPsfOld().getText().equals("") || getPsfNew().getText().equals("") ||getPsfConfirm().getText().equals("")) {
				JOptionPane.showMessageDialog(null, ERROR_REQUIRED_FIELDS, DATOS_USUARIO_ERROR, JOptionPane.ERROR_MESSAGE);
				return;
			} else if (getPsfOld().getText()!=null && getPsfOld().getText().equals(currentPassword)) {
				// Comprobación de errores, nombre
				if (getTxfUserName().getText().length()<5) {
					JOptionPane.showMessageDialog(null, ERROR_LONGITUD_NICKNAME, DATOS_USUARIO_ERROR, JOptionPane.ERROR_MESSAGE);
					return;
				} else if (!getTxfUserName().getText().matches("^^[a-zA-Z0-9._]+$")) {
					JOptionPane.showMessageDialog(null, ERROR_CARACTERES_NICKNAME, DATOS_USUARIO_ERROR, JOptionPane.ERROR_MESSAGE);
					return;
				} else
					try {
						if (ICGuitarDatabase.buscarUsuarioCreado(getTxfUserName().getText())) {
							JOptionPane.showMessageDialog(null, String.format(ERROR_NICKNAME_EXISTENTE, getTxfUserName().getText()), DATOS_USUARIO_ERROR, JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				// Comprobación de errores, contraseña
				if (getPsfNew().getText().length() < 10) {
	            	JOptionPane.showMessageDialog(null, ERROR_LONGITUD_CONTRASENIA, DATOS_USUARIO_ERROR, JOptionPane.ERROR_MESSAGE);
	                return;
	            } else if (!getPsfNew().getText().matches(".*[A-Z].*")) {
	            	JOptionPane.showMessageDialog(null, ERROR_MAYUSCULA_CONTRASENIA, DATOS_USUARIO_ERROR, JOptionPane.ERROR_MESSAGE);
	                return;
	            } else if (!getPsfNew().getText().matches(".*[a-z].*")) {
	            	JOptionPane.showMessageDialog(null, ERROR_MINUSCULA_CONTRASENIA, DATOS_USUARIO_ERROR, JOptionPane.ERROR_MESSAGE);
	                return;
	            } else if (!getPsfNew().getText().matches(".*\\d.*")) {
	            	JOptionPane.showMessageDialog(null, ERROR_DIGITO_CONTRASENIA, DATOS_USUARIO_ERROR, JOptionPane.ERROR_MESSAGE);
	                return;
	            } else if (!getPsfNew().getText().matches(".*[^a-zA-Z0-9].*")) {
	            	JOptionPane.showMessageDialog(null, ERROR_ESPECIAL_CONTRASENIA, DATOS_USUARIO_ERROR, JOptionPane.ERROR_MESSAGE);
	                return;
	            }
				
				// Comprobación contraseñas iguales
				if (getPsfNew().getText()!=null && getPsfConfirm().getText()!=null && getPsfNew().getText().equals(getPsfConfirm().getText())) {
					System.out.println("Datos actualizados.");
					try {
						ICGuitarDatabase.modificarUsuario(ICGuitar.getUsuarioIniciado(), getTxfUserName().getText(), getTxfName().getText(), getTxfApellidos().getText(), getPsfNew().getText());
						ICGuitar.setUsuarioIniciado(getTxfUserName().getText());
						JOptionPane.showMessageDialog(null, String.format(DATOS_USUARIO_ACTUALIZADO, ICGuitar.getUsuarioIniciado()), DATOS_ACTUALIZADOS, JOptionPane.INFORMATION_MESSAGE);
						LogingFrame.getScreen().getLblUserName().setText("<html><h3>"+"@"+ICGuitar.getUsuarioIniciado()+"</h3></html>");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					LogingFrame.getScreen().setEnabled(true);
					try {
						LogingFrame.getScreen().mostrarFotoPerfil();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, ERROR_NO_COINCIDEN_CONTRASENIAS, DATOS_USUARIO_ERROR, JOptionPane.ERROR_MESSAGE);
	                return;
				}
			} else {
				JOptionPane.showMessageDialog(null, ERROR_DATOS_USUARIO_CONT, DATOS_USUARIO_ERROR, JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
