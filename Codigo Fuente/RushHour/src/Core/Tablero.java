package Core;

import java.util.Vector;

/**
 * Tablero es usado para simular un estacionamiento, donde los carros est&aacute;n estacionados y un carro
 * debe poder salir.
 * 
 * @author Caicedo Hidalgo Geraldine 0939713
 * @author Hern&aacute;ndez Abahonza Diana Carolina 1031083
 * @author Pab&oacute;n Ruiz Jorge Eduardo 1325856
 * @see <a href="Pieza.html">Class Pieza</a>
 * @version 0.0.1
 */
public class Tablero
{
	//Parametros
	
	/*!Boleano que representa si el juego termina */
	private boolean termina;		//* Variable que guarda si el juego termina o no
	/*!Matris de enteneros donde se almacenan todos los carros */
	private int tablero[][];		//* Matriz donde se encuentra los carros.
	
	//Objetos de otras clases
	
	/*!Vector de objetos de la Clase Pieza que tiene los carros que etan actualmente en el tablero*/
	private Vector<Pieza> carros;	//* Piezas en el tablero.
	
	//*************************************************************************************************
	
	/**
	 * Construye un tablero del tama&ntilde;o especificado.
	 * 
	 * @param filas Cantidad de filas del tablero.
	 * @param columnas Cantidad de columnas del tablero.
	 */
	public Tablero(int filas,int columnas)
	{
		//* Creacion de la matriz
		tablero=new int[filas][columnas];
		//* Inicializacion de las piezas del tablero
		carros=new Vector<Pieza>(1,1);
	}
	//*************************************************************************************************
	
	/**
	 * Adiciona una pieza al tablero.
	 * 
	 * @param pieza Pieza que va a ser adicionada.
	 * @param posFila Fila en la que la pieza ser&aacute; adicionada.
	 * @param posColumna Columna en la que la pieza ser&aacute; adicionada.
	 * @param orientacion La direcci&oacute;n del carro.
	 */
	public void adicionarPieza(int pieza,int posFila,int posColumna,int orientacion)
	{
		//* Crea un objeto pieza con los parametros de entrada y lo adiciona en el vector carros.
		Pieza objPieza=new Pieza(pieza,posFila,posColumna,orientacion);
		tablero=objPieza.obtenerPosicionInicial(tablero);
		carros.add(objPieza);
	}
	//*************************************************************************************************
	
	/**
	 */
	public void reiniciarTablero()
	{
		//* Borra todos los carros usados
		carros.clear();		
	}
	//*************************************************************************************************
	
	/**
	 * Devuelve los valores del tablero.
	 * 
	 * @return Retorna el tablero.
	 */
	public Vector<Pieza> obtenerTablero()
	{
		//* Devuelve todos los carros en el tablero
		return carros;
	}
	//*************************************************************************************************
	
	/**
	 * Mueve una pieza dentro del tablero.
	 * 
	 * @param pieza Pieza que ser&aacute; movida.
	 * @param posFila Fila en la que la pieza ser&aacute; movida.
	 * @param posColumna Columna en la que la pieza ser&aacute; movida.
	 * @return True si fue v&aacute;lido el movimiento, False en caso contrario.
	 */
	public boolean moverPieza(int pieza,int posFila,int posColumna)
	{
		//* Tablero temporal donde se guarda el resultado de mover la pieza.
		int[][] tableroTmp=new int[tablero.length][tablero[0].length];
		//* Variable donde se guarda si el movimiento es valido
		boolean valido=true;
		
		//* Verifica si el carro rojo ya salio del tablero
		if(pieza==1 && posColumna>=tablero.length-1)
		{
			
			termina=true;
			valido=true;
		}
		else
		{
			//* Doble "for" usado para recorrer la matriz y encontrar la pieza en el tablero.
			for(int i=0;i<tablero.length;i++)
			{
				for(int j=0;j<tablero[0].length;j++)
				{
					if(tablero[i][j]==pieza)
					{
						//* "for" usado para encontrar la pieza en el vector de carros.
						for(int k=0;k<carros.size();k++)
						{
							if(carros.elementAt(k).compararPieza(pieza))
							{
								tablero=carros.elementAt(k).moverEnTablero(tablero,posFila,posColumna);
								valido=carros.elementAt(k).obtenerMovimientoValido();
								k=carros.size();
								i=tablero.length;
								j=tablero[0].length;
							}
						}
					}
				}
			}
		}
				
		return valido;
	}
	
	//*************************************************************************************************
	
	/**
	 * Devuelve si el juego ya termino.
	 * 
	 * @return True si el carro rojo ya salio por completo, False en caso contrario.
	 */
	public boolean obtenerTermina()
	{
		//* Devuelve si el carro rojo ya paso por completo el limite del tablero
		return termina;
	}
	//*************************************************************************************************
}
