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
			for(int j=0;j<img.getHeight(); j++) if(img.getRGB(i,  j) != -1) aux++;
			if(h < aux) h = aux;
		}
		return h;
	}
	
	/**
	 * calculo da largura do objeto contido na imagem.
	 * @return a largura do objeto contido na imagem.
	 */
	public int width(){
		int w=0, aux=0;
		for(int i=0;i<img.getHeight();i++){
			aux=0;
			for(int j=0;j<img.getWidth();j++) if(img.getRGB(j, i) != -1) aux++;
			if(w < aux) w = aux;
		}
		return w;
	}
	
	/**
	 * calculo o número de pontos da borda da imagem usando o chain codes
	 * @return o número de pontos da borda da imagem
	 */
	public int border(){
		int initial[] = start();
		int x=0, y=0, xfinal=initial[0], yfinal=initial[1], npoints=0, firstTime=1;
		int vetImg[][] = new int[img.getHeight()][img.getWidth()];
		
		for(int i=0;i<img.getHeight();i++){		//Copia a imagem para o vetor
			for(int j=0;j<img.getWidth();j++){
				if(img.getRGB(j, i) != -1) vetImg[j][i] = 1;
				else vetImg[j][i] = 0;
			}
		}
		
		while(xfinal != x || yfinal != y){
			
			if(firstTime == 1){
				x=initial[0];
				y=initial[1];
				vetImg[xfinal][yfinal] = 9;
				firstTime=0;
			}
			
			//Leste - 0
			while(vetImg[xfinal+1][yfinal] == 1){ //Condição para ir para leste
				xfinal++;
				vetImg[xfinal][yfinal] = 2;
				npoints++;
				if(vetImg[xfinal+1][yfinal] != 1){ //Se a condição de ir para leste for quebrada, ande para sudeste
					//Sudeste - 7
					while(vetImg[xfinal+1][yfinal+1] == 1){ //Condição para ir para sudeste
						xfinal++;
						yfinal++;
						vetImg[xfinal][yfinal] = 2;
						npoints++;
						if(vetImg[xfinal+1][yfinal] == 1) break; //Se a condição para ir para leste voltar a ser válida, siga por este caminho
						if(vetImg[xfinal+1][yfinal+1] != 1){ //Se a condição de ir para sudeste for quebrada, ande para sul
							//Sul - 6
							while(vetImg[xfinal][yfinal+1] == 1){//Condição para ir para sul
								yfinal++;
								vetImg[xfinal][yfinal] = 2;
								npoints++;
								if(vetImg[xfinal+1][yfinal+1] == 1) break; //Se a condição para ir para sudeste voltar a ser válida, siga por este caminho
								if(vetImg[xfinal][yfinal+1] != 1){ //Se a condição de ir para sul for quebrada, ande para sudoeste
									//Sudoeste - 5
									while(vetImg[xfinal-1][yfinal+1] == 1){//Condição para ir para sudoeste
										xfinal--;
										yfinal++;
										vetImg[xfinal][yfinal] = 2;
										npoints++;
										if(vetImg[xfinal][yfinal+1] == 1) break; //Se a condição para ir para sul voltar a ser válida, siga por este caminho
										if(vetImg[xfinal-1][yfinal+1] != 1){ //Se a condição de ir para sudoeste for quebrada, ande para oeste
											//Oeste - 4
											while(vetImg[xfinal-1][yfinal] == 1){//Condição para ir para oeste
												xfinal--;
												vetImg[xfinal][yfinal] = 2;
												npoints++;
												if(vetImg[xfinal-1][yfinal+1] == 1) break; //Se a condição para ir para sudoeste voltar a ser válida, siga por este caminho
												if(vetImg[xfinal-1][yfinal] != 1){ //Se a condição de ir para oeste for quebrada, ande para noroeste
													//Noroeste - 3
													while(vetImg[xfinal-1][yfinal-1] == 1){//Condição para ir para Noroeste
														xfinal--;
														yfinal--;
														vetImg[xfinal][yfinal] = 2;
														npoints++;
														if(vetImg[xfinal-1][yfinal] == 1) break; //Se a condição para ir para oeste voltar a ser válida, siga por este caminho
														if(vetImg[xfinal-1][yfinal-1] != 1){ //Se a condição de ir para noroeste for quebrada, ande para norte
															//Norte - 2
															while(vetImg[xfinal][yfinal-1] == 1){//Condição para ir para Norte
																yfinal--;
																vetImg[xfinal][yfinal] = 2;
																npoints++;
																if(vetImg[xfinal-1][yfinal-1] == 1) break; //Se a condição para ir para noroeste voltar a ser válida, siga por este caminho
																if(vetImg[xfinal][yfinal-1] != 1){ //Se a condição de ir para norte for quebrada, ande para nordeste
																	//Nordeste - 2
																	while(vetImg[xfinal+1][yfinal-1] == 1){//Condição para ir para Nordeste
																		xfinal++;
																		yfinal--;
																		vetImg[xfinal][yfinal] = 2;
																		npoints++;
																		if(vetImg[xfinal-1][yfinal-1] == 1) break; //Se a condição para ir para noroeste voltar a ser válida, siga por este caminho
																		if(vetImg[xfinal][yfinal-1] != 1){ //Se a condição de ir para nordeste for quebrada, ande para nordeste
																			break;
																		}
																	}
																}
															}
														}	
													}
												}
											}
										}
									}
								}	
							}
						}
					}
				}
			}

			for(int i=0;i<img.getHeight();i++){
				for(int j=0;j<img.getWidth();j++){
					System.out.printf("%d ", vetImg[j][i]);
				}
				System.out.printf("\n");
			}
			//System.out.printf("xfinal:%d yfinal:%d // x:%d y:%d // npoints:%d\n", xfinal, yfinal, x, y, npoints );
		}
		
		/*for(int i=0;i<img.getHeight();i++){
			for(int j=0;j<img.getWidth();j++){
				System.out.printf("%d ", vetImg[j][i]);
			}
			System.out.printf("\n");
		}*/
		
		return npoints;
	}
}
