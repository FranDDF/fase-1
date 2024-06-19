package validadores;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.util.StringUtils;

public class Valid {
	public static boolean vTexto(String texto) {
		return texto != null && texto.length() > 0 && texto.length() <= 50
				&& (!StringUtils.isEmptyOrWhitespaceOnly(texto));
	}

	public static boolean vStockP(int stock) {
		return stock >= 0;
	}

	public static boolean vPrecioP(double precio) {
		return precio > 0;
	}

	public static boolean flag() {
		return true;
	}

	public static boolean filasAfectadas(int fa) {
		return fa > 0;
	}

	public static boolean vTel(String tel) {
		return tel.length() == 13;
	}

	public static boolean vDni(String dni) {
		return dni.length() >= 7 || dni.length() <= 8;
	}

	public static int leerInt(Scanner input, String mensaje) {
		
		while (true) {
			try {
				System.out.print(mensaje);
				int valor = input.nextInt();
				input.nextLine();
				return valor;
			} catch (InputMismatchException e) {
				System.out.println("[ERROR]Debe ingresar un número entero.");
				input.next();
			}
		}
	}

	public static double leerDouble(Scanner input, String mensaje) {
		while (true) {
			try {
				System.out.print(mensaje);
				double valor = input.nextDouble();
				input.nextLine();
				return valor;
			} catch (InputMismatchException e) {
				System.out.println("[ERROR]Debe ingresar un número decimal o entero.");
				input.next();
			}
		}
	}

}
