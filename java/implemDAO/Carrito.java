package implemDAO;

import java.util.ArrayList;

import modelosDB.Productos;

public class Carrito {
	private ArrayList<Productos> listaProductos;

	public Carrito() {
		this.listaProductos = new ArrayList<>();
	}

	public void agregarProducto(Productos producto) {
		listaProductos.add(producto);
		System.out.println("Producto agregado al carrito.");
	}

	public void mostrarCarrito() {
		double contador = 0;
		for (Productos p : listaProductos) {
			System.out.println("Id: " + p.getIdProducto() + ". Nombre: " + p.getDescripcion() + ". Stock: "
					+ p.getStock() + ". Precio: $" + p.getPrecioVenta());
			contador += p.getPrecioVenta();
		}
		System.out.println("Total: $" + contador);
	}

	public void vaciarCarrito() {
		if (!listaProductos.isEmpty()) {
			listaProductos.clear();
			System.out.println("Carrito vaciado.");
		} else {
			System.out.println("No hay productos en el carrito.");
		}
	}

}
