class Erro extends java.lang.Throwable{
	
	public Erro(int e, int linha, String frase){
		
		System.out.println("Na linha: " + (linha+1));
		
	

		switch(e){
			case 1:
				System.out.println("\tSem ':' no final da linha!");
				break;
			case 2:
				System.out.println("\tVariável '"+frase+"' não declarada!");
				break;
			case 3:
				System.out.println("\tEsperado '"+frase+"'.");
				break;
			case 4:
				System.out.println("\tVariável '"+frase+"' não é numérica.");
				break;
		}
	
		
	}
}