package ar.edu.ort;

public class Test {
	
	public static void main(String[] args) {
		Persona juan = new Persona(new Long(1), "Perez", "Juan", 70);
		juan.agregar();
		
		juan.setCantidadHijos(71);
		juan.actualizar();
		
		juan.eliminar();
		
		Persona pedro = new Persona();
		pedro.buscar(2);//(No va funcionar por la conexion a la bd)
	}
}