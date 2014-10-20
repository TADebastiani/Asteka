import java.util.Scanner;
import java.util.regex.Pattern;

class Funcoes{

	Boolean	senao = false;

	public void interpreta(String linha[], int totalLinhas) throws Erro{
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
					
					Variaveis.setVariavel(auxVar, auxVal);
				}else {
					Variaveis.setVariavel(auxVar, null);
				}

			}

			else if (linha[line].startsWith("imprime")){
			
				 imprime(linha[line], line);
			}

			else if (linha[line].startsWith("leia")){

				leia(linha[line]);
			}

			else if (linha[line].startsWith("se ")){

				line = se(linha, line);

			}

			else if (linha[line].startsWith("senao")){

				line = senao(linha, line);
			}

			else if (linha[line].startsWith("laco")){

				line = laco(linha, line);
			}

		}
	}

	public void imprime(String linha, int i) throws Erro{
		String aux;
		Boolean novaLinha = false;
				
		//se for só o comando imprime, iprime uma linha em branco
		if (linha.equals("imprime:")){
			System.out.println();
		
		// verifica se é uma frase ou uma variável
		}else if (linha.contains("'")) { // se tiver ' é uma frase
			
			// para imprimir na próxima linha dentro da frase
			if (linha.contains("_")){
				linha = linha.replaceAll("_","\n");
			}
					
			// imprime o que estiver entre as aspas simples
			System.out.print("imprimindo: " + linha.substring(linha.indexOf("'")+1, linha.indexOf("'",linha.indexOf("'")+1)));


		}else { // se não, é uma variavel
			if (linha.contains("_")){
				linha = linha.replaceAll("_","");
				novaLinha = true;
			}
			// "pega" a palavra a partir do 'e' do imprime
			aux = linha.substring(linha.indexOf("e ")+2).replaceAll(":","").trim();

			// verifica se a variavel existe e imprime
			if (Variaveis.existeVariavel(aux)){ 
				System.out.print("imprimindo: " + Variaveis.getVariavel(aux));
				if (novaLinha){
					System.out.println();
				}
			} else{
				throw new Erro(2, i,"");
				//Erro.erro(2, i);
			}
		}
	}

	public void leia(String linha){
		String auxVar, auxVal;
		Scanner s = new Scanner(System.in);

		auxVar = linha.substring(linha.indexOf("'")+1, linha.indexOf("'",linha.indexOf("'")+1));

		auxVal = s.nextLine();

		if (Variaveis.existeVariavel(auxVar)){ 
				Variaveis.setValor(Variaveis.indiceVariavel(auxVar), auxVal);
		}
	}

	public int se(String [] linha, int line) throws Erro{
		Boolean expr;
		int countIf=0;
		
		expr = new Boolean(linha[line].substring(linha[line].indexOf("[")+1, linha[line].indexOf("]")).trim());
		
		if (expr){
			return line;
		}
		else{
			for (int i = line; linha[i] != null; i++){
				if (linha[i].startsWith("se ")){
					countIf++;
				}

				if (linha[i].equals("endse:") || linha[i].startsWith("senao")){
					if(countIf > 0){
						countIf--;
					}

					if(countIf == 0){
						if (linha[i].startsWith("senao")){
							senao = true;
						}
						return i;
					}
				}
			}

			throw new Erro(3, line, "endse:");
		}
	}

	public int senao(String [] linha, int line) throws Erro{
		Boolean expr = true;
		int countElse = 0;
		
		if (linha[line].contains("[")){
			expr = new Boolean(linha[line].substring(linha[line].indexOf("[")+1, linha[line].indexOf("]")).trim());
		}

		if (expr && senao){
			senao = false;
			return line;
		}
		else{
			for (int i = line; linha[i] != null; i++){
				if (linha[i].startsWith("senao")){
					countElse++;
					
				}
				if (linha[i].equals("endsenao:")){
					if(countElse > 0){
						countElse--;
					}
					if(countElse == 0){
						return i;
					}
				}
			}
			throw new Erro(3, line, "endsenao:");
		}
	}

	public int laco(String [] linha, int line) throws Erro{
		Boolean expr = new Boolean(linha[line].substring(linha[line].indexOf("[")+1, linha[line].indexOf("]")).trim());
		int countWhile = 0;

		if (expr){
			return line;

		}else{
			for (int i = line; linha[i] != null; i++){
				if (linha[i].startsWith("laco")){
					countWhile++;
					
				}
				if (linha[i].equals("endlaco:")){
					if(countWhile > 0){
						countWhile--;
					}
					if(countWhile == 0){
						return i;
					}
				}
			}
			throw new Erro(3, line, "endsenao:");
		}

	}


}