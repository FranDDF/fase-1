package almacenUsuario;

import java.util.Scanner;

import implemDAO.Carrito;
import implemDAO.ClientesDAO;
import implemDAO.ProductosDAO;
import modelosDB.Clientes;
import modelosDB.Productos;
import validadores.Valid;

public class MenuComprador {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ClientesDAO daoC = new ClientesDAO();
		ProductosDAO daoP = new ProductosDAO();
		Carrito carrito = new Carrito();
		int opcion;

		do {
			System.out.println("[MENÚ CLIENTE]");
			System.out.println("1. Ingresar datos personales para registro.");
			System.out.println("2. Ver todos los productos disponibles.");
			System.out.println("3. Comprar un producto.");
			System.out.println("4. Mostrar un producto a partir de su Id.");
			System.out.println("5. Modificar datos personales.");
			System.out.println("6. Mostrar carrito.");
			System.out.println("7. Vaciar carrito.");
			System.out.println("8. Salir del menú.");
			opcion = Valid.leerInt(input, "Indique una opción:");

			switch (opcion) {
			case 1:
				System.out.print("Indique su DNI:");
				String dni = input.nextLine();
				System.out.print("Indique su nombre:");
				String nombre = input.nextLine();
				System.out.print("Indique su apellido:");
				String apellido = input.nextLine();
				System.out.print("Indique su email:");
				String email = input.nextLine();
				System.out.print("Indique su teléfono. Debe contener el código de área de país y provincia:");
				String telefono = input.nextLine();

				Clientes cliente = new Clientes(dni, nombre, apellido, email, telefono);
				daoC.insertar(cliente);
				redireccionMenu();
				break;
			case 2:
				daoP.listar();
				redireccionMenu();
				break;
			case 3:
				int idProducto = Valid.leerInt(input, "Indique el id del producto que desea comprar:");
				int cantidad = Valid.leerInt(input, "Indique la cantidad que desea comprar:");
				Productos producto = daoP.filtrarIdP(idProducto);

				if (cantidad <= producto.getStock()) {
					daoP.decrementarStock(idProducto, cantidad);
					carrito.agregarProducto(producto);
				} else {
					System.out.println(
							"La cantidad indicada supera el stock disponible. Seleccione la opción 2 o 4 del menú para ver el stock actual.");
				}
				redireccionMenu();
				break;
			case 4:
				idProducto = Valid.leerInt(input, "Indique el id del producto:");
				daoP.filtrarId(idProducto);
				redireccionMenu();
				break;
			case 5:
				cliente = new Clientes();
				int idCliente = Valid.leerInt(input, "Indique el id del cliente a modificar:");
				System.out.print("Indique el nuevo DNI:");
				dni = input.nextLine();
				System.out.print("Indique el nuevo nombre:");
				nombre = input.nextLine();
				System.out.println("Indique el nuevo apellido:");
				apellido = input.next();
				System.out.println("Indique el nuevo email:");
				email = input.next();
				System.out.println("Indique el nuevo teléfono:");
				telefono = input.next();

				cliente.setIdCliente(idCliente);
				cliente.setDni(dni);
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setEmail(email);
				cliente.setTelefono(telefono);
				redireccionMenu();
				break;
			case 6:
				carrito.mostrarCarrito();
				redireccionMenu();
				break;
			case 7:
				carrito.vaciarCarrito();
				redireccionMenu();
				break;
			case 8:
				System.out.println("[FIN]");
				break;
			default:
				System.out.println("Indique una opción válida.");
			}

		} while (opcion != 8);
		input.close();
	}

	private static String redireccionMenu() {
		return "Será redirigido al menú.";
	}

}
