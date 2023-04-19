package cine;

import java.util.ArrayList;

public class MetodosCine {
	
	char[][] inciarCine(){
		
		char cine [][]  = new char[17][25];
		
		for(int i = 0;i<=16;i++) {
			for(int j = 0;j<=24;j++) {
				
				cine[i][j]='-';
			}	
		}
		return cine;	
	}
	
	int entradasVendidas(char[][] cine) {
		int ventas = 0;
		for(int i = 0;i<=16;i++) {
			for(int j = 0;j<=24;j++) {
				
				if(cine[i][j]=='x') {
					ventas +=1;
				}
			}	
		}
		
		
		return ventas;	
	}
	
	void consultarAsientos(char[][] cine) {
		
		for(int i = 0;i<=16;i++) {
			for(int j = 0;j<=24;j++) {
				
				System.out.print(cine[i][j]);
				if(j==16) {
					System.out.println('\n');
				}
			}	
		}	
	}
	
	void consultarAsientosfila(int fila,char[][] cine) {
		ArrayList<String> resultado = new ArrayList<String>();
		
		for(int i = 0;i<=16;i++) {
			int j = fila;
			
			
			if(cine[i][j]=='-') {
					String resultadoCrudo = "["+cine[i][j]+"]";
					resultado.add(resultadoCrudo);
				}
		}	
		System.out.println("En la fila "+fila+" hay "+resultado.size()+" asientos disponibles.");
	}	
	
	void filasLibres(char[][] cine) {
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		
		for(int i = 0;i<=16;i++) {
			boolean stopper = false;
			
			while(stopper) {
				for(int j = 0;j<=24;j++) {
					
					if(cine[i][j]=='-') {
						resultado.add(i);
						stopper = true;
					}
				
				}	
			}		
		}
		System.out.println("Hay asientos disponibles en las filas");
		for(Integer a :resultado) {
		System.out.println(a+",");
		}
	}
	
	
	void venderEntrada(char[][] cine,int columna,int fila) {
		
		cine[columna][fila] = 'x';
			
	}
	
}
	

