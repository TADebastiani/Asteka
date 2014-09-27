class Variaveis{

	private String variaveis[] = new String[2000];
	private String valores[] = new String[2000];
	protected int index; // possiveis classes filha não podem 

	public String getVariavel(int index){
		return this.valores[index];
	}

	public void setVariavel(String variavel, String valor){
		this.variaveis[index] = variavel;
		this.valores[index] = valor;
		this.index++;
	}

	public int procuraVariavel(String variavel){
		/** 
		*	pode ser substituido por <variaveis.indexOf()>
		*/
		for (i=0; variaveis[index] != null; index++) {
			if (variaveis[index] == variavel){
				index;
			}else{
				return null; // em teste
			}

		}
	}
}