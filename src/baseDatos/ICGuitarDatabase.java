package baseDatos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import javax.imageio.ImageIO;

import clases.ICGuitar;

public class ICGuitarDatabase {
	
    // Configuro la conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/ICGuitar";
    private static final String USUARIO = "root";
    private static final String CONTRASENNIA = "";

    // Método para establecer la conexión
    public static Connection conectar() {
        Connection conexion = null;
        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENNIA);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el controlador JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        }
        return conexion;
    }

    // Método para cerrar la conexión
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
                e.printStackTrace();
            }
        }
    }
    
    // Método para insertar datos
    public static void insertarDatosDB(String consulta, String [] campos) {
    	// Establecer la conexión a la base de datos
        Connection conexion = conectar();
        int totalCampos = campos.length+1;
        System.out.println(totalCampos);
        if (conexion != null) {
            try {
                // Preparar la consulta SQL para insertar un nuevo usuario
                PreparedStatement statement = conexion.prepareStatement(consulta);
                for (int i = 1; i < totalCampos; i++) {
                    statement.setString(i, campos[i - 1]);
                }
                
                // Ejecutar la consulta
                statement.executeUpdate();
                System.out.println("Consulta: '" + consulta + "' realizada correctamente.");
                
                // Cerrar los recursos
                statement.close();
                cerrarConexion(conexion);
            } catch (SQLException e) {
                System.err.println("Error al guardar datos en la base de datos.");
                e.printStackTrace();
            }
        }
    }
    
    // Método para insertar álbum
    public static void insertarAlbum(String nombre, String idArtista, Date fechaLanzamiento, File imageFile, FileInputStream fis) {
    	// Establecer la conexión a la base de datos
        Connection conexion = conectar();
        String consulta = "INSERT INTO Album (nombre, idArtistaAux, fechaLanzamiento, fotoAlbum) VALUES(?,?,?,?)";
        if (conexion != null) {
            try {
                // Preparar la consulta SQL para insertar un nuevo usuario
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setString(1, nombre);
                statement.setString(2, idArtista);
                statement.setDate(3, fechaLanzamiento);
                statement.setBinaryStream(4, fis, (int) imageFile.length());
                
                // Ejecutar la consulta
                statement.executeUpdate();
                System.out.println("Consulta: '" + consulta + "' realizada correctamente.");
                
                // Cerrar los recursos
                statement.close();
                cerrarConexion(conexion);
            } catch (SQLException e) {
                System.err.println("Error al guardar datos en la base de datos.");
                e.printStackTrace();
            }
        }
    }
    
 // Método para insertar imagenes
    public static void insertarImagenDB(String consulta, File imagenFile, FileInputStream fis, String nickname) {
        // Establecer la conexión a la base de datos
        Connection conexion = conectar();
        if (conexion != null) {
            try {
                // Preparar la consulta SQL para actualizar la imagen de perfil del usuario
                PreparedStatement statement = conexion.prepareStatement(consulta);
                
                // Configurar los parámetros para la imagen y el nickname
                statement.setBinaryStream(1, fis, (int) imagenFile.length());
                statement.setString(2, nickname);
                
                // Ejecutar la consulta
                statement.executeUpdate();
                System.out.println("Consulta: '" + consulta + "' realizada correctamente.");
                
                // Cerrar los recursos
                statement.close();
                cerrarConexion(conexion);
            } catch (SQLException e) {
                System.err.println("Error al guardar datos en la base de datos.");
                e.printStackTrace();
            }
        }
    }


    
 // Método para recibir datos
    public static ArrayList<String[]> recibirDatosDB(String consulta, String[] campos) {
        ArrayList<String[]> resultados = new ArrayList<>();
        Connection conexion = conectar();

        if (conexion != null) {
            try {
                PreparedStatement statement = conexion.prepareStatement(consulta);

                // Establecer los parámetros de la consulta
                for (int i = 0; i < campos.length; i++) {
                    statement.setString(i + 1, campos[i]);
                }

                ResultSet resultado = statement.executeQuery();

                // Procesar el resultado
                while (resultado.next()) {
                    String[] datosFila = new String[campos.length];
                    for (int i = 0; i < campos.length; i++) {
                        datosFila[i] = resultado.getString(campos[i]);
                    }
                    resultados.add(datosFila);
                }

                // Cerrar los recursos
                resultado.close();
                statement.close();
            } catch (SQLException e) {
                System.err.println("Error al ejecutar la consulta en la base de datos.");
                e.printStackTrace();
            } finally {
                cerrarConexion(conexion);
            }
        }
        return resultados;
    }
    
    // Metodo para comprobar si un usuario ha sido creado
    public static boolean buscarUsuarioCreado(String username) throws SQLException {
    	boolean encontrado = false;
        Connection conexion = conectar();
        PreparedStatement st = null;
        ResultSet resultado = null;
		String sql="SELECT * FROM usuario WHERE nickname=?";
		try{
			st = conexion.prepareStatement(sql);
	        st.setString(1, username);
	        resultado = st.executeQuery();
			while (resultado.next()){
				encontrado = true;
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return encontrado;
	}
    
    // Método para obtener un usuario y sus datos
    public static ResultSet obtenerUsuario(String nick) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
		String sql="SELECT nickname, nombre, apellidos, fechaCreacion, fechaNacimiento FROM usuario WHERE nickname=?";
		try{
			st = con.prepareStatement(sql);
	        st.setString(1, nick);
	        resultado = st.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
    
    // Obtiene el ID del artista
    public static String obtenerIdArtista(String nick, String bio) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        String id = null;
		String sql="SELECT DISTINCT idArtista FROM artista WHERE nombre=? AND biografia=?";
		try{
			st = con.prepareStatement(sql);
			st.setString(1, nick);
			st.setString(2, bio);
	        resultado = st.executeQuery();
	        while (resultado.next()) {
	        	id = resultado.getString("idArtista");
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
    
    // Obtiene la contraseña 
    public static String obtenerContrasena(String nick) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        String pass = null;
		String sql="SELECT AES_DECRYPT(contrasennia, 'passwordKey') AS contrasena FROM usuario WHERE nickname=?";
		try{
			st = con.prepareStatement(sql);
			st.setString(1, nick);
	        resultado = st.executeQuery();
	        while (resultado.next()) {
	        	pass = resultado.getString("contrasena");
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return pass;
	}
    
    // Modifica los datos del usuario
    public static void modificarUsuario(String nickViejo, String nick, String nombre, String apellidos, String contrasennia) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
		String sql="UPDATE usuario SET nickname = ?, nombre = ?, apellidos = ?, contrasennia = AES_ENCRYPT(?, 'passwordKey') WHERE usuario.nickname = ?";
		try{
			st = con.prepareStatement(sql);
			st.setString(1, nick);
			st.setString(2, nombre);
			st.setString(3, apellidos);
			st.setString(4, contrasennia);
			st.setString(5, nickViejo);
			st.executeUpdate();
			System.out.println("Modificación realizada.");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
    // Obtiene el nombre e id artista
    public static ResultSet obtenerDatosArtista() throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
		String sql="SELECT DISTINCT nombre, idArtista FROM artista";
		try{
			st = con.prepareStatement(sql);
	        resultado = st.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
    
    // Obtiene los albumes de cada artista
    public static ArrayList<String> obtenerAlbumesArtista(String idArtista) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        ArrayList<String> albumes = new ArrayList<String>();
        String idAlbum;
		String sql="SELECT idAlbum FROM album WHERE idArtistaAux=?";
		try{
			st = con.prepareStatement(sql);
			st.setString(1, idArtista);
	        resultado = st.executeQuery();
	        while(resultado.next()) {
	        	idAlbum=resultado.getString(1);
	        	albumes.add(idAlbum+";"+obtenerNombreAlbum(idAlbum));
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return albumes;
	}
    
    // obtiene el nombre del album
    public static String obtenerNombreAlbum(String idAlbumAux) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        String nombre="";
		String sql="SELECT nombre FROM album WHERE idAlbum=?";
		try{
			st = con.prepareStatement(sql);
			st.setString(1, idAlbumAux);
	        resultado = st.executeQuery();
	        while(resultado.next()) {
	        	nombre=resultado.getString(1);
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return nombre;
	}
    
    // Obtiene el nombre del artista
    public static String obtenerNombreArtista(String idArtista) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        String nombre = "";
		String sql="SELECT DISTINCT nombre FROM artista where idArtista=?";
		try{
			st = con.prepareStatement(sql);
			st.setString(1, idArtista);
	        resultado = st.executeQuery();
	        while (resultado.next()) {
	        	nombre=resultado.getString(1);
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return nombre;
	}
    
    // Obtiene las canciones registradas por un usuario
    public static String obtenerUsuarioCancion(String idCancion) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        String nombre = "";
		String sql="SELECT DISTINCT nicknameAux FROM registraCancion where idCancionAux=?";
		try{
			st = con.prepareStatement(sql);
			st.setString(1, idCancion);
	        resultado = st.executeQuery();
	        while (resultado.next()) {
	        	nombre=resultado.getString(1);
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return nombre;
	}
    
    // Obtiene los datos de un album
    public static ResultSet obtenerDatosAlbum() throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
		String sql="SELECT DISTINCT idAlbum, nombre, fechaLanzamiento, fotoAlbum, idArtistaAux FROM album";
		try{
			st = con.prepareStatement(sql);
	        resultado = st.executeQuery();
	        
	        /*
	         Para obtener dichos datos:
	         	resultado.getString(1);
		        resultado.getString(2);
		        resultado.getDate(3);
		        fotoAlbum = ImageIO.read(resultado.getBinaryStream("fotoAlbum"));
		        resultado.getString(5);
	        */
	        
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
    
    // Obtiene la biografia de un artista
    public static String obtenerBioArtista(String idArtista) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        String camposArtista = null;
		String sql="SELECT DISTINCT biografia FROM artista where idArtista=?";
		try{
			st = con.prepareStatement(sql);
			st.setString(1, idArtista);
	        resultado = st.executeQuery();
	        while (resultado.next()) {
	        	camposArtista=resultado.getString(1);
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return camposArtista;
	}
    
    // Obtiene la foto de perfil
    public static BufferedImage obtenerFotoPerfil(String nick) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        BufferedImage fotoPerfil = null;
		String sql="SELECT fotoperfil FROM usuario WHERE nickname=?";
		try{
			st = con.prepareStatement(sql);
	        st.setString(1, nick);
	        resultado = st.executeQuery();
	        while (resultado.next()) {
				try {
					fotoPerfil = ImageIO.read(resultado.getBinaryStream("fotoPerfil"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return fotoPerfil;
	}
    
    // Obtiene la foto del artista
    public static BufferedImage obtenerFotoArtista(String id) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        BufferedImage fotoPerfil = null;
		String sql="SELECT fotoArtista FROM artista WHERE idArtista=?";
		try{
			st = con.prepareStatement(sql);
	        st.setString(1, id);
	        resultado = st.executeQuery();
	        while (resultado.next()) {
				try {
					fotoPerfil = ImageIO.read(resultado.getBinaryStream("fotoArtista"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return fotoPerfil;
	}
    
    // Obtiene una cancion
    public static ResultSet obtenerCancion(String id) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
		String sql="SELECT * FROM cancion WHERE idCancion=?";
		try{
			st = con.prepareStatement(sql);
	        st.setString(1, id);
	        resultado = st.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
    
    // Obtiene un album
    public static ResultSet obtenerAlbum(String id) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
		String sql="SELECT * FROM album WHERE idAlbum=?";
		try{
			st = con.prepareStatement(sql);
	        st.setString(1, id);
	        resultado = st.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
    
    // Obtiene todos los datos de x tabla
    public static ResultSet obtenerGlobal(String tabla) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
		String sql="SELECT * FROM "+tabla+";";
		try{
			st = con.prepareStatement(sql);
	        resultado = st.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
    
    // Obtiene canciones de un album
    public static ArrayList<String> obtenerCancionesAlbum(String album) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        ArrayList<String> canciones = new ArrayList<String>();
		String sql="SELECT * FROM albumcontiene WHERE idAlbumAux=?;";
		try{
			st = con.prepareStatement(sql);
			st.setString(1, album);
	        resultado = st.executeQuery();
	        
	        while (resultado.next()) {
	        	canciones.add(obtenerListaCanciones(resultado.getString("idCancionAux"))+";"+resultado.getString("idCancionAux"));
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return canciones;
	}
    
    // Obtiene la lista de canciones
    public static String obtenerListaCanciones(String idCancion) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        String titulo = null;
		String sql="SELECT nombre FROM cancion WHERE idCancion=?;";
		try{
			st = con.prepareStatement(sql);
			st.setString(1, idCancion);
	        resultado = st.executeQuery();
	        while(resultado.next()) {
	        	titulo=resultado.getString(1);
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return titulo;
	}
    
    // Obtiene canciones de un album
    public static ResultSet obtenerAlbumCancion(String id) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
		String sql="SELECT * FROM albumContiene WHERE idCancionAux=?";
		try{
			st = con.prepareStatement(sql);
	        st.setString(1, id);
	        resultado = st.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
    
    // Obtiene dificultades
    public static ResultSet obtenerDificultades() throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
		String sql="SELECT DISTINCT idDificultad, dificultad FROM dificultad";
		try{
			st = con.prepareStatement(sql);
	        resultado = st.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
    
    // Obtiene comentarios
    public static ResultSet obtenerComentarios(String idCancion) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
		String sql="SELECT DISTINCT * FROM comentaCancion where idCancionAux=?";
		try{
			st = con.prepareStatement(sql);
			st.setString(1, idCancion);
	        resultado = st.executeQuery();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
    
    // Comprueba si una cancion, album o artista son favoritos de x usuario / canciones son subidas por x usuario
    public static Boolean esFavorita(String id, String nicknameAux, String opcion) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        Boolean esFavorita=false;
		String sql="";
		switch (opcion) {
			case "album":
				sql="SELECT * from albumesFavorito WHERE idAlbumAux=? AND nicknameAux=?";
				System.out.println("Album");
				break;
			case "cancion":
				sql="SELECT * from cancionesFavoritas WHERE idCancionAux=? AND nicknameAux=?";
				System.out.println("Cancion");
				break;
			case "artista":
				sql="SELECT * from artistasFavoritos WHERE idArtistaAux=? AND nicknameAux=?";
				System.out.println("Artista");
				break;
			case "misCanciones":
				sql="SELECT * from registraCancion WHERE idCancionAux=? AND nicknameAux=?";
				System.out.println("Artista");
				break;
		}
		try{
			st = con.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2, nicknameAux);
	        resultado = st.executeQuery();
	        while (resultado.next()) {
	        	esFavorita=true;
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return esFavorita;
	}
    
    // Obtiene el último id registrado
    public static String obtenerUltimoID() throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        String id = null;
		String sql="SELECT @@IDENTITY AS ultimoId";
		try{
			st = con.prepareStatement(sql);
	        resultado = st.executeQuery();
	        
	        while(resultado.next()) {
	        	id = resultado.getString("ultimoId");
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
    
    // Obtiene el genero de una canción
    public static String obtenerGeneroCancion(String idCancionAux) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        String genero = null;
		String sql="SELECT idGeneroAux from perteneceGenero where idCancionAux = ?";
		try{
			st = con.prepareStatement(sql);
			st.setString(1, idCancionAux);
	        resultado = st.executeQuery();
	        
	        while(resultado.next()) {
	        	String idGenero = resultado.getString(1);
	        	genero=obtenerGenero(idGenero);
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return genero;
	}
    
    // Obtiene el genero
    public static String obtenerGenero(String idGenero) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        String genero = null;
		String sql="SELECT genero from generomusical where idGenero = ?";
		try{
			st = con.prepareStatement(sql);
			st.setString(1, idGenero);
	        resultado = st.executeQuery();
	        
	        while(resultado.next()) {
	        	genero = resultado.getString(1);
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return genero;
	}
    
    // Obtiene el tipo de cancion
    public static String obtenerTipoCancion(int idTipo) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        String tipo = null;
		String sql="SELECT tipo from tipocancion where idTipo = ?";
		try{
			st = con.prepareStatement(sql);
			st.setInt(1, idTipo);
	        resultado = st.executeQuery();
	        
	        while(resultado.next()) {
	        	tipo = resultado.getString(1);
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return tipo;
	}
    
    
    // Obtiene el tipo de cancion
    public static String obtenerTipoDeCancion(String idCancion) throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        String tipo = null;
		String sql="SELECT idTipoAux from cancion where idCancion = ?";
		try{
			st = con.prepareStatement(sql);
			st.setString(1, idCancion);
	        resultado = st.executeQuery();
	        
	        while(resultado.next()) {
	        	tipo = resultado.getString(1);
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return tipo;
	}
    
    // Obtiene canciones
    public static ResultSet obtenerCanciones() throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
		String sql="SELECT * from cancion";
		try{
			st = con.prepareStatement(sql);
	        resultado = st.executeQuery();
	       
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
    
    // Obtiene albumes
    public static ResultSet obtenerAlbumes() throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
		String sql="SELECT * from album";
		try{
			st = con.prepareStatement(sql);
	        resultado = st.executeQuery();
	       
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
    
    // Obtiene generos
    public static ArrayList<String> obtenerGeneros() throws SQLException {
		Connection con=conectar();
		PreparedStatement st = null;
        ResultSet resultado = null;
        ArrayList<String> generos = new ArrayList<String>();
        String genero = null;
		String sql="SELECT * from generomusical";
		try{
			st = con.prepareStatement(sql);
	        resultado = st.executeQuery();
	        
	        while(resultado.next()) {
	        	genero = resultado.getString(1)+";"+resultado.getString(2);
	        	generos.add(genero);
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return generos;
	}
    
    // Registra canciones
    public static void registrarCanciones(int idCancion, String nicknameAux) throws SQLException {
		Connection con=conectar();
		String sql="Insert into registraCancion (idCancionAux, nicknameAux) VALUES (?,?)";
		if (con != null) {
            try {
                // Preparar la consulta SQL para insertar un nuevo usuario
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, idCancion);
                statement.setString(2, nicknameAux);
                
                // Ejecutar la consulta
                statement.executeUpdate();
                System.out.println("Canción registrada.");
                
                // Cerrar los recursos
                statement.close();
                cerrarConexion(con);
            } catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
	}
    
    // Inserta generos de una cancion
    public static void insertarGenero(int idCancion, String idGenero) throws SQLException {
		Connection con=conectar();
		String sql="Insert into perteneceGenero (idCancionAux, idGeneroAux) VALUES (?,?)";
		if (con != null) {
            try {
                // Preparar la consulta SQL para insertar un nuevo usuario
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, idCancion);
                statement.setString(2, idGenero);
                
                // Ejecutar la consulta
                statement.executeUpdate();
                System.out.println("Genero registrada.");
                
                // Cerrar los recursos
                statement.close();
                cerrarConexion(con);
            } catch (SQLException e) {
    			e.printStackTrace();
    		}
        }
	}
    
 // Método para insertar álbum
    public static boolean insertarCancion(String idGenero, String nombreCancion, String idDificultad, String urlClip, String idAlbumAux, int tipoCancion, String cancion) {
    	// Establecer la conexión a la base de datos
        Connection conexion = conectar();
        String consulta = "INSERT INTO cancion (totalComentarios, nombre, idDificultadAux, videoCancion, cancion, idTipoAux) VALUES (?, ?, ?, ?, ?, ?)";
        if (conexion != null) {
            try {
                // Preparar la consulta SQL para insertar un nuevo usuario
                PreparedStatement statement = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, 0);
                statement.setString(2, nombreCancion);
                statement.setString(3, idDificultad);
                statement.setString(4, urlClip);
                statement.setString(5, cancion);
                statement.setInt(6, tipoCancion);
                
                // Ejecutar la consulta
                statement.executeUpdate();
                
                ResultSet obtenerID = statement.getGeneratedKeys();
                if (obtenerID.next()) {
                    int idCancion = obtenerID.getInt(1);
                    registrarCanciones(idCancion, ICGuitar.getUsuarioIniciado());
                    albumContiene(idCancion, idAlbumAux);
                    insertarGenero(idCancion, idGenero);
                }
                System.out.println("Canción '" + nombreCancion + "' registrada correctamente.");
                
                // Cerrar los recursos
                statement.close();
                obtenerID.close();
                cerrarConexion(conexion);                
                return true;
            } catch (SQLException e) {
                System.err.println("Error al guardar datos en la base de datos.");
                e.printStackTrace();
            }
        }
		return false;
    }
    
 // Método para insertar álbum
    public static void albumContiene(int idCancionAux, String idAlbumAux) {
    	// Establecer la conexión a la base de datos
        Connection conexion = conectar();
        String consulta = "INSERT INTO albumcontiene (idCancionAux, idAlbumAux) VALUES (?, ?)";
        if (conexion != null) {
            try {
                // Preparar la consulta SQL para insertar un nuevo usuario
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setInt(1, idCancionAux);
                statement.setString(2, idAlbumAux);
 
                // Ejecutar la consulta
                statement.executeUpdate();
                System.out.println("Canción vinculada al álbum: " + idAlbumAux);
                
                // Cerrar los recursos
                statement.close();
                cerrarConexion(conexion);
            } catch (SQLException e) {
                System.err.println("Error al guardar datos en la base de datos.");
                e.printStackTrace();
            }
        }
    }
    
 // Método para insertar comentarios
    public static void comentar(String nickname, String idCancion, String texto) {
    	// Establecer la conexión a la base de datos
        Connection conexion = conectar();
        String consulta = "INSERT INTO comentaCancion (nicknameAux, idCancionAux, comentario) VALUES (?, ?, ?)";
        if (conexion != null) {
            try {
                // Preparar la consulta SQL para insertar un nuevo usuario
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setString(1, nickname);
                statement.setString(2, idCancion);
                statement.setString(3, texto);
 
                // Ejecutar la consulta
                statement.executeUpdate();
                System.out.println("Comentario añadido.");
                
                // Cerrar los recursos
                statement.close();
                cerrarConexion(conexion);
            } catch (SQLException e) {
                System.err.println("Error al guardar datos en la base de datos.");
                e.printStackTrace();
            }
        }
    }
    
 // Método para insertar comentarios
    public static void marcarFavorita(String nickname, String id, String opcion) {
    	// Establecer la conexión a la base de datos
        Connection conexion = conectar();
        String consulta = "";
        switch (opcion) {
	        case "cancion":
	        	consulta="INSERT INTO cancionesfavoritas (nicknameAux, idCancionAux) VALUES (?, ?)";
	        	break;
	        case "album":
	        	consulta="INSERT INTO albumesFavorito (nicknameAux, idAlbumAux) VALUES (?, ?)";
	        	break;
	        case "artista":
	        	consulta="INSERT INTO artistasfavoritos (nicknameAux, idArtistaAux) VALUES (?, ?)";
	        	break;
	    }
        if (conexion != null) {
            try {
                // Preparar la consulta SQL para insertar un nuevo usuario
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setString(1, nickname);
                statement.setString(2, id);
 
                // Ejecutar la consulta
                statement.executeUpdate();
                System.out.println("Marcada como favorita.");
                
                // Cerrar los recursos
                statement.close();
                cerrarConexion(conexion);
            } catch (SQLException e) {
                System.err.println("Error al guardar datos en la base de datos.");
                e.printStackTrace();
            }
        }
    }
    
 // Método para insertar comentarios
    public static void desmarcarFavorita(String nickname, String idCancion, String opcion) {
    	// Establecer la conexión a la base de datos
        Connection conexion = conectar();
        String consulta = "";
        switch (opcion) {
	        case "album":
	        	consulta="DELETE FROM albumesFavorito WHERE nicknameAux=? AND idAlbumAux=?";
	        	break;
	        case "cancion":
	        	consulta="DELETE FROM cancionesfavoritas WHERE nicknameAux=? AND idCancionAux=?";
	        	break;
	        case "artista":
	        	consulta="DELETE FROM artistasfavoritos WHERE nicknameAux=? AND idArtistaAux=?";
	        	break;
	    }
        if (conexion != null) {
            try {
                // Preparar la consulta SQL para insertar un nuevo usuario
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setString(1, nickname);
                statement.setString(2, idCancion);
 
                // Ejecutar la consulta
                statement.executeUpdate();
                System.out.println("Eliminada como favorita.");
                
                // Cerrar los recursos
                statement.close();
                cerrarConexion(conexion);
            } catch (SQLException e) {
                System.err.println("Error al guardar datos en la base de datos.");
                e.printStackTrace();
            }
        }
    }

    
 // Método para insertar álbum
    public static void registrarUsuario(String nick, String nombre, String apellidos, String contrasennia, Date fechaNac, Date fechaCreacion, File imageFile, FileInputStream fis) {
    	// Establecer la conexión a la base de datos
        Connection conexion = conectar();
        String consulta = "INSERT INTO usuario (nickname, nombre, apellidos, contrasennia, fechaNacimiento, fechaCreacion, fotoPerfil) VALUES (?, ?, ?, aes_encrypt(?, 'passwordKey'), ?, ?, ?)";
        if (conexion != null) {
            try {
                // Preparar la consulta SQL para insertar un nuevo usuario
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setString(1, nick);
                statement.setString(2, nombre);
                statement.setString(3, apellidos);
                statement.setString(4, contrasennia);
                statement.setDate(5, fechaNac);
                statement.setDate(6, fechaCreacion);
                statement.setBinaryStream(7, fis, (int) imageFile.length());
                
                // Ejecutar la consulta
                statement.executeUpdate();
                System.out.println("Usuario '" + nick + "' registrado correctamente.");
                
                // Cerrar los recursos
                statement.close();
                cerrarConexion(conexion);
            } catch (SQLException e) {
                System.err.println("Error al guardar datos en la base de datos.");
                e.printStackTrace();
            }
        }
    }
}
