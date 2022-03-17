/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author xmnis
 */
public class ConexionBD implements IConexionBD{
    final String CADENA_CONEXION = "jdbc:mysql://localhost/club_nautico";
    final String USUARIO = "root";
    final String CONTRASENIA = "8255963";
        
    @Override
    public Connection crearConexion() throws SQLException{
        //Se establece una conexion con MySQL, si no se puede lanza una SQLException
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASENIA);
        return conexion;
    }
   
}
