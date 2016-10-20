package Control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 * BaseDeDatos es usada para poder conectarse a una determinada base de datos, editar y 
 * crear tablas y consultar los contenidos de estas.
 * 
 * @author Caicedo Hidalgo Geraldine 0939713
 * @author Hern&aacute;ndez Abahonza Diana Carolina 1031083
 * @author Pab&oacute;n Ruiz Jorge Eduardo 1325856
 */
public class BaseDeDatos
{
	/*! String con el nombre de la base de datos*/
	private String baseDatos;		//* Nombre de la base de datos
	/*! String con el nombre del usuario*/
	private String usuario;			//* Usuario con el que se va a ingresar a la base de datos
	/*! String con la contrasena del usuario*/
	private String contrasena;		//* Contrasena con el que se va a ingresar a la base de datos
	/*!Boolean que dice si la base de datos esta conectado o no */
	private boolean conectada;		//* Variable que guarda si la base de datos esta conectada o no
	/*! Objeto de la clase Connection*/
	private Connection conexion;	//* Conexion por la cual se accede a la base de datos
	
	//*************************************************************************************************
	
	/**
	 * Constructor por Defecto de la clase BaseDeDatos. Inicializa las variables
	 */
	public BaseDeDatos()
	{
		//* Inicializacion de las variables
		baseDatos="";
		usuario="";
		contrasena="";
		conectada=false;
		conexion=null;
	}
	//*************************************************************************************************
	
	/**
	 * Constructor con parametros de la clase BaseDeDatos. Inicializa las variables
	 * @param baseDatosEntrada 
	 * @param usuarioEntrada
	 * @param contrasenaEntrada
	 */
	public BaseDeDatos(String baseDatosEntrada,String usuarioEntrada,String contrasenaEntrada)
	{
		//* Asignacion de los valores de entrada en los atributos 
		baseDatos=baseDatosEntrada;
		usuario=usuarioEntrada;
		contrasena=contrasenaEntrada;
		conectada=false;
		conexion=null;
	}
	//*************************************************************************************************
	
	/**
	 * Agrega la base de datos 
	 * @param baseDatosEntrada nombre de la base de datos
	 */
	public void agregarBaseDatos(String baseDatosEntrada)
	{
		//* Asigna el valor del parametro que recibe a baseDatos
		baseDatos=baseDatosEntrada;
	}
	//*************************************************************************************************
	
	/**
	 * Agrega un usuario a la base de datos
	 * @param usuarioEntrada nombre del usuario
	 */
	public void agregarUsuario(String usuarioEntrada)
	{
		//* Asigna el valor del parametro que recibe a usuario
		usuario=usuarioEntrada;
	}
	//*************************************************************************************************
	
	/**
	 * Agrega la contrasena del usuario a la base de datos
	 * @param contrasenaEntrada contrasena del usuario
	 */
	public void agregarContrasena(String contrasenaEntrada)
	{
		//* Asigna el valor del parametro que recibe a contrasena
		contrasena=contrasenaEntrada;
	}
	//*************************************************************************************************
	
	/**
	 * Se conecta a la base de datos y devuelve un mensaje que informa el estado de la conexion
	 * @param driver driver de la base de datos
	 */
	public String conectarBaseDatos(String driver)
	{
		//* Revisa si la base de datos esta conectada
		if(conectada)
		{
			desconectarBaseDatos();
			conectada=false;
		}
		
		String respuesta="bdConectada";
		//* Valida si el driver es correcto o no
		respuesta=validarDriver(driver);
		
		if(respuesta.equals("bdConectada"))
		{
			try
			{
				//* Conecta la base de datos de acuerdo a los atributos hechos para este fin
				conexion=DriverManager.getConnection(baseDatos,usuario,contrasena);
				conectada=true;
			}
			catch(Exception e)
			{
				respuesta="Se ha presentado el siguiente error:\n"+e.toString();
			}
		}
		
		return respuesta;
	}
	//*************************************************************************************************
	
	/**
	 * Valida si el driver de la base de datos es el indicado
	 * @param driver driver de la base de datos
	 */
	private String validarDriver(String driver)
	{
		String respuesta="bdConectada";
		//* Revisa si se han ingresado datos para hacer la conexion
		if(baseDatos.equals("") || usuario.equals("") || contrasena.equals(""))
		{
			respuesta="Los datos no estan completos, no se puede acceder a la base de datos";
		}
		
		try
		{
			//* Intenta usar el diver antes de la conexion
			Class.forName(driver).newInstance();
		}
		catch(ClassNotFoundException cnfe)
		{
			respuesta="Se ha presentado el siguiente error:\n"+cnfe.toString();
		}
		catch(InstantiationException ite)
		{
			respuesta="Se ha presentado el siguiente error:\n"+ite.toString();
		}
		catch(IllegalAccessException iae)
		{
			respuesta="Se ha presentado el siguiente error:\n"+iae.toString();
		}
		
		return respuesta;
	}
	//*************************************************************************************************
	
	/**
	 * Retorna un vector de string con los datos consultados de una tabla
	 * @param consulta sentencia de consulta
	 * @return vector con los datos consultadod e la tabla
	 */
	public Vector<String> consultarTabla(String consulta)
	{
		Vector<String> respuesta=new Vector<String>(1,1);
		
		if(conectada)
		{
			try
			{
				//* Realiza una consulta en la base de datos
				Statement s=conexion.createStatement();
				ResultSet resultado=s.executeQuery(consulta);
				
				ResultSetMetaData rd = resultado.getMetaData();
				int columnas= rd.getColumnCount();
				
				while(resultado.next())
				{
					//* Guarda los datos de la consulta en un vector
					for(int i=1;i<=columnas;i++)
					{
						respuesta.add(resultado.getString(i));
					}
				}
			}
			catch(SQLException sqle)
			{
				respuesta.add("Se ha presentado el siguiente error:\n"+sqle.toString());
			}
		}
		
		return respuesta;
	}
	//*************************************************************************************************
	
	/**
	 * Modifica una tabla de la base de datos y retorna un string con una respuesta
	 * @param instruccionSQL sentencia de instruccion SQL
	 * @return respuesta por la modificacion
	 */
	public String modificarTabla(String instruccionSQL)
	{
		String respuesta="sentenciaEjecutada";
		if(conectada)
		{
			try
			{
				//* Realiza una modificacion en la base de datos
				Statement s=conexion.createStatement();
				s.executeUpdate(instruccionSQL);
			}
			catch(SQLException sqle)
			{
				respuesta="Se ha presentado el siguiente error:\n"+sqle.toString();
			}
		}
		else
		{
			respuesta="sentenciaNoEjecutada";
		}
		
		return respuesta;
	}
	//*************************************************************************************************
	
	/**
	 * Desconecta la vase de datos y retorna un String con un mensaje
	 * @return mensaje de advertencia.
	 */
	public String desconectarBaseDatos()
	{
		String respuesta="ok";
		
		try
		{
			//* Cierra la conexion a la base de datos
			conexion.close();
		}
		catch(SQLException sqle)
		{
			respuesta="Se ha presentado el siguiente error:\n"+sqle.toString();
		}
		
		return respuesta;
	}
	//*************************************************************************************************
	
	/**
	 * Revisa si la conexion se establecio corectamente
	 * @return true o false si la base de datos esta conectada o no
	 */
	public boolean revisarConexion()
	{
		//* Devuelve el valor de la variable conectada
		return conectada;
	}
	//*************************************************************************************************
}
