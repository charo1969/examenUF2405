package dao;

import java.util.List;

import modelo.Centro;
import modelo.Departamento;

public interface CentroDao {
	
	List<Centro>getCentro();
	Centro getCentro(int codCentro);
	int insertarCentro(Centro centro);
	int actualizarCentro(Centro centro);
	int eliminarCentro(int codCentro);
	

}
