package ar.edu.ort;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Persistible {

	public void buscar(Long id);
	public void cargar(ResultSet rs) throws SQLException;
	public void actualizar();
	public Long agregar();
	public boolean eliminar();
	public Long obtenerProximoId();
}