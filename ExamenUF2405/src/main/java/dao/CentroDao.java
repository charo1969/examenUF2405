package dao;

import java.util.List;

import modelo.Centros;

public interface CentroDao {
	
	List<Centros>getCentros();
	Centros getCentros(int cod_centro);
	

}
