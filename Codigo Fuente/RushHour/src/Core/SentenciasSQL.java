package Core;

import java.util.Vector;

/**
 * SentenciasSQL es usada para entregar una sentencia sql seg&uacute;n la necesidad.
 * 
 * @author Caicedo Hidalgo Geraldine 0939713
 * @author Hern&aacute;ndez Abahonza Diana Carolina 1031083
 * @author Pab&oacute;n Ruiz Jorge Eduardo 1325856
 */
public class SentenciasSQL
{
	//Parametros
	
	/*! Matris de String con los usuarios iniciales */
	private String [][] usuariosIniciales;	//* Matriz donde se encuentran los usuarios iniciales.
	
	//*************************************************************************************************
	
	/**
	 * Construye una SenteciasSql por defecto.
	 */
	public SentenciasSQL()
	{
		usuariosIniciales = new String[3][3];
		usuariosIniciales[0][0]="dianacha";
		usuariosIniciales[0][1]="Diana Hernandez";
		usuariosIniciales[0][2]="aslan456";
		usuariosIniciales[1][0]="sombra";
		usuariosIniciales[1][1]="Eduardo Pabon";
		usuariosIniciales[1][2]="pepito1236";
		usuariosIniciales[2][0]="chou";
		usuariosIniciales[2][1]="Geral";
		usuariosIniciales[2][2]="alice123";
	}
	//*********************************************************************************************************
	
	/**
	 * Entrega la sentencia necesaria para crear tablas.
	 * 
	 * @return Sentencia que permite crear tablas.
	 */
	public Vector<String> crearTabla()
	{
		Vector<String> datos=new Vector<String>(1,1);
		String sentencia="";
		
		sentencia="create table if not exists usuario(id int auto_increment, primary key(id), nombre varchar(20), nombrePila varchar(40), contrasennia varchar(40)) engine=InnoDB";
		datos.add(sentencia);
		sentencia="create table if not exists datosJuego(id int auto_increment, primary key(id), id_Usuario int, index(id_Usuario), foreign key(id_Usuario) references usuario(id) on delete cascade, nivel varchar(20), tablero varchar(10), puntuacion int) engine=InnoDB";
		datos.add(sentencia);
		
		return datos;
	}
	//*********************************************************************************************************
	
	/**
	 * Entrega la sentencia necesaria para adicionar datos iniciales en las tablas.
	 * 
	 * @return Sentencia que permite adicionar datos iniciales en las tablas.
	 */
	public Vector<String> adicionarDatosIniciales()
	{
		Vector<String> datos=new Vector<String>(1,1);
		String sentencia="";
		
		for(int i=0; i<usuariosIniciales.length;i++)
		{
			sentencia="insert into usuario(id, nombre, nombrePila, contrasennia) values("+(i+1)+",'"+usuariosIniciales[i][0]+"','"+usuariosIniciales[i][1]+"','"+usuariosIniciales[i][2]+"')";
			datos.add(sentencia);
		}
		
		return datos;
	}
	//*********************************************************************************************************
	
	/**
	 * Entrega la sentencia necesaria para adicionar un nuevo dato en las tablas.
	 * 
	 * @return Sentencia que permite adicionar un nuevo dato en las tablas.
	 */
	public String adicionarDatos(String nombre, String nombrePila, String contrasennia)
	{
		String sentencia="";
		
		sentencia="insert into usuario(nombre, nombrePila, contrasennia) values('"+nombre+"','"+nombrePila+"','"+contrasennia+"')";
		
		return sentencia;
	}
	//*********************************************************************************************************
	
	/**
	 * Entrega la sentencia necesaria para consultar la tabla usuario.
	 * 
	 * @return Sentencia que permite consultar los datos de un usuario.
	 */
	public String consultarUsuario(String paramNombre,String paramContrasennia)
	{
		String sentencia="";
		String nombre=paramNombre;
		String contrasennia=paramContrasennia;
		
		sentencia="select * from usuario where nombre='"+nombre+"' and contrasennia='"+contrasennia+"'";
		
		return sentencia;
	}
	//*************************************************************************************************
}
