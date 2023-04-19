package cine;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		MetodosCine met =new MetodosCine();
		Scanner sc = new  Scanner(System.in);
		
		char[][]cine = met.inciarCine();
		
		boolean seguir = true ;
			while(seguir) {
			System.out.println("1. Consultar total entradas vendidas");
			System.out.println("2. Mostrar ocupación de la sala");
			System.out.println("3. Consultar asientos disponibles por fila");
			System.out.println("\t a. Introduce el número de fila");
			System.out.println("\t b. Volver al menú anterior");
			System.out.println("4. Vender una entrada");
			System.out.println("\t a. Mostrar filas con asientos disponibles");
			System.out.println("\t b. Vender una entrada");
			System.out.println("\t \t i. Introducir Fila");
			System.out.println("\t \t ii. Introducir Asiento");
			System.out.println("5. Salir");
			
			System.out.println("Introduce el numero de la opcion :");
			int opcion= Integer.parseInt(sc.nextLine());
			
			switch(opcion) {
				
				case 1: int vendidas= met.entradasVendidas(cine);
						System.out.println("Hay "+vendidas+" entradas vendidas");
						break;
				
				case 2:met.consultarAsientos(cine);
						break;
				
				case 3: System.out.println("Introduce el numero de fila");
						int fila = Integer.parseInt(sc.nextLine());
						
						met.consultarAsientosfila(fila, cine);
						break;
				
				case 4: System.out.println("Introduce el numero de fila");
						int filaVenta = Integer.parseInt(sc.nextLine());
						System.out.println("Introduce el numero de columna");
						int columnaVenta = Integer.parseInt(sc.nextLine());
	
						met.venderEntrada(cine, filaVenta, columnaVenta);
						break;
						
				default: seguir=false;
					
				}
				
			}
		}
	
	
}
