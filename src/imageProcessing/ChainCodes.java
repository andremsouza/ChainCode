package imageProcessing;

import java.io.IOException;

/**
 * Esta classe calcula o numero de pontos da borda, assim como seu tamanho, utilizando o algorítmo Chain Codes.
 * @author andre.moreira.souza@usp.br, guilherme.menegali@usp.br
 *
 */
public class ChainCodes extends Image{

	private int pixels[][];
	private int visited[][];
	private int bpoints;
	private double bsize;
	
	/**
	 * Construtor da classe. Calcula todos os elementos necessarios.
	 * @param filename - Nome do arquivo para a classe Image
	 * @throws IOException
	 */
	public ChainCodes(String filename) throws IOException{
		super(filename);
		bpoints = 0;
		bsize = 0;
		pixels = new int[super.img.getHeight()][super.img.getWidth()];
		visited = new int[super.img.getHeight()][super.img.getWidth()];
		
		for (int i = 0; i < super.img.getHeight(); i++) {
			for (int j = 0; j < super.img.getWidth(); j++) {
				pixels[i][j] = super.img.getRGB(j, i);
				if (pixels[i][j] != -1) pixels[i][j] = 1;
				else pixels[i][j] = 0;
				visited[i][j] = 0;
			}
		}
		chainCodes(super.start()[0], super.start()[1]);
		borderPoints();
	}
	
	/**
	 * Calcula o numero de pontos da borda e guarda na variável bpoints da classe.
	 */
	public void borderPoints() {
		for (int i = 0; i < super.img.getHeight(); i++)
			for (int j = 0; j < super.img.getWidth(); j++)
				if (pixels[i][j] == 1)if (isBorderPixel(i, j)) bpoints++;
	}
	
	/**
	 * Verifica se um pixel da imagem pertence a borda.
	 * @param i - coordenada x
	 * @param j - coordenada x
	 * @return - true, se pertence a borda. Senao false.
	 */
	public boolean isBorderPixel(int i, int j) {
		if (pixels[i][j] == 0) return false;
		
		//left
		if (j == 0) return true;
		if (j > 0) if (pixels[i][j - 1] == 0) return true;

		//up
		if (i == 0) return true;
		if (i > 0) if (pixels[i - 1][j] == 0) return true;

		//right
		if (j == super.img.getWidth()) return true;
		if (j < super.img.getWidth()) if (pixels[i][j + 1] == 0) return true;

		//down
		if (i == super.img.getHeight()) return true;
		if (i < super.img.getHeight()) if (pixels[i + 1][j] == 0) return true;

		return false;
	}
	
	/**
	 * Funcao auxiliar para o calculo do tamanho da borda, assim como para a recursao do algoritmo Chain Codes.
	 * @param i - coordenada x
	 * @param j - coordenada y
	 * @return - o indice do proximo ponto. Usado na recursao pelo metodo chainCodes 
	 */
	public int[] borderNeighbors(int i, int j) {
		int index[] = new int[2];
		boolean flag = false;

		//east
		if (isBorderPixel(i, j+1) && !flag && visited[i][j+1] == 0) {
			j += 1;
			bsize += 1;
			flag = true;
			index[0] = i;
			index[1] = j;
			return index;
		}
		//southeast
		if (isBorderPixel(i+1, j+1) && !flag && visited[i+1][j+1] == 0) {
			i += 1;
			j += 1;
			bsize += Math.sqrt(2);
			flag = true;
			index[0] = i;
			index[1] = j;
			return index;
		}
		//south
		if (isBorderPixel(i+1, j) && !flag && visited[i+1][j] == 0) {
			i += 1;
			bsize += 1;
			flag = true;
			index[0] = i;
			index[1] = j;
			return index;
		}
		//southwest
		if (isBorderPixel(i+1, j-1) && !flag && visited[i+1][j-1] == 0) {
			i += 1;
			j -= 1;
			bsize += Math.sqrt(2);
			flag = true;
			index[0] = i;
			index[1] = j;
			return index;
		}
		//west
		if (isBorderPixel(i, j-1) && !flag && visited[i][j-1] == 0) {
			j -= 1;
			bsize += 1;
			flag = true;
			index[0] = i;
			index[1] = j;
			return index;
		}
		//northwest
		if (isBorderPixel(i-1, j-1) && !flag && visited[i-1][j-1] == 0) {
			i -= 1;
			j -= 1;
			bsize += Math.sqrt(2);
			flag = true;
			index[0] = i;
			index[1] = j;
			return index;
		}
		//north
		if (isBorderPixel(i-1, j) && !flag && visited[i-1][j] == 0) {
			i -= 1;
			bsize += 1;
			flag = true;
			index[0] = i;
			index[1] = j;
			return index;
		}
		//northeast
		if (isBorderPixel(i-1, j+1) && !flag && visited[i-1][j+1] == 0) {
			i -= 1;
			j += 1;
			bsize += Math.sqrt(2);
			flag = true;
			index[0] = i;
			index[1] = j;
			return index;
		}
		// no neighbor border pixels 
		index[0] = i;
		index[1] = j;
		return index;
	}
	
	/**
	 * Metodo principal que executa o algoritmo chain code. Chamado no construtor
	 * @param i - coordenada x
	 * @param j - coordenada y
	 */
	public void chainCodes(int i, int j) {
		int[] index = new int[2]; 
		
		index = borderNeighbors(i, j);
		visited[i][j] = 1;
 
		if (visited[index[0]][index[1]] == 0) chainCodes(index[0], index[1]);
	}
	
	/**
	 * 
	 * @return numero de pontos da borda
	 */
	public int getBpoints() {
		return bpoints;
	}
	
	/**
	 * 
	 * @return tamanho da borda.
	 */
	public double getBsize() {
		return bsize;
	}
}