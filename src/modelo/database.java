package modelo;
import java.sql.*;
/**

 * @author Mouse
 */
public class database {
 /* DATOS PARA LA CONEXION */
  /** base de datos por defecto es test*/
  private String db = "fcaro_Proyecto";
  /** usuario */
  private String user = "Admin";
  /** contraseña de MySql*/
  private String password = "@currocaro2001*";
  /** Cadena de conexion */
  private String url = "jdbc:mysql://79.148.236.236:3306/"+db+"?noAccessToProcedureBodies=true";
  /** variable para trabajar con la conexion a la base de datos */
  private Connection conn = null;

   /** Constructor de clase */
   public database(){
        this.url = "jdbc:mysql://79.148.236.236:3306/"+this.db+"?noAccessToProcedureBodies=true";
       try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         conn = DriverManager.getConnection( this.url, this.user , this.password );         
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }catch(ClassNotFoundException e){
         System.err.println( e.getMessage() );
      }
   }


   public Connection getConexion()
   {
    return this.conn;
   }

}
