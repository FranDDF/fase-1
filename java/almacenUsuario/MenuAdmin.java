package almacenUsuario;

import java.util.Scanner;

import implemDAO.ClientesDAO;
import implemDAO.ProductosDAO;
import modelosDB.Productos;
import validadores.Valid;

public class MenuAdmin {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ProductosDAO daoP = new ProductosDAO();
		ClientesDAO daoC = new ClientesDAO();
		int opcion;

		do {
			System.out.println("[MENÚ ADMINISTRADOR]");
			System.out.println("1. Insertar un producto.");
			System.out.println("2. Ver todos los productos disponibles.");
			System.out.println("3. Eliminar un producto.");
			System.out.println("4. Mostrar un producto a partir de su Id.");
			System.out.println("5. Actualizar los datos de un producto.");
			System.out.println("6. Aumentar el stock de un producto.");
			System.out.println("7. Mostrar lista de clientes registrados.");
			System.out.println("8. Mostrar un cliente a partir de su Id.");
			System.out.println("9. Salir del menú.");

			opcion = Valid.leerInt(input, "Indique una opción:");
			switch (opcion) {
			case 1:
				String descripcion;
				int stock;
				double precioVenta;

				System.out.print("Indique el nombre del producto:");
				descripcion = input.nextLine();

				stock = Valid.leerInt(input, "Indique el stock del producto:");
				precioVenta = Valid.leerDouble(input, "Indique el precio del producto:");

				Productos producto = new Productos(descripcion, stock, precioVenta);
				daoP.insertar(producto);
				redireccionMenu();
				break;
			case 2:
				daoP.listar();
				System.out.println("Será redirigido al menú.");
				break;
			case 3:
				int idProducto = Valid.leerInt(input, "Indique el id del producto a eliminar:");
				daoP.eliminar(idProducto);
				redireccionMenu();
				break;
			case 4:
				idProducto = Valid.leerInt(input, "Indique el id del producto que desea consultar:");
				input.nextLine();
				daoP.filtrarId(idProducto);
				redireccionMenu();
				break;
			case 5:
				producto = new Productos();
				idProducto = Valid.leerInt(input, "Indique el id del producto a modificar:");
				System.out.print("Indique el nuevo nombre:");
				descripcion = input.nextLine();
				stock = Valid.leerInt(input, "Indique el nuevo stock:");
				precioVenta = Valid.leerDouble(input, "Indique el nuevo precio:");

				producto.setIdProducto(idProducto);
				producto.setDescripcion(descripcion);
				producto.setStock(stock);
				producto.setPrecioVenta(precioVenta);
				daoP.actualizar(producto);
				redireccionMenu();
				break;
			case 6:
				idProducto = Valid.leerInt(input, "Indique el id del producto al cual desea aumentarle el stock:");
				int cantidad = Valid.leerInt(input, "Indique la cantidad a sumar:");

				daoP.incrementarStock(idProducto, cantidad);
				redireccionMenu();
				break;
			case 7:
				daoC.listar();
				redireccionMenu();
				break;
			case 8:
				int idCliente = Valid.leerInt(input, "Indique el id del cliente:");
				daoC.filtrarId(idCliente);
				redireccionMenu();
				break;
			case 9:
				System.out.println("[FIN]");
				break;
			default:
				System.out.println("Indique una opción válida. Será redirigido al menú.");
				break;
			}
		} while (opcion != 9);
		input.close();
	}

	private static String redireccionMenu() {
		return "Será redirigido al menú.";
	}
}
