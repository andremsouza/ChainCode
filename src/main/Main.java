package main;
import imageProcessing.Image;
import io.EntradaTeclado;

public class Main {

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
		}
	}
}

