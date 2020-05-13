package controlador;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//se importa modelo e interfaz
import modelo.modelo;
import vista.Jugadores;
import vista.interfaz;
/**
 * @author Mouse
 */
public class controladorJugador implements ActionListener,MouseListener{

    /** instancia a nuestra interfaz de usuario*/
    Jugadores vista ;
    /** instancia a nuestro modelo */
    modelo modelo = new modelo();

    /** Se declaran en un ENUM las acciones que se realizan desde la
     * interfaz de usuario VISTA y posterior ejecución desde el controlador
     */
    public enum AccionMVC
    {
        aniadirJugador,
        modJugador,
        nuevoFichaje,
        volver,
        verJugadores
    }

    /** Constrcutor de clase
     * @param vista Instancia de clase interfaz
     */
    public controladorJugador( Jugadores vista )
    {
        this.vista = vista;
    }

    /** Inicia el skin y las diferentes variables que se utilizan */
    public void iniciar()
    {
        // Skin tipo WINDOWS
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}

        //declara una acción y añade un escucha al evento producido por el componente
        
        this.vista.aniadirJugador.setActionCommand( "aniadirJugador" );
        this.vista.aniadirJugador.addActionListener(this);
        this.vista.modJugador.setActionCommand( "modJugador" );
        this.vista.modJugador.addActionListener(this);
        this.vista.nuevoFichaje.setActionCommand( "nuevoFichaje" );
        this.vista.nuevoFichaje.addActionListener(this);
        this.vista.volver.setActionCommand( "volver" );
        this.vista.volver.addActionListener(this);
        this.vista.verJugadores.setActionCommand( "verJugadores" );
        this.vista.verJugadores.addActionListener(this);
        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.tablaJugadores.addMouseListener(this);
        this.vista.tablaJugadores.setModel( new DefaultTableModel() );
    }

    //Eventos que suceden por el mouse
    public void mouseClicked(MouseEvent e) {
        if( e.getButton()== 1)//boton izquierdo
        {
             int fila = this.vista.tablaJugadores.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.txtNif.setText( String.valueOf( this.vista.tablaJugadores.getValueAt(fila, 0) ));
                this.vista.txtNombre.setText( String.valueOf( this.vista.tablaJugadores.getValueAt(fila, 1) ));
                this.vista.txtApellido.setText( String.valueOf( this.vista.tablaJugadores.getValueAt(fila, 2) ));
                this.vista.txtNacionalidad.setText( String.valueOf( this.vista.tablaJugadores.getValueAt(fila, 3) ));
                this.vista.txtAnio.setText( String.valueOf( this.vista.tablaJugadores.getValueAt(fila, 4) ));
             }
        }
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) { }
 
    //Control de eventos de los controles que tienen definido un "ActionCommand"
    public void actionPerformed(ActionEvent e) {

    switch ( AccionMVC.valueOf( e.getActionCommand() ) )
        {
            case aniadirJugador:
               if (this.modelo.Janiadirjugador(
                    this.vista.txtNif.getText() ,
                    this.vista.txtNombre.getText(),
                    this.vista.txtApellido.getText(),
                    this.vista.txtNacionalidad.getText(),
                    Integer.parseInt(this.vista.txtAnio.getText()) ))
                    this.vista.tablaJugadores.setModel( this.modelo.getJugadores() );
                JOptionPane.showMessageDialog(vista,"Exito: Nuevo registro agregado.");
                this.vista.txtNif.setText("");
                this.vista.txtNombre.setText("") ;
                this.vista.txtApellido.setText("");
                this.vista.txtNacionalidad.setText("") ;
                this.vista.txtAnio.setText("") ;
                break;
            case modJugador:
               if (this.modelo.Jmodjugador(
                    this.vista.txtNif.getText() ,
                    this.vista.txtNombre.getText(),
                    this.vista.txtApellido.getText(),
                    this.vista.txtNacionalidad.getText(),
                    this.vista.txtAnio.getText() ))
                this.vista.tablaJugadores.setModel( this.modelo.getJugadores() );
                this.vista.txtNif.setText("");
                this.vista.txtNombre.setText("") ;
                this.vista.txtApellido.setText("");
                this.vista.txtNacionalidad.setText("") ;
                this.vista.txtAnio.setText("") ;
                break;
            case verJugadores:
                this.vista.tablaJugadores.setModel( this.modelo.getJugadores() );
                break;
            case nuevoFichaje:
                if (this.modelo.Jnuevofichaje(
                    this.vista.txtNifichaje.getText() ,
                    this.vista.txtNombrefichaje.getText(),
                    this.vista.txtTemporada.getText() ));
                JOptionPane.showMessageDialog(vista,"Exito: Nuevo Fichaje creado.");
                this.vista.txtNifichaje.setText("");
                this.vista.txtNombrefichaje.setText("") ;
                this.vista.txtTemporada.setText("");
                break;
            case volver:
                 this.vista.dispose();
                
                break;
                  
        }
    }
}