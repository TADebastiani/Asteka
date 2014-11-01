class Operacoes{
	

	static public String operaMat(String valores, int linha) throws Erro{

		String[] operadores, numeros;
		int j =0;
				
		// Separa os numeros dos operadores em vetores separados
		operadores	= valores.split("\\d+"); //identifica os numeros
		numeros		= valores.split("\\+|\\-|\\*|\\/|\\%|\\$|\\§|\\#|\\@"); //identifica os caracteres especiais
		
		//apenas imprime pra verificar
		for (int i=0; i < numeros.length; i++){
			System.out.println("val: "+numeros[i].trim());
		}for (int i=0; i < operadores.length; i++){			//primeiro sempre fica vazio
			System.out.println("op: "+operadores[i]);
		}

		

		Double result = Double.parseDouble(numeros[0].trim());
		for (int i=1; i< operadores.length; i++){
			operadores[i] = operadores[i].trim();

			Double aux = Double.parseDouble(numeros[i].trim());
			//System.out.println(operadores[i].trim());
			//System.out.println(numeros[i].trim());
			
			switch(operadores[i].charAt(0)){
				case '*': result = result + aux; break; //soma
				case '/': result = result - aux; break; //subtração
				case '+': result = result * aux; break; //multiplicação
				case '-': result = result / aux; break; //divisão
				case '%': result = result % aux; break; //mod
			}

			System.out.println("result: "+result);
			

			System.out.println(result);
		}

		return result.toString();
	}
	
}