/**
* 
*
* Interpretador da linguagem 'Asteka'.
*
* Trabalho realizado para a matéria de Programação I do curso 
* Ciencia da Computação - UFFS.
* 
* Autores:
*	Carine Viecile <carine.viecile@gmail.com>
*	Tiago A. Debastiani <debastianister@gmail.com>
*
*
* Para executar, rode o seguinte comando no terminal:
*	java Asteka ./teste.ast
* 
* Por Fernando Bevilacqua <fernando.bevilacqua@uffs.edu.br>
*/

import java.util.Scanner;
import java.io.*;

class Asteka {
	public static void main(String args[]) throws Exception, Erro{
        File file;
        Scanner sc;
        String linha[] = new String[2000]; // arquivo pode ter, no máximo, 2000 linhas.
		Funcoes f;
		        
        try {
            // Referencia o arquivo. args[0] conterá os dados passados pela linha de comando.
            file = new File(args[0]);
            // Mandamos o Scanner ler a partir do arquivo.
            sc = new Scanner(file);
            // Instanciamos o interpretador.
            f = new Funcoes();
            
            // Lemos todas as linhas do arquivo para dentro do
            // vetor "linha".
            int i = 0;
            while(sc.hasNextLine()) {
                linha[i] = sc.nextLine();
                
                linha[i] = linha[i].trim(); //remove os espaços do inicio e do final da linha.

				if (linha[i].length() > 0 && !linha[i].endsWith(":")){
					if(linha[i].startsWith("se") || linha[i].startsWith("laco")){

					}else{
						throw new Erro(1, i,"");
					}
				}

				i++;
            }
            
            // Inicializamos o interpretador com o vetor de linhas. A partir
            // desse ponto, o objeto "f" irá interpretar o código lido do arquivo.
            f.interpreta(linha, i);
            /*
			System.out.println();
			System.out.println("-- IMPRIMINDO O VETOR LINHA --");
			for (i=0; linha[i] != null; i++){
				System.out.println(i + ": " + linha[i]);
			}*/
			System.out.print("\n -- IMPRIMINDO AS 5 PRIMEIRAS VARIAVEIS --\n");
			for (i=0; i<5 ; i++){
				Variaveis.imprimeVariavel(i);
			}
			


        } catch (IOException e) {
            System.out.println("Nao consegui ler o arquivo: " + (args.length > 0 ? args[0] : "(desconhecido)"));
            System.out.println("Uso:");
            System.out.println("\tjava Asteka /caminho/para/arquivo.ast");
            System.out.println("    ou");
            System.out.println("\tjava -jar Asteka /caminho/para/arquivo.ast");
        }
        
    }
	
}