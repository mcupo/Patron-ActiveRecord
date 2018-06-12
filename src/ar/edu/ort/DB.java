package ar.edu.ort;

//Clase que encapsula la comunicación con la base de datos
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB {

	public static PreparedStatement prepararConsulta(String consultainsert) {
		//...
		return null;
	}

	public static void limpiar(PreparedStatement query) {
		//...
	}

	public static void limpiar(PreparedStatement query, ResultSet rs) {
		//...
	}
}