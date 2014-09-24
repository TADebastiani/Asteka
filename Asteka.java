import java.util.Scanner;
import java.util.regex.Pattern;

class Asteka {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String linha[] = new String[2000];
		
		int i=0;
		while (100){
			linha[i] = sc.next();
			i++;
		}
				
		for (i=0; linha[i] != null; i++)
			System.out.println(i + ": " + linha[i] + ".");

	}
}