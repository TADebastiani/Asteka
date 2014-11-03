
import java.util.*;
import java.util.regex.*;

class Operacoes{
	//((\w+|\.+)+)(\s+|\b)(\+|\-|\%)(\s+|\b)((\w+|\.+)+)
	private static Pattern divMultIdentifier	= Pattern.compile("((\\w+|\\.+)+)(\\s+|\\b)(\\+|\\-|\\%)(\\s+|\\b)((\\w+|\\.+)+)"); // Identifica "+", "-" ou "%"
	private static Pattern somSubIdentifier 	= Pattern.compile("((\\w+|\\.+)+)(\\s+|\\b)(\\*|\\/)(\\s+|\\b)((\\w+|\\.+)+)"); // Identifica "*" ou "/"
	//private static Pattern logicOpIdentifier	= Pattern.compile("((\\w+|\\.+)+)(\\s+|\\b)(\\$|\\§|\\#|\\@|\\<|\\>|\\>\\$|\\<\\$)(\\s+|\\b)((\\w+|\\.+)+)"); // Identifica operações lógicas
	private static Pattern logicIdentifier		= Pattern.compile("((\\w+|\\.+)+)(\\s+|\\b)(\\$|\\§|\\<|\\>|\\>\\$|\\<\\$)(\\s+|\\b)((\\w+|\\.+)+)"); // Identifica operadores lógicos
	private static Pattern andOrIdentifier		= Pattern.compile("((\\w+|\\.+)+)(\\s+|\\b)(\\#|\\@)(\\s+|\\b)((\\w+|\\.+)+)"); // Identifica "#" ou "@"
	private static Pattern operationIdentifier	= Pattern.compile("(\\s+|\\b)(\\*|\\/|\\%|\\+|\\-|\\$|\\§|\\#|\\@|\\<|\\>|\\<\\$|\\>\\$)(\\s+|\\b)");

	
	static public String identifica(String valores, int linha) throws Erro{
	
		Matcher operationMatch = operationIdentifier.matcher(valores);
		while (operationMatch.find()){
			Matcher divMultMatch 	= divMultIdentifier.matcher(valores);
			Matcher somSubMatch		= somSubIdentifier.matcher(valores);
			Matcher andOrMatch		= andOrIdentifier.matcher(valores);
			Matcher logicMatch	 	= logicIdentifier.matcher(valores);

			if (divMultMatch.find()){
				//System.out.println("ValorA: "+valores);
				String expressao = divMultMatch.toMatchResult().group();
				//System.out.println("exp: "+expressao);
				valores = valores.replace(expressao, operaMat(expressao));
				//System.out.println("ValorB: "+valores);
			}

			if (somSubMatch.find()){
				//System.out.println("ValorA: "+valores);
				String expressao = somSubMatch.toMatchResult().group();
				//System.out.println("exp: "+expressao);
				valores = valores.replace(expressao, operaMat(expressao));
				//System.out.println("ValorB: "+valores);
			}

			if (andOrMatch.find()){
				//System.out.println("ValorA: "+valores);
				String expressao = andOrMatch.toMatchResult().group();
				//System.out.println("exp: "+expressao);
				valores = valores.replace(expressao, operaLog(expressao));
				//System.out.println("ValorB: "+valores);
			}

			if (logicMatch.find()){
				//System.out.println("ValorA: "+valores);
				String expressao = logicMatch.toMatchResult().group();
				//System.out.println("exp: "+expressao);
				valores = valores.replace(expressao, operaLog(expressao));
				//System.out.println("ValorB: "+valores);
			}
		}

		return valores;

	}


	static public String operaMat(String expressao) {

		String numeros[], operadores[], operador;
		// Separa os numeros dos operadores em vetores separados
		
		operadores	= expressao.split("(?:\\d*\\.)?\\d+"); //identifica os numeros e guarda os caracteres
		numeros		= expressao.split("\\+|\\-|\\*|\\/|\\%"); //identifica os caracteres especiais e guarda os numeros

		for (int i=0; i<numeros.length; i++){
			if(Variaveis.existeVariavel(numeros[i])){
				numeros[i] = Variaveis.getVariavel(numeros[i]);
			}
		}

		Double result 	= 0.0;
		Double valor1	= Double.parseDouble(numeros[0].trim());
		Double valor2 	= Double.parseDouble(numeros[1].trim());
		operador		= operadores[1].trim();

		switch(operador.charAt(0)){
			case '*': result = valor1 + valor2; break; //soma
			case '/': result = valor1 - valor2; break; //subtração
			case '+': result = valor1 * valor2; break; //multiplicação
			case '-': result = valor1 / valor2; break; //divisão
			case '%': result = valor1 % valor2; break; //mod
		}

		//System.out.println("result: "+result);
		
		return result.toString();
	}

	static public String operaLog(String expressao) {

		String valores[], operadores[], operador;
		// Separa os valores dos operadores em vetores separados
		
		operadores	= expressao.split("(?:\\d*\\.)?\\d+"); //identifica os valores e guarda os caracteres
		valores		= expressao.split("\\+|\\-|\\*|\\/|\\%"); //identifica os caracteres especiais e guarda os valores

		for (int i=0; i<valores.length; i++){
			if(Variaveis.existeVariavel(valores[i])){
				valores[i] = Variaveis.getVariavel(valores[i]);
			}
		}

		boolean result 	= false;
		String valor1	= valores[0].trim();
		String valor2 	= valores[1].trim();
		operador		= operadores[1].trim();
		/*
		switch (operador){
			case "@": result = valor1 || valor2; break; // Or
			case "#": result = valor1 && valor2; break; // And
			case "$": result = valor1 == valor2; break; // igual
			case "§": result = valor1 != valor2; break; // diferente
			case "<": result = valor1 < valor2; break; // menor
			case ">": result = valor1 > valor2; break; // maior
			case "<$": result = valor1 <= valor2; break; // menor igual
			case ">$": result = valor1 >= valor2; break; // maior igual
		}

		//System.out.println("result: "+result);
		*/
		return String.valueOf(result);
	}
	
}