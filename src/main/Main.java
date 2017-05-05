package main;
import imageProcessing.Image;
import imageProcessing.ChainCodes;
import io.EntradaTeclado;

/**
 * esta classe gerencia metodos implementados para processamento de imagens.
 * @author andre.moreira.souza@usp.br, guilherme.menegali@usp.br
 *
 */
public class Main {
	
	/**
	 * recebe nome dos arquivos e calcula as propriedades de cada imagem.
	 * @param args
	 */
	public static void main(String[] args) {
		while(true){
			Image img;
			ChainCodes chain;
			System.out.print("Digite o nome do arquivo ou '-1' para sair: ");
			String s = EntradaTeclado.nextLine();
			if(s.equals("-1")) break;
			try{
				img = new Image(s);
				chain = new ChainCodes(s);
			}catch(Exception e){
				System.out.println("Arquivo invalido.");
				continue;
			}
			System.out.println("Arquivo: "+s);
			System.out.println("Ponto inicial: " + "(" + img.start()[0] + ", " + img.start()[1] + ")");
			System.out.println("Altura: " + img.height());
			System.out.println("Largura: " + img.width());
			System.out.println("Numero de pontos da borda: " + chain.getBpoints());
			System.out.println("Tamanho da borda: " + chain.getBsize());
		}
	}
}

