package modelosDB;

import validadores.Valid;

public class Productos {
	private int idProducto;
	private String descripcion;
	private int stock;
	private double precioVenta;

	public Productos(String descripcion, int stock, double precioVenta) {
		setDescripcion(descripcion);
		setStock(stock);
		setPrecioVenta(precioVenta);
	}

	public Productos() {

	}

	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if (Valid.vTexto(descripcion)) {
			descripcion.trim();
			this.descripcion = descripcion;
		} else {
			System.out.println("[ERROR]La descripción no puede estar vacía o excede el máximo de 50 caracteres.");
		}
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		if (Valid.vStockP(stock)) {
			this.stock = stock;
		} else {
			System.out.println("[ERROR]El stock no puede ser menor a 0.");
		}
	}

	public double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		if (Valid.vPrecioP(precioVenta)) {
			this.precioVenta = precioVenta;
		} else {
			System.out.println("[ERROR]El precio de venta debe ser mayor a 0.");
		}
	}

	@Override
	public String toString() {
		return "Productos [idProducto=" + idProducto + ", descripcion=" + descripcion + ", stock=" + stock
				+ ", precioVenta=" + precioVenta + "]";
	}

}
