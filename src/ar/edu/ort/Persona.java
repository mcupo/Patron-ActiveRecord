package ar.edu.ort;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
La tabla en SQL seria:
create table persona (idPersona int primary key, apellido varchar,
nombre varchar, cantidadHijos int)
*/
public class Persona {

	private Long 	idPersona;
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
	
	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidadHijos() {
		return cantidadHijos;
	}

	public void setCantidadHijos(int cantidadHijos) {
		this.cantidadHijos = cantidadHijos;
	}

	public static Persona buscar(Long idPersona) {		
		Persona resultado = null;
		PreparedStatement query = null;
		ResultSet rs = null;
		try {
			query = DB.prepararConsulta(consultaBusqueda);
			query.setLong(1, idPersona.longValue());
			rs = query.executeQuery();
			rs.next();
			resultado = cargar(rs);
			return resultado;
		} catch (SQLException e) {
			//...
		} finally {
			DB.limpiar(query, rs);
		}
		return null;
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
			query = DB.prepararConsulta(consultaUpdate);
			query.setString(1, apellido);
			query.setString(2, nombre);
			query.setInt(3, cantidadHijos);
			query.setInt(4, getIdPersona().intValue());
			query.execute();
		} catch (Exception e) {
			//...
		} finally {
			DB.limpiar(query);
		}
	}

	public Long agregar() {
		PreparedStatement query = null;
		try {
			query = DB.prepararConsulta(consultaInsert);
			setIdPersona(obtenerProximoId());
			query.setInt(1, getIdPersona().intValue());
			query.setString(2, apellido);
			query.setString(3, nombre);
			query.setInt(4, cantidadHijos);
			query.execute();
			return getIdPersona();
		} catch (Exception e) {
			//...
		} finally {
			DB.limpiar(query);
		}
		return null;
	}
	
	public Long obtenerProximoId() {
		return new Long(12);
	}

	public float calcularGanancias() {
		return (float) (0.35/cantidadHijos);
	}

	/**
	 * Más métodos de la lógica del negocio...
	**/
}