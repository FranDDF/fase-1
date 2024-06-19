package modelosDB;

import validadores.Valid;

public class Clientes {
	private int idCliente;
	private String dni;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;

	//Constructor
	public Clientes(String dni, String nombre, String apellido, String email, String telefono) {
		setDni(dni);
		setNombre(nombre);
		setApellido(apellido);
		setEmail(email);
		setTelefono(telefono);
	}
	
	//Sobrecarga de constructor
	public Clientes() {
		
	}
	
	//Getters y setters
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		if (Valid.vDni(dni)) {
			this.dni = dni;
		} else {
			System.out.println("[ERROR]El DNI debe contener una longitud mínima de 7 números y una máxima de 8.");
		}
	}

	public String getNombre() { 
		return nombre;
	}

	public void setNombre(String nombre) {
		if (Valid.vTexto(nombre)) {
			this.nombre = nombre;
		} else {
			System.out.println("[ERROR]El nombre no puede estar vacío y su máximo es de 50 caracteres.");
		}
		
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		if (Valid.vTexto(apellido)) {
			this.apellido = apellido;
		} else {
			System.out.println("[ERROR]El apellido no puede estar vacío y su máximo es de 50 caracteres.");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (Valid.vTexto(email)) {
			this.email = email;
		} else {
			System.out.println("[ERROR]El email no puede estar vacío y su máximo es de 50 caracteres.");
		}
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (Valid.vTel(telefono)) {
			this.telefono = telefono;
		} else {
			System.out.println("[ERROR]El teléfono debe contener 13 números (debe incluir prefijo de país y provincia. Ej: 5491151044856).");
		}
	}

	@Override
	public String toString() {
		return "Clientes [idCliente=" + idCliente + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", email=" + email + ", telefono=" + telefono + "]";
	}
	
	
}
