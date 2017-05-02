package file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageFile {
	
	public static BufferedImage getImg(String filename) throws IOException{
		return ImageIO.read(new File(filename));
	}
}
