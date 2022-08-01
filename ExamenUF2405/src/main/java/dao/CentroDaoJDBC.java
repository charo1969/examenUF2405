package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Centros;
import modelo.Departamento;
import utilidades.ConexionBD;

public class CentroDaoJDBC implements CentroDao {
	
	private ConexionBD conexion;
	private Statement consulta = null;
	private PreparedStatement consultaPreparada = null;
	private ResultSet resultado = null;
	
	public CentroDaoJDBC() {
		conexion = new ConexionBD();
	}

	@Override
	public List<Centros> getCentros() {
		List<Centros> listaCentros = new ArrayList<Centros>();
		Connection con = conexion.getConexion();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from Centros");
			while (resultado.next()) {
				int cod_centro = resultado.getInt("cod_centro");
				
				String nombre = resultado.getString("nombre");
				String direccion = resultado.getString("direccion");
				
				Centros centro = new Centros(cod_centro, nombre, direccion);
				
				

				listaCentros.add(centro);
			}
			System.out.println("A�adidos todos los centros: ");
			System.out.println(listaCentros);
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta sobre los centros: "+e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}

		
		return listaCentros;
	}
	
	

	@Override
	public Centros getCentros(int cod_centro) {
		Connection con = conexion.getConexion();
		int resultado=0;
		
		try {
			consultaPreparada = con.prepareStatement("DELETE FROM Centros\r\n"
					+ "WHERE cod_centro = ?");
			
			consultaPreparada.setInt(1, cod_centro);
			resultado=consultaPreparada.executeUpdate();
			System.out.println("Departamento borrado correctamente: "+cod_centro);

		} catch (SQLException e) {
			System.out.println("Error al realizar el borrado de Centro: "+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
			
		}
		return resultado;
			
	}
	

}
