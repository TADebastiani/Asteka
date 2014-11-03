import java.util.Scanner;
import java.util.regex.Pattern;

class Funcoes{

	Boolean	senao = false;

	public void interpreta(String linha[], int totalLinhas) throws Erro{
		String auxVar, auxVal = null;
		int laco =0;
		

		for (int line=0; linha[line] != null; line++){
			

			if (linha[line].startsWith("var")){
				
				if (linha[line].contains("!")){
					auxVar = linha[line].substring(linha[line].indexOf("var")+3, linha[line].indexOf("!")).trim();
					auxVal = linha[line].substring(linha[line].indexOf("!")+2, linha[line].indexOf(":")).trim();
				}else{
					auxVar = linha[line].substring(linha[line].indexOf("var")+3, linha[line].indexOf(":")).trim();
				}

				declaracao(auxVar, auxVal, line);

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
			
			else if (linha[line].contains("!")){
				auxVar = linha[line].substring(0, linha[line].indexOf("!"));
				auxVal = linha[line].substring(linha[line].indexOf("!")+1, linha[line].indexOf(":"));

				atribuicao(auxVar, auxVal, line);
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
				linha = linha.replaceAll("_","");
				novaLinha = true;
			}
					
			// imprime o que estiver entre as aspas simples
			System.out.print(linha.substring(linha.indexOf("'")+1, linha.indexOf("'",linha.indexOf("'")+1)));
			if (novaLinha){
				System.out.println();
			}


		}else { // se não, é uma variavel
			if (linha.contains("_")){
				linha = linha.replaceAll("_","");
				novaLinha = true;
			}
			// "pega" a palavra a partir do 'e' do imprime
			aux = linha.substring(linha.indexOf("e ")+2).replaceAll(":","").trim();

			// verifica se a variavel existe e imprime
			if (Variaveis.existeVariavel(aux)){ 
				System.out.print(Variaveis.getVariavel(aux));
				if (novaLinha){
					System.out.println();
				}
			} else{
				throw new Erro(2, i,"");
				
			}
		}
	}

	public void leia(String linha){
		String auxVar, auxVal;
		Scanner s = new Scanner(System.in);

		auxVar = linha.substring(linha.indexOf("'")+1, linha.indexOf("'",linha.indexOf("'")+1));

		auxVal = s.nextLine();

		if (Variaveis.existeVariavel(auxVar)){ 
				Variaveis.setValor(auxVar, auxVal);
		}
	}

	public int se(String [] linha, int line) throws Erro{
		String aux = linha[line].substring(linha[line].indexOf("[")+1, linha[line].indexOf("]")).trim();;
		Boolean expr = Boolean.parseBoolean(Operacoes.identifica(aux, line));;
		int countIf=0;
		

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
		String aux;
		Boolean expr = true;
		int countElse = 0;
		
		if (linha[line].contains("[")){
			aux = linha[line].substring(linha[line].indexOf("[")+1, linha[line].indexOf("]")).trim();
			expr = Boolean.parseBoolean(Operacoes.identifica(aux, line));
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
		String aux = linha[line].substring(linha[line].indexOf("[")+1, linha[line].indexOf("]")).trim();
		Boolean expr = Boolean.parseBoolean(Operacoes.identifica(aux, line));
		
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
			throw new Erro(3, line, "endlaco:");
		}
	}

	public int endlaco(String[] linha, int line) throws Erro{
		return line;
	}

	private void atribuicao(String var, String valor, int linha) throws Erro{
		if(Variaveis.existeVariavel(var.trim())){
			if (valor.contains("+") || valor.contains("-") || valor.contains("*") || valor.contains("/") || valor.contains("%")){
				valor = Operacoes.identifica(valor, linha);
			}
			Variaveis.setValor(var, valor.trim());
		}else{
			throw new Erro(2, linha, var);
		}
	}

	private void declaracao(String var, String valor, int linha) throws Erro{
		
		if (valor.contains("+") || valor.contains("-") || valor.contains("*") || valor.contains("/") || valor.contains("%")){
			valor = Operacoes.identifica(valor, linha);
		}
		Variaveis.setVariavel(var, valor.trim());
		
	}



}