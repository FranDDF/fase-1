package conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static final String URL = "jdbc:mysql://localhost:3306/Almacen";
	private static final String USER = "root";
	private static final String PASSWORD = "aguantecasla1";
	
	public static Connection conectar() {
		Connection conex = null;
		
		try {
			conex = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conexión establecida con la base de datos del almacén.");
		} catch (SQLException e) {
			System.out.println("Error al conectar con la base de datos.");
			e.printStackTrace();
		}
		
		return conex;
	}
	

	public static void cerrarC(Connection conex) {
		if (conex != null) {
			try {
				conex.close();
				System.out.println("Desconexión exitosa de la base de datos.");
			} catch (SQLException e) {
				System.out.println("Desconexión fallida.");
				e.printStackTrace();
			}
		}
	}
	
	public static void reversar(Connection conex) {
		if (conex != null) {
			try {
				conex.rollback();
				System.out.println("Reversión de la operación realizada con éxito.");
			} catch (SQLException e) {
				System.out.println("Reversión fallida.");
				e.printStackTrace();
			}
		}
	}
	
	

}
