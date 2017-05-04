package imageProcessing;

import java.awt.image.BufferedImage;
import java.io.IOException;
import file.ImageFile;

/**
 * esta classe cria e calcula propriedades de uma imagem do tipo TYPE_BYTE_GRAY, a partir de um arquivo.
 * @author andre
 *
 */
public class Image {
	private BufferedImage img;
	
	/**
	 * cria objeto Image, criado a partir do arquivo "filename"
	 * @param filename - Nome do arquivo a ser aberto.
	 * @throws IOException
	 */
	public Image(String filename) throws IOException{
		this.img = ImageFile.getImg(filename);
	}
	
	/**
	 * calculo ponto inicial da imagem. Defini-se como ponto inicial o primeiro RGB(x, y) != -1.
	 * @return coordenadas do ponto inicial (x, y). 
	 */
	public int[] start(){
		for(int i=0;i<img.getHeight(); i++){
			for(int j=0;j<img.getWidth(); j++){
				if(img.getRGB(j, i) != -1) return new int[] {j, i};
			}
		}
		return new int[] {-1, -1};
	}
	
	/**
	 * calculo da altura do objeto contido na imagem.
	 * @return a altura do objeto contido na imagem.
	 */
	public int height(){
		int h=0, aux=0;
		for(int i=0;i<img.getWidth(); i++){
			aux=0;
			for(int j=0;j<img.getHeight(); j++){
				if(img.getRGB(i,  j) != -1) aux++;
				else h=Integer.max(h, aux); 
			}
		}
		return Integer.max(h, aux);
	}
	
	/**
	 * calculo da largura do objeto contido na imagem.
	 * @return a largura do objeto contido na imagem.
	 */
	public int width(){
		int w=0, aux=0;
		for(int i=0;i<img.getHeight();i++){
			aux=0;
			for(int j=0;j<img.getWidth();j++){
				if(img.getRGB(j, i) != -1) aux++;
				else w=Integer.max(w, aux);
			}
		}
		return Integer.max(w, aux);
	}
	/* TO DO
	public int border(){
		
	}
	*/
}
