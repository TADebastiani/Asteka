import java.util.Scanner;
import java.util.regex.Pattern;

class Funcoes{
	
	public void decodLinha(String linha[]){
		String auxVar, auxVal;
		Scanner sc;
		int x;

		for (int line=0; linha[line] != null; line++){
			sc = new Scanner(linha[line]);

			if (linha[line].startsWith("var")){
				sc.next();
				auxVar = sc.next().replaceAll(":",""); 

				if (sc.hasNext("!")){
					auxVal = linha[line].substring(linha[line].indexOf("!")+2, linha[line].indexOf(":"));
					// System.out.println(auxVal);
					Variaveis.setVariavel(auxVar, auxVal);
				}else {
					Variaveis.setVariavel(auxVar, null);
				}

				//Variaveis.imprimeVariavel(i);
			}

			else if (linha[line].startsWith("imprime")){
			
				 imprime(linha[line], line);
			}

			else if (linha[line].startsWith("leia")){

				leia(linha[line]);
			}

		}
	}

	public void imprime(String linha, int i){
		String aux;
				
		//se for só o comando imprime, iprime uma linha em branco
		if (linha.equals("imprime:")){
			System.out.println();
		
		// verifica se é uma frase ou uma variável
		}else if (linha.contains("'")) { // se tiver ' é uma string
			
			// para imprimir na próxima linha dentro da frase
			if (linha.contains("_")){
				linha = linha.replaceAll("_","\n");
			}
					
			// imprime o que estiver entre as aspas simples
			System.out.print("imprimindo: " + linha.substring(linha.indexOf("'")+1, linha.indexOf("'",linha.indexOf("'")+2)));


		}else { // se não, é uma variavel
			
			// "pega" a palavra entre o 'e' do imprime e do :
			aux = linha.substring(linha.indexOf("e ")+2, linha.indexOf(":"));
						
			// verifica se a variavel existe e imprime
			if (Variaveis.existeVariavel(aux)){ 
				System.out.println("imprimindo: " + Variaveis.getVariavel(aux));
			} else{
				Erro.erro(2, i);
			}
		}
	}

	public void leia(String linha){
		String auxVar, auxVal;
		Scanner s = new Scanner(System.in);

		auxVar = linha.substring(linha.indexOf("'")+1, linha.indexOf("'",linha.indexOf("'")+2));

		auxVal = s.nextLine();

		if (Variaveis.existeVariavel(auxVar)){ 
				Variaveis.setValor(Variaveis.indiceVariavel(auxVar), auxVal);
		}
	}

}