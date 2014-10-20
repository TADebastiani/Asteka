class Operacoes extends Variaveis{
	

	 static public Double Operacoes(String variavel, String valores){

		String aux = "vazio", antesigual, atribui = "vazio";
		double res, auxiliar, valor;
		char operador = 0;
		int cont_atribui = 1, atribu = 0;
		
		//verifica o token e atribui ele ao operador
		for(int i = 0; i < valores.length(); i++){
			if(variavel.charAt(i) == '+' || variavel.charAt(i) == '-' || variavel.charAt(i) == '*' || variavel.charAt(i) == '/' || variavel.charAt(i) == '%'){
				operador = variavel.charAt(i);
			}

		}

		//repassa as variaveis auxiliares 
		String aux1 = variavel.substring(variavel.indexOf("!")+1, variavel.indexOf(operador));
		String aux2 = variavel.substring(variavel.indexOf(operador)+1, variavel.indexOf(":"));

		//repassa os
		Double auxiliar2 = Double.parseDouble(aux1);
		Double auxiliar3 = Double.parseDouble(aux2);

			double result = 0;

			if(operador== '*'){
				result = auxiliar2 + auxiliar3;

			}else if(operador == '/'){
				result = auxiliar2 - auxiliar3;

			}else if(operador == '+'){
				result = auxiliar2 * auxiliar3;

			}else if(operador == '-'){
				result = auxiliar2 / auxiliar3;

			}else if(operador == '%'){
				result = auxiliar2 % auxiliar3;

			}else if(operador == '!'){
				result = auxiliar2;
		        }
		return result;
		
		}
}

		

		
		
		
	

