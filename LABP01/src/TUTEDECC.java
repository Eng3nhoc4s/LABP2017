import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TUTEDECC {

	private static final int ALPHABET_ALPHA_A_OFFSET = 65;
	private static final int ALPHABET_LOWER_A_OFFSET = 97;

	private static final char[] alfabetoL = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	private static final char[] alfabetoA = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'G', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	/**
	 * Obscurecimento por omissão de letras - que retira do texto original um
	 * conjunto dado de letras.
	 * 
	 * @param in
	 *            BufferedReader de entrada com o texto a processar
	 * @param letras
	 *            BufferedReader de entrada com as letras a remover
	 * @param out
	 *            BufferedWriter com o texto processado
	 * @throws IOException
	 *             Se o ficheiro de entrada não for encontrado
	 */
	public static void retiraLetras(BufferedReader in, BufferedReader letras, BufferedWriter out) throws IOException {

		StringBuilder sb = new StringBuilder();

		String letrasParaRetirar = letras.readLine();

		String linhaActual;

		while ((linhaActual = in.readLine()) != null) {

			for (int i = 0; i < letrasParaRetirar.length(); i++) {

				linhaActual = linhaActual.replaceAll(Character.toString(letrasParaRetirar.charAt(i)), "");

			}

			sb.append(linhaActual);
			sb.append("\n");
		}

		out.write(sb.toString());

		in.close();
		letras.close();
		out.close();
	}

	/**
	 * Obscurecimento por trocar de letras - que depois de omitir do texto a
	 * pontuação e espaçamento, faz a substituição de todas as letras,
	 * maiúsculas, ou minúsculas “e” por 3, “s” por 5, “t” por 7 e “o” por 0.
	 * 
	 * @param in
	 *            BufferedReader de entrada com o texto a processar
	 * @param out
	 *            BufferedWriter com o texto processado
	 * @throws IOException
	 *             Se o ficheiro não existir
	 */
	public static void letrasPorNumeros(BufferedReader in, BufferedWriter out) throws IOException {

		StringBuilder sb = new StringBuilder();

		String linhaActual;

		while ((linhaActual = in.readLine()) != null) {

			String prontoParaAvaliar = linhaActual.toLowerCase();

			for (int i = 0; i < prontoParaAvaliar.length(); i++) {

				char caracterActual = prontoParaAvaliar.charAt(i);

				switch (caracterActual) {
				case 'e':
					sb.append("3");
					break;

				case 's':
					sb.append("5");
					break;

				case 't':
					sb.append("7");
					break;

				case 'o':
					sb.append("0");
					break;

				default:
					sb.append(caracterActual);
				}
			}

			sb.append("\n");
		}

		out.write(sb.toString());

		in.close();
		out.close();
	}

	/**
	 * Obscurecimento por rotação das letras - que após apagar a pontuação e os
	 * espaços entre as palavras no texto original, aplica uma rotação de n
	 * posições a cada uma das letras, isto é, se a rotação for de 5, o “a” deve
	 * ser substituído por “f”, o “b” por “g”,..., “z” por “g”.
	 * 
	 * @param in
	 *            BufferedReader de entrada com o texto a processar
	 * @param out
	 *            BufferedWriter com o texto processado
	 * @param i
	 *            Valor do incremento da letra do alfabeto
	 * @throws IOException
	 *             Se o ficheiro não existir
	 */
	public static void rotacao(BufferedReader in, BufferedWriter out, int i) throws IOException {

		StringBuilder sb = new StringBuilder();

		String linhaActual;

		while ((linhaActual = in.readLine()) != null) {

			String paraProcessar = linhaActual.replaceAll("\\W", "");

			for (int j = 0; j < paraProcessar.length(); j++) {

				char processarChar = paraProcessar.charAt(j);

				if (Character.isLowerCase(processarChar))
					sb.append(alfabetoL[((processarChar - ALPHABET_LOWER_A_OFFSET) + i) % 26]);
				else
					sb.append(alfabetoA[((processarChar - ALPHABET_ALPHA_A_OFFSET) + i) % 26]);
			}

			sb.append("\n");
		}

		out.write(sb.toString());

		in.close();
		out.close();
	}

	/**
	 * Conta as ocorrências de cada uma das letras do alfabeto
	 * 
	 * @param in
	 *            BufferedReader de entrada com o texto a processar
	 * @param out
	 *            BufferedWriter com o texto processado
	 * @throws IOException
	 *             Se o ficheiro não existir
	 */
	public static void frequenciasLetras(BufferedReader in, BufferedWriter out) throws IOException {

		int[] contadorAlfabeto = new int[alfabetoL.length];

		StringBuilder sb = new StringBuilder();

		String linhaActual;

		while ((linhaActual = in.readLine()) != null) {

			String prontoParaAvaliar = linhaActual.replaceAll("\\W", "").toLowerCase();

			while (prontoParaAvaliar.length() > 0) {

				// Count number of chars
				int quantidadeDeCaracteres = prontoParaAvaliar.length();

				// Get the current char
				char caracterActual = prontoParaAvaliar.charAt(0);

				// Remove all ocurrences of that char
				prontoParaAvaliar = prontoParaAvaliar.replaceAll(Character.toString(caracterActual), "");

				// Find the difference
				int delta = quantidadeDeCaracteres - prontoParaAvaliar.length();

				// Increment the count
				contadorAlfabeto[caracterActual - ALPHABET_LOWER_A_OFFSET] += delta;

			}
		}

		// Print it!
		for (int i = 0; i < contadorAlfabeto.length; i++) {
			sb.append(alfabetoL[i] + ": " + contadorAlfabeto[i]);
			sb.append("\n");
		}

		out.write(sb.toString());
		in.close();
		out.close();
	}

	/**
	 * Conta as ocorrências de padrões dados
	 * 
	 * @param in
	 *            BufferedReader de entrada com o texto a processar
	 * @param padrao
	 *            BufferedReader de entrada com o texto do padrão
	 * @param out
	 *            BufferedReader de saida com o texto processado
	 * @throws IOException
	 *             Se o ficheiro não existir
	 */
	public static void frequenciasPadrao(BufferedReader in, BufferedReader padroes, BufferedWriter out)
			throws IOException {

		String padrao;
		String linhaActual;

		StringBuilder sb = new StringBuilder();

		ArrayList<String> listaDePadroes = new ArrayList<String>();

		// Obter todos os padroes, 1 por linha no ficheiro
		while ((padrao = padroes.readLine()) != null) {

			listaDePadroes.add(padrao);

		}

		int[] contadorDePadroes = new int[listaDePadroes.size()];

		while ((linhaActual = in.readLine()) != null) {

			int indexPadroes = 0;

			// Testar todos os padroes contra a linha corrente
			while (indexPadroes < listaDePadroes.size()) {

				String lineBackup = linhaActual;

				int index = linhaActual.indexOf(listaDePadroes.get(indexPadroes));

				while (index != -1) {
					contadorDePadroes[indexPadroes]++;
					lineBackup = lineBackup.substring(index + 1);
					index = lineBackup.indexOf(listaDePadroes.get(indexPadroes));
				}

				indexPadroes++;
			}
		}

		// Print it
		for (int i = 0; i < listaDePadroes.size(); i++) {
			sb.append(listaDePadroes.get(i) + ": " + contadorDePadroes[i]);
			sb.append("\n");
		}

		out.write(sb.toString());
		in.close();
		out.close();
	}

}
