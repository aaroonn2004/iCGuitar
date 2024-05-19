package clases;

public interface LenguajeTexto {
	final String APP_ICON = "/Assets/Images/favicon.png";
	final String MENSAJE_INGRESO_NICKNAME = "Ingresa un nick/alias de usuario: ";
    final String ERROR_LONGITUD_NICKNAME = "Error: el nombre de usuario debe tener una longitud mayor a cinco caracteres.";
    final String ERROR_CARACTERES_NICKNAME = "Error: el nombre de usuario solo puede contener caracteres alfanuméricos, guiones y puntos.";
    final String ERROR_NICKNAME_EXISTENTE = "Error: el nombre de usuario @%s ya existe.";
    final String ERROR_CREACION_CUENTA = "Error al crear la cuenta.";
    final String ERROR_SELECCION_IMAGEN = "Error: no se puede cargar esa imagen.";
    final String MENSAJE_INGRESO_NOMBRE = "Ingresa tu nombre: ";
    final String MENSAJE_INGRESO_APELLIDOS = "Ingresa tus apellidos: ";
    final String MENSAJE_INGRESO_CONTRASENIA = "Ingresa una contraseña: ";
    final String ERROR_LONGITUD_CONTRASENIA = "Error: la contraseña debe tener una longitud de al menos diez caracteres.";
    final String ERROR_MAYUSCULA_CONTRASENIA = "Error: la contraseña debe contener al menos una letra mayúscula.";
    final String ERROR_MINUSCULA_CONTRASENIA = "Error: la contraseña debe contener al menos una letra minúscula.";
    final String ERROR_DIGITO_CONTRASENIA = "Error: la contraseña debe contener al menos un dígito.";
    final String ERROR_ESPECIAL_CONTRASENIA = "Error: la contraseña debe contener al menos un carácter especial.";
    final String MENSAJE_CONFIRMACION_CONTRASENIA = "Confirma tu contraseña: ";
    final String ERROR_NO_COINCIDEN_CONTRASENIAS = "Error: las contraseñas no coinciden.";
    final String MENSAJE_INGRESO_DIA_NACIMIENTO = "Ingresa tu día de nacimiento (1-31): ";
    final String MENSAJE_INGRESO_MES_NACIMIENTO = "Ingresa tu mes de nacimiento (1-12): ";
    final String MENSAJE_INGRESO_ANIO_NACIMIENTO = "Ingresa tu año de nacimiento (1924-2024): ";
    final String MENSAJE_SESION_INICIADA = "Sesión iniciada como @%s.";
    final String ERROR_RANGO_DIA_NACIMIENTO = "Error: ingresa un día entre el 1 y el 31.";
    final String ERROR_RANGO_MES_NACIMIENTO = "Error: ingresa un mes entre el 1 y el 12.";
    final String ERROR_RANGO_ANIO_NACIMIENTO = "Error: ingresa un año entre 1924 y 2024.";
    final String ERROR_CONTRASENNIA_INCORRECTA = "Error: la contraseña y/o usuario ingresados son incorrectos.";
    final String ERROR_USUARIO_INEXISTENTE = "Error: el usuario @%s no existe.";
    final String ERROR_FORMATO_FECHA_NACIMIENTO = "Error: formato de fecha de nacimiento inválido. Debe ser en formato dd/MM/yyyy.";
    final String MENSAJE_BIENVENIDA = "¡Bienvenido, %s!";
    final String MENSAJE_USUARIO_CREADO = "Usuario: @";
    final String MENSAJE_FECHA_CREACION = " dado de alta el día - ";
    final String MENSAJE_SELECCIONAR_OPCION = "Selecciona una opción válida: ";
    final String MENSAJE_SELECCIONAR_REGISTRAR = " Registro: ";
    final String MENSAJE_SELECCIONAR_INICIAR = " Iniciar sesión: ";
    final String MENSAJE_SELECCIONAR_SALIR = " Salir: ";
    final String MENSAJE_SELECCIONAR_ATRAS = " Atrás: ";
    final String MENSAJE_DESPEDIDA = "Saliendo... ";
    final String MENSAJE_OPCION = "Opción: ";
    final String ERROR_OPCION_INCORRECTA = "Error: selecciona una opción válida.";
    final String TITULO_APP = "iCGuitar";
    final String VENTANA_LOG_IN = "iCGuitar - Inicio";
    final String VENTANA_REGISTER = "iCGuitar - Registro";
    final String VENTANA_HOME = "iCGuitar - Inicio @";
    final String ERROR_RED = "Error de conexión.";
    final String ERROR_RED_DB = "Imposible conectar a la base de datos.";
    
    // Loging Frame
    final String LF_USERNAME = "Nombre de usuario:";
    final String LF_PASSWORD = "Contraseña:";
    final String LF_CONFIRM_PASSWORD = "Confirmar contraseña:";
    final String LF_REMEMBER_ACCOUNT = "Recordar inicio de sesión.";
    final String LF_LOG_IN = "Iniciar sesión";
    final String LF_NO_ACCOUNT = "¿No tienes una cuenta?";
    final String LF_CREATE_ACCOUNT = "Crear una cuenta";
    final String ERROR_DATABASE_LECTURA = "Error al iniciar sesión.";
    final String ERROR_DATABASE_REGISTRO = "Error al registrar.";
    
    // Register Frame
    final String RF_NAME = "Nombre:";
    final String RF_SURNAME = "Apellidos:";
    final String RF_DATE = "Fecha de nacimiento:";
    final String RF_CREATE_ACCOUNT = "¿Ya tienes una cuenta?";
    final String ERROR_REQUIRED_FIELDS = "Error: no puede haber ningún campo en blanco.";
    final String RF_USER_CREATED = "Usuario creado.";
    final String RF_AVATAR = "Seleccionar avatar";
    final String ERROR_DATABASE_FECHA = "Error: la fecha de nacimiento no debe ser posterior a la fecha actual.";
    final String ERROR_DATABASE_FECHAAC = "Error: selecciona una fecha.";
    
    
    // HomeScreen
    final String MENU_ARCHIVO = "Archivo";
    final String MENU_ARCHIVO_BUSCAR = "Buscar...";
    final String MENU_CANCION = "Canción";
    final String MENU_CANCION_SUBIR_TAB = "Subir canción...";
    final String MENU_CANCION_SUBIR_ACORDE = "Subir acordes";
    final String MENU_CANCION_SUBIR_LETRA = "Subir letra";
    final String MENU_CANCION_FAV = "Favoritas";
    final String MENU_CANCION_SUBIDAS = "Mis canciones";
    final String MENU_ALBUM = "Álbum";
    final String MENU_ALBUM_NUEVO = "Nuevo álbum";
    final String MENU_CUALQUIER_FAV = "Favoritos";
    final String MENU_ALBUM_SUBIDOS = "Mis álbumes";
    final String MENU_ARTISTAS = "Artistas";
    final String MENU_ARTISTA_REGISTRAR = "Registrar artista";
    final String MENU_OPCIONES = "Opciones";
    final String MENU_USUARIO = "Usuario";
    final String MENU_USUARIO_MIS_DATOS = "Mis datos";
    final String MENU_USUARIO_CERRAR_SESION = "Cerrar sesión";
    final String MENU_AYUDA = "Ayuda";
    final String MENU_AYUDA_ICGUITAR = "iCGuitar Web";
    final String MENU_AYUDA_ACERCA_DE = "Acerca de iCGuitar";
    final String ACERCA_DE = "Sobre iCGuitar: Aplicación desarrollada para el módulo de Programación del\nCiclo Formativo de Grado Superior Desarrollo de Aplicaciones Multiplataforma.\nAarón Aperador Barriga, C.E.S. Fuencarral. ";
    final String ARTISTAS_DESTACADOS = "Artistas añadidos reciéntemente:";
    final String ARTISTAS_NO_ENCONTRADOS = "No se encuentran artistas en la base de datos.";
    
    // RegistrarAlbum
    final String UTILITY_ALBUM_REGISTRAR = "Nuevo álbum";
    final String REGISTRAR_PREVIEW = "Vista previa:";
    final String REGISTRAR_ALBUM_CANCELAR = "Cancelar";
    final String REGISTRAR_ALBUM_REGISTRAR = "Registrar álbum";
    final String REGISTRAR_ALBUM_NOMBRE = "Nombre del álbum:";
    final String REGISTRAR_ALBUM_FECHA = "Fecha de publicación:";
    final String REGISTRAR_ALBUM_ARTISTA = "Artista al que pertenece:";
    final String REGISTRAR_ALBUM_SELECCION_PORTADA = "Seleccionar portada";
    final String REGISTRAR_ALBUM_PORTADA_PLACEHOLDER = "Aquí se mostrará la portada:";
    final String ERROR_REGISTRO_ARTISTA_NO_ENCONTRADO = "Artista no seleccionado o no encontrado.";
    final String ERROR_REGISTRO_CAMPOS_BLANCO = "No puede haber campos sin rellenar.";
    final String ERROR_REGISTRO_NO_PORTADA = "Necesitas subir la imagen de portada del álbum.";
    final String ERROR_REGISTRO_FECHA_INVALIDA = "Introduce una fecha válida.";
    final String ERROR_REGISTRO = "Error al registrar el álbum.";
    final String REGISTRO_EXITOSO = "Álbum registrado correctamente.";
    
    // Registrar artista
    final String UTILITY_ARTISTA_REGISTRAR = "Nuevo artista";
    final String REGISTRAR_ARTISTA_NOMBRE = "Nombre del artista:";
    final String REGISTRAR_ARTISTA_BIOGRAFIA = "Biografía:";
    final String ARTISTA_EGISTRO_EXITOSO = "Artista registrado correctamente.";
    final String ERROR_REGISTRO_ARTISTA = "Error al registrar el artista.";
    
    // DatosUsuario
    final String DATOS_USUARIO_TITULO = "Datos del usuario:";
    final String DATOS_USUARIO_CREACION = "Miembro desde";
    final String DATOS_USUARIO_NEW = "Nueva contraseña:";
    final String DATOS_USUARIO_OLD = "Contraseña actual:";
    final String DATOS_USUARIO_SALIR = "Salir";
    final String DATOS_USUARIO_APLICAR = "Aplicar";
    final String DATOS_USUARIO_EDIT = "Activar edición de datos";
    final String DATOS_USUARIO_AVISO = "* Por seguridad, una vez cambiados los datos, es necesario restablecer la contraseña.";
    final String DATOS_USUARIO_ERROR = "Error al registrar el artista.";
    final String DATOS_USUARIO_MIEMBRO = "hace %s días.";
    final String ERROR_DATOS_USUARIO_CONT = "La contraseña introducida no corresponde con la contraseña actual.";
    final String DATOS_ACTUALIZADOS = "Datos actualizados.";
    final String DATOS_USUARIO_ACTUALIZADO = "Los datos del usuario @%s se han actualiado correctamente.";
    final String DATOS_USUARIO_AVATAR = "El avatar de @%s se ha actualiado correctamente.";
    
    // RegistrarCancion
    final String REGISTRAR_CANCION = "Registrar una canción";
    final String REGISTRAR_CANCION_TAB = "Canción - Tablatura";
    final String TABLATURA_BLANK = " E|-------------------------|----------------------|-------------------------|-------------------------|\r\n"
    		+ " B|-------------------------|----------------------|-------------------------|-------------------------|\r\n"
    		+ " G|-------------------------|----------------------|-------------------------|-------------------------|\r\n"
    		+ " D|-------------------------|----------------------|-------------------------|-------------------------|\r\n"
    		+ " A|-------------------------|----------------------|-------------------------|-------------------------|\r\n"
    		+ " E|-------------------------|----------------------|-------------------------|-------------------------|";
    final String INGRESA_TABLATURA = "Ingresa todas las tablaturas necesarias, adaptándolas a tu solución.";
    final String TEMP_TAB = "Puedes copiar esta tablatura en blanco, para ayudarte a escribir.";
    final String TABLATURA_INFO = "* Escribe aquí la tablatura completa de la canción.";
    final String REGISTRAR_CANCION_DATOS = "Datos de la canción:";
    final String REGISTRAR_CANCION_DATOS_TITULO = "Título:";
    final String REGISTRAR_CANCION_DATOS_ALBUM = "Álbum:";
    final String REGISTRAR_CANCION_DATOS_ARTISTA = "Artista:";
    final String REGISTRAR_CANCION_DATOS_VIDEO = "URL Videoclíp:";
    final String REGISTRAR_CANCION_DATOS_SUBIR = "Subir";
    final String REGISTRAR_CANCION_DATOS_CANCELAR = "Cancelar";
    final String REGISTRAR_CANCION_DATOS_DIFICULTAD = "Dificultad:";
    final String REGISTRAR_CANCION_DATOS_ERROR = "Error al registrar la canción.:";
    final String REGISTRAR_CANCION_DATOS_ERRORA = "Se ha producido un error al registrar la canción.";
    final String REGISTRAR_CANCION_DATOS_REGISTRO = "Se ha registrado la canción %s.";
    final String REGISTRAR_CANCION_DATOS_REGISTRADA = "Canción registrada.";
    final String REGISTRAR_CANCION_LETRA = "Canción - Letra";
    final String REGISTRAR_CANCION_ACORDES = "Canción - Acordes";
    final String TEMP_LETRA = "Escribe dentro del recuadro la letra/tablatura/acordes de la canción.";
    final String LETRA_BLANK = "Letra de:";
    final String TAB_BLANK = "Tablatura de:";
    final String ACORDES_BLANK = "Acordes de:";
    final String OPC_TAB = "Es una tablatura.";
    final String OPC_ACORD = "Es una hoja de acordes.";
    final String OPC_LETRA = "Es únicamente la letra.";
    final String ANNADIR_TAB = "Añadir tablatura";
    
    // Cancion
    final String TITULO_CANCION = "Título:";
    final String ALBUM_CANCION = "Álbum:";
    final String ARTISTA_CANCION = "Artista:";
    final String DIFICULTAD_CANCION = "Dificultad:";
    final String USUARIO_CANCION = "Aporte realizado por el usuario: @%s.";
    final String FAVORITA_CANCION = "Marcar como favorita";
    final String DESMARCAR_CANCION = "Desmarcar como favorita";
    final String CLIP_CANCION = "Ver videoclip";
    final String COMENTARIO_CANCION = "Comentar";
    final String CANCIONES_ALBUM = "Canciones de %s";
    final String CANCIONES_GENERO = "Género: ";
    final String TOTAL_COMENTARIOS = "Total de comentarios: ";
    
    // Misc (varios)
    final String FILE_EXTENSION_IMAGE = "Archivos de imagen";
    final String FILE_CHOOSER_ACCEPT = "Aceptar";
    final String FILE_CHOOSER_CANCELAR = "Cancelar";
    final String BUSCAR_BUSQUEDA = "Buscar en iCGuitar";
    final String BUSCAR_ERROR1 = "Error al buscar";
    final String BUSCAR_ERROR2 = "No se encuentran los datos.";
    final String FAV_MARCAR = "Marcar como favorito";
    final String FAV_DESMARCAR = "Desmarcar favorito";
    final String FAV_CANCIONES = "Canciones favoritas de @%s:";
    final String MIS_CANCIONES = "Canciones subidas por @%s:";
    final String FAV_ALBUM = "Álbumes favoritos de @%s:";
    final String FAV_ARTISTA = "Artistas favoritos de @%s:";
    final String NO_FAV = "Aún no hay nada en favoritos. Navega por las diferentes secciones y añade contenido a tu gusto.";
    final String NO_CONTENIDO = "Aún no has subido ninguna interpretación. Navega por las diferentes secciones y añade contenido a tu gusto.";
}