package render2d.write;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Word {

	// alphabet from bmfc
	
	private static String path = new File("res/abc.fnt").getAbsolutePath();//"D:/workspace/"projekt_nev"/res/abc.fnt";
	private static Symbol[] symb;

	public static final float SCALE = 64;
	public static final float TXSIZE = 512f;//(scale*scale)/(scale/8);

	int x, y;
	int[] word;
	int dscale;
	
	public Word(int x, int y, int scale, String s){
		this.x = x;
		this.y = y;
		this.dscale = scale;
		word = new int[s.length()];
		for(int i = 0; i < s.length(); i++)
			word[i] = s.charAt(i);

		
	}
	public static void loadSymbols(){
		BufferedReader br;
		int k = 0;		
		try {
			String s= new String();						
			br = new BufferedReader(new FileReader(path));
		   	StringTokenizer st;
			while((s = br.readLine()) != null){
				st = new StringTokenizer(s);
				if(k > 3)
			   		symb[k-4] = new Symbol(
			   				Integer.parseInt(st.nextToken("="+" "+st.nextToken("="+" ")+st.nextToken("="+" "))),
			   				Integer.parseInt(st.nextToken("="+" "+st.nextToken("="+" "))),
			   				Integer.parseInt(st.nextToken("="+" "+st.nextToken("="+" "))),
			   				Integer.parseInt(st.nextToken("="+" "+st.nextToken("="+" ")))
			   			);
				else if(k == 3)
				   	symb = new Symbol[Integer.parseInt(st.nextToken("chars count="))];
				
				k++;	
			}
			br.close();
		}
		catch (Exception e) {e.printStackTrace();}	   	
	}
		
	public void draw(){
		int x = this.x;
		for(int i = 0;i < word.length;i++){
			Symbol s = getSymb(word[i]);
			int w = (int)(s.getW()*(dscale/SCALE));
			
			glTexCoord2f(s.getX()/TXSIZE, s.getY()/TXSIZE);
			glVertex2i(x, y);
			
			glTexCoord2f((s.getX()+s.getW())/TXSIZE, (s.getY()+SCALE)/TXSIZE);
			glVertex2i(x+w, y+dscale);
			
			glTexCoord2f(s.getX()/TXSIZE, (s.getY()+SCALE)/TXSIZE);
			glVertex2i(x,y+dscale);
			
			
			glTexCoord2f(s.getX()/TXSIZE, s.getY()/TXSIZE);
			glVertex2i(x, y);

			glTexCoord2f((s.getX()+s.getW())/TXSIZE, s.getY()/TXSIZE);
			glVertex2i(x+w, y);
	        
			glTexCoord2f((s.getX()+s.getW())/TXSIZE, (s.getY()+SCALE)/TXSIZE);
			glVertex2i(x+w, y+dscale);
			
			x += w;
			
		}	
	}	
	
	public static Symbol getSymb(int id){
		int i = 0;
		while(id != symb[i].getId())
			i++;
		return symb[i];
	}
}
