package test;

import static org.junit.Assert.*;
import imageProcessing.*;

import org.junit.Test;

public class ChainCodeTest {

	@Test
	public void testCirculo() {
		String s = "circulo.png";
		Image img=null;
		ChainCodes chain=null;
		try{
			img = new Image(s);
			chain = new ChainCodes(s);
		}
		catch (Exception e){
			fail();
		}
		assertEquals(20, img.height());
		assertEquals(20, img.width());
		assertEquals(52, chain.getBpoints());
	}

}
