package imageProcessing;

import java.awt.image.BufferedImage;
import java.io.IOException;

import file.ImageFile;

public class Image {
	private BufferedImage img;
	
	public Image(String filename) throws IOException{
		this.img = ImageFile.getImg(filename);
	}
	
	public int[] start(){
		
		for(int i=0;i<img.getHeight(); i++){
			for(int j=0;j<img.getWidth(); j++){
				if(img.getRGB(j, i) != -1) return new int[] {j, i};
			}
		}
		return new int[] {-1, -1};
	}
}
