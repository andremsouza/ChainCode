package file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * esta classe abre um arquivo que contem uma imagem, a partir de "filename".
 * @author andre
 *
 */
public class ImageFile {
	
	/**
	 * abre um arquivo que contem uma imagem, a partir de "filename".
	 * @param filename - nome do arquivo a ser aberto
	 * @return a imagem aberta.
	 * @throws IOException
	 */
	public static BufferedImage getImg(String filename) throws IOException{
		return ImageIO.read(new File(filename));
	}
}
