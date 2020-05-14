package controlador;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.table.DefaultTableModel;
//se importa modelo e interfaz
import modelo.modelo;
import vista.interfaz;
import vista.Jugadores;
import vista.Equipos;
/**
 * @author Mouse
 */
public class controlador implements ActionListener,MouseListener{

    /** instancia a nuestra interfaz de usuario*/
    interfaz vista ;
    /** instancia a nuestro modelo */
    modelo modelo = new modelo();

    /** Se declaran en un ENUM las acciones que se realizan desde la
     * interfaz de usuario VISTA y posterior ejecución desde el controlador
     */
    public enum AccionMVC
    {
        Jjugadores,
        Jequipos,
        mostrarJugadores,
        mostrarEquipos,
        botonSalir,
        mostrarJugadoresde,
        mostrarEquipode
    }

    /** Constrcutor de clase
     * @param vista Instancia de clase interfaz
     */
    public controlador( interfaz vista )
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
        this.vista.Jjugadores.setActionCommand( "Jjugadores" );
        this.vista.Jjugadores.addActionListener(this);
        this.vista.Jequipo.setActionCommand( "Jequipos" );
        this.vista.Jequipo.addActionListener(this);
        this.vista.mostrarJugadores.setActionCommand( "mostrarJugadores" );
        this.vista.mostrarJugadores.addActionListener(this);
        this.vista.mostrarEquipos.setActionCommand( "mostrarEquipos" );
        this.vista.mostrarEquipos.addActionListener(this);
        this.vista.botonSalir.setActionCommand( "botonSalir" );
        this.vista.botonSalir.addActionListener(this);
        this.vista.mostrarJugadoresde.setActionCommand( "mostrarJugadoresde" );
        this.vista.mostrarJugadoresde.addActionListener(this);
        this.vista.mostrarEquipode.setActionCommand( "mostrarEquipode" );
        this.vista.mostrarEquipode.addActionListener(this);
        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.tablaBusquedas.addMouseListener(this);
        this.vista.tablaBusquedas.setModel( new DefaultTableModel() );
    }

    //Eventos que suceden por el mouse
    public void mouseClicked(MouseEvent e) {
        if( e.getButton()== 1)//boton izquierdo
        {
             int fila = this.vista.tablaBusquedas.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.buscarEquipode.setText( String.valueOf( this.vista.tablaBusquedas.getValueAt(fila, 0) ));
                
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
            case Jjugadores:
                new controladorJugador( new Jugadores() ).iniciar() ;
                break;
            case Jequipos:
                new controladorEquipos( new Equipos() ).iniciar() ;
                break;
            case mostrarJugadores:   
                this.vista.tablaBusquedas.setModel( this.modelo.getJugadores() ); 
                break;    
            case mostrarEquipos:
                this.vista.tablaBusquedas.setModel( this.modelo.getEquipos() );    
                break;
            case mostrarJugadoresde:
                this.vista.tablaBusquedas.setModel( this.modelo.getJugadoresDe(this.vista.buscarJugadoresde.getText()));
                this.vista.buscarJugadoresde.setText("");
                break;
            case mostrarEquipode:
                this.vista.tablaBusquedas.setModel( this.modelo.getEquipoDe(this.vista.buscarEquipode.getText()));
                this.vista.buscarEquipode.setText("");
                break;    
            case botonSalir:
                this.vista.dispose();
                break;
                
                
                  
        }
    }

}
