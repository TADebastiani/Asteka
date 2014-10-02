class Erro{
	
	public static void erro(int e, int linha){
		System.out.println("Na linha: " + (linha+1));
	

		switch(e){
			case 1:
				System.out.println("\tSem ':' no final da linha!");
				break;
			case 2:
				System.out.println("\tVariável não declarada!");
				break;
		}
	
		System.exit(0);
	}
}