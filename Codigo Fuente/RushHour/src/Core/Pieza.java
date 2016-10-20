package Core;

/**
 * Pieza es usada como representaci&oacute;n de un carro en el juego.
 * 
 * @author Caicedo Hidalgo Geraldine 0939713
 * @author Hern&aacute;ndez Abahonza Diana Carolina 1031083
 * @author Pab&oacute;n Ruiz Jorge Eduardo 1325856
 * @see <a href="Direccion.html">Class Direccion</a>
 * @version 0.0.1
 */
public class Pieza
{
	//Parametros
	
	/*! Entero que representa el tipo del carro */
	private int tipo;					//* Tipo de carro
	/*! Entero que representa la fila inicial del carro*/
	private int filaInicial;			//* Fila inicail del carro
	/*! Entero que representa la columna inicial del carro */
	private int columnaInicial;			//* Columna inicial del carro
	/*! Entero que representa la orientacion del carro */
	private int orientacion;			//* Orientacion del carro
	/*! Entero que representa el tamano del carro*/
	private int tamano;					//* Tamano del carro
	/*! Booleano que dice si el movimento es valido o no */
	private boolean movimientoValido;	//* Guarda si el moviemto es valido o no
	
	//Objetos de otras clases
	
	/*! Objeto de la clase Direccion */
	private Direccion objDireccion;		//* Direccion del carro, donde puede moverse
	
	//***********************************************************************************************************
	
	/**
	 * Construye una pieza del tipo especificado con cierta orientac&oacute;n.
	 * 
	 * @param tipoEntrada Tipo de carro.
	 * @param filaInicialEntrada Fila en la inicia el carro.
	 * @param columnaInicialEntrada Columna en la inicia el carro.
	 * @param orientacionEntrada Orientaci&oacute; del carro.
	 */
	public Pieza(int tipoEntrada,int filaInicialEntrada,int columnaInicialEntrada,int orientacionEntrada)
	{
		//* Asignacion del tipo que entra al tipo de la clase
		tipo=tipoEntrada;
		
		//* Actualizar tamano
		actualizarTamano();
		
		//* Asignacion de la fila inicial del carro
		filaInicial=filaInicialEntrada;
		//* Asignacion de la columna inicial del carro
		columnaInicial=columnaInicialEntrada;
		//* Asignacion de la orientacion que entra a la orientacion de la clase
		orientacion=orientacionEntrada;
		//* Inicializacion por defecto
		movimientoValido=true;
	}
	//***********************************************************************************************************
	
	/**
	 * Devuelve el tablero con el carro ubicado en la posici&oacute;n inicial.
	 * 
	 * @param tablero Tablero donde ser&aacute; ubicado el carro.
	 * @return Tablero con el carro ubicado.
	 */
	public int[][] obtenerPosicionInicial(int[][] tablero)
	{
		//* Revisa la orientacion
		if(orientacion==0)
		{
			//* For que pone el tipo del carro en el tablero en la casillas que va el carro
			for(int i=columnaInicial;i<columnaInicial+tamano;i++)
			{
				tablero[filaInicial][i]=tipo;
			}
		}
		else
		{
			//* For que pone el tipo del carro en el tablero en la casillas que va el carro
			for(int i=filaInicial;i<filaInicial+tamano;i++)
			{
				tablero[i][columnaInicial]=tipo;
			}
		}
		//* Crea la direccion del carro
		objDireccion=new Direccion(tipo,orientacion,tamano);
		objDireccion.adicionarTablero(tablero);
		return tablero;
	}
	//***********************************************************************************************************
	
	/**
	 * Compara si un c&oacute;digo es igual al c&oacute;digo de esta clase.
	 * 
	 * @return True si los c&oacute;digos son iguales, False de lo contrario.
	 */
	public boolean compararPieza(int tipoEntrada)
	{
		//* Variable que guarda la respuesta que se produce al comparar un tipo de carro con el tipo de este
		boolean respuesta=false;
		//* Compara el tipo de un carro con el tipo de este
		if(tipoEntrada==tipo)
		{
			respuesta=true;
		}
		return respuesta;
	}
	//***********************************************************************************************************
	
	/**
	 * Mueve el carro en el tablero especificado, verificando si la posici&oacute;n a la que va es v&aacute;lida.
	 * 
	 * @return Tablero con el carro ya movido.
	 */
	public int[][] moverEnTablero(int[][] tablero,int posFilaEntrada,int posColumnaEntrada)
	{
		//* Agrega el tablero actual a la direccion de este carro y revisa si un movimiento es valido
		objDireccion.adicionarTablero(tablero);
		movimientoValido=objDireccion.revisarMovimiento(filaInicial,columnaInicial,posFilaEntrada,posColumnaEntrada);
		
		
		if(movimientoValido)
		{
			if(orientacion==0)
			{
				//* Borra las casillas donde se encuentra actualmente este carro
				for(int i=columnaInicial;i<columnaInicial+tamano;i++)
				{
					tablero[filaInicial][i]=0;
				}
				//* Asigna la columna a la que va a ir como columna inicial
				columnaInicial=posColumnaEntrada;
				//* For que pone el tipo del carro en el tablero en las nuevas casillas que va el carro
				for(int i=columnaInicial;i<columnaInicial+tamano;i++)
				{
					tablero[filaInicial][i]=tipo;
				}
			}
			else
			{
				//* Borra las casillas donde se encuentra actualmente este carro
				for(int i=filaInicial;i<filaInicial+tamano;i++)
				{
					tablero[i][columnaInicial]=0;
				}
				//* Asigna la fila a la que va a ir como fila inicial
				filaInicial=posFilaEntrada;
				//* For que pone el tipo del carro en el tablero en las nuevas casillas que va el carro
				for(int i=filaInicial;i<filaInicial+tamano;i++)
				{
					tablero[i][columnaInicial]=tipo;
				}
			}
		}
		
		return tablero;
	}
	//***********************************************************************************************************
	
	/**
	 * Metodo que devuelve la orientacion de la pieza
	 * @return entero que representa la orientacion del carro
	 */ 
	public int obtenerOrientacion()
	{
		//* Devuelve el valor de la variable orientacion
		return orientacion;
	}
	//***********************************************************************************************************
	
	/**
	 * Metodo que retorna el tamano de la pieza
	 * @return Entero que representa el tamano
	 */ 
	public int obtenerTamano()
	{
		//* Devuelve el valor de la variable tamano
		return tamano;
	}
	//***********************************************************************************************************
	
	/**
	 * Metodo que retorna un arreglo de enteros que representan la posicion del carro
	 * @return arreglo con la posicion en la que se encuentra el carro
	 */ 
	public int[] obtenerPosicion()
	{
		//* Devuelve un arreglo con la fila y columna donde se encuentra la primera casilla del carro
		int posicion[]=new int[2];
		posicion[0]=filaInicial;
		posicion[1]=columnaInicial;
		
		return posicion;
	}
	//***********************************************************************************************************
	
	/**
	 * Metodo que devuelve un entero con el tipo del carro
	 * @return entero que representa el tipo
	 */ 
	public int obtenerTipo()
	{
		//* Devuelve el valor de la variable tipo
		return tipo;
	}
	//************************************************************************************************************
	
	/**
	 * Metodo que dependiendo del tipo que sea el carro define su tamano
	 */ 
	public void actualizarTamano()
	{
		//* Comparacion del tipo para encontrar el tamano
		switch(tipo)
		{
			case 1:
				tamano=2;
				break;
			case 2:
				tamano=3;
				break;
			case 3:
				tamano=2;
				break;
			case 4:
				tamano=2;
				break;
			case 5:
				tamano=2;
				break;
			case 6:
				tamano=3;
				break;
			case 7:
				tamano=2;
				break;
			case 8:
				tamano=3;
				break;
			case 9:
				tamano=3;
				break;
			case 10:
				tamano=2;
				break;
			case 11:
				tamano=2;
				break;
			case 12:
				tamano=2;
				break;
			case 13:
				tamano=2;
				break;
			case 14:
				tamano=2;
				break;
			case 15:
				tamano=2;
				break;
			case 16:
				tamano=2;
				break;
		}
	}
	//*************************************************************************************************
	
	/**
	 * Metodo que retornar si el moviento hecho es un movimiento valido
	 * @return valor booleano que representa si el movimiento es valido
	 */ 
	public boolean obtenerMovimientoValido()
	{
		//* Devuelve el valor de la variable movimientoValido
		return movimientoValido;
	}
	//*************************************************************************************************
}
