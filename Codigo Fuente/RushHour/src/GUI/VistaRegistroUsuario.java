package GUI;

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;

/** 
 * La Clase VistaRegistroUsuario es usada en caso de que un usuario no este en la base de datos y se desea registrar, en tal
 * caso se muestra esta ventana.
 * @author Caicedo Hidalgo Geraldine 0939713
 * @author Hern&aacute;ndez Abahonza Diana Carolina 1031083
 * @author Pab&oacute;n Ruiz Jorge Eduardo 1325856
 * @see <a href="http://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html">JFrame</a>
 * @see <a href="http://docs.oracle.com/javase/7/docs/api/java/awt/event/ActionListener.html">ActionListener</a>
 **/
public class VistaRegistroUsuario extends JDialog implements ActionListener
{
	//Parámetros
	/*! Objeto de la clase Container*/
	private Container contenedor;							//* Contenedor de la ventana
	/*!JLabel con el texto nombre de usuario */
	private JLabel lbNombreUsuario;							//* JLabel con el texto nombres de usuario
	/*!JLabel con el texto nick usauario */
	private JLabel lbNickUsuario;							//* JLabel con el texto nick usuario
	/*!JLabel con el texto password */
	private JLabel lbPassword;								// JLabel con el texto password
	/*!JLabel con la imagen de avatar */
	private JLabel lbAvatar;								// JLabel con la imagen del avatar
	/*!JTextField donde se escribe el nombre del usuario */
	private JTextField tfNombreUsuario;						//* Variable donde se escribe el nombre real del usuario que se va a registrar
	/*!JTextField donde se escribe el nick del usuario */
	private JTextField tfNickUsuario;						//* Variable donde se escribe el nombre de usuario que se va a registrar
	/*!JPasswordField donde se escribe la contraseña del usuario */
	private JPasswordField tfPassword;						//* Variable donde se escribe la contraseña del usuario que se va a registrar
	/*! Button que para Elegir un Avatar*/
	private JButton bElegirAvatar; 							//* Botón para el proceso de elección de avatar para el nuevo usuario	
	/*! Button que para guardar un usuario*/
	private JButton bGuardarUsuario;						//* Botón para el proceso de registro de nuevos usuarios
	/*! JFileChooser para elegir el avatar */
	private JFileChooser jfcAvatar;							//* ventana para la selección del fichero correspondiente a la imagen del avatar para cada usuario.
	/*! Icon con la imagen de avatar*/
	private Icon iAvatar;									// Imagen del avatar
	/*! String con el nombre del usuario*/
	private String nombreUsuario; 							//* Variable que guarda el nombre real del usuario que se desea registrar
	/*! String con el nick del usuario*/
	private String nickUsuario; 							//* Variable que guarda el nombre de usuario que se desea registrar
	/*! String con la contrasena del usuario*/
	private String password;								//* Variable que guarda la contraseña del usuario que se desea registrar
	/*! Vector de String con los datos de registro*/
	private Vector<String> datosRegistro;					//* Variable que guarda los datos del usuario que se desea registrar
	
	//*************************************************************************************************
	
	/**
	 * Constructor con inicialización de atricutos de la clase. 
	 * @param padre JDialog que representa la ventana padre
	 * @param modal representa si la ventana se pone modal o no
	 * */
	protected VistaRegistroUsuario(JDialog padre, boolean modal)
	{
		super(padre, modal); //* indica que el presente JDialog es modal y que tiene como ventana padre el primer parámetro
		contenedor = getContentPane();
		contenedor.setLayout(new BorderLayout());
		
		datosRegistro = new Vector<String>(1);
		
		ejecutar(); //* propiedades generales para la presente ventana (JDialog)
	}
	//*************************************************************************************************
	
	/**
	 * Inicializa los componentes de la clase
	 */
	private void inicializarComponentes()
    {
		nombreUsuario="";
		nickUsuario="";
		password="";
		
		iAvatar   = new ImageIcon("Images/Avatares/avatar05.jpg");
		jfcAvatar = new JFileChooser("Images/Avatares/");
		
		lbNombreUsuario = new JLabel("Name:");
		lbNickUsuario   = new JLabel("User Name:");
		lbPassword      = new JLabel("Password:");
		lbAvatar        = new JLabel(iAvatar);
		
		tfNombreUsuario = new JTextField(8);
		tfNickUsuario   = new JTextField(8);
		tfPassword      = new JPasswordField();
		
		bElegirAvatar = new JButton("Choose Avatar");
		bElegirAvatar.addActionListener(this); //* Escucha del botón bElegirAvatar
		bGuardarUsuario = new JButton("Save");
		bGuardarUsuario.addActionListener(this); //* Escucha del botón bGuardarUsuario
    }
    //*************************************************************************************************
	
	/**
	 * Organiza los componentes de la clase en la ventana
	 */
    private void organizarComponentes()
    {
		JPanel pBotonAvatar = new JPanel(new FlowLayout());
		pBotonAvatar.add(bElegirAvatar);
		
		JPanel pIzquierdo = new JPanel(new BorderLayout()); //* Panel que organiza los componentes gráficos relacionados con la elección del avatar
		pIzquierdo.add(lbAvatar, BorderLayout.CENTER); 
		pIzquierdo.add(pBotonAvatar, BorderLayout.SOUTH);
		//*******************************
		JPanel pDatosUsuario = new JPanel(new GridLayout(3,2));
			pDatosUsuario.add(lbNombreUsuario); 
			pDatosUsuario.add(tfNombreUsuario);
			pDatosUsuario.add(lbNickUsuario); 
			pDatosUsuario.add(tfNickUsuario);
			pDatosUsuario.add(lbPassword); 
			pDatosUsuario.add(tfPassword);
		pDatosUsuario.setBorder(BorderFactory.createEmptyBorder(25,15,20,15));//* n,w,s,e
		
		JPanel pBotonGuardar = new JPanel(new FlowLayout());
		pBotonGuardar.add(bGuardarUsuario);
		
		JPanel pDerecho = new JPanel(new BorderLayout()); //* Panel que organiza los componentes gráficos relacionados con los datos necesarios para el ingreso al juego
		pDerecho.add(pDatosUsuario, BorderLayout.CENTER); 
		pDerecho.add(pBotonGuardar, BorderLayout.SOUTH);
		//*******************************
		
		contenedor.add(pIzquierdo, BorderLayout.WEST);
        contenedor.add(pDerecho, BorderLayout.EAST);
    }
    //*************************************************************************************************
	
	/**
	 * Ejecuta la ventana del login de usuario
	 */
    private void ejecutar()
	{
		setTitle("Register");
		inicializarComponentes(); //* inicializa los componentes gráficos de la ventana
		organizarComponentes(); //* organiza los componentes gráficos de la ventana
		setLocationRelativeTo(this);
        setSize(340, 200); //* H, V
        setResizable(false); //* indica que la ventana no cambia de tamaño
        setVisible(true); //* inicia la visibilidad de la ventana
	}
	//*************************************************************************************************
	
	/**
	 * Limpia los campos a llenar
	 */
	private void limpiarCampos()
	{
		tfNombreUsuario.setText("");
		tfNickUsuario.setText("");
		tfPassword.setText("");
	}
	//*************************************************************************************************
	
	/**
	 * Muestra una ventana auxiliar (predeterminada) para la selección del fichero (imagen) ue eligen los nuevos usuarios en su registro
	 */
	private void elegirAvatar()
	{
		jfcAvatar.showOpenDialog(this);
		File fileAvatar=jfcAvatar.getSelectedFile();
		
		String avatar="";
		
		if(fileAvatar!=null)
		{
			avatar=jfcAvatar.getSelectedFile().getName();
			lbAvatar.setIcon(new ImageIcon("Images/Avatares/"+avatar));
		}
	}
	//*************************************************************************************************
	
	/**
	 * Guarda los datos escrito en los campos a llenar para el nuevo registro del usuario
	 */
	private void capturarDatosRegistro()
	{
		nombreUsuario = tfNombreUsuario.getText(); //* Variable que guarda el nombre real del usuario que se desea registrar
		nickUsuario = tfNickUsuario.getText(); //* Variable que guarda el nombre de usuario que se desea registrar
		password    = new String( tfPassword.getPassword() ); //* Variable que guarda la contraseña del usuario que se desea registrar
		
		datosRegistro.addElement(nombreUsuario); //* agrega el nombre real del usuario al vector de datos de usuario que se desea registrar
		datosRegistro.addElement(nickUsuario); //* agrega el nombre de usuario al vector de datos de usuario que se desea registrar
		datosRegistro.addElement(password); //* agrega la contraseña de usuario al vector de datos de usuario que se desea registrar
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
	 * Registra el usuario tras haber escrito correctamente en todos los campos
	 */
	private void registrarUsuario()
	{
		if(!(tfNombreUsuario.getText().equals(" ")) &&
		   !(tfNickUsuario.getText().equals(" ")) &&
		   !(tfPassword.getPassword().toString().equals(" ")))
		{//* si todos los campos están llenos
			capturarDatosRegistro();
		    limpiarCampos();
		    lbAvatar.setIcon(new ImageIcon("Images/Avatares/avatar05.jpg"));
		    dispose();
		}//* si faltan campos por llenar
		else
		{
		    limpiarCampos();
		    lbAvatar.setIcon(new ImageIcon("Images/Avatares/avatar05.jpg"));
		}
	}
	//*************************************************************************************************
	
	/**
    * Metodo Sobreescrito de la clase implementad ActionListener
    * @param e  ActionEvent que identifica la fuente de donde se hace el evento
    */ 
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==bElegirAvatar)
		{
			elegirAvatar();
		}
		
		if(e.getSource()==bGuardarUsuario)
		{
			registrarUsuario();
		}
	}
	//*************************************************************************************************
}
