package registroDatos;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import baseDatos.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import clases.*;
import interfaz.HomeScreen;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JFileChooser;

public class RegisterFrame extends JFrame implements LenguajeTexto, ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField psfConfirmar;
	private JTextField txfNombre;
	private JTextField txfApellidos;
	private JButton btnIniciarSesion;
	private JTextField txfUsername;
	private JDateChooser dteFechaNac;
	private JButton btnRegistrarse;
	private JPasswordField psfPassw;
	private JLabel lblError;
	private JButton btnImagenPerfil;
	private String imageFile = "";

	/**
	 * Create the frame.
	 */
	public RegisterFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeScreen.class.getResource(APP_ICON)));
		setResizable(false);
		setTitle(VENTANA_REGISTER);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 579);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel(TITULO_APP);
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 26));
		lblTitle.setBounds(52, 10, 141, 69);
		contentPane.add(lblTitle);

		JLabel lblUsuario = new JLabel(LF_USERNAME);
		lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblUsuario.setBounds(20, 71, 173, 22);
		contentPane.add(lblUsuario);

		txfUsername = new JTextField();
		txfUsername.setFont(new Font("Segoe UI Variable", Font.PLAIN, 12));
		lblUsuario.setLabelFor(txfUsername);
		txfUsername.setBounds(20, 103, 155, 19);
		contentPane.add(txfUsername);
		txfUsername.setColumns(10);

		JLabel lblPassw = new JLabel(LF_PASSWORD);
		lblPassw.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPassw.setBounds(20, 227, 173, 22);
		contentPane.add(lblPassw);

		psfPassw = new JPasswordField();
		lblPassw.setLabelFor(psfPassw);
		psfPassw.setFont(new Font("Segoe UI Variable", Font.PLAIN, 12));
		psfPassw.setBounds(20, 259, 155, 19);
		contentPane.add(psfPassw);


		psfConfirmar = new JPasswordField();
		psfConfirmar.setFont(new Font("Segoe UI Variable", Font.PLAIN, 12));
		psfConfirmar.setBounds(20, 314, 155, 19);
		contentPane.add(psfConfirmar);

		JLabel lblConfirmarContrasea = new JLabel(LF_CONFIRM_PASSWORD);
		lblConfirmarContrasea.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblConfirmarContrasea.setBounds(20, 282, 173, 22);
		contentPane.add(lblConfirmarContrasea);

		JLabel lblNombre = new JLabel(RF_NAME);
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNombre.setBounds(20, 121, 173, 22);
		contentPane.add(lblNombre);

		txfNombre = new JTextField();
		txfNombre.setFont(new Font("Segoe UI Variable", Font.PLAIN, 12));
		txfNombre.setColumns(10);
		txfNombre.setBounds(20, 153, 155, 19);
		contentPane.add(txfNombre);

		JLabel lblApellidos = new JLabel(RF_SURNAME);
		lblApellidos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblApellidos.setBounds(20, 173, 173, 22);
		contentPane.add(lblApellidos);

		txfApellidos = new JTextField();
		txfApellidos.setFont(new Font("Segoe UI Variable", Font.PLAIN, 12));
		txfApellidos.setColumns(10);
		txfApellidos.setBounds(20, 205, 155, 19);
		contentPane.add(txfApellidos);

		dteFechaNac = new JDateChooser();
		dteFechaNac.getCalendarButton().addActionListener(dteFechaNac);
		dteFechaNac.setBounds(20, 370, 155, 19);
		contentPane.add(dteFechaNac);

		JLabel lblFechaNacimiento = new JLabel(RF_DATE);
		lblFechaNacimiento.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblFechaNacimiento.setBounds(20, 338, 173, 22);
		contentPane.add(lblFechaNacimiento);

		btnRegistrarse = new JButton(LF_CREATE_ACCOUNT);
		btnRegistrarse.setBounds(20, 446, 155, 21);
		btnRegistrarse.addActionListener(this);
		contentPane.add(btnRegistrarse);
		
		JLabel lblSplash = new JLabel("");
		lblSplash.setIcon(new ImageIcon(RegisterFrame.class.getResource("/Assets/Images/splash.png")));
		lblSplash.setBounds(211, 10, 431, 502);
		contentPane.add(lblSplash);
		
		JLabel lblIniciar = new JLabel(RF_CREATE_ACCOUNT);
		lblIniciar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblIniciar.setBounds(38, 475, 155, 13);
		contentPane.add(lblIniciar);
		
		btnIniciarSesion = new JButton(LF_LOG_IN);
		btnIniciarSesion.setBounds(20, 491, 155, 21);
		btnIniciarSesion.addActionListener(this);
		contentPane.add(btnIniciarSesion);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblError.setBounds(30, 522, 593, 20);
		contentPane.add(lblError);
		
		btnImagenPerfil = new JButton(RF_AVATAR);
		btnImagenPerfil.addActionListener(this);
		btnImagenPerfil.setBounds(20, 410, 155, 21);
		contentPane.add(btnImagenPerfil);
	}
	
	public JButton getBtnIniciarSesion() {
		return btnIniciarSesion;
	}
	
	public JTextField getTxfUsername() {
		return txfUsername;
	}
	
	public JTextField getTxfNombre() {
		return txfNombre;
	}
	
	public JTextField getTxfApellidos() {
		return txfApellidos;
	}
	
	public JDateChooser getDteFechaNac() {
		return dteFechaNac;
	}
	
	public JButton getBtnRegistrarse() {
		return btnRegistrarse;
	}
	
	public JPasswordField getPsfPassw() {
		return psfPassw;
	}
	
	public JPasswordField getPsfConfirmar() {
		return psfConfirmar;
	}
	
	public JLabel getLblError() {
		return lblError;
	}
	public JButton getBtnImagenPerfil() {
		return btnImagenPerfil;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource().equals(getBtnRegistrarse())) {
	    	// Gestiona el control de errores y registra al usuario
	    	String nombre = getTxfNombre().getText();
	    	String apellidos = getTxfApellidos().getText();
	    	Date fechaNacDate = getDteFechaNac().getDate();
	    	String fechaNac="";
	    	if (fechaNacDate != null) {
	    	    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
	    	    Date fechaActual = new Date(); // Obtener la fecha actual

	    	    if (fechaNacDate.after(fechaActual)) {
	    	        JOptionPane.showMessageDialog(null, ERROR_DATABASE_FECHA, ERROR_DATABASE_REGISTRO, JOptionPane.ERROR_MESSAGE);
	    	        return;
	    	    }

	    	    fechaNac = formatoFecha.format(fechaNacDate);
	    	    fechaNac = fechaNac.replaceAll("-", "/");
	    	    // Resto del código para registrar el usuario
	    	} else {
	    	    JOptionPane.showMessageDialog(null, ERROR_DATABASE_FECHAAC, ERROR_DATABASE_REGISTRO, JOptionPane.ERROR_MESSAGE);
	    	    return;
	    	}
	    	String username = getTxfUsername().getText();
	        String password = new String(getPsfPassw().getPassword());
	        String passwordConfirm = new String(getPsfConfirmar().getPassword());
	        System.out.println(fechaNac);

	        registrarUsuario(username, nombre, apellidos, password, passwordConfirm, fechaNac, imageFile);
	    }

	    if (e.getSource().equals(getBtnIniciarSesion())) {
	    	dispose();
            ICGuitar.getLogin().setVisible(true);
            ICGuitar.getRegister().setVisible(false);
	    }
	    
	    if (e.getSource().equals(btnImagenPerfil)) {
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
	            imageFile=selectedFile.getAbsolutePath();
	        }
	    }
	}
	
	public static void registrarUsuario(String nickname, String nombre, String apellidos, String contrasennia, String confirmarContrasennia, String fechaNacimiento, String imageFile) {
    	// Declaración de variables del método registrar usuario
        boolean error;
        LocalDate fechaCreacion;
        DateTimeFormatter formateadorFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        File imagen = new File(imageFile);
        
        if (nickname.equalsIgnoreCase("") || nombre.equalsIgnoreCase("") || apellidos.equalsIgnoreCase("") || confirmarContrasennia.equalsIgnoreCase("") || fechaNacimiento.equalsIgnoreCase("") || imagen==null) {
        	JOptionPane.showMessageDialog(null, ERROR_REQUIRED_FIELDS, ERROR_DATABASE_REGISTRO, JOptionPane.ERROR_MESSAGE);
        	return;
        }
        
        // Comprueba que el nombre de usuario cumpla unas normas básicas
        do {
            error = false;

            // Guarda el nick transformando la primera letra a mayúsculas
            nickname=nickname.toLowerCase();

            if (nickname.length() < 5) {
            	JOptionPane.showMessageDialog(null, ERROR_LONGITUD_NICKNAME, ERROR_DATABASE_REGISTRO, JOptionPane.ERROR_MESSAGE);
                error = true;
                return;
            } else if (!nickname.matches("^^[a-zA-Z0-9._]+$")) {
            	JOptionPane.showMessageDialog(null, ERROR_CARACTERES_NICKNAME, ERROR_DATABASE_REGISTRO, JOptionPane.ERROR_MESSAGE);
                error = true;
                return;
            }
        } while (error);
        
        // Guarda el nombre transformando la primera letra a mayúsculas        
        nombre = Character.toUpperCase(nombre.charAt(0)) + nombre.substring(1);
        
        // Guarda el apellido transformando la primera letra a mayúsculas
        String[] apellidosSplit = apellidos.split(" ");
        String resultado = "";
        
        for (String parte : apellidosSplit) {
            if (!resultado.isEmpty()) {
                resultado += " ";
            }
            resultado += Character.toUpperCase(parte.charAt(0)) + parte.substring(1);
        }
        
        apellidos = resultado;
        
        // Pide y comprueba las normas básicas de las contraseñas
        do {
            error = false;
            
            if (contrasennia.length() < 10) {
            	JOptionPane.showMessageDialog(null, ERROR_LONGITUD_CONTRASENIA, ERROR_DATABASE_REGISTRO, JOptionPane.ERROR_MESSAGE);
                error = true;
                return;
            } else if (!contrasennia.matches(".*[A-Z].*")) {
            	JOptionPane.showMessageDialog(null, ERROR_MAYUSCULA_CONTRASENIA, ERROR_DATABASE_REGISTRO, JOptionPane.ERROR_MESSAGE);
                error = true;
                return;
            } else if (!contrasennia.matches(".*[a-z].*")) {
            	JOptionPane.showMessageDialog(null, ERROR_MINUSCULA_CONTRASENIA, ERROR_DATABASE_REGISTRO, JOptionPane.ERROR_MESSAGE);
                error = true;
                return;
            } else if (!contrasennia.matches(".*\\d.*")) {
            	JOptionPane.showMessageDialog(null, ERROR_DIGITO_CONTRASENIA, ERROR_DATABASE_REGISTRO, JOptionPane.ERROR_MESSAGE);
                error = true;
                return;
            } else if (!contrasennia.matches(".*[^a-zA-Z0-9].*")) {
            	JOptionPane.showMessageDialog(null, ERROR_ESPECIAL_CONTRASENIA, ERROR_DATABASE_REGISTRO, JOptionPane.ERROR_MESSAGE);
                error = true;
                return;
            }

        } while (error);
        
        // Comprueba que ambas contraseñas sean idénticas
        do {
            error = false;

            if (!confirmarContrasennia.equals(contrasennia)) {
            	JOptionPane.showMessageDialog(null, ERROR_NO_COINCIDEN_CONTRASENIAS, ERROR_DATABASE_REGISTRO, JOptionPane.ERROR_MESSAGE);
                error = true;
                return;
            }

        } while (error);
        
     	// Guarda el usuario en la base de datos
        fechaCreacion = LocalDate.now();
        LocalDate fecha = LocalDate.parse(fechaNacimiento, formateadorFecha);
        FileInputStream fis;
        try {
			fis = new FileInputStream(imagen);			
			guardarUsuarioEnBD(nickname, nombre, apellidos, contrasennia, fecha, fechaCreacion, imagen, fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, ERROR_SELECCION_IMAGEN, ERROR_CREACION_CUENTA, JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, ERROR_SELECCION_IMAGEN, ERROR_DATABASE_REGISTRO, JOptionPane.ERROR_MESSAGE);
	        error = true;
	        return;
		}
		JOptionPane.showMessageDialog(null, MENSAJE_USUARIO_CREADO + nickname + MENSAJE_FECHA_CREACION + fechaCreacion.format(formateadorFecha) + ".\n" + String.format(MENSAJE_BIENVENIDA, nombre), RF_USER_CREATED, JOptionPane.INFORMATION_MESSAGE);
		
    }
	
	private static void guardarUsuarioEnBD(String nick, String nombre, String apellidos, String contrasennia, LocalDate fechaNacimiento, LocalDate fechaCreacion, File imagen, FileInputStream fis) {
        
        try {
			 if (!ICGuitarDatabase.buscarUsuarioCreado(nick)) {
				 ICGuitarDatabase.registrarUsuario(nick, nombre, apellidos, contrasennia, java.sql.Date.valueOf(fechaNacimiento), java.sql.Date.valueOf(fechaCreacion), imagen, fis);
			 } else {
				 JOptionPane.showMessageDialog(null, String.format(ERROR_NICKNAME_EXISTENTE, nick), ERROR_CREACION_CUENTA, JOptionPane.INFORMATION_MESSAGE);
				 return;
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}