package implemDAO;

public interface IManejoDAO<T> {
	void insertar(T t);

	void eliminar(int t);

	void listar();

	void filtrarId(int id);

	void actualizar(T t);

}
