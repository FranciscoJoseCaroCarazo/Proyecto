package modelo;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class modelo extends database{

    /** Constructor de clase */
    public modelo (){}

    /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public DefaultTableModel getJugadores()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"ID","Nombre","Apellidos","Nacionalidad","Año Nacimiento"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM Jugadores");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][5];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM Jugadores");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "nif" );
                data[i][1] = res.getString( "nombre" );
                data[i][2] = res.getString( "apellidos" );
                data[i][3] = res.getString( "nacionalidad" );
                data[i][4] = res.getString( "anio_nacimiento" );
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
    
    public DefaultTableModel getEquipos()
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"Nombre del Equipo","Año de creación","Nombre del Estadio"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM Equipos");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][5];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM Equipos");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "nombre" );
                data[i][1] = res.getString( "anio_creacion" );
                data[i][2] = res.getString( "Nombre_estadio" );
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
    
     
    /** Registra un nuevo producto */

    public boolean Janiadirjugador(String id, String nombre , String apellido, String nacionalidad, int fecha)
    {
            //Se arma la consulta
            String q=" INSERT INTO Jugadores ( nif , nombre , apellidos, nacionalidad , anio_nacimiento  ) "
                    + "VALUES ( '" + id + "','" + nombre + "','" + apellido + "','"+ nacionalidad +"','" + fecha + "' )";
            //se ejecuta la consulta
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
        }
    
    public boolean Jnuevofichaje(String id, String nombre , String temporada)
    {
            //Se arma la consulta
            String q=" INSERT INTO Fichajes ( nif , nombre , temporada) "
                    + "VALUES ( '" + id + "','" + nombre + "','" + temporada + "' )";
            //se ejecuta la consulta
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
        }
    
    public boolean Jnuevoequipo(String nombre, int anio , String estadio)
    {
            //Se arma la consulta
            String q=" INSERT INTO Equipos ( nombre , anio_creacion , Nombre_estadio) "
                    + "VALUES ( '" + nombre + "','" + anio + "','" + estadio + "' )";
            //se ejecuta la consulta
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
        }
    
    public boolean Jmodjugador(String id, String nombre , String apellido, String nacionalidad, String fecha)
    {
            //Se arma la consulta
            String q="UPDATE Jugadores SET  nombre = '" +nombre+ "' , apellidos = '" +apellido+ "' , nacionalidad = '" +nacionalidad+ "' , anio_nacimiento = '" +fecha+ "' WHERE  nif = '"+id+"'";
            //se ejecuta la consulta
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
        }
    
    public boolean Jmodequipo(String nombre, String anio , String estadio)
    {
            //Se arma la consulta
            String q="UPDATE Equipos SET  nombre = '" +nombre+ "' , anio_creacion = '" +anio+ "' , Nombre_estadio = '" +estadio+ "' WHERE nombre = '"+nombre+"'";
            //se ejecuta la consulta
            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;
            }catch(SQLException e){
                System.err.println( e.getMessage() );
            }
            return false;
        }
    
    public DefaultTableModel getJugadoresDe(String equipo)
    {
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"NIF","Nombre","Apellidos","Nombre Equipo","Temporada"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM Jugadores");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][5];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         CallableStatement castm = this.getConexion().prepareCall("{call JugadoresDe(?)}");
         castm.setString(1, equipo);
         ResultSet res = castm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "Fichajes.nif" );
                data[i][1] = res.getString( "Jugadores.nombre" );
                data[i][2] = res.getString( "Jugadores.apellidos" );
                data[i][3] = res.getString( "Fichajes.nombre" );
                data[i][4] = res.getString( "fichajes.temporada" );
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }

}