package implemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexionDB.Conexion;
import modelosDB.Clientes;
import validadores.Valid;

public class ClientesDAO implements IManejoDAO<Clientes> {
	private static final String QUERY_CREAR = "INSERT INTO Clientes(dni,nombre,apellido,email,telefono) VALUES (?,?,?,?,?);";
	private static final String QUERY_ELIMINAR = "DELETE FROM Clientes WHERE idCliente = ?;";
	private static final String QUERY_SELECCIONAR = "SELECT*FROM Clientes;";
	private static final String QUERY_FILTRAR_ID = "SELECT*FROM Clientes WHERE idCliente = ?;";
	private static final String QUERY_ACTUALIZAR = "UPDATE Clientes SET dni = ?, nombre = ?, apellido = ?, email = ?, telefono = ? WHERE idCliente = ?;";

	@Override
	public void insertar(Clientes cliente) {

		if (!Valid.vDni(cliente.getDni()) || !Valid.vTexto(cliente.getNombre()) || !Valid.vTexto(cliente.getApellido())
				|| !Valid.vTexto(cliente.getEmail()) || !Valid.vTel(cliente.getTelefono())) {
			System.out.println("No fue posible realizar la inserción del cliente.");
			return;
		}

		Connection conex = Conexion.conectar();
		try (PreparedStatement pstmt = conex.prepareStatement(QUERY_CREAR)) {

			conex.setAutoCommit(false);
			pstmt.setString(1, cliente.getDni());
			pstmt.setString(2, cliente.getNombre());
			pstmt.setString(3, cliente.getApellido());
			pstmt.setString(4, cliente.getEmail());
			pstmt.setString(5, cliente.getTelefono());

			int filasAfectadas = pstmt.executeUpdate();

			if (Valid.filasAfectadas(filasAfectadas)) {
				conex.commit();
				System.out.println("Inserción exitosa del cliente en la base de datos. Operación confirmada.");
			}
		} catch (SQLException e) {
			System.out.println("Falló la inserción del cliente. Se realizará una reversión de la operación.");
			Conexion.reversar(conex);
			e.printStackTrace();
		} finally {
			Conexion.cerrarC(conex);
		}
	}

	@Override
	public void eliminar(int id) {

		Connection conex = Conexion.conectar();
		try (PreparedStatement pstmt = conex.prepareStatement(QUERY_ELIMINAR)) {

			conex.setAutoCommit(false);
			pstmt.setInt(1, id);
			int filasAfectadas = pstmt.executeUpdate();

			if (Valid.filasAfectadas(filasAfectadas)) {
				conex.commit();
				System.out.println("Eliminación exitosa del cliente con el id n° " + id
						+ " de la base de datos. Operación confirmada.");
			} else {
				System.out.println("No existe el registro indicado en la base de datos.");
			}
		} catch (SQLException e) {
			System.out.println("Falló la eliminación del cliente. Se realizará una reversión de la operación.");
			Conexion.reversar(conex);
			e.printStackTrace();
		} finally {
			Conexion.cerrarC(conex);
		}
	}

	@Override
	public void listar() {

		Connection conex = Conexion.conectar();
		try (PreparedStatement pstmt = conex.prepareStatement(QUERY_SELECCIONAR); ResultSet rs = pstmt.executeQuery()) {

			boolean flag = false;
			while (rs.next()) {
				flag = Valid.flag();
				int idCliente = rs.getInt("idCliente");
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				String telefono = rs.getString("telefono");

				String infoCliente = String.format(
						"Id: %d. DNI: %s. Nombre: %s. Apellido: %s. Email: %s. Teléfono: %s.", idCliente, dni, nombre,
						apellido, email, telefono);
				System.out.println(infoCliente);

				if (!flag) {
					System.out.println("No existen registros en la base de datos.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al listar.");
			e.printStackTrace();
		} finally {
			Conexion.cerrarC(conex);
		}

	}

	@Override
	public void filtrarId(int id) {

		Connection conex = Conexion.conectar();
		try (PreparedStatement pstmt = conex.prepareStatement(QUERY_FILTRAR_ID)) {

			boolean flag = false;
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				flag = Valid.flag();
				int idCliente = rs.getInt("idCliente");
				String dni = rs.getString("dni");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				String telefono = rs.getString("telefono");

				String infoCliente = String.format(
						"Id: %d. DNI: %s. Nombre: %s. Apellido: %s. Email: %s. Teléfono: %s.", idCliente, dni, nombre,
						apellido, email, telefono);
				System.out.println(infoCliente);

				if (!flag) {
					System.out.println("No existen registros en la base de datos.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al listar.");
			e.printStackTrace();
		} finally {
			Conexion.cerrarC(conex);
		}

	}

	@Override
	public void actualizar(Clientes cliente) {

		if (!Valid.vDni(cliente.getDni()) || !Valid.vTexto(cliente.getNombre()) || !Valid.vTexto(cliente.getApellido())
				|| !Valid.vTexto(cliente.getEmail()) || !Valid.vTel(cliente.getTelefono())) {
			System.out.println("No fue posible realizar la inserción del cliente.");
			return;
		}

		Connection conex = Conexion.conectar();
		try (PreparedStatement pstmt = conex.prepareStatement(QUERY_ACTUALIZAR)) {

			conex.setAutoCommit(false);
			pstmt.setString(1, cliente.getDni());
			pstmt.setString(2, cliente.getNombre());
			pstmt.setString(3, cliente.getApellido());
			pstmt.setString(4, cliente.getEmail());
			pstmt.setString(5, cliente.getTelefono());
			int filasAfectadas = pstmt.executeUpdate();

			if (Valid.filasAfectadas(filasAfectadas)) {
				conex.commit();
				System.out.println("Registro actualizado exitosamente.");
			} else {
				System.out.println("No hay registros afectados, la operación no fue realizada.");
			}
		} catch (SQLException e) {
			System.out.println("Falló la actualización del registro. Se realizará una reversión de la operación.");
			Conexion.reversar(conex);
			e.printStackTrace();
		} finally {
			Conexion.cerrarC(conex);
		}
	}

}
