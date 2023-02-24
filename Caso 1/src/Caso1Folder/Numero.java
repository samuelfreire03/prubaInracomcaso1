package Caso1Folder;

public class Numero {
	
	//Inicializa la creacion del numero

	public int numero;
	
	public Numero(int i) {
		
		this.numero = i;
		
	}
	
	public void MasNumero() {
		
		numero += 1; 
		
	}
	
	@Override
	public String toString() {
		
		return String.valueOf(numero);
	}
	
	public  int getNumero() {
		return numero;
	}

}
