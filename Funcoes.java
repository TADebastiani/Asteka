import java.util.Scanner;

class Funcoes{
	
	Erro erro;


	public void decodLinha(String linha[], Variaveis var){
		String auxVar, auxVal;
		Scanner sc;
		int x;

		for (int i=0; linha[i] != null; i++){		
			sc = new Scanner(linha[i]);

			if (sc.hasNext("var")){
				sc.next();
				auxVar = sc.next().replaceAll(":",""); 

				// System.out.println(auxVar);
				
				if (sc.hasNext("=")){
					auxVal = linha[i].substring(linha[i].indexOf("=")+2, linha[i].indexOf(":"));
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
						erro = new Erro(2, i);
					}
				}
			}

		}
	}

}