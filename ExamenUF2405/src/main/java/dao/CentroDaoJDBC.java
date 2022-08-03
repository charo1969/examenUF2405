package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Centro;
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
	public List<Centro> getCentro() {
		List<Centro> listaCentros = new ArrayList<Centro>();
		Connection con = conexion.getConexion();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from Centros");
			while (resultado.next()) {
				int cod_centro = resultado.getInt("codCentro");
				
				String nombre = resultado.getString("nombre");
				String direccion = resultado.getString("direccion");
				
				Centro centro = new Centro(cod_centro, nombre, direccion);
				
				

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
	public int insertarCentro(Centro centro) {
		// TODO Esbozo de método generado automáticamente
		return 0;
	}

	@Override
	public int actualizarCentro(Centro centro) {
		// TODO Esbozo de método generado automáticamente
		return 0;
	}
	
	

	

	@Override
	public Centro getCentro(int codCentro) {
		// TODO Esbozo de método generado automáticamente
		return null;
	}

	@Override
	public int eliminarCentro(int codCentro) {
		Connection con = conexion.getConexion();
		int resultado=0;
		
		try {
			consultaPreparada = con.prepareStatement("DELETE FROM Centros\r\n"
					+ "WHERE cod_centro = ?");
			
			consultaPreparada.setInt(1, codCentro);
			resultado=consultaPreparada.executeUpdate();
			System.out.println("Departamento borrado correctamente: "+codCentro);

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

	

	

	
	


