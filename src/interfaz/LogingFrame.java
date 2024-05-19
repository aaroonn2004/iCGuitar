package interfaz;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import clases.*;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import baseDatos.*;

public class LogingFrame extends JFrame implements LenguajeTexto, ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txfUsername;
	private JPasswordField psfPassw;
	private static JCheckBox chkRememberMe;
	private JButton btnIniciarSesion;
	private JButton btnCrearUnaCuenta;
	private JLabel lblImgSplash;
	private static HomeScreen screen;

	/**
	 * Create the frame.
	 */
	public LogingFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HomeScreen.class.getResource(APP_ICON)));
		setResizable(false);
		setTitle(VENTANA_LOG_IN);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 391);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel(LF_USERNAME);
		lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblUsuario.setBounds(27, 86, 173, 22);
		contentPane.add(lblUsuario);

		txfUsername = new JTextField();
		txfUsername.setFont(new Font("Segoe UI Variable", Font.PLAIN, 12));
		lblUsuario.setLabelFor(txfUsername);
		txfUsername.setBounds(27, 118, 155, 19);
		contentPane.add(txfUsername);
		txfUsername.setColumns(10);

		JLabel lblPassw = new JLabel(LF_PASSWORD);
		lblPassw.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPassw.setBounds(27, 147, 173, 22);
		contentPane.add(lblPassw);

		psfPassw = new JPasswordField();
		lblPassw.setLabelFor(psfPassw);
		psfPassw.setFont(new Font("Segoe UI Variable", Font.PLAIN, 12));
		psfPassw.setBounds(27, 179, 155, 19);
		contentPane.add(psfPassw);

		btnIniciarSesion = new JButton(LF_LOG_IN);
		btnIniciarSesion.addActionListener(this);
		btnIniciarSesion.setBackground(SystemColor.control);
		btnIniciarSesion.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnIniciarSesion.setBounds(27, 205, 155, 21);
		contentPane.add(btnIniciarSesion);

		JLabel lblTitle = new JLabel(TITULO_APP);
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 26));
		lblTitle.setBounds(48, 27, 141, 69);
		contentPane.add(lblTitle);

		JLabel lblNewAccount = new JLabel(LF_NO_ACCOUNT);
		lblNewAccount.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewAccount.setBounds(37, 262, 145, 22);
		contentPane.add(lblNewAccount);

		btnCrearUnaCuenta = new JButton(LF_CREATE_ACCOUNT);
		btnCrearUnaCuenta.addActionListener(this);
		btnCrearUnaCuenta.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCrearUnaCuenta.setBackground(SystemColor.control);
		btnCrearUnaCuenta.setBounds(27, 290, 155, 21);
		contentPane.add(btnCrearUnaCuenta);

		lblImgSplash = new JLabel("");
		lblImgSplash.setIcon(new ImageIcon(LogingFrame.class.getResource("/Assets/Images/splash.png")));
		lblImgSplash.setBounds(210, 10, 432, 334);
		contentPane.add(lblImgSplash);
	}
	
	public static JCheckBox getChkRememberMe() {
		return chkRememberMe;
	}
	
	public JTextField getTxfUsername() {
		return txfUsername;
	}
	
	public JPasswordField getPsfPassw() {
		return psfPassw;
	}
	
	public JButton getBtnIniciarSesion() {
		return btnIniciarSesion;
	}
	
	public JButton getBtnCrearUnaCuenta() {
		return btnCrearUnaCuenta;
	}

	public static HomeScreen getScreen() {
		return screen;
	}

	public static void setScreen(HomeScreen screen) {
		LogingFrame.screen = screen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource().equals(getBtnIniciarSesion())) {
	        String username = getTxfUsername().getText();
	        String password = new String(getPsfPassw().getPassword());	        

	        ICGuitar.setUsuarioIniciado(iniciarSesion(username, password));
	    }

	    if (e.getSource().equals(getBtnCrearUnaCuenta())) {
	    	ICGuitar.getLogin().setVisible(false);
            ICGuitar.getRegister().setVisible(true);
	    }
	}
	
	public static String iniciarSesion(String nickname, String contrasennia) {
	    // Declaración e inicialización de variables del método iniciarSesion
	    boolean error = false;
	    boolean existe = false;
	    boolean contrasenniaCorrecta = false;
	    boolean iniciado = false;
	    String nickCorrecto = "";
	    
	    if (nickname.isEmpty() || contrasennia.isEmpty()) {
	        JOptionPane.showMessageDialog(null, ERROR_REQUIRED_FIELDS, ERROR_DATABASE_LECTURA, JOptionPane.ERROR_MESSAGE);
	        return "";
	    }
	    
	    // Conecta con la base de datos
	    
	    Connection conexion = ICGuitarDatabase.conectar();
	    
	    if (conexion != null) {
	        try {
	        	String consulta = "SELECT nickname, aes_decrypt(contrasennia, 'passwordKey') AS contrasennia FROM usuario WHERE nickname = ?"; // Obtiene los datos para iniciar sesi´no del usuario
	            PreparedStatement statement = conexion.prepareStatement(consulta);
	            statement.setString(1, nickname.toLowerCase());
	            
	            ResultSet resultado = statement.executeQuery();
	            
	            while (resultado.next()) {
	                existe = true;
	                String nickDB = resultado.getString("nickname");
	                String contrasenniaDB = resultado.getString("contrasennia");
	                
	                if (contrasennia.equals(contrasenniaDB) && !iniciado) {
	                    nickCorrecto = nickDB;
	                    contrasenniaCorrecta = true;
	                    iniciado = true;
	                }
	            }
	            
	            resultado.close();
	            statement.close();
	            ICGuitarDatabase.cerrarConexion(conexion);
	        } catch (Exception e) {
	            System.err.println("Error al iniciar sesión: " + e.getMessage());
	            e.printStackTrace();
	            error = true;
	        }
	    }
	    
	    // Gestiona los distintos tipos de erorres
	    
	    if (error) {
	    	JOptionPane.showMessageDialog(null, ERROR_DATABASE_LECTURA, ERROR_DATABASE_LECTURA, JOptionPane.ERROR_MESSAGE);
	        return "";
	    }
	    
	    if (!existe || !contrasenniaCorrecta) {
	    	JOptionPane.showMessageDialog(null, ERROR_CONTRASENNIA_INCORRECTA, ERROR_DATABASE_LECTURA, JOptionPane.ERROR_MESSAGE);
	        return "";
	    }
	    
	    if (iniciado) {
	        ICGuitar.setUsuarioIniciado(nickCorrecto);
	        JOptionPane.showMessageDialog(null, String.format(MENSAJE_SESION_INICIADA, nickCorrecto), String.format(MENSAJE_BIENVENIDA, nickCorrecto), JOptionPane.INFORMATION_MESSAGE);
	        setScreen(new HomeScreen());
	        getScreen().setVisible(true);
	        ICGuitar.getLogin().dispose();
	    }
	    
	    return nickCorrecto;
	}
}