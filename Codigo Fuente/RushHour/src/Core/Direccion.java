package Core;

/**
 * Direccion es usada para verificar si un movimiento es valido o no.
 * 
 * @author Caicedo Hidalgo Geraldine 0939713
 * @author Hern&aacute;ndez Abahonza Diana Carolina 1031083
 * @author Pab&oacute;n Ruiz Jorge Eduardo 1325856
 */
public class Direccion
{
	/*!Entero que representa el tipo del carro */
	private int tipo;			//* Tipo del carro el cual tiene esta direccion
	/*!Entero que representa la posicion inicial del carro segun la Fila*/
	private int posI;			//* Fila inicial del carro el cual tiene esta direccion
	/*!Entero que representa la posicion inicial del carro segun la columna */
	private int posJ;			//* Columna inicial del carro el cual tiene esta direccion
	/*!Entero que representa la orientacion del carro */
	private int orientacion;	//* Sentido en que se mueve el carro el cual tiene esta direccion -vertical u horizontal-
	/*!Matris de enteneros donde se almacenan todos los carros */
	private int tablero[][];	//* Tablero en el cual estan todos los carros del juego actual
	/*! Entero que representa el tamano del carro*/
	private int tamano;			//* Tamano del carro el cual tiene esta direccion
	
	//*************************************************************************************************
	
	/**
	 * Constructor de la Clase Direccion, Inicializa las variables.
	 * @param tipoEntrada entero que representa el tipo del carro
	 * @param orientacionEntrada entero que representa la orientacion del carro
	 * @param tamanoEntrada entero que representa el tamano del carro
	 */
	public Direccion(int tipoEntrada,int orientacionEntrada,int tamanoEntrada)
	{
		//* Asignacion del tipo de carro
		tipo=tipoEntrada;
		//* Asignacion de la orientacion del carro
		orientacion=orientacionEntrada;
		//* Asignacion del tamano del carro
		tamano=tamanoEntrada;
	}
	//*************************************************************************************************
	
	/**
	 * Metodo que adiciona el tablero que entra como parametro.
	 * @param tableroEntrada Matriz de enteros que representa un tablero
	 */
	public void adicionarTablero(int tableroEntrada[][])
	{
		//* Asignacion del tablero
		tablero=tableroEntrada;
	}
	//*************************************************************************************************
	
	/**
	 * Metodo que retorna true o false si el movimiento hecho es valido o no
	 * @param posicionIInicialEntrada entero que representa la posicion I inicial
	 * @param posicionJInicialEntrada entero que representa la posicion J inicial
	 * @param posicionIEntrada entero que representa la posicion I que se va a validar
	 * @param posicionJEntrada entero que representa la posicion J que se va a validar
	 * @return boleano que representa si el movimiento es valido o no
	 */
	public boolean revisarMovimiento(int posicionIInicialEntrada,int posicionJInicialEntrada,int posicionIEntrada,int posicionJEntrada)
	{
		//* Asignacion de la fila inicial y/o la fila donde se encuentra actualmente el carro
		posI=posicionIInicialEntrada;
		//* Asignacion de la columna inicial y/o la columana donde se encuentra actualmente el carro
		posJ=posicionJInicialEntrada;
		//* Asignacion de la fila a donde se va a mover el carro
		int posicionI=posicionIEntrada;
		//* Asignacion de la columna a donde se va a mover el carro
		int posicionJ=posicionJEntrada;
		
		//* Revisa si el carro puede moverse en una casilla o no
		if(orientacion==0 && posicionI==posI)
		{
			if(posicionJ<posJ)
			{
				//* Verifica si sobrepaso uno de los limites y si la posicion a la que se mueve es cero
				if(posicionJ<0)
				{
					return false;
				}
				else
				{
					if(tablero[posI][posicionJ]!=0)
					{
						return false;
					}
				}
			}
			else
			{
				//* Verifica si sobrepaso uno de los limites y si la posicion a la que se mueve es cero
				if(posicionJ+(tamano-1)>=tablero.length)
				{
					return false;
				}
				else
				{
					if(tablero[posI][posicionJ+tamano-1]!=0)
					{
						return false;
					}
				}
			}
		}
		else if(orientacion==1 && posicionJ==posJ)
		{
			if(posicionI<posI)
			{
				//* Verifica si sobrepaso uno de los limites y si la posicion a la que se mueve es cero
				if(posicionI<0)
				{
					return false;
				}
				else
				{
					if(tablero[posicionI][posJ]!=0)
					{
						return false;
					}
				}
			}
			else
			{
				//* Verifica si sobrepaso uno de los limites y si la posicion a la que se mueve es cero
				if(posicionI+(tamano-1)>=tablero[0].length)
				{
					return false;
				}
				else
				{
					if(tablero[posicionI+tamano-1][posJ]!=0)
					{
						return false;
					}
				}
			}
		}
		
		return true;
	}
	//*************************************************************************************************
}
