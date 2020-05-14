package controlador;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

//se importa modelo e interfaz
import modelo.modelo;
import vista.Equipos;
import vista.interfaz;
/**
 * @author Mouse
 */
public class controladorEquipos implements ActionListener,MouseListener{

    /** instancia a nuestra interfaz de usuario*/
    Equipos vista ;
    /** instancia a nuestro modelo */
    modelo modelo = new modelo();

    /** Se declaran en un ENUM las acciones que se realizan desde la
     * interfaz de usuario VISTA y posterior ejecución desde el controlador
     */
    public enum AccionMVC
    {
        aniadirEquipo,
        modEquipo,
        volver,
        verEquipos
    }

    /** Constrcutor de clase
     * @param vista Instancia de clase interfaz
     */
    public controladorEquipos( Equipos vista )
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
        
        this.vista.aniadirEquipo.setActionCommand( "aniadirEquipo" );
        this.vista.aniadirEquipo.addActionListener(this);
        this.vista.modEquipo.setActionCommand( "modEquipo" );
        this.vista.modEquipo.addActionListener(this);
        this.vista.volver.setActionCommand( "volver" );
        this.vista.volver.addActionListener(this);
        this.vista.verEquipos.setActionCommand( "verEquipos" );
        this.vista.verEquipos.addActionListener(this);
        //añade e inicia el jtable con un DefaultTableModel vacio
        this.vista.tablaEquipo.addMouseListener(this);
        this.vista.tablaEquipo.setModel( new DefaultTableModel() );
    }

    //Eventos que suceden por el mouse
    public void mouseClicked(MouseEvent e) {
        if( e.getButton()== 1)//boton izquierdo
        {
             int fila = this.vista.tablaEquipo.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.txtNombre.setText( String.valueOf( this.vista.tablaEquipo.getValueAt(fila, 0) ));
                this.vista.txtAnio.setText( String.valueOf( this.vista.tablaEquipo.getValueAt(fila, 1) ));
                this.vista.txtEstadio.setText( String.valueOf( this.vista.tablaEquipo.getValueAt(fila, 2) ));
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
            case aniadirEquipo:
               if (!validardatosEquipos()){
                   if (this.modelo.Jnuevoequipo(
                    this.vista.txtNombre.getText() ,
                    Integer.parseInt(this.vista.txtAnio.getText()),
                    this.vista.txtEstadio.getText() ));
                this.vista.tablaEquipo.setModel( this.modelo.getEquipos() );
                JOptionPane.showMessageDialog(vista,"Exito: Nuevo Equipo añadido.");
                this.vista.txtNombre.setText("");
                this.vista.txtAnio.setText("") ;
                this.vista.txtEstadio.setText("");
               } 
                break;
            case modEquipo:
                if (this.modelo.Jmodequipo(
                    this.vista.txtNombre.getText() ,
                    this.vista.txtAnio.getText(),
                    this.vista.txtEstadio.getText() ));
                this.vista.tablaEquipo.setModel( this.modelo.getEquipos() );
                this.vista.txtNombre.setText("");
                this.vista.txtAnio.setText("") ;
                this.vista.txtEstadio.setText("");
                break;
            case verEquipos:
                this.vista.tablaEquipo.setModel( this.modelo.getEquipos() );
                break;
            case volver:
                 this.vista.dispose();
                
                break;
                  
        }
    }
    
    public boolean validardatosEquipos(){
        return this.vista.txtNombre.getText().length() == 0  ||
                this.vista.txtAnio.getText().length() == 0  ||
                this.vista.txtEstadio.getText().length() == 0;        
    }
}