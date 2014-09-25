/**
* Interpretador da linguagem 'Ateka'.
*
* Trabalho realizado para a matéria de Programação I do curso 
* Ciencia da Computação - UFFS.
* 
* Por:
* Carine Viecile <carine.viecile@gmail.com>
* Tiago A. Debastiani <debastianister@gmail.com>
*/

import java.util.Scanner;
import java.io.*;

class Asteka {

	public static void main(String args[]) {
		File f = new File(args[0]);
		Scanner sc = new Scanner(f);
		String linha[] = new String[2000];
		int i=0;
		
		while (sc.hasNext()){
			linha[i] = sc.nextLine();
			i++;
		}
					
		for (i=0; linha[i] != null; i++){
			System.out.println(i + ": " + linha[i] + ".");
		}
	}
}