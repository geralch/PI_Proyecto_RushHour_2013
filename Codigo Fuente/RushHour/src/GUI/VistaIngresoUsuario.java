package GUI;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** 
 * La Clase VistaIngresoUsuario es usada para mostrar una ventana de ingreso de usuarios al juego.
 * @author Caicedo Hidalgo Geraldine 0939713
 * @author Hern&aacute;ndez Abahonza Diana Carolina 1031083
 * @author Pab&oacute;n Ruiz Jorge Eduardo 1325856
 * @see <a href="http://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html">JFrame</a>
 * @see <a href="http://docs.oracle.com/javase/7/docs/api/java/awt/event/ActionListener.html">ActionListener</a>
 **/
public class VistaIngresoUsuario extends JDialog implements ActionListener
{
	//Parámetros
	/*! Objeto de la clase Container*/
	private Container contenedor;						//* Contenedor de la ventana
	/*!JLabel con el texto nick usauario */
	private JLabel lbNickUsuario; 						//* JLabel con el texto nick usuario
	/*!JLabel con el texto password */
	private JLabel lbPassword;							// JLabel con el texto password
	/*!JTextField donde se escribe el nombre der usuario */
	private JTextField tfNickUsuario;					//* Variable donde se escribe el nombre de usuario que va a jugar
	/*!JPasswordField donde se escribe la contraseña del usuario */
	private JPasswordField tfPassword;					//* Variable donde se escribe la contraseña de usuario que va a jugar
	/*! Button que para loggearse e iniciar el juego*/
	private JButton bIniciarJuego; 						//* Botón para el proceso de verificación de usuario
	/*! Button para registra un nuevo usuario*/
	private JButton bRegistrarUsuario;					//* Botón para el proceso de registro de nuevos usuarios
	/*! Objeto de la clase VistaRegistroUsuario*/
	protected VistaRegistroUsuario vistaRegistro;		//* Ventana de registro de nuevos usuarios
	/*! String con el nick del usuario*/
	private String nickUsuario; 						//* Variable que guarda el nombre de usuario que desea jugar
	/*! String con la contrasena del usuario*/
	private String password;							//* Variable que guarda la contraseña del usuario que desea jugar
	/*! Vector de String con los datos de ingreso*/
	private Vector<String> datosIngreso;				//* Variable que guarda los datos del usuario que desea jugar
	/*! Vector de String con los datos de registro*/
	private Vector<String> datosRegistro;				//* Variable que guarda los datos del usuario que se desea registrar
	/*! Boolean que indica si se ha pedido un nuevo usario*/
	private boolean nuevoUsuario;						//* Variable auxiliar que indica si se ha pedido el registro de un nuevo usuario
	
	//*************************************************************************************************
	
	/**
	 * Constructor con inicialización de atricutos de la clase. 
	 * @param padre JFrame que representa la ventana padre
	 * @param modal representa si la ventana se pone modal o no
	 * */
	protected VistaIngresoUsuario(JFrame padre, boolean modal)
	{
		super(padre, modal); //* indica que el presente JDialog es modal y que tiene como ventana padre el primer parámetro
		
		contenedor = getContentPane();
		contenedor.setLayout(new BorderLayout()); // eC, eF
		
		datosIngreso = new Vector<String>(1);
		datosRegistro = new Vector<String>(1);
		nuevoUsuario=false;
		
		ejecutar();//* propiedades generales para la presente ventana (JDialog)
	}
	//*************************************************************************************************
	
	/**
	 * Inicializa los componentes de la clase
	 */
	private void inicializarComponentes()
    {
		nickUsuario="";
		password="";
		
		lbNickUsuario   = new JLabel("User Name:");
		lbPassword      = new JLabel("Password:");
		
		tfNickUsuario   = new JTextField(10);
		tfPassword      = new JPasswordField();
		
		bIniciarJuego = new JButton("Sign In");
		bIniciarJuego.addActionListener(this); //* Escucha del botón bIniciarJuego
		bRegistrarUsuario = new JButton("Sign Up");
		bRegistrarUsuario.addActionListener(this); //* Escucha del botón bIniciarJuego
    }
    //*************************************************************************************************
	
	/**
	 * Organiza los componentes de la clase en la ventana
	 */
    private void organizarComponentes()
    {
		JPanel pDatosUsuario = new JPanel(new GridLayout(2,2,0,0)); //* Panel que organiza los componentes gráficos relacionados con los datos necesarios para el ingreso al juego
			pDatosUsuario.add(lbNickUsuario); 
			pDatosUsuario.add(tfNickUsuario);
			pDatosUsuario.add(lbPassword); 
			pDatosUsuario.add(tfPassword);
		pDatosUsuario.setBorder(BorderFactory.createEmptyBorder(25,15,20,15));//n,w,s,e
		
		JPanel pBotones = new JPanel(new FlowLayout()); //* Panel que organiza los botones relacionados con el ingreso al juego y el registro de nuevos usuarios
		pBotones.add(bIniciarJuego); pBotones.add(bRegistrarUsuario);
		
		JPanel pGeneral = new JPanel(new BorderLayout());
		pGeneral.add(pDatosUsuario, BorderLayout.CENTER); 
		pGeneral.add(pBotones, BorderLayout.SOUTH);
		
        contenedor.add(pGeneral, BorderLayout.CENTER);
    }
    //*************************************************************************************************
	
	/**
	 * Ejecuta la ventana del login de usuario
	 */
    private void ejecutar()
	{
		setTitle("Login");
		inicializarComponentes(); //* inicializa los componentes gráficos de la ventana
		organizarComponentes(); //* organiza los componentes gráficos de la ventana
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //* indica que se deshabilita la opción de cerrar directamente la ventana con el botón de la barra de título
        setSize(300, 150); //* H, V
        setResizable(false); //* indica que la ventana no cambia de tamaño
        setVisible(true); //* inicia la visibilidad de la ventana
	}
	//*************************************************************************************************
	
	/**
	 * Limpia los campos a llenar
	 */
	private void limpiarCampos()
	{
		tfNickUsuario.setText("");
		tfPassword.setText("");
	}
	//*************************************************************************************************
	
	/**
	 * Guarda los datos escrito en los campos a llenar
	 */
	private void capturarDatosIngreso()
	{
		nickUsuario = tfNickUsuario.getText(); //* Variable que guarda el nombre de usuario digitado
		password    = new String( tfPassword.getPassword() ); //* Variable que guarda la contraseña de usuario digitado
		
		datosIngreso.addElement(nickUsuario); //* agrega el nombre de usuario al vector de datos de usuario que desea jugar
		datosIngreso.addElement(password); //* agrega la contraseña de usuario al vector de datos de usuario que desea jugar
	}
	//*************************************************************************************************
	
	/**
	 * Retorna los datos de usuario ingresado
	 * @return vector de string con los datos de usuario
	 */
	public Vector<String> obtenerDatosIngreso()
	{
		return datosIngreso;
	}
	//*************************************************************************************************
	
	/**
	 * Retorna los datos de usuario de un nuevo usario registrado
	 * @return vector de string con los datos de registro del nuevo usuario
	 */
	public Vector<String> obtenerDatosRegistro()
	{
		return datosRegistro;
	}
	//*************************************************************************************************
	
	/**
	 * Retorna si se va a ingresar o nuevo usuario o no
	 * @return true o false si se va a ingresar un nuevo usuario
	 */
	public boolean obtenerNuevoUsuario()
	{
		return nuevoUsuario;
	}
	//*************************************************************************************************
	
	/**
	 * Inicia el juego tras haberse logeado correctamente
	 */
	private void iniciarJuego()
	{
		capturarDatosIngreso();
		limpiarCampos();
		nuevoUsuario=false;
		dispose();
	}
	//*************************************************************************************************
	
	/**
	 * Inicia la ventana de registro si se desea registrar un nuevo usuario
	 */
	private void iniciarRegistro()
	{
		limpiarCampos();
		vistaRegistro = new VistaRegistroUsuario(this, true);
		datosRegistro=vistaRegistro.obtenerDatosRegistro();
		nuevoUsuario=true; //* Se ha pedido el registro de un nuevo usuario
		dispose();
	}
	//*************************************************************************************************
	
	/**
    * Metodo Sobreescrito de la clase implementad ActionListener
    * @param e  ActionEvent que identifica la fuente de donde se hace el evento
    */ 
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==bIniciarJuego)
		{
			iniciarJuego();
		}
		
		if(e.getSource()==bRegistrarUsuario)
		{
			iniciarRegistro();
		}
	}
	//*************************************************************************************************
}

