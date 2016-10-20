package Control;

import Core.Archivo;
import Core.Pieza;
import Core.SentenciasSQL;
import Core.Tablero;
import java.io.*;
import java.util.*;

/** 
 * La Clase Controlador se encarga de controlar el flujo de datos entre la interfaz y el nucleo
 * del programa.
 *  
 * @author Caicedo Hidalgo Geraldine 0939713
 * @author Hern&aacute;ndez Abahonza Diana Carolina 1031083
 * @author Pab&oacute;n Ruiz Jorge Eduardo 1325856
 * @version 0.0.1
 * @see <a href="Archivo.html">Class Archivo</a>
 * @see <a href="Tablero.html">Class Tablero</a>
 * @see <a href="Pieza.html">Class Pieza</a>
 * */
public class Controlador
{
	//Parametros
	
	/*! String con el nombre del jugador */
	private String nombre_usuario;									// nombre del usuario que va a iniciar el juego
	/*! String con la ruta en la que se encuentra el archivo del tablero */
	private String direccion_tablero;								// ruta del archivo del tablero que se desea jugar
	/*! Objeto de la Clase Archivo */
	private Archivo archivo_nuevo;									// Objeto de la clase Archivo
	/*! Objeto de la Clase Tablero */
	private Tablero tablero_nuevo;									// Objeto de la Clase Tablero
	/*! Vector de objetos de la Clase Pieza */
	private Vector <Pieza> carros_partida_guardada;					// Vector que contiene los carros que estaran en el tablero
	/*! Vector de Arreglos de String donde se guardan los datos de entrada del archivo */
	private Vector <String[]> datos_archivo_entrada;				// Vector que contiene los datos de entrea da del archivo en arreglos
	/*! Vector de String donde se guardan los datos de salida para el ultimo movimiento */
	private Vector <String> datos_archivo_ultimo_movimiento;		// Vector que contiene los datos de el ultimo movimiento hecho
	/*! Vector de String donde se guardan los datos de salida de los movimientos hechos*/
	private Vector <String> datos_archivo_movimientos;				//Vector que contiene los datos de todos los movimientos hehcos en un tablero
	/*! Vector de Arreglos de String donde se guardan los movimientos minimos de cada tablero*/
	private Vector <String[]> datos_archivo_movimientos_minimos;	// Vector que contiene los movimientos minimos de cada tablero
	/*! Objeto de la clase SenteciasSQL*/
	private SentenciasSQL objSentenciasSQL;							// Objeto de la clase SenteciasSQL
	/*! Objero de la clase BaseDeDatos*/
	private BaseDeDatos objBaseDeDatos;								// Objeto de la clase BaseDeDatos
	/*! Vector de String con los datos del usuario*/
	private Vector <String> datos_usuario;							// Vector con los datos del usuario, nombre, contrasena, y nombre de ingreso
	/*! Vector de String con los datos del juego*/
	private Vector <String> datos_juego;							// Vector con los datos del juego
	
	//*************************************************************************************************
	
	/**
	 * Inicializa los objetos y los vectores internos de la clase Controlador
	 */
	public Controlador()
	{
		//* Inicializacion de los Objetos de la clase Archivo y la clase Tablero
		archivo_nuevo = new Archivo();
		tablero_nuevo = new Tablero(6,6); //Se hace un tablero con el tamano de 6 x 6
		
		//* Inicializacion delObjeto de la clase objSenteciasSQL
		objSentenciasSQL=new SentenciasSQL();
		// Se pone los datos de ingreso a la base de datos
		objBaseDeDatos=new BaseDeDatos("jdbc:mysql://mysql:3306/geralch","geralch","geralch");//"jdbc:mysql://mysql:3306/dianacha", "dianacha", "dianacha"
		//Se conecta a la base de datos con el driver de MySQL
		objBaseDeDatos.conectarBaseDatos("com.mysql.jdbc.Driver");
		//Inicializacion del Vector de string con los datos del usuario
		datos_usuario=new Vector<String>(1,1);
		//Inicializacion del Vector de string con los datos del juego
		datos_juego=new Vector<String>(1,1);
		
		//Inicializacion de los vectores de la clase
		carros_partida_guardada = new Vector<Pieza>();
		datos_archivo_entrada = new Vector <String[]>();
		datos_archivo_ultimo_movimiento = new Vector <String>();
		datos_archivo_movimientos = new Vector <String>();
		datos_archivo_movimientos_minimos = new Vector <String[]>();		
	}
	//*************************************************************************************************
	
	/** 
	 * Crea las tablas necesarias para el programa. 
	*/
	public void crearTablas()
	{
		//Inicializacion del vector de string con las sentecias de la base de datos
		Vector<String> sentencias=new Vector<String>(1,1);
		//Creacion de una tabla en la base de datos
		sentencias=objSentenciasSQL.crearTabla();
		
		for(int i=0;i<sentencias.size();i++)
		{
			objBaseDeDatos.modificarTabla(sentencias.elementAt(i));
		}
	}
	//*************************************************************************************************
	
	/** 
	 * Ingresa nuevos datos en las tablas ya creadas. 
	*/
	public void insertarDatos()
	{
		//Inicializacion del vector de string con las sentecias de la base de datos
		Vector<String> sentencias=new Vector<String>(1,1);
		sentencias=objSentenciasSQL.adicionarDatosIniciales();
		//Se anaden los elementos a la tabla
		for(int i=0;i<sentencias.size();i++)
		{
			objBaseDeDatos.modificarTabla(sentencias.elementAt(i));
		}
	}
	
	//*************************************************************************************************
	
	/** 
	 * Ingresa nuevo usuario. 
	 * @param nombre  nombre del usuario
	 * @param nombrePila nombre de ingreso del usuario
	 * @param contrasennia contrasena del usuario
	 * @return true o false si el usuario esta registrado
	 */
	public boolean insertarUsuario(String nombre, String nombrePila, String contrasennia)
	{
		//*Inicializacion de las variables
		boolean usuarioRegistrado=false;
		String sentencia="";
		//Se anaden los datos del usuario a la base de datos
		sentencia=objSentenciasSQL.adicionarDatos(nombre, nombrePila, contrasennia);
		objBaseDeDatos.modificarTabla(sentencia);
		
		if(sentencia.length()!=0)
		{
			usuarioRegistrado=true;
		}
		
		return usuarioRegistrado;
	}
	//*************************************************************************************************
	
	/** 
	 * Consulta los datos de las tablas existentes. 
	 * @param nombre  nombre de ingreso del usuario
	 * @param contrasennia contrasena del usuario
	 * @return true o false si el usuario fue encontrado
	 */
	public boolean consultarDatos(String nombre,String contrasennia)
	{
		//*Inicializacion de las variables
		boolean usuarioEncontrado=false;
		String sentencia="";
		int idUsuario=0;
		int tamVectorUsu=0;
		
		//Consulta si el usuario se encuentra en la base de datos
		sentencia=objSentenciasSQL.consultarUsuario(nombre,contrasennia);
		datos_usuario=objBaseDeDatos.consultarTabla(sentencia);
		tamVectorUsu=datos_usuario.size();
		
		if(tamVectorUsu!=0)
		{
			idUsuario=Integer.parseInt(datos_usuario.elementAt(0));
		}
		
		if(idUsuario!=0)
		{
			usuarioEncontrado=true;
		}
		
		//Se retorna si el usuario fue encontrado o no
		return usuarioEncontrado;
	}
	//*************************************************************************************************
	
	/** 
	 * Devuelve los datos del usuario consultado. 
	 * @return Vector de string con los datos del usuario
	 */
	public Vector<String> obtenerDatosUsuario()
	{
		return datos_usuario;
	}
	//*************************************************************************************************
	
	/** 
	 * Devuelve los datos del juego. 
	 * @return Vector de string con los datos del juego
	 */
	public Vector<String> obtenerDatosJuego()
	{
		return datos_juego;
	}
	//*************************************************************************************************
	
	/** 
	 * Controla las sentencias que son ejecutadas en la base de datos. 
	 */
	public void inicializar()
	{
		crearTablas();
		insertarDatos();
	}
	//*************************************************************************************************
	
	/**
	 * Guarda el nombre del jugador y crea una carpeta con el nombre de este para guardar ahi las partidas guardadas.
	 * @param param_nombre_usuario Nombre del Jugador que se va a guardar.
	 */
	public void guardarNombreUsuario(String param_nombre_usuario)
	{
		nombre_usuario = param_nombre_usuario;
		archivo_nuevo.crearCarpeta(nombre_usuario);
	}
	//*************************************************************************************************
	
	/**
	 * Guarda la ruta del archivo, guarda los datos del archivo usando el metodo cargarDatosArchivo(), y los
	 * acomoda en un tablero usando el metodo adiccionarPiezasTablero().
	 * @param param_ruta Ruta del tablero que se va a guardar.
	 */
	public void cargarNombreArchivo(String param_ruta)
	{
		direccion_tablero = param_ruta;
	}
	//*************************************************************************************************
	
	/**
	 * Utiliza los metodos para cargar los datos del archivo y adiccionar las piezas al tablero
	 */
	public void cargarTablero()
	{
		cargarDatosArchivo();
		adiccionarPiezasTablero();
	}
	//*************************************************************************************************
	
	/**
	 * Lee los datos en el archivo de la ruta especificada y los guarda en un vector de arreglos de string.
	 */
	private void cargarDatosArchivo()
	{
		archivo_nuevo.leerArchivoConColumnas(direccion_tablero,datos_archivo_entrada," ");
	}
	//*************************************************************************************************
	
	/**
	 * Adicciona una pieza nueva al tablero mediante el metodo adicionarPieza() de la clase Tablero, obteniendo
	 * los datos del vector de arreglos de string de la clase.
	 */
	private void adiccionarPiezasTablero()
	{
		//*El for recorre el vector con los datos del archivo y segun los datos en este guarda las piezas en un tabler nuevo
		for(int i=0; i<datos_archivo_entrada.size(); i++)
		{
			tablero_nuevo.adicionarPieza(Integer.parseInt(datos_archivo_entrada.elementAt(i)[2]),Integer.parseInt(datos_archivo_entrada.elementAt(i)[0]), Integer.parseInt(datos_archivo_entrada.elementAt(i)[1]),Integer.parseInt(datos_archivo_entrada.elementAt(i)[3]));									 
		}
	}
	//*************************************************************************************************
	
	/**
	 * Retorna un vector de piezas que representa un tablero.
	 * @return Vector de objetos de la Clase Pieza.
	 */	
	public Vector <Pieza> obtenerPiezas()
	{
		return tablero_nuevo.obtenerTablero();
	}
	//*************************************************************************************************
	
	/**
	 * Guarda un archivo con el ultimo moviento realizado por el jugador
	 */
	public void guardarUltimoMovimiento()
	{	
		//* Se obtiene la juagda hecha por el juagador	
		carros_partida_guardada = tablero_nuevo.obtenerTablero();
		//Se limpia el vector de los datos que iran en el documento
		datos_archivo_ultimo_movimiento.clear();
		//El for recoorre el vector del movimeinto hecho por el jugador y guarda las coordenadas de los carros en el archivo
		for(int i=0; i<carros_partida_guardada.size(); i++)
		{
			datos_archivo_ultimo_movimiento.add(carros_partida_guardada.elementAt(i).obtenerPosicion()[0]+" "+carros_partida_guardada.elementAt(i).obtenerPosicion()[1]+" "+carros_partida_guardada.elementAt(i).obtenerTipo()+" "+carros_partida_guardada.elementAt(i).obtenerOrientacion());
		}
		
		archivo_nuevo.guardarArchivo(nombre_usuario+"/PartidaGuardada",datos_archivo_ultimo_movimiento);
		
	}
	//*************************************************************************************************
	
	/**
	 * Guarda todos los movimeientos hechos por el jugador en un tablero
	 */
	public void guardarTodosMovimientos(int param_movimientos_actuales)
	{
		//* Se obtiene la juagda hecha por el juagador	
		carros_partida_guardada = tablero_nuevo.obtenerTablero();
		
		//El for recoorre el vector del movimeinto hecho por el jugador y guarda las coordenadas de los carros en el archivo
		for(int i=1; i<carros_partida_guardada.size(); i++)
		{
			datos_archivo_movimientos.add(carros_partida_guardada.elementAt(i).obtenerPosicion()[0]+" "+carros_partida_guardada.elementAt(i).obtenerPosicion()[1]+" "+carros_partida_guardada.elementAt(i).obtenerTipo()+" "+carros_partida_guardada.elementAt(i).obtenerOrientacion());		
		}
		//separa cada movimiento con una linea
		datos_archivo_movimientos.add("-");
		
		archivo_nuevo.guardarArchivo(nombre_usuario+"/MovimientosHechos",datos_archivo_movimientos);
	}
	//*************************************************************************************************
	
	/**
	 * Lee el archivo con los movimientos minimos de cada tablero
	 */
	private void cargarMovimientosMinimos()
	{
		archivo_nuevo.leerArchivoConColumnas("Tableros/MovimientosMinimos",datos_archivo_movimientos_minimos," ");
	}
	//*************************************************************************************************
	
	/**
	 * Retorna el numero de movimientos minimos posible para el tablero del cual se queire saber
	 * @param param_dificultad entero que representa la dificultad del tablero
	 * @param param_nivel entero que representa el nivel del tablero
	 * @return movimientos minimos del tablerp
	 */
	public int obtenerMovimientoMinimoTablero(int param_dificultad, int param_nivel)
	{
		cargarMovimientosMinimos();
		int aux_mm = Integer.parseInt(datos_archivo_movimientos_minimos.elementAt(param_dificultad)[param_nivel]);
		return aux_mm;
	}
	//*************************************************************************************************
	
	/**
	 * Retorna true o falsesi la pieza tiene permitido moverse a la posicion que entra como parametro
	 * @param param_tipo  entero que representa el tipo del carro
	 * @param param_posFila entero que representa la posicion en la fila del carro
	 * @param param_posColumna entero que representa la posicion en la columna del carro
	 * @return true o false si el movimiento es valido o no
	 */
	public boolean moverPiezaTablero(int param_tipo, int param_posFila, int param_posColumna)
	{
		boolean pieza_valida = true;
		
		pieza_valida = tablero_nuevo.moverPieza(param_tipo,param_posFila,param_posColumna);
		
		return pieza_valida;
	}
	//*************************************************************************************************
	
	/**
	 * Retorna true o false si la partida ya se ha ganado
	 * @return true o false si el auto rojo ya ha salido del tablero
	 */
	public boolean partidaGanada()
	{
		return tablero_nuevo.obtenerTermina();
	}
	
	//*************************************************************************************************
}
