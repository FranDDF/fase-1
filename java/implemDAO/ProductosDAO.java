package implemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexionDB.Conexion;
import modelosDB.Productos;
import validadores.Valid;

public class ProductosDAO implements IManejoDAO<Productos>, IProd {
	private static final String QUERY_CREAR = "INSERT INTO Productos(descripcion,stock,precioVenta) VALUES (?,?,?);";
	private static final String QUERY_ELIMINAR = "DELETE FROM Productos WHERE idProducto = ?;";
	private static final String QUERY_SELECCIONAR = "SELECT*FROM Productos;";
	private static final String QUERY_FILTRAR_ID = "SELECT*FROM Productos WHERE idProducto = ?;";
	private static final String QUERY_ACTUALIZAR = "UPDATE Productos SET descripcion = ?, stock = ?, precioVenta = ? WHERE idProducto = ?;";
	private static final String QUERY_INCREMENTAR_STOCK = "UPDATE productos SET stock = stock + ? WHERE idProducto = ?";
	private static final String QUERY_DECREMENTAR_STOCK = "UPDATE productos SET stock = stock - ? WHERE idProducto = ?";

	@Override
	public void insertar(Productos prod) {

		if (!Valid.vTexto(prod.getDescripcion()) || !Valid.vStockP(prod.getStock())
				|| !Valid.vPrecioP(prod.getPrecioVenta())) {
			System.out.println("No fue posible realizar la inserción del producto.");
			return;
		}

		Connection conex = Conexion.conectar();
		try (PreparedStatement pstmt = conex.prepareStatement(QUERY_CREAR)) {

			conex.setAutoCommit(false);
			pstmt.setString(1, prod.getDescripcion());
			pstmt.setInt(2, prod.getStock());
			pstmt.setDouble(3, prod.getPrecioVenta());
			int filasAfectadas = pstmt.executeUpdate();

			if (Valid.filasAfectadas(filasAfectadas)) {
				conex.commit();
				System.out.println("Inserción exitosa del producto en la base de datos. Operación confirmada.");
			}
		} catch (SQLException e) {
			System.out.println("Falló la inserción del producto. Se realizará una reversión de la operación.");
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
				System.out.println("Eliminación exitosa del producto con el id n° " + id
						+ " de la base de datos. Operación confirmada.");
			} else {
				System.out.println("No existe el registro indicado en la base de datos.");
			}
		} catch (SQLException e) {
			System.out.println("Falló la eliminación del producto. Se realizará una reversión de la operación.");
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
				int idProducto = rs.getInt("idProducto");
				String descripcion = rs.getString("descripcion");
				int stock = rs.getInt("stock");
				double precioVenta = rs.getDouble("precioVenta");

				String infoProducto = String.format("Id: %d. Descripción: %s. Stock: %d. Precio: %.2f.", idProducto,
						descripcion, stock, precioVenta);
				System.out.println(infoProducto);
			}

			if (!flag) {
				System.out.println("No existen registros en la base de datos.");
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
				int idProducto = rs.getInt("idProducto");
				String descripcion = rs.getString("descripcion");
				int stock = rs.getInt("stock");
				double precioVenta = rs.getDouble("precioVenta");

				String infoProducto = String.format("Id: %d. Descripción: %s. Stock: %d. Precio: %.2f.", idProducto,
						descripcion, stock, precioVenta);
				System.out.println(infoProducto);
			}

			if (!flag) {
				System.out.println("No existe el registro indicado en la base de datos.");
			}
		} catch (SQLException e) {
			System.out.println("Error al listar.");
			e.printStackTrace();
		} finally {
			Conexion.cerrarC(conex);
		}
	}

	@Override
	public void actualizar(Productos prod) {

		if (!Valid.vTexto(prod.getDescripcion()) || !Valid.vStockP(prod.getStock())
				|| !Valid.vPrecioP(prod.getPrecioVenta())) {
			System.out.println("No fue posible realizar la actualización del producto.");
			return;
		}

		Connection conex = Conexion.conectar();
		try (PreparedStatement pstmt = conex.prepareStatement(QUERY_ACTUALIZAR)) {

			conex.setAutoCommit(false);
			pstmt.setString(1, prod.getDescripcion());
			pstmt.setInt(2, prod.getStock());
			pstmt.setDouble(3, prod.getPrecioVenta());
			pstmt.setInt(4, prod.getIdProducto());

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

	@Override
	public void incrementarStock(int idProducto, int cantidad) {

		if (!Valid.vStockP(cantidad)) {
			System.out.println("No fue posible realizar el aumento del stock del producto.");
			return;
		}
		Connection conex = Conexion.conectar();
		try (PreparedStatement pstmt = conex.prepareStatement(QUERY_INCREMENTAR_STOCK)) {

			conex.setAutoCommit(false);
			pstmt.setInt(1, cantidad);
			pstmt.setInt(2, idProducto);
			int filasAfectadas = pstmt.executeUpdate();

			if (Valid.filasAfectadas(filasAfectadas)) {
				conex.commit();
				System.out.println("Se incrementó el stock del producto con id n° " + idProducto);
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

	@Override
	public void decrementarStock(int idProducto, int cantidad) {

		if (!Valid.vStockP(cantidad)) {
			System.out.println("No fue posible realizar la reducción del stock del producto.");
			return;
		}
		Connection conex = Conexion.conectar();
		try (PreparedStatement pstmt = conex.prepareStatement(QUERY_DECREMENTAR_STOCK)) {

			conex.setAutoCommit(false);
			pstmt.setInt(1, cantidad);
			pstmt.setInt(2, idProducto);
			int filasAfectadas = pstmt.executeUpdate();

			if (Valid.filasAfectadas(filasAfectadas)) {
				conex.commit();
				System.out.println("Se redujo el stock del producto con id n° " + idProducto);
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

	@Override
	public Productos filtrarIdP(int idProducto) {
		Productos producto = new Productos();
		Connection conex = Conexion.conectar();
		try (PreparedStatement pstmt = conex.prepareStatement("SELECT * FROM productos WHERE idProducto = ?")) {
			pstmt.setInt(1, idProducto);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				producto = new Productos();
				producto.setIdProducto(rs.getInt("idProducto"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setStock(rs.getInt("stock"));
				producto.setPrecioVenta(rs.getDouble("precioVenta"));
			}
		} catch (SQLException e) {
			System.out.println("Error al filtrar el producto por ID.");
			e.printStackTrace();
		} finally {
			Conexion.cerrarC(conex);
		}
		return producto;
	}

}
