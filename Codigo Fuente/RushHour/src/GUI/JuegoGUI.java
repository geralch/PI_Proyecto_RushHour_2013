package GUI;

import Control.Controlador;
import Core.Pieza;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * La Clase JuegosGUI se encarga de mostrar la interfaz principal del juego, el tablero y los botones
 * que permiten navegar en el juego.
 * 
 * @author Caicedo Hidalgo Geraldine 0939713
 * @author Hern&aacute;ndez Abahonza Diana Carolina 1031083
 * @author Pab&oacute;n Ruiz Jorge Eduardo 1325856
 * @version 0.1.5
 * @see <a href="Controlador.html">Class Controlador</a>
 * @see <a href="ManejadorMusica.html">Class ManejadorMusica</a>
 * @see <a href="VistaIngresoUsuario.html">Class VistaIngresoUsuario</a>
 * @see <a href="http://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html">JFrame</a>
 * @see <a href="http://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseMotionListener.html">MoseMotionListener</a>
 * @see <a href="http://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseListener.html">MouseListener</a>
 * @see <a href="http://docs.oracle.com/javase/7/docs/api/java/awt/event/ActionListener.html">ActionListener</a>
 */
public class JuegoGUI extends JFrame implements MouseMotionListener, MouseListener, ActionListener
{
	//Parametros para el manejo del Tablero
	
	/*! Objeto de la Clase Controlador*/
    private Controlador control;				//Objeto Controlador
    /*! Vector de Objetos de la Clase Pieza */
    private Vector<Pieza> vector; 				//Vector de Objetos Pieza donde se almacenan los elementos de un tablero
    /*! Matriz de JLabel */
    private JLabel tablero[][];   				//Matriz de JLabel que representa el tablero
    /*! Arreglo de JLabel*/  
    private JLabel carros[];					//Arreglo de JLabel que representa la imagen de un carro del tablero
    /*! Contenedor */
    Container contenedor;						//Container que contiene los elementos de la ventana del juego
    /*! Objeto de la Clase ManejadorMusica */
	//private ManejadorMusica objManejadorMusica; //Objero de la clase ManejadorMusica que se encarga del sonido cuando se gana un tablero
    /*! String que guarda la dificultad del tablero*/
    private String dificultad_tablero;			//Guarda un String que representa la dificultad del tabelro que se esta jugando
    /*! Entero que almacena el nivel del tablero*/
    private int nivel_tablero;					//Entero que representa el nivel del tablero que se esta jugando
    /*! Entero que almacena el numero de movimientos en el plano*/
    private int movimientos;					//Entero que representa el numero de movimientos en el plano, podiendo ser un numero negativo
    /*! Entero que almacena el numero de movimientos realizados por el jugador*/
    private int movimientos_realizados;			//Entero que representa el numero de movimientos realizados por el jugador 
    
    //Parametros del Panel Principal   
    /*! Panel principal panel juego*/  			
    private JPanel panel_juego;					//Panel que contiene los elementos principales de la ventana
    /*!JLabel con el fondo del tablero */
    private JLabel fondoTablero;				//Label con el fondo del tablero de juego
    /*! Jlabel con el fondo de la ventana */
    private JLabel fondo;						//Label con el fondo principal de la ventana
    /*! JLabel con el numero de movimientos*/
    private JLabel numero_movimientos;			//Label que se actualiza con el numero de movimientos que hace el jugador
    /*! JLabel con la dificultad y elnivel del tablero*/
    private JLabel dificultad_nivel;			//Label con el nombre de la dificultad y el numero del nivel del tablero 
    /*!JButton Rehacer */
    private JButton boton_rehacer;				//Button para rehacer un movimiento deshecho
    /*! JButton deshacer*/
    private JButton boton_deshacer;				//Button para deshacer un movimiento hecho
    /*! JButton reiniciar*/
    private JButton boton_reiniciar;			//Butoon para reiniciciar un tablero que se esta jugando
    /*! JButton pausar*/
    private JButton boton_pausar;				//Button para pausar el juego
    /*! JButton detener*/
    private JButton boton_detener;				//Button para detener el juego
	
	//Parametros del Panel del Acerca De.
	/*! Panel con la informacion de acercaDe*/	
	private JPanel panel_acercade;				//Panel que contiene la informacion acerca de nosotros y el creador del juego
	/*! Label con el fondo de acercaDe*/
	private JLabel fondo_acercade;				//Fonod del acercaDe
	/*! Button para cerrar el acercaDe */
	private JButton acercade_cerrar;			//Button para cerra el panel del AcercaDe
	/*! Button acercade*/
    private JButton boton_acercade;				//Button que muestra la informacion acer de nodotros y el creador del juego
    
    //Parametros de los Botones para Elegir una Dificultad
    /*! String con la dificultad seleccionad*/
    private String dificultad_seleccionada;		//String para seleccionar una dificultad sin cambiar la dificultad actual
    /*! Panel con los botones para elegir el nivel*/
    private JPanel panel_niveles;				//Panel que contiene los botones para elegir el nivel que se desea jugar
    /*! Fondo del panel de los niveles*/
    private JLabel fondo_niveles;				//Label que contiene el fondo del panel de niveles
    /*! Button de dificultad facil*/
    private JButton boton_facil;				//Boton para elegir la dificultad Facil 
    /*! Button de dificultad media*/
    private JButton boton_medio;				//Boton para elegir la dificultad Medio
    /*! Button de dificultad dificl*/
    private JButton boton_dificil;				//Boton para elegir la dificultad Dificil
    /*! Button de dificultad experto*/
    private JButton boton_experto; 				//Boton para elegir la dificultad Experto
    /*! Arreglo de button con los botones de los niveles*/
    private JButton botones_niveles[];			//Arreglo de botonoes con los 25 botones de los nivelea a elegir
    
    //Parametros del Panel de la imagen de victoria
    /*!Panel de la ventana de victoria */
    private JPanel panel_victoria; 				//Panel que contiene la imagen y los datos que se muestran al acabar una partida
    /*! Label con el fondo del panel victoria*/
    private JLabel fondo_ganar;					//Label que contiene el fonfo que se muestra al ganar
    /*! Numero de movimientos minimos del tablero*/
    private int numero_mov_minimos;				//Entero que representa el numero de movimientos minimos que tiene el tablero actual
    /*! Label con los movimientos minimos del tablero*/
    private JLabel movimientos_minimos;			//Label que muestra los movimientos minimos del tablero
    /*! Label con los movimientos totales del tablero*/
    private JLabel movimientos_totales;			//Label que muestra el numero de movimientos hechos por el jugador
    /*! Button intentar de nuevo*/
    private JButton boton_intentar_denuevo;		//Button para intentar de nuevo el tablero jugado
    /*! Button siguiente reto*/
    private JButton boton_siguiente_reto;		//Button para seguir con el siguiente tablero a jugar
    
    //Parametros para el movimiento de los Carros
    /*! Arreglo de posiciones en el eje X*/
    private int arregloX[];						//Arreglo de posiciones de los carros en el eje X
    /*! Arreglo de posiciones en el eje Y*/
    private int arregloY[];						//Arreglo de posiciones de los carros en el eje Y
    /*! Posicion del mouse en X*/
    private int presX;							//Posicion del mouse en el eje X
    /*! Posicion del mouse en Y*/
    private int presY;   						//Posicion del mouse en el eje Y
    /*! Posicion inicial del tablero en X*/
    private int iniX = 300;						//Posicion en el eje X donde se ubica el tablero
    /*! Posicion inicial del tablero en Y*/
    private int iniY = 80; 						//Posicion en el eje Y donde se ubica el tablero
    
    //Parametros para el ingreso a la primera Ventana
    /*! Nombre del usuario que accedio*/
    private String usuario;						// Nombre del Usuario que se registro para jugar
    /*! Booleano de inicio por primera vez*/
    private boolean inicioPrimeraVez;			//Booleano que representa si ya se inicio una vez el registro de usario
    /*! Objeto de la clase VistaIngresoUsuario*/
    private VistaIngresoUsuario vistaIngreso;   //Objeto de la clase VistaIngresoUsuario
    /*! Vector de string con los datos de ingreso*/
    private Vector<String> datosIngreso;		//Vector de String con los datos de ingreso del usuario logiado
    /*! Vector de string con los datos de registro*/
    private Vector<String> datosRegistro;		//Vector de String con los datos de registro de un nuevo usuario
    
    /** Constructor de la Clase JuegoGUI, Inicializa los parametros y utiliza los metodos de configuracion de la interfaz*/        
    JuegoGUI()
    {
		// Inicializacion de los Parametros
		
		usuario="";
		inicioPrimeraVez=true;
		contenedor = this.getContentPane();
		contenedor.setLayout(null); //* Hace que el contenedor tenga un Layour Vacio para poder acomodar los Paneles con setBound

		//* Inicializacion de el numero de movimientos realizados y los movimientos minimos
		movimientos = 0;
		movimientos_realizados = 0;
		numero_mov_minimos = 0;
		
		//* Dificultad y Nivel por defecto al iniciar el juego
		dificultad_tablero = "Facil"; 
        nivel_tablero = 1;
        
        //* Este metodo inicia y acomoda los componentes de la ventana del juego
		configurarPanelJuego(dificultad_tablero,nivel_tablero);  
		//* Con este metodo se configura la ventana que se muestra al terminar un tablero
		configurarPanelVictoria();				
		
		this.setSize(635,500); // Tamano del contenedor
		this.setLocationRelativeTo(null); //
		this.setResizable(false); // No se puede redimensionar la ventana
		this.setVisible(true); // Se muestra la ventana
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Se activa el cierre de la ventana con el boton cerrar
    }
    //*************************************************************************************************
    
    /**
     * Metodo que incia las vistas de entrada de usuario
     */
    private void iniciarVistas()
    {
		//*Inicializa un usuario nuevo
		boolean usuarioNuevo=false;
		
		//*Inicializacion de el objeto de la clase VistaIngresoUsuario
		vistaIngreso = new VistaIngresoUsuario(this, true);
		// obtenerner si el usuario esta registrado o no
		usuarioNuevo=vistaIngreso.obtenerNuevoUsuario();
		
		//Si el no es un usario nuevo
        if(usuarioNuevo==false)
        {
			// Se obtienen los datos de ingreso del usuario que quiere ingresar, y si el usuario ya esta registrado o no
			datosIngreso=vistaIngreso.obtenerDatosIngreso();
			usuario=datosIngreso.elementAt(0);
			verificarUsuario( datosIngreso );
		}
		//En caso de que el usuario sea nuevo
		else
		{
			//Se registran los datos del nuevo usuario
			datosRegistro=vistaIngreso.obtenerDatosRegistro();
			control.insertarUsuario(datosRegistro.elementAt(1), datosRegistro.elementAt(0), datosRegistro.elementAt(2));
			iniciarVistas();
		}
	}
	//*************************************************************************************************
	
	/**
	 * Metodo que verifica si un usario esta registrado
	 * @param paramDatosIngreso Vector de String con los datos del usuario
	 */ 
	private void verificarUsuario(Vector<String> paramDatosIngreso)//
	{
		//*Inicializa la variable local
		boolean encontrado = true;
		
		//Se uguala encontrado por el valor que retorne el metodo para saber si el usuario se encontro o no
		encontrado=control.consultarDatos(paramDatosIngreso.elementAt(0), paramDatosIngreso.elementAt(1));
		encontrado = true;
		//Si el usuario se encontro
		if(encontrado)
		{
			return;
		}
		//En caso contrario
		else
		{
			//Vuelve a mostrar la vista de ingreso
			vistaIngreso = new VistaIngresoUsuario(this, true);
			verificarUsuario( vistaIngreso.obtenerDatosIngreso() );
		}
	}
    //*************************************************************************************************
    
    /**
     * Metodo que acomoda los elementos del Panel Juego, que es donde se encuentran todos los elementos de la ventana
     * @param param_dificulta  String con la dificultad elegida.
     * @param param_nivel      String con el nivel del tablero de la dificultad.
     */ 
    private void configurarPanelJuego(String param_dificultad, int param_nivel)
    {
		//*Se crea un nuevo controlador cada vez que se accede a un tablero diferente
		control = new Controlador(); // Inicializacion del objeto Controlador
		control.inicializar();//inicializa la base de datos
		
		//* Se Asigna el nombre del archivo en el que se encuentra la configuracion del tablero elegido
		control.cargarNombreArchivo("Tableros/"+param_dificultad+"/Tablero"+param_nivel);
		
		//* Si ya se inicio el programa, no se vuelve a mostrar la vista principal de ingreso de usuario
		if(inicioPrimeraVez)
		{		
			iniciarVistas();
			inicioPrimeraVez=false;
		}
		
		control.guardarNombreUsuario(usuario); //Se guarda el nombre de usuario segun el usuario que accede
		control.cargarTablero(); //Carga el tablero que se quiere jugar
		
		//* Se igualan los datos globales de la dificultad y el nivel del tablero con los parametros de entrada
		dificultad_tablero = param_dificultad; 
		nivel_tablero = param_nivel;
		
		//* Se da los valores del vector de objetos de la clase Pieza
		vector = control.obtenerPiezas();
		
		//* Inicializacion de los vectores de los datos del usuario
		datosIngreso = new Vector<String>(1);//
        datosRegistro = new Vector<String>(1);//
		
		//* Inicializacion del objeto de la clase ManejadorMusica
		//objManejadorMusica=new ManejadorMusica();
		
		//* Configuracion del panel juego
		panel_juego = new JPanel(); // Inicializacion
		panel_juego.setLayout(null); // El panel no contiene ningun layout
		panel_juego.setBounds(0,0,633,471); // Se da la posicion y el tamano del Panel
		panel_juego.addMouseMotionListener(this); // Se le agrega un escuchas de MouseMotionListener para el movimiento de las fichas
		
		//* Configuaracion del label que muetras los movimientos realizado
		numero_movimientos = new JLabel(""+movimientos_realizados); //Inicializacion con el numero de movimientos realizados como parametro
		numero_movimientos.setForeground(Color.white); // Color de las letras
		numero_movimientos.setFont(new Font("Century Gothic", Font.BOLD, 20)); // Fuente y tamano de la letra
		numero_movimientos.setBounds(140,175,50,50); // Ubicacion y tamano del label
		
		int aux_niv = param_nivel-1; // Ya los niveles de los tableros empiezan desde 1, se le resta 1 , para acceder a la posicion en el vector
		//* Se asigna un valor al numero de movimientos minimos del actual tablero. 
		numero_mov_minimos = control.obtenerMovimientoMinimoTablero(cambiarNivelNumero(param_dificultad),aux_niv);
		
		//*Configuracion del label que mestra la dificultad y el nivel del tablero que se esta jugando
		dificultad_nivel = new JLabel(cambiarIdioma(param_dificultad)+" \u0023 "+param_nivel); // inicializacion con la dificultad y el numero del nivel
		dificultad_nivel.setFont(new Font("Century Gothic", Font.BOLD, 15)); // Fuente y tamano de la letra
		dificultad_nivel.setForeground(Color.white); //Coloe de las letras	
		dificultad_nivel.setBounds(500,410,100,50); // Ubicacion y tamabo del label
		
		//* Se busca si hay alguna excepcion, en caso de no encontrar las imagenes que se necesitan para configurar los carros
		try 
		{
			configurarCarros();
		} 
		catch (IOException ex) 
		{
			System.out.println("No hay imagen");
		}
		
		configurarTablero(); // Configuracion del Tablero donde se mueven las fichas
		
		configurarBotones(); // Configuracion de los botones que componen la interfaz principal
        crearBotonesPanelNiveles();  //Configura el panel donde se muestran los niveles de cada dificultad       
        panel_niveles.setVisible(false); //Se oculta el panel que muestra los niveles, hasta que el usuario desee verlo
        configurarPanelAcercaDe(); // Configa el panel de la informacio Acerca de nosotros
        panel_acercade.setVisible(false); //Se oculta el panel de Acer de hasta que el usuario desee verlo
	
		//* Inicializacion de el label con el fondo del juego, posicion y tamaño del fondo
		fondo = new JLabel(new ImageIcon("Images/Fondos/000.png"));
		fondo.setBounds(0, 0, 630, 472);
		
		//* Se anaden los elementos al panel principal panel juego y se anade el panel juego al contenedor.
		panel_juego.add(panel_acercade);
		panel_juego.add(numero_movimientos);
		panel_juego.add(dificultad_nivel);
		panel_juego.add(fondo);
		contenedor.add(panel_juego);
	}
	//*************************************************************************************************
	
	/**
	 * Metodo que le acomoda la posicion y el tamano de un boton, ademas de adicionarle ell ActionListener
	 * y quitarle los bordes.
	 * @param boton JBurron que se va a editar
	 * @param x  posicion x en la que se pondra el boton
	 * @param y  posicion y en la que se pondra el boton
	 * @param w  ancho del boton
	 * @param h  alto del boton
	 */
    private void crearBoton(JButton boton, int x, int y, int w, int h)
    {
		boton.setRolloverEnabled(false); // Quita la iluminacion al pasar el mouse por el boton 
		boton.setBorderPainted(false); // Quita el borde del boton
		boton.setBounds(x, y, w, h); // Ubicacion y tamano del boton
		boton.addActionListener(this); // Escucha del boton
	}
	//*************************************************************************************************
	
	/**
	 * Metodo que configura el panel que se muestra cuando se ha termiando un tablero  
	 */
    private void configurarPanelVictoria()
    {		
		//* Inicializacion del label donde se encuentra la imagen que se muetra al ganar
		fondo_ganar = new JLabel();
		fondo_ganar.setBounds(0,0,633,471);	// Posicion y Tamano del label	

		//* Inicializacion del panel donde se mostrara que se gano la partida
		panel_victoria = new JPanel();
		panel_victoria.setLayout(null); // panel_victoria no tiene layouts
		panel_victoria.setBounds(0,0,633,471); // Posicion y tamano del panel
		
		//* Inicializacion del label donde se muestra el numero de movimientos hechos por el jugador
		movimientos_totales = new JLabel();
		movimientos_totales.setForeground(Color.red); //Color de las letras
		movimientos_totales.setFont(new Font("Arial", Font.BOLD, 15)); // Fuente y tamano de la letra
		movimientos_totales.setBounds(360,170,50,50); // Posicion y tamano del label
		
		//* Inicializacion del label donde se muestra el numero de movimientos minimos del tablero hecho
		movimientos_minimos = new JLabel();
		movimientos_minimos.setForeground(Color.red); // Color de las letras
		movimientos_minimos.setFont(new Font("Arial", Font.BOLD, 15)); // Fuente y tamano de la letra
		movimientos_minimos.setBounds(360,191,50,50); //Posicion y tamano del label

		//* Inicializacion del boton intentar de nuevo que devuelve al panel juego con el tablero que se hizo 
		boton_intentar_denuevo = new JButton(new ImageIcon("Images/Botones/007.png"));
		boton_intentar_denuevo.setFocusPainted(false); // 
		boton_intentar_denuevo.setContentAreaFilled(false); // Pone transparencia al boton
		crearBoton(boton_intentar_denuevo,195,310,105,20); // Posicion y tamano del boton

		//* Inicializacion del boron siguiente reto que devuelve al panel juego con el tablero siguiente
		boton_siguiente_reto = new JButton(new ImageIcon("Images/Botones/008.png"));
		boton_siguiente_reto.setFocusPainted(false); //
		boton_siguiente_reto.setContentAreaFilled(false); // Pone transparecncia al boton
		crearBoton(boton_siguiente_reto,345,310,105,20); // Posicion y tamano del boton
		
		//* Se anaden los elementos al panel_victoria
		panel_victoria.add(movimientos_totales);
		panel_victoria.add(movimientos_minimos);
		panel_victoria.add(boton_intentar_denuevo);
		panel_victoria.add(boton_siguiente_reto);
		panel_victoria.add(fondo_ganar);
    }
	//*************************************************************************************************
	
	/**
	 * Metodo que configura el panel que se muestra cuando se da al boton AcercaDe
	 */
	private void configurarPanelAcercaDe()
	{
		//* Inicializacion del Panel AcercaDe donde se encuentran los elementos que muestran informacion sobre el juego
		panel_acercade = new JPanel();
		panel_acercade.setLayout(null); // El panel no contiene layouts
		panel_acercade.setBounds(20,120,252,258); // Posicion y Tamano del panel
		
		//* Inicializacion del Label que contiene la imagen de fondo del panel
		fondo_acercade = new JLabel(new ImageIcon("Images/Fondos/fondoabout.png"));
		fondo_acercade.setBounds(0,0,252,258); // Posicion y tamano del label
		
		//* Inicializacion del buton cerrar del panel, el cual esconde nuevamente el panel de AcercaDe 
		acercade_cerrar = new JButton("X Close");
		crearBoton(acercade_cerrar,74,220,90,12); // Posicion y tamano del boton
		acercade_cerrar.setForeground(Color.white); // Color de las letras del boton
		acercade_cerrar.setFont(new Font("Century Gothic", Font.BOLD, 12)); // Fuente y tamano de la letra
		acercade_cerrar.setFocusPainted(false); //
		acercade_cerrar.setContentAreaFilled(false); // Pone el boton en transparencia
		
		//* Se anaden los elementos al panel acercade
		panel_acercade.add(acercade_cerrar);
		panel_acercade.add(fondo_acercade);	
	}
	//*************************************************************************************************
	
	/**
	 * Metodo que configura el tablero del juego, poniendole el fondo y los cuadros que representan una posicion en el tablero
	 */
    private void configurarTablero()
    {             
		//* Inicializacion de la matix de label tablero, con un tamano de 6 x 6  
        tablero = new JLabel[6][6]; 
        
        // Variables locales       
        int x; // posicion en x
        int y; // posicion en y       
        int w = 50; // ancho de la imagen del cuadro
        int h = 50; //alto de la imagen del cuadro
        
        //* Inicializacion de una variable ImageIcon que tiene la imagen del cuadrodo en el tablero
        ImageIcon image = new ImageIcon("Images/Fondos/020.png");
        
        //* Este for recoore la matrix de labels tablero, añade un label con la imagen del cuadro a la
        // matriz y lo acomodo en una posicion del panel juego para anadirlo al panel juego.
        for(int i=0;i<6;i++)
        {
            for(int j=0;j<6;j++)
            { 
				//las posiciones de cada cuadrito estan determinadas por la posicion en la que se ubica el tablero en el panel juego
				//la posicion del tablero es 300-80 , entonces los cuadritos se iran acomodando, segun el cuadro que sea, en la posicion
				//del tablero mas el ancho de la imagen por el numero que sea.
                x = iniX + (i*w);
                y = iniY + (j*h);
                
                tablero[i][j] = new JLabel(image);
                tablero[i][j].setBounds(x, y, w, h);
                panel_juego.add(tablero[i][j]);
            }
        }
        //* Inicializacion de el label que contiene la imagen de fondo del tablero
        fondoTablero = new JLabel(new ImageIcon("Images/Fondos/019.png"));
        fondoTablero.setBounds(iniX-10, iniY-10, 320, 320); //Posicion y Tamano del fondo
        panel_juego.add(fondoTablero); // Se agrega el fondo al panel juego
    }    
    //*************************************************************************************************
       
    /**
     * Metodo que configura los botones que se encuentra en el panel juego
     */   
    private void configurarBotones()
    {
		//* Inicializacion de la Imagen y el boton deshacer
		ImageIcon imagen_deshacer_d = new ImageIcon("Images/Botones/001.png");
		boton_deshacer = new JButton(imagen_deshacer_d);
		crearBoton(boton_deshacer,60,70,46,46); // Posicion y tamano del boton deshacer
			
		//* Inicializacion de la Imagen y el boton rehacer		
		ImageIcon imagen_rehacer_d = new ImageIcon("Images/Botones/002.png");
		boton_rehacer = new JButton(imagen_rehacer_d);
		crearBoton(boton_rehacer,125,70,46,46);// Posicion y tamano del boton rehacer
		
		//* Inicializacion de la Imagen y el boton reiniciar		
		ImageIcon imagen_reiniciar_d = new ImageIcon("Images/Botones/003.png");
		boton_reiniciar = new JButton(imagen_reiniciar_d);
		crearBoton(boton_reiniciar,190,70,46,46);// Posicion y tamano del boton reiniciar
		
		//* Inicializacion de la Imagen y el boton pausar
		ImageIcon imagen_pausar = new ImageIcon("Images/Botones/009.png");
		boton_pausar = new JButton(imagen_pausar);
		boton_pausar.setFocusPainted(false); //
		boton_pausar.setContentAreaFilled(false); // Pone el boton en transparencia
		crearBoton(boton_pausar,20,418,35,35);// Posicion y tamano del boton pausar
		
		//* Inicializacion de la Imagen y el boton detener
		ImageIcon imagen_detener = new ImageIcon("Images/Botones/010.png");
		boton_detener = new JButton(imagen_detener);
		boton_detener.setFocusPainted(false); //
		boton_detener.setContentAreaFilled(false);// Pone el boton en transparencia
		crearBoton(boton_detener,60,418,35,35);// Posicion y tamano del boton detener
		
		//* Inicializacion de la Imagen y el boton acercade
		ImageIcon imagen_acercade = new ImageIcon("Images/Botones/011.png");
		boton_acercade = new JButton(imagen_acercade);
		boton_acercade.setFocusPainted(false);//
		boton_acercade.setContentAreaFilled(false);// Pone el boton en transparencia
		crearBoton(boton_acercade,100,418,35,35);// Posicion y tamano del boton acercade
			
		//* Inicializacion de la Imagen y el boton de la dificultad facil	
		ImageIcon imagen_but_facil = new ImageIcon("Images/Botones/easybutton.png");
		boton_facil = new JButton(imagen_but_facil);
		boton_facil.setFocusPainted(false);//
		boton_facil.setContentAreaFilled(false);// Pone el boton en transparencia
		crearBoton(boton_facil,26,394,60,18);// Posicion y tamano del boton de la dificultad facil
		
		//* Inicializacion de la Imagen y el boton de la dificultad media		
		ImageIcon imagen_but_medio = new ImageIcon("Images/Botones/mediumbutton.png");
		boton_medio = new JButton(imagen_but_medio);
		boton_medio.setFocusPainted(false);//
		boton_medio.setContentAreaFilled(false);// Pone el boton en transparencia
		crearBoton(boton_medio,86,394,60,18);// Posicion y tamano del boton de la dificultad media
		
		//* Inicializacion de la Imagen y el boton de la dificultad dificil
		ImageIcon imagen_but_dificil = new ImageIcon("Images/Botones/hardbutton.png");
		boton_dificil = new JButton(imagen_but_dificil);
		boton_dificil.setFocusPainted(false);//
		boton_dificil.setContentAreaFilled(false);// Pone el boton en transparencia
		crearBoton(boton_dificil,146,394,60,18);// Posicion y tamano del boton de la dificultad dificil
		
		//* Inicializacion de la Imagen y el boton de la dificultad experto		
		ImageIcon imagen_but_experto = new ImageIcon("Images/Botones/expertbutton.png");
		boton_experto = new JButton(imagen_but_experto);
		boton_experto.setFocusPainted(false);//
		boton_experto.setContentAreaFilled(false);// Pone el boton en transparencia
		crearBoton(boton_experto,206,394,60,18);// Posicion y tamano del boton de la dificultad experto
		
		//* Se anaden los botones al panel juego	
		panel_juego.add(boton_pausar);
		panel_juego.add(boton_detener);
		panel_juego.add(boton_acercade);		
		panel_juego.add(boton_facil);
		panel_juego.add(boton_medio);
		panel_juego.add(boton_dificil);
		panel_juego.add(boton_experto);
		panel_juego.add(boton_deshacer);
		panel_juego.add(boton_rehacer);
		panel_juego.add(boton_reiniciar);
	}
	//*************************************************************************************************
	
	/**
	 * Metodo que acomoda los carros y camiones en el tablero de juego, segun las distribucion del tablero deseado
	 * @throws IOException
	 */
    private void configurarCarros() throws IOException
    {
		//* Inicializacion de el vector de label que represaenta los carros, y de los vectores de enteros
		//que representan las posiciones de cada carro, su tamano depende del vector de piezas que se 
		//obtiene segun el tablero
        carros = new JLabel[vector.size()]; 
        arregloX = new int[vector.size()];
        arregloY = new int[vector.size()];
        
        // Variables locales
        int x; // posicion en X 
        int y; // posicion en Y
        int w; // ancho del carro
        int h; // alto del carro
        
        //* Este for se encarga de recorer el vector de objetos de la Clase Pieza   y obteniendo
        //la posicion X y Y del la pieza i del vector , y segun su orientacion y tamano, se coloca
        //un label que representara ese carro en el tablero.     
        for(int i=0;i<vector.size();i++)
        {
            Pieza carro = vector.get(i); // hacemos una variable del tipo Pieza que se igual a el objeto de la posicion i del vector
            x = iniX + (carro.obtenerPosicion()[1]*50); // la posicion en X es igual a la posicion x del carro por el tamano de la imagen
            y = iniY + (carro.obtenerPosicion()[0]*50);// la posicion en Y es igual a la posicion y del carro por el tamano de la imagen
            
            //* Se iguala las posiciones en los arreglos de posiciones con las posiciones del carro ordenadamente
            arregloX[i] = carro.obtenerPosicion()[1];
            arregloY[i] = carro.obtenerPosicion()[0];
        
				//* En caso de que la orientacion del carro sea horizontal, se multiplica el ancho del carro por el tamano de la imagen
				if(carro.obtenerOrientacion() == 0)
				{
					w = 50*carro.obtenerTamano();
					h = 50;                
					carros[i] = new JLabel(new ImageIcon("Images/Carros/"+carro.obtenerTipo()+".png"));
				}
				//*en caso contrario se multiplica el alto.
				else
				{
					w = 50;
					h = 50*carro.obtenerTamano();
					carros[i] = new JLabel(new ImageIcon("Images/Carros/"+carro.obtenerTipo()+"V.png"));
				}
            
            carros[i].setBounds(x, y, w, h); // Posicion y tamano del label carro
            carros[i].addMouseMotionListener(this); // Se adicciona el MouseMotionListener para el arrastre de los label
            carros[i].addMouseListener(this); // Se adicciona el MouseListener
            panel_juego.add(carros[i]);// Se agrega el carro en el panel juego.
        }       
    } 
    //*************************************************************************************************
    
    /**
     * Metodo que cambia el String de la dificultad por un numero.
     * @param param_dificultad  String con la dificultad elegida
     * @return entero que representa el nivel elegido
     */    
    private int cambiarNivelNumero(String param_dificultad)
    {
		// Variable local 
		int aux_difi = 0;
		
		// Si el String es igual a "Facil" se cambia la variable local por 0
		if(param_dificultad==("Facil"))
		{
			aux_difi = 0;
		}
		
		// Si el String es igual a "Medio" se cambia la variable local por 1
		if(param_dificultad==("Medio"))
		{
			aux_difi = 1;
		}
		
		// Si el String es igual a "Dificil" se cambia la variable local por 2
		if(param_dificultad==("Dificil"))
		{
			aux_difi = 2;
		}
		
		// Si el String es igual a "Experto" se cambia la variable local por 3
		if(param_dificultad==("Experto"))
		{
			aux_difi = 3;
		}
		
		//Se retorna la variable local actualizada
		return aux_difi;
	}
    //*************************************************************************************************
    
    /**
     * Metodo que cambia el String de la dificultad por su semejante en Ingles.
     * @param param_dificultad  String con la dificultad elegida
     * @return String con la dificultad en ingles.
     */
    private String cambiarIdioma(String param_dificultad)
    {
		//Variable local
		String aux_difi = "";
		
		// Si el String es igual a "Facil" se cambia la variable local por "Easy"
		if(param_dificultad==("Facil"))
		{
			aux_difi = "Easy";
		}
		
		// Si el String es igual a "Medio" se cambia la variable local por "Medium"
		if(param_dificultad==("Medio"))
		{
			aux_difi = "Medium";
		}
		
		// Si el String es igual a "Dificil" se cambia la variable local por "Hard"
		if(param_dificultad==("Dificil"))
		{
			aux_difi = "Hard";
		}
		
		// Si el String es igual a "Experto" se cambia la variable local por "Expert"
		if(param_dificultad==("Experto"))
		{
			aux_difi = "Expert";
		}
		
		//Se retorna la variable local actualizada
		return aux_difi;
	}
    //*************************************************************************************************
    
    /**
     * Metodo que cambia las variables de la dificultad y el nivel, cuando se quiere cambiar de tablero
     */
    private void cambiarSiguienteTablero()
    {
		//* Primero se decide cual es la dificultad del tablero
		if(dificultad_tablero==("Facil"))
		{
			// Si la dificultad del tablero es "Facil" y el nivel del tablero en 25, se cambia a la Dificultad Media y al tablero 1
			if(nivel_tablero==25)
			{
				dificultad_tablero = "Medio";
				nivel_tablero = 1;
			}
			//En caso de que sea un nivel diferente al 25 se cambia el nivel por el siguiente.
			else
				nivel_tablero++;
		}
		
		else if(dificultad_tablero==("Medio"))
		{
			// Si la dificultad del tablero es "Medio" y el nivel del tablero en 25, se cambia a la Dificultad Dificil y al tablero 1
			if(nivel_tablero==25)
			{
				dificultad_tablero = "Dificil";
				nivel_tablero = 1;
			}
			//En caso de que sea un nivel diferente al 25 se cambia el nivel por el siguiente.
			else
				nivel_tablero++;
		}
		
		else if(dificultad_tablero==("Dificil"))
		{
			// Si la dificultad del tablero es "Dificil" y el nivel del tablero en 25, se cambia a la Experto Media y al tablero 1
			if(nivel_tablero==25)
			{
				dificultad_tablero = "Experto";
				nivel_tablero = 1;
			}
			//En caso de que sea un nivel diferente al 25 se cambia el nivel por el siguiente.
			else
				nivel_tablero++;
		}
		
		else if(dificultad_tablero==("Experto"))
		{
			// Si la dificultad del tablero es "Experto" y el nivel del tablero en 25, se mantiene en la misma dificultad y en el mismo nivel
			if(nivel_tablero==25)
			{
				dificultad_tablero = "Experto";
				nivel_tablero = 25;
			}
			//En caso de que sea un nivel diferente al 25 se cambia el nivel por el siguiente.
			else
				nivel_tablero++;
		}
	}
    //*************************************************************************************************
    
    /**
     * Metodo crea y configura los botones para el panel de eleccion de nivel
     */    
    private void crearBotonesPanelNiveles()
    {
		//* Inicializacion del panel niveles
		panel_niveles = new JPanel();
		panel_niveles.setLayout(null); // El panel no tiene ningun layout
		panel_niveles.setBounds(20,142,255,270); //Posicion y tamano de el panel		
		panel_niveles.addMouseMotionListener(this); // Se adiciona al panel un MouseMotionListener para que desaparezca si el mouse no esta dentro del panel
		
		//*Inicializacion del label con el fondo de este panel
		fondo_niveles = new JLabel();
		fondo_niveles.setBounds(0,0,255,270); // Posicion y yamano de el fondo
		botones_niveles = new JButton[25]; // Inicializacion de la matriz de botones de eleccion del nivel
				
		//* este for recorre el arreglo de botones con los cuales se elegira el nivel que se quiere jugar		
		for(int i=0; i<5; i++)
		{
			for(int j=0; j<5; j++)
			{
				//Se acomodan los botones en su posicion correspondiente dependiendo del boton que sea
				//y se agregan al panel niveles
				int n = j*5+i;
				botones_niveles[n] = new JButton(""+(n+1));
				botones_niveles[n].setMargin(new Insets(0,0,0,0));
				crearBoton(botones_niveles[n],20+i*45,38+j*43,33,33);
				panel_niveles.add(botones_niveles[n]);
			}
		}
		
		//* Se agregan el fondo al panel niveles, y el panel nivele al panel juego
		panel_niveles.add(fondo_niveles);
		panel_juego.add(panel_niveles);
	}
    //*************************************************************************************************
    
    /**
     * Metodo que cambia el fondo que se muestra al ganar una partida, dependiendo si el numero de movimientos
     * fue el minimo
     */
    private void cambiarFondoGanar()
    {
		//* Si el numero de movimientos es mayor al numero de movimientos minimos de el tablero realizado se muestra una imagen diferenre
		//y tambien se escucha un sonido.
		if(movimientos_realizados>numero_mov_minimos)
		{
			fondo_ganar.setIcon(new ImageIcon("Images/Fondos/010.png"));
			//objManejadorMusica.asignarNombre("Sonidos/perder.mp3");
			//objManejadorMusica.reproducirUnaVez();
		}
		//*En caso de que se hago el numero de movimientos igual o menor al minimo, se muestra otra imagen con otro sonido
		else
		{
			fondo_ganar.setIcon(new ImageIcon("Images/Fondos/011.png"));
			//objManejadorMusica.asignarNombre("Sonidos/ganar.mp3");
			//objManejadorMusica.start();
		}
	}
	//*************************************************************************************************
	
	/**
	 * Metodo que muestra cuando se ha ganado una partida y su respetiva informacion
	 */
	private void ganarPartida()
	{
		contenedor.removeAll(); // Se remueve los elementos del contenedor para poder poner los nuevos elementos
		contenedor.add(panel_victoria); // se anade el panelvictoria al contenedor para mostrar su contenido
		cambiarFondoGanar(); // dependiendo de los movimientos se cambia la imagen de fondo y el sonido
		movimientos_minimos.setText(""+numero_mov_minimos); // Se muestra el numero de movimientos minimos del tablero realizado
		movimientos_totales.setText(""+movimientos_realizados); // Se muestra el numero de movimientos realizados por el juagdor
		movimientos_realizados=0; // Se borran los movimientos hechos para empezar el nuevo juego
		movimientos=0;	// Se borran los movimientos en coordenada para empezar a contar los movimientos denuevo
		contenedor.doLayout(); //
		contenedor.repaint(); // Se pinta el contenedor denuevo
	}
    //*************************************************************************************************
    
    /**
     * Metodo Sobreescrito de la clase implementada MouseMotionListener
     * @param e  MoeseEvent que identifica la fuente de donde se hace el evento
     */
    public void mouseDragged(MouseEvent e)
    {	
		//* Este for recorre el vector de carros y con algunos validaciones dice si se permite o no el movimiento de un a ficha en el tablero
        for(int i=0;i<carros.length;i++)
        {
			//*Si la fuente del evento es uno de los carros del vector
            if(e.getSource() == carros[i])
            {
				//*Se igualan los datos del carro, posicion y tamaño con variables locales
                int x = carros[i].getX();
                int y = carros[i].getY();
                int w = carros[i].getWidth();
                int h = carros[i].getHeight();
                //Se crea una variable Point que representa la ubicacion del mouse en el contenedor
                Point mouse= contenedor.getMousePosition();
          
                try{
                   //*Si es horizontal
                    if(vector.get(i).obtenerOrientacion() == 0)
                    {   
						//*Si se da click en cierta pocion y se arrastra hasta que el mouse se halla movido 35 pixeles a la izquierda desde su punto inicial               
                        if((mouse.x - presX)<-35)
                        {
							//*Se Valida con los metodos del controlador si el carro que se quiere mover se puede mover
							//ingresando el carro a mover y la posicion que tendra si es movido
                            if(control.moverPiezaTablero(vector.get(i).obtenerTipo(), arregloY[i], arregloX[i]-1))
                            {   
								//*Si se puede mover, se le resta uno a la posicion X del carro ya que se mueve a la izquierda
                                arregloX[i]-=1;
                                //*Su posicion en X es igual a la posicion que acabamos de mover
                                x = iniX + (arregloX[i]*50);
                                //*Al ser horizontal, su posicion en Y se mantiene
                                y = carros[i].getY();
                                //*Se obtienen las piezas nuevamente con las nuevas coordenada
                                vector = control.obtenerPiezas();
                                presX = contenedor.getMousePosition().x;//
                                // Se resta uno al numero de movimientos en coordenada ya que se mueve hacia la izquierda
                                movimientos--;
                                //Se guardan los datos de la jugada hecha, y se van agregando todos los movimeintos hechos
                                control.guardarUltimoMovimiento();
                                control.guardarTodosMovimientos(movimientos_realizados);
                            }
                        }
                        //*Si se da click en cierta pocion y se arrastra hasta que el mouse se halla movido 35 pixeles a la derecha desde su punto inicial                            
                        if((mouse.x - presX)>35)
                        {
							//*Se Valida con los metodos del controlador si el carro que se quiere mover se puede mover
							//ingresando el carro a mover y la posicion que tendra si es movido
                            if(control.moverPiezaTablero(vector.get(i).obtenerTipo(), arregloY[i], arregloX[i]+1))
                            {   
								//*Si se puede mover, se le suma uno a la posicion X del carro ya que se mueve a la derecha								                                
                                arregloX[i]+=1;
                                //*Su posicion en X es igual a la posicion que acabamos de mover
                                x = iniX + (arregloX[i]*50);
                                //*Al ser horizontal, su posicion en Y se mantiene
                                y = carros[i].getY();
                                //*Se obtienen las piezas nuevamente con las nuevas coordenada
                                vector = control.obtenerPiezas();
                                presX = contenedor.getMousePosition().x;
                                // Se suma uno al numero de movimientos en coordenada ya que se mueve hacia la derecha
                                movimientos++;
                                //Se guardan los datos de la jugada hecha, y se van agregando todos los movimeintos hechos
                                control.guardarUltimoMovimiento();
                                control.guardarTodosMovimientos(movimientos_realizados);
                                
                                //*Ya que el carro rojo siempre se mueve horizontalmente hacia la derecha, se valida si se ha ganado
                                //en este if
                                if(control.partidaGanada())
                                {	
									//*Si el controlador retorna que si se gano la partida, los movimientos totales seran el valor absoluto de los movimiento
									//en coordenada hechos
									movimientos_realizados += Math.abs(movimientos);
									ganarPartida();
								}
                            }
                        }
                    }
                    
                    //*En caso del que e movimiento sea vertical                
                    else
                    { 
						//*Si se da click en cierta pocion y se arrastra hasta que el mouse se halla movido 35 pixeles hacia arriba desde su punto inicial
						if((mouse.y - presY)<-35)
						{
							//*Se Valida con los metodos del controlador si el carro que se quiere mover se puede mover
							//ingresando el carro a mover y la posicion que tendra si es movido
							if(control.moverPiezaTablero(vector.get(i).obtenerTipo(), arregloY[i]-1, arregloX[i]))
                            {   
								//*Si se puede mover, se le resta uno a la posicion Y del carro ya que se mueve hacia arriba
                                arregloY[i]-=1;
                                //*Al ser vertical, su posicion en X se mantiene
                                x = carros[i].getX();
                                //*Su posicion en Y es igual a la posicion que acabamos de mover
                                y = iniY + (arregloY[i]*50);
                                //*Se obtienen las piezas nuevamente con las nuevas coordenada
                                vector = control.obtenerPiezas();
                                presY = contenedor.getMousePosition().y; 
                                // Se resta uno al numero de movimientos en coordenada ya que se mueve hacia arriba
                                movimientos--;
                                //Se guardan los datos de la jugada hecha, y se van agregando todos los movimeintos hechos
                                control.guardarUltimoMovimiento();
                                control.guardarTodosMovimientos(movimientos_realizados);
                            }
                        }
                        
                        //*Si se da click en cierta pocion y se arrastra hasta que el mouse se halla movido 35 pixeles hacia abajo desde su punto inicial
                        if((mouse.y - presY)>35)
                        {           
							//*Se Valida con los metodos del controlador si el carro que se quiere mover se puede mover
							//ingresando el carro a mover y la posicion que tendra si es movido                 
                            if(control.moverPiezaTablero(vector.get(i).obtenerTipo(), arregloY[i]+1, arregloX[i]))
                            {   
								//*Si se puede mover, se le suma uno a la posicion Y del carro ya que se mueve hacia abajo
                                arregloY[i]+=1;
                                //*Al ser vertical, su posicion en X se mantiene
                                x = carros[i].getX();
                                //*Su posicion en Y es igual a la posicion que acabamos de mover
                                y = iniY + (arregloY[i]*50);
                                //*Se obtienen las piezas nuevamente con las nuevas coordenada
                                vector = control.obtenerPiezas();
                                presY = contenedor.getMousePosition().y;
                                // Se suma uno al numero de movimientos en coordenada ya que se mueve hacia abajo
                                movimientos++;
                                //Se guardan los datos de la jugada hecha, y se van agregando todos los movimeintos hechos
                                control.guardarUltimoMovimiento();
                                control.guardarTodosMovimientos(movimientos_realizados);
                            }                                           
                        }
                    }
                    
                    //*Se dibuja el label que representa el carro en la posicion actualizada
                    carros[i].setLocation(x,y); 
                }
                
                catch(Exception exception){}                             
            }
		}
    }
    //*************************************************************************************************
    
   /**
    * Metodo Sobreescrito de la clase implementada MouseMotionListener
    * @param e  MoeseEvent que identifica la fuente de donde se hace el evento
    */
    public void mouseMoved(MouseEvent e) 
    {
		//* Si el el cursor se mueve dentro del panel niveles, el panel se mantendra visible
        if(e.getSource() == panel_niveles ){
			panel_niveles.setVisible(true);
		}
		//*En caso contrario el panel se ocultara
		else
		{
			panel_niveles.setVisible(false);
		}
    }
    //*************************************************************************************************
    
    /**
    * Metodo Sobreescrito de la clase implementada MouseListener
    * @param e  MoeseEvent que identifica la fuente de donde se hace el evento
    */
    public void mouseClicked(MouseEvent e){}
	//*************************************************************************************************
	
    /**
    * Metodo Sobreescrito de la clase implementad MouseListener
    * @param e  MoeseEvent que identifica la fuente de donde se hace el evento
    */
    public void mousePressed(MouseEvent e) 
    {
		//*Retorna la posicion del mouse cuendo se da click, y se igualan las 
		//variable globales a esta posicion
        presX = contenedor.getMousePosition().x;
        presY = contenedor.getMousePosition().y; 
    }
	//*************************************************************************************************
	
    /**
    * Metodo Sobreescrito de la clase implementad MouseListener
    * @param e  MoeseEvent que identifica la fuente de donde se hace el evento
    */
    public void mouseReleased(MouseEvent e) 
    {
	   //* Cada vez que el mouse es soltado se suma el valor absoluto de los movimientos en coordenad
	   //con los movimientos realizados, y se reinicia la variable de los movimientos en coordenada
       movimientos_realizados += Math.abs(movimientos);
       movimientos = 0;
       //*Se ponen el numero de movimientos realizado por el jugador en el label de movimientos
       numero_movimientos.setText(""+movimientos_realizados);
    }
	//*************************************************************************************************
	
    /**
    * Metodo Sobreescrito de la clase implementad MouseListener
    * @param e  MoeseEvent que identifica la fuente de donde se hace el evento
    */
    public void mouseEntered(MouseEvent e){}
	//*************************************************************************************************
	
    /**
    * Metodo Sobreescrito de la clase implementad MouseListener
    * @param e  MoeseEvent que identifica la fuente de donde se hace el evento
    */
    public void mouseExited(MouseEvent e){}
    //*************************************************************************************************
    
    /**
    * Metodo Sobreescrito de la clase implementad ActionListener
    * @param e  ActionEvent que identifica la fuente de donde se hace el evento
    */  
	public void actionPerformed(ActionEvent e) 
	{
		//*Si la fuente del evento es el boton_facil
		if(e.getSource()==boton_facil)
		{
			//Se cambia la dificultad seleccionada a Facil
			dificultad_seleccionada = "Facil";
			//Se cambia el fondo del panel niveles al fondo de la dificultad facil				
			fondo_niveles.setIcon(new ImageIcon("Images/Fondos/fondoeasy.png"));
			//Se hace visible el panel
			panel_niveles.setVisible(true);	
			//Se pinta denuevo el contenedor
			contenedor.repaint();
		}
		
		//*Si la fuente del evento es el boton_medio
		else if(e.getSource()==boton_medio)
		{
			//Se cambia la dificultad seleccionada a Medio
			dificultad_seleccionada = "Medio";	
			//Se cambia el fondo del panel niveles al fondo de la dificultad Medio
			fondo_niveles.setIcon(new ImageIcon("Images/Fondos/fondomedium.png"));
			//Se hace visible el panel
			panel_niveles.setVisible(true);
			//Se pinta denuevo el contenedor	
			contenedor.repaint();
		}
		
		//*Si la fuente del evento es el boton_dificil
		else if(e.getSource()==boton_dificil)
		{
			//Se cambia la dificultad seleccionada a Dificil
			dificultad_seleccionada = "Dificil";
			//Se cambia el fondo del panel niveles al fondo de la dificultad Dificil
			fondo_niveles.setIcon(new ImageIcon("Images/Fondos/fondohard.png"));
			//Se hace visible el panel
			panel_niveles.setVisible(true);	
			//Se pinta denuevo el contenedor
			contenedor.repaint();
		}
		
		//*Si la fuente del evento es el boton_experto
		else if(e.getSource()==boton_experto)
		{
			//Se cambia la dificultad seleccionada a Experto
			dificultad_seleccionada = "Experto";
			//Se cambia el fondo del panel niveles al fondo de la dificultad Experto
			fondo_niveles.setIcon(new ImageIcon("Images/Fondos/fondoexpert.png"));
			//Se hace visible el panel
			panel_niveles.setVisible(true);
			//Se pinta denuevo el contenedor	
			contenedor.repaint();
		}
		
		//*Si la fuente del evento es el boton_intentar_denuevo
		else if(e.getSource()==boton_intentar_denuevo)
		{
			// Se detiene la musica que sono al ganar
			//objManejadorMusica.detener();
			// Se remueven todos los elementos del contenedor
			contenedor.removeAll(); 
			//Se organiza nuevamente el tablero con la organizacion del tablero hecho anteriormente 
			configurarPanelJuego(dificultad_tablero,nivel_tablero); 
			//se anade el panel juego al contenedor
			contenedor.add(panel_juego);
			contenedor.doLayout();//
			//Se pinta de nuevo el contenedor
			contenedor.repaint();
		}
		
		//*Si la fuente del evento es el boton_siguiente_reto
		else if(e.getSource()==boton_siguiente_reto)
		{
			// Se detiene la musica que sono al ganar
			//objManejadorMusica.detener();
			// Se remueven todos los elementos del contenedor
			contenedor.removeAll();
			//Se organiza nuevamente el tablero con la organizacion del tablero siguiente 
			cambiarSiguienteTablero();
			configurarPanelJuego(dificultad_tablero,nivel_tablero);
			//se anade el panel juego al contenedor
			contenedor.add(panel_juego);
			contenedor.doLayout();//
			//Se pinta de nuevo el contenedor
			contenedor.repaint();
		}
		
		//*Si la fuente del evento es el boton_reiniciar		
		else if(e.getSource() == boton_reiniciar)
		{
			// Se remueven todos los elementos del contenedor
			contenedor.removeAll();
			// Se reinician los movimientos hechos
			movimientos_realizados = 0;
			movimientos = 0;
			//Se organiza nuevamente el tablero con la organizacion incial del tablero reiniciado 
			configurarPanelJuego(dificultad_tablero,nivel_tablero);
			//se anade el panel juego al contenedor
			contenedor.add(panel_juego);
			contenedor.doLayout();//
			//Se pinta de nuevo el contenedor
			contenedor.repaint();
		}
		
		//*Si la fuente del evento es el boton_acercade
		else if(e.getSource() == boton_acercade)
		{
			//Se hace visible el panel con la informacion de acercaDe
			panel_acercade.setVisible(true);	
			contenedor.repaint();
		}
		
		//*Si la fuente del evento es el acercade_cerrar
		else if(e.getSource() == acercade_cerrar)
		{
			//Se oculta el panel del acercaDe.
			panel_acercade.setVisible(false);	
			contenedor.repaint();
		}
		
		else
		{
			//*Se hace un for que recorre el vector de botonoes que elige el nivel del tablero
			for(int i=0;i<25;i++)
			{
				//*Si la fuente del evento es un boton del vector botones_niveles
				if(e.getSource()==botones_niveles[i])
				{
					//La dificultad sdel tablero se cambia por la dificultad que se selecciono
					dificultad_tablero=dificultad_seleccionada;
					//Se reinician los contadores de movimientos
					movimientos_realizados = 0;
					movimientos = 0;
					// Se remueven todos los elementos del contenedor
					contenedor.removeAll();
					//Se organiza nuevamente el tablero con la organizacion del tablero elegido 
					configurarPanelJuego(dificultad_tablero,i+1);
					//Se oculta el panel de niveles
					panel_niveles.setVisible(false);
					//se anade el panel juego al contenedor
					contenedor.add(panel_juego);
					contenedor.doLayout();//
					//Se pinta de nuevo el contenedor
					contenedor.repaint();
				}
			}
		}
	}
    //*************************************************************************************************
        
    public static void main(String [] args)
    {
		JuegoGUI ventana = new JuegoGUI();
    }
    //************************************************************************************************* 
}
