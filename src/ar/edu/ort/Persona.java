package ar.edu.ort;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
La tabla en SQL seria:
create table persona (idPersonaa int primary key, apellido varchar,
nombre varchar, cantidadHijos int)
*/
public class Persona {

	private long 	idPersona;
	private String 	apellido;
	private String 	nombre;
	private int 	cantidadHijos;

	private final static String consultaBusqueda =
	"SELECT idPersona, apellido, nombre, cantidadHijos" +
	" FROM persona" +
	" WHERE idPersonaa = ?";

	private final static String consultaUpdate =
	"UPDATE persona" +
	" set apellido = ?, nombre = ?, cantidadHijos = ?" +
	" where idPersonaa = ?";

	private final static String consultaInsert =
	"INSERT INTO persona VALUES (?, ?, ?, ?)";

	private final static String consultaDelete =
	"DELETE FROM persona" +
	"WHERE idPersonaa = ?";

	public Persona(Long idPersona, String apellido, String nombre, int cantidadHijos) {
		this.idPersona 		= idPersona;
		this.apellido 		= apellido;
		this.nombre 		= nombre;
		this.cantidadHijos 	= cantidadHijos;
	}

	public static Persona buscar(Long idPersona) {
		PreparedStatement query = null;
		ResultSet rs = null;
		try {
			query = DB.prepare(consultaBusqueda);
			query.setLong(1, idPersona.longValue());
			rs = query.executeQuery();
			rs.next();
			rs = cargar(rs);
			return rs;
		} catch (SQLException e) {
			//...
		} finally {
			DB.cleanUp(query, rs);
		}
	}

	public static Persona buscar(long idPersona) {
		return buscar(new Long(idPersona));
	}

	public static Persona cargar(ResultSet rs) throws SQLException {
		Long idPersona = new Long(rs.getLong(1));
		String parametroApellido = rs.getString(2);
		String parametroNombre = rs.getString(3);
		int parametroCantHijos = rs.getInt(4);
		Persona resultado = new Persona(idPersona, parametroApellido, parametroNombre, parametroCantHijos);
		return resultado;
	}

	public void actualizar() {
		PreparedStatement query = null;
		try {
			query = DB.prepare(consultaUpdate);
			query.setString(1, apellido);
			query.setString(2, nombre);
			query.setInt(3, cantidadHijos);
			query.setInt(4, getID().intValue());
			query.execute();
		} catch (Exception e) {
			//...
		} finally {
			DB.cleanUp(query);
		}
	}

	public Long agregar() {
		PreparedStatement query = null;
		try {
			query = DB.prepare(consultaInsert);
			setID(findNextDatabaseId());
			query.setInt(1, getID().intValue());
			query.setString(2, apellido);
			query.setString(3, nombre);
			query.setInt(4, cantidadHijos);
			query.execute();
			return getID();
		} catch (Exception e) {
			//...
		} finally {
			DB.cleanUp(query);
		}
	}

	public float calcularGanancias() {
		return (float) (0.35/cantidadHijos);
	}

	/**
	 * Más métodos de la lógica del negocio...
	**/
}