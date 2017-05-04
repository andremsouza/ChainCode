package main;
import imageProcessing.Image;
import io.EntradaTeclado;

/**
 * esta classe gerencia metodos implementados para processamento de imagens.
 * @author andre
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
			System.out.print("Digite o nome do arquivo ou '-1' para sair: ");
			String s = EntradaTeclado.nextLine();
			if(s.equals("-1")) break;
			try{
				img = new Image(s);
			}catch(Exception e){
				System.out.println("Arquivo invalido.");
				continue;
			}
			System.out.println("Start: " + img.start()[0] + ", " + img.start()[1]);
			System.out.println("Height: " + img.height());
			System.out.println("Width: " + img.width());
		}
	}
}

