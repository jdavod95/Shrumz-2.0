package render2d;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Font {

	private static String path = new File("res/abc.fnt").getAbsolutePath();//"D:/workspace/TEngine/res/abc.fnt";
	private static List<Symbol> symb = new ArrayList<>();
	
	public static int scale = 64;
	public static int txSize = 512;//(scale*scale)/(scale/8);

	public static void LoadSymbols(){
		BufferedReader br;
		int k = 0;		
		try {
			String s= new String();						
			br = new BufferedReader(new FileReader(path));
		   	StringTokenizer st;
			while((s = br.readLine()) != null){
				st = new StringTokenizer(s);
			   	if(k > 3)
			   		symb.add(new Symbol(
			   				Integer.parseInt(st.nextToken("="+" "+st.nextToken("="+" ")+st.nextToken("="+" "))),
			   				Integer.parseInt(st.nextToken("="+" "+st.nextToken("="+" "))),
			   				Integer.parseInt(st.nextToken("="+" "+st.nextToken("="+" "))),
			   				Integer.parseInt(st.nextToken("="+" "+st.nextToken("="+" ")))
			   			));				
				k++;	
			}
			br.close();
		}
		catch (Exception e) {e.printStackTrace();}
		
	   	
	}
		
	
	public static void writeString(int x, int y, String s, int size, boolean centered, int parentw, int parenth){
		int width = 0;
		Symbol symbs[] = new Symbol[s.length()]; 
			
		for(int i = 0; i < s.length(); i++){
			int j = 0;	
			while(s.charAt(i) != symb.get(j).id || j == symb.size()-1)
				j++;	
			symbs[i] = symb.get(j);
		}

		if(centered){
			int sw = 0;
			for(int i = 0; i < symbs.length; i++)
				sw += symbs[i].width;
			x += (int)((parentw - sw)/2);
			y -= (int)((parenth - Symbol.heigth)/2);
		}

		glEnd();
		glBindTexture(GL_TEXTURE_2D,1);
		glBegin(GL_QUADS);
		
		for(int i = 0; i < symbs.length; i++){
			drawSymbol(i*2+x+width/(scale/size),y,symbs[i],size);
			width += symbs[i].width;
		}
		
		glEnd();
		glBindTexture(GL_TEXTURE_2D,0);
	    glBegin(GL_TRIANGLES);	
	}
	
	public static void drawSymbol(int posX, int posY, Symbol symb, int desFontSize){
		 
		float i = symb.px;
		float j = symb.py;
		
		glTexCoord2f(i/txSize, j/txSize);
		glVertex2i(posX, posY);
        
		glTexCoord2f(i/txSize, (j+scale)/txSize);
		glVertex2i(posX, posY+desFontSize);
        
		glTexCoord2f((i+symb.width)/txSize, (j+scale)/txSize);
		glVertex2i(posX+symb.width/(scale/desFontSize), posY+desFontSize);
        		
		glTexCoord2f((i+symb.width)/txSize, j/txSize);
		glVertex2i(posX+symb.width/(scale/desFontSize), posY);

	}
	
	
}
