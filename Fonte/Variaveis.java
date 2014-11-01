class Variaveis{

	static private String variaveis[] = new String[2000];
	static private String valores[] = new String[2000];
	static int index;

	// retorna o valor da variável no indice x
	static public String getVariavel(int x){
		return valores[x];
	}

	// retorna o valor da variável com o nome recebido
	static public String getVariavel(String variavel){
		return valores[indiceVariavel(variavel)];
	}

	// guarda o nome da variavel e seu valor em dois vetores no mesmo indice
	static public void setVariavel(String variavel, String valor){
		variaveis[index] = variavel;
		valores[index] = valor;
		index++;
	}

	// atribui um novo valor a variavel existente
	static public void setValor(String variavel, String valor){
		valores[indiceVariavel(variavel)] = valor;
	}

	// imprime o nome da variavel e seu valor
	static public void imprimeVariavel(int index){
		System.out.println("Variavel: " + variaveis[index]);
		System.out.println("Valor: " + valores[index]);
	}

	// procura no vetor de variaveis a variavel recebida como parametro
	static public int indiceVariavel(String variavel){
		//	não pode ser substituido por <variaveis.indexOf()>
		int ind = -1;
		variavel = variavel.trim();
		for (int i=0; variaveis[i] != null; i++) {
			if (variaveis[i].equals(variavel)){
				ind = i;
			}
		}

		return ind;
	}

	//verifica se a variavel dada como parâmetro existe
	static public boolean existeVariavel(String variavel){

		if (indiceVariavel(variavel) > -1){
			return true;
		}else {
			return false;
		}
	}
}