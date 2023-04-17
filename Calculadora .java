package CalculadoraRedes;

import java.util.Scanner;

public class Calculadora {
	Scanner teclado = new Scanner(System.in);
	int primer, segundo, tercer, cuarto;
	int mascara,subredes; 

	String clase;
	
	int [] mascaraBin = new int [31];
	int [] mascaraAmp = new int [31];
	
	double base = 2;
	int rango=0; 
	
//metodo que pide los datos de entrada
	public void pedirdatos() {
		System.out.println("Introduce el primer valor de la IP");
		primer = Integer.parseInt(teclado.nextLine());
		
		System.out.println("Introduce el segundo valor de la IP");
		segundo = Integer.parseInt(teclado.nextLine());
		
		System.out.println("Introduce el tercer valor de la IP");
		tercer = Integer.parseInt(teclado.nextLine());
		
		System.out.println("Introduce el cuarto valor de la IP");
		cuarto = Integer.parseInt(teclado.nextLine());
		
		System.out.println("Introduce la mascara de la red");
		mascara = Integer.parseInt(teclado.nextLine());
		
		System.out.println("Introduce el numero de subredes que necesitas");
		subredes = Integer.parseInt(teclado.nextLine());
	}

//metodo que devuelva la clase de la red introducida
	public void obtenerClase() {
		if (primer>=0 && primer<=127) {
			clase = "A";
		}else if(segundo>=128 && segundo<=191) {
			clase = "B";			
		}else if (tercer>=192 && tercer<=223) {
			clase = "C";
		}else {
			System.out.println("Clase no valida");
			//boleano que cierre el programa
			
		}
	}
//metodo que me calcule la mascara
	public void calcularMascara() {
		for (int i=0; i<mascara;i++) {
			mascaraBin[i]=1;
		}
	}
//metodo que calcule los bits que necesitamos para el numero de subredes que queremos 
	public int calcularBits() {
		double exponente = 1;
		double resultado = Math.pow(base, exponente);
		
		boolean seguir = true; 
		
		while (seguir) {
			if (resultado >= subredes) {
				seguir = false; 
			}else {
				exponente++;
			}
		}
		return (int)exponente; 
	}
//metodo nueva mascara subred (le entra el exponente del metodo calcularBits)
	public void mascaraAmpliada(int a) {
		int nuevaMasc = mascara + a;
		for (int i=0; i <= nuevaMasc;i++) {
			mascaraAmp[i] = 1;
		}
	}
//metodo que calcule los host por subred
	public int hostSubred() {
		int contador = 0;
		for (int i=0; i<=mascaraAmp.length;i++) {
			if(mascaraAmp[i]==0) {
				contador++;
			}
		}
		double hosts = Math.pow(base, contador) - 2;
		return (int)hosts;
	}
//metodo que calcule los saltos de red
	public int saltosRed(int [] a) {
		for (int i=0; i<=a.length;i++) {
			if(a[i]!=255) {
				rango = a [i];
			}
		}
		return rango; 
	}
	
	//metodo que pase de binario a decimal
	public int [] pasarDecimal (ArraysOctetos a) {
		double prDec = 0, sgDec = 0, trDec = 0,crDec = 0;
		double exponente = 7;
		int [] deciMasc = new int [4];
		
		//primer octeto
		for (int i=0; i<=7;i++) {
			int [] trabajo =a.getSegundoOcteto();
			if(trabajo[i]==1) {
				sgDec = sgDec + Math.pow(base, exponente);
				exponente--;
			}
		}
		deciMasc[0] = (int)prDec;
		//segundo octeto
		for (int i=0; i<=7;i++) {
			int [] trabajo =a.getTercerOcteto();
			if(trabajo[i]==1) {
				trDec = trDec + Math.pow(base, exponente);
				exponente--;
			}
		}
		deciMasc[1] = (int)sgDec;
		//tercer octeto
		for (int i=0; i<=7;i++) {
			int [] trabajo =a.getCuartoOcteto();
			if(trabajo[i]==1) {
				crDec = crDec + Math.pow(base, exponente);
				exponente--;
			}
		}
		deciMasc[2] = (int)trDec;
		//cuarto octeto
		for (int i=0; i<=7;i++) {
			int [] trabajo =a.getPrimerOcteto();
			if(trabajo[i]==1) {
				prDec = prDec + Math.pow(base, exponente);
				exponente--;
			}
		}
		deciMasc[3] = (int)crDec;
	return deciMasc;
	}
	//metodo que pase los arrays a octetos
	public void crearObjeto (int [] a) {
		ArraysOctetos arrayOctetos = new ArraysOctetos(null, null, null, null);
		int [] octeto = new int [7];
		for (int i=0; i<=7;i++) {
			octeto [i] = a [i];
		}
		arrayOctetos.setPrimerOcteto(octeto);
		for (int i=8; i<=15;i++) {
			octeto [i] = a [i];
		}
		arrayOctetos.setSegundoOcteto(octeto);
		for (int i=16; i<=23;i++) {
			octeto [i] = a [i];
		}
		arrayOctetos.setTercerOcteto(octeto);
		for (int i=24; i<=31;i++) {
			octeto [i] = a [i];
		}
		arrayOctetos.setCuartoOcteto(octeto);
	}
//metodo que me imprime la tabla
	public void imprimirResultados() {
		int modificado; 
		
		System.out.println("IP introducida: " + primer +"."+segundo+"."+tercer+"."+cuarto);
		System.out.println("Esta red es de clase: " + clase);
		System.out.println("Mascara: " + mascara);
		System.out.println("ADR Subred" + "Rango" + "Broadcast");
		System.out.println("Subred "+"");
	
		if (clase.equalsIgnoreCase("A")) {
			modificado = segundo;
			for (int i = 0; i<=subredes-1;i++) {	
				System.out.print(i + ": "+primer+ "." +modificado+"."+tercer+"."+cuarto);
				System.out.print(primer+ "." +modificado+1+"."+tercer+"."+cuarto);				
				System.out.print(+primer+ "." +modificado+(rango-2)+"."+tercer+"."+cuarto);
				System.out.print(+primer+ "." +modificado+(rango-1)+"."+tercer+"."+cuarto);
				modificado = modificado+rango; 
			}
		}
		if (clase.equalsIgnoreCase("B")) {
			modificado = tercer;
			for (int i = 0; i<=subredes-1;i++) {	
				System.out.print(i + ": "+primer+ "." +segundo+"."+modificado+"."+cuarto);
				System.out.print(primer+ "." +segundo+1+"."+modificado+1+"."+cuarto);				
				System.out.print(+primer+ "." +segundo+"."+modificado+(rango-2)+"."+cuarto);
				System.out.print(+primer+ "." +segundo+"."+modificado+(rango-1)+"."+cuarto);
				modificado = modificado+rango; 
			}
		}
		if (clase.equalsIgnoreCase("C")) {
			modificado = cuarto;
			for (int i = 0; i<=subredes-1;i++) {	
				System.out.print(i + ": "+primer+ "." +modificado+"."+tercer+"."+modificado);
				System.out.print(primer+ "." +segundo+"."+tercer+"."+modificado+1);				
				System.out.print(+primer+ "." +segundo+"."+tercer+"."+modificado+(rango-2));
				System.out.print(+primer+ "." +segundo+"."+tercer+"."+modificado+(rango-1));
				modificado = modificado+rango; 
			}
		}

		
	}
}
