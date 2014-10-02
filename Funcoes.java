import java.util.Scanner;

class Funcoes{
	
	private String auxVar, auxVal;
	private Scanner sc;


	public void decodLinha(String linha[], Variaveis var){
		int x;

		for (int i=0; linha[i] != null; i++){		
			sc = new Scanner(linha[i]);

			if (linha[i].startsWith("var")){
				sc.next();
				auxVar = sc.next().replaceAll(":",""); 

				// System.out.println(auxVar);
				
				if (sc.hasNext("|")){
					auxVal = linha[i].substring(linha[i].indexOf("|")+2, linha[i].indexOf(":"));
					// System.out.println(auxVal);
					var.setVariavel(auxVar, auxVal);
				}else {
					var.setVariavel(auxVar, null);
				}

				//var.imprimeVariavel(i);
			}

			else if(linha[i].startsWith("imprime")){
				if (linha[i].contains("'")){ // se tiver ' é uma string

					// alterar para imprimir sem enter
					System.out.println("imprimindo: " + linha[i].substring(linha[i].indexOf("'")+1, linha[i].indexOf("':")));

				}else { // se não, é uma variavel
					auxVar = linha[i].substring(linha[i].indexOf("e ")+2, linha[i].indexOf(":"));
					x = var.procuraVariavel(auxVar);
					
					// verifica se a variavel existe, transformar em um metodo em Variaveis
					if (x > -1){ 
						System.out.println("imprimindo: " + var.getVariavel(x));
					} else{
						Erro.erro(2, i);
					}
				}

				// imprime(linha[i], i);
			}

		}
	}

	// AINDA EM TESTE

	/* public void imprime(String linha, int i){
		 String aux;
		 int x;
		// verifica se é uma frase ou uma variável
		if (linha.contains("'")){ // se tiver ' é uma string

			// alterar para imprimir sem enter
			System.out.printf("imprimindo: " + linha.substring(linha.indexOf("'")+1, linha.indexOf("':")));

		}else { // se não, é uma variavel
			
			aux = linha.substring(linha.indexOf("e ")+2, linha.indexOf(":"));
			x = Variaveis.procuraVariavel(auxVar);
			
			// verifica se a variavel existe, transformar em um metodo em Variaveis
			if (x > -1){ 
				System.out.println("imprimindo: " + Variaveis.getVariavel(x));
			} else{
				Erro.erro(2, i);
			}
		}
	} */

}