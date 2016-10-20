package Core;

import java.io.*;
import java.util.*;

/**
 * Archivo es usado para manejar archivos y carpetas. Lee y guarda datos en archivos de la extensi&oacute;n 
 * dada, tambi&eacute;n puede crear carpetas y obtener una lista de carpetas y archvos que se encuantren en una 
 * carpeta determinada.
 * 
 * @author Caicedo Hidalgo Geraldine 0939713
 * @author Hern&aacute;ndez Abahonza Diana Carolina 1031083
 * @author Pab&oacute;n Ruiz Jorge Eduardo 1325856
 */
public class Archivo
{
	/*!String con la extension del archivo */
	private String extension; //* Variable donde se guarda la extension del archivo
	
	/**
	 * Construye un archivo por defecto.
	 */	
	public Archivo()
	{
		//* Inicializacion de una extesion por defecto
		extension="txt";
	}
	//*************************************************************************************************
	
	/**
	 * Construye un archivo con una extensi&oacute;n determinada.
	 * 
	 * @param extensionEntrada Extensi&oacute;n que se usara para leer y guardar archivos.
	 */	
	public Archivo(String extensionEntrada)
	{
		//* Asigna una nueva extension al iniciar
		extension=extensionEntrada;
	}
	//*************************************************************************************************
	
	/**
	 * Cambia el valor del atributo extensi&oacute;n.
	 * 
	 * @param extensionEntrada Extensi&oacute;n que se usara para leer y guardar archivos.
	 */	
	public void cambiarExtension(String extensionEntrada)
	{
		//* Asigna una nueva extension
		extension=extensionEntrada;
	}
	//*************************************************************************************************
	
	/**
	 * Crea una carpeta si esta no existe.
	 * 
	 * @param carpeta Nombre de la carpeta que se crear&aacute;.
	 */	
	public void crearCarpeta(String carpeta)
	{
		//* Crea un archivo tipo carpeta con el parametro recibe el metodo
		File nombre=new File(carpeta);
		//* Verifica si la carpeta ya existe de lo contrario la crea
		if(!(nombre.exists()))
		{
			nombre.mkdir();
		}
	}
	//*************************************************************************************************
	
	/**
	 * Borra una carpeta si esta existe.
	 * 
	 * @param carpeta Nombre de la carpeta que se borrar&aacute;.
	 */	
	public void borrarCarpeta(String carpeta)
	{
		//* Crea un archivo de tipo carpeta con el parametro recibe el metodo
		File nombre=new File(carpeta);
		//* Verifica si la carpeta ya existe y la borra si este es el caso
		if(nombre.exists())
		{
			nombre.delete();
		}
	}
	//*************************************************************************************************
	
	/**
	 * Lee las l&iacute;neas de un archivo determinado.
	 * 
	 * @param nombreArchivo Nombre del archivo que se va a leer.
	 * @param vector Vector donde se guardar&aacute; las l&iacute;neas le&iacute;das del archivo.
	 */
	public void leerArchivo(String nombreArchivo,Vector<String> vector)
	{		
		try
		{
			//* Crea un archivo con el nombre que recibe de parametro y la extension guardada
			FileReader fr = new FileReader(nombreArchivo+"."+extension);
			BufferedReader entrada = new BufferedReader(fr);
			String linea="";
			do
			{
				linea=entrada.readLine();
				if(linea!=null)
				{
					//* Guarda las lineas del archivo en el vector que recibe como parametro
					vector.add(linea);
				}
			}
			while(!(linea==null));
		}
		catch(Exception ex)
		{
			System.out.println("Se presento el error: "+ex.toString());
		}
	}
	//*************************************************************************************************
	
	/**
	 * Lee las l&iacute;neas de un archivo determinado y separa de acuerdo con un separador
	 * determinado en columnas.
	 * 
	 * @param nombreArchivo Nombre del archivo que se va a leer.
	 * @param vector Vector donde se guardar&aacute; las l&iacute;neas le&iacute;das del archivo.
	 * @param separador Separador de las columnas de cada l&iacute;nea del archivo.
	 */
	public void leerArchivoConColumnas(String nombreArchivo,Vector<String[]> vector,String separador)
	{
		//* Inicializa variables temporales
		Vector<String> vectorTmp=new Vector<String>(1,1);
		String[] palabras;
		Vector<String> palabrasTmp=new Vector<String>(1,1);
		//* Lee el archivo por lineas
		leerArchivo(nombreArchivo,vectorTmp);
		//* Doble for que separa las lineas leidas en el archivo en columnas
		for(int i=0;i<vectorTmp.size();i++)
		{
			String[] fraseTmp=vectorTmp.elementAt(i).split("");
			String palabra="";
			for(int j=1;j<fraseTmp.length;j++)
			{
				if((j+1)==fraseTmp.length)
				{
					palabra+=fraseTmp[j];
					palabrasTmp.add(palabra);
				}
				else if(fraseTmp[j].equals(separador))
				{
					palabrasTmp.add(palabra);
					palabra="";
				}
				else
				{
					palabra+=fraseTmp[j];
				}
			}
			palabras=new String[palabrasTmp.size()];
			for(int j=0;j<palabras.length;j++)
			{
				palabras[j]=palabrasTmp.elementAt(j);
			}
			vector.add(palabras);
			palabrasTmp.clear();
		}
	}
	//*************************************************************************************************
	
	/**
	 * Guarda los datos de un vector en las l&iacute;neas de un archivo.
	 * 
	 * @param nombreArchivo Nombre del archivo donde se guardar&aacute; los datos.
	 * @param vector Vector donde se encuentran los datos a guardar.
	 */
	public void guardarArchivo(String nombreArchivo,Vector<String> vector)
	{		
		try
		{
			//* Crea un archivo con el nombre que recibe de parametro y la extension guardada
			FileWriter fw = new FileWriter(nombreArchivo+"."+extension);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			for(int i=0;i<vector.size();i++)
			{
				//* Imprime los datos de vector en el archivo
				salida.println(vector.elementAt(i));
			}
			salida.close();
		}
		catch(Exception ex) 
		{
			System.out.println("se presento el error: "+ex.toString());
		}
	}
	//*************************************************************************************************
	
	/**
	 * Devuelve un arreglo de string con los nombres de las carpetas y archivos que se encuentran en la carpeta 
	 * determinada.
	 * 
	 * @param nombreCarpeta Nombre de la carpeta donde se leer&aacute;n las carpetas y archivos.
	 * @return Lista de nombres de las carpetas y archivos.
	 */
	public String[] obtenerCarpetas(String nombreCarpeta)
	{
		//* Lista todos los nombres de archivos y carpetas dentro de la carpeta que se recibe como parametro
		String[] lista;
		File file=new File(nombreCarpeta);
		lista=file.list();
		return lista;
	}
	//*************************************************************************************************
}
