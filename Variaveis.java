class Variaveis{

	private String variaveis[] = new String[2000];
	private String valores[] = new String[2000];
	protected int index; // possiveis classes filha não podem alterar

	// retorna o valor da variável no indice x
	public String getVariavel(int x){
		return this.valores[x];
	}

	// guarda o nome da variavel e seu valor em dois vetores no mesmo indice
	public void setVariavel(String variavel, String valor){
		this.variaveis[index] = variavel;
		this.valores[index] = valor;
		this.index++;
	}

	// imprime o nome da variavel e seu valor
	public void imprimeVariavel(int index){
		System.out.println("Variavel: " + this.variaveis[index]);
		System.out.println("Valor: " + this.valores[index]);
	}

	// procura no vetor de variaveis se existe a variavel recebida como parametro
	public int procuraVariavel(String variavel){
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
}