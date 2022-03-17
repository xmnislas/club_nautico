/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Socio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xmnis
 */
public class SociosDAO implements ISociosDAO{
    private IConexionBD conexionBD;

    public SociosDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    @Override
    public boolean agregar(Socio socio) {
        
        try{
            //Se establece una conexion con MySQL, si no se puede lanza una SQLException
            Connection conexion = this.conexionBD.crearConexion();
            
            //Se define un objeto statement para enviar comandos SQL.
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("INSERT INTO socios (nombre, curp) VALUES ('%s','%s');", socio.getNombre(), socio.getCurp());
            
            //Este método se utiliza para hacer operaciones que alteren datos (INSERT, DELETE, UPDATE)
            int numeroRegistrosAfectados = comandoSQL.executeUpdate(codigoSQL);
            
            conexion.close();
            
            return (numeroRegistrosAfectados == 1);
         
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean actualizar(Socio socio) {
        try{
            Connection conexion = this.conexionBD.crearConexion();
            
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("UPDATE socios SET nombre = '%s', curp = '%s' WHERE id_socios = %d", socio.getNombre(), socio.getCurp(), socio.getIdSocio());
            
            int numeroSociosActualizados = comandoSQL.executeUpdate(codigoSQL);
            
            conexion.close();
            
            return(numeroSociosActualizados == 1);
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Long idSocio) {
        try{
            Connection conexion = this.conexionBD.crearConexion();
            
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("DELETE FROM socios WHERE id_socios = %d", idSocio);
            
            int numeroSociosEliminados = comandoSQL.executeUpdate(codigoSQL);
            
            conexion.close();
            
            return(numeroSociosEliminados == 1);
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Socio consultar(Long idSocio) {
        Socio socio = null;
        
        try{
            Connection conexion  = this.conexionBD.crearConexion();
            
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("SELECT id_socios, nombre, curp FROM socios WHERE id_socios = %d;", idSocio);
            
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            
            if(resultado.next()){
                Long id = resultado.getLong("id_socios");
                String nombre = resultado.getString("nombre");
                String curp = resultado.getString("curp");
                
                socio = new Socio(id, nombre, curp);
            }
            
            conexion.close();
            return socio;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return socio;
        }
    }

    @Override
    public List<Socio> consultarTodos() {
        List<Socio> listaSocios = new ArrayList<>();
        
        try{
            Connection conexion  = this.conexionBD.crearConexion();
            
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("SELECT id_socios, nombre, curp FROM socios;");
            
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            
            while(resultado.next()){
                Long idSocio = resultado.getLong("id_socios");
                String nombre = resultado.getString("nombre");
                String curp = resultado.getString("curp");
                
                Socio socio = new Socio(idSocio, nombre, curp);
                listaSocios.add(socio);
            }
            
            conexion.close();
            return listaSocios;
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            return listaSocios;
        }
    }
    
}
