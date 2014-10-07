class Operacoes{
	

	public void verificaOperador(String linha, String [] guardaVar, double [] guardaValores, int linhasGuardaVar){

		String aux = "vazio", antesigual, atribui = "vazio";
		double res, auxiliar, auxiliar2, auxiliar3;
		char operador = 0;
		int cont_atribui = 1, atribu = 0;





		antesigual = linha.substring(0, linha.indexOf("!"));
		//possivel verificação de erro

		//verifica o token e atribui ele ao operador
		for(int i = 0; i < linha.length(); i++){
			if(linha.charAt(i) == '+' || linha.charAt(i) == '-' || linha.charAt(i) == '*' || linha.charAt(i) == '/' || linha.charAt(i) == '%'){
				operador = linha.charAt(i);
			}
			else if(linha.charAt(i) == '!'){
				atribui = linha.substring(linha.indexOf("!")+1, linha.indexOf(":"));//string que recebe o que será atribuido
				atribu = 1;
			}
			else if(operador != 0){
				res = Opera(operador, linha, guardaVar, guardaValores, linhasGuardaVar);
				valorAtual(res, aux, guardaVar, guardaValores, linhasGuardaVar );

				break;

			}

		}
	}


		//verifica os valores de antes do token
		public double antesToken(String aux1, String [] guardaVar, double [] guardaValores, int linhasGuardaVar){
			double auxiliar = 0;
			int i = 0;

			if(aux1.matches("^[0.0-9.0]*$")){
				auxiliar = Double.parseDouble(aux1);
			}
			else{
				for(i =0; i < linhasGuardaVar; i++){
					if(guardaVar[i].equals(aux1)){
						auxiliar = guardaValores[i];
					}
				}
			}
			return auxiliar;//retorna o valor após o =
		} 

		//verifica os valores depois do token
		public double depoisToken(String aux2, String [] guardaVar, double [] guardaValores, int linhasGuardaVar){
			double auxiliar = 0;
			int i = 0;

			if(aux2.matches("^[0.0-9.0]*$")){
				auxiliar = Double.parseDouble(aux2);
			}
			else{
				for(i =0; i < linhasGuardaVar; i++){
					if(guardaVar[i].equals(aux2)){
						auxiliar = guardaValores[i];
					}
				}
			}
			return auxiliar;//retorna o valor após o =
		}

		public void valorAtual (double resultado, String aux, String [] guardaVar, double [] guardaValores, int linhasGuardaVar ){
			for (int i = 0; i<linhasGuardaVar; i++) {
				if(guardaVar[i].equals(aux)){
					guardaValores[i] = resultado;
					break;
				}
				
			}
		}

		public double Opera(char operando, String linha, String [] guardaVar, double [] guardaValores, int linhasGuardaVar){
			double result = 0;
			String aux1 = linha.substring(linha.indexOf("!")+1, linha.indexOf(operando));
			String aux2 = linha.substring(linha.indexOf(operando)+1, linha.indexOf(":"));
			
			double auxiliar2 = antesToken(aux1, guardaVar, guardaValores, linhasGuardaVar);
			double auxiliar3 = depoisToken(aux2, guardaVar, guardaValores, linhasGuardaVar);

			if(operando == '*'){
				result = auxiliar2 + auxiliar3;

			}else if(operando == '/'){
				result = auxiliar2 - auxiliar3;

			}else if(operando == '+'){
				result = auxiliar2 * auxiliar3;

			}else if(operando == '-'){
				result = auxiliar2 / auxiliar3;

			}else if(operando == '%'){
				result = auxiliar2 % auxiliar3;

			}else if(operando == '!'){
				result = auxiliar2;
		        }
		return result;
		}
	}	
	

