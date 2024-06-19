package implemDAO;

import modelosDB.Productos;

public interface IProd {
	void incrementarStock(int idProducto, int cantidad);
	
	void decrementarStock(int idProducto, int cantidad);
    
    Productos filtrarIdP(int idProducto);
    
}
