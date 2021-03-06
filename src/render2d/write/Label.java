package render2d.write;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;
import static org.lwjgl.opengl.GL11.GL_LINEAR_MIPMAP_LINEAR;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

import render2d.Texturing;
import render2d.drawable.Shape;
import render2d.elements.Point;


public class Label extends Shape{

	// alphabet from bmfc
	
	private static String path = new File("res/abc.fnt").getAbsolutePath();
	private static Symbol[] symb;

	public static final float SCALE = 64;
	public static final float TXSIZE = (SCALE*SCALE)/(SCALE/8);

	int[] word;

	public Label(Point pos, int scale, String s){
		super(pos, 0, scale);
		word = new int[s.length()];
		for(int i = 0; i < s.length(); i++)
			word[i] = s.charAt(i);
	}
	
	@Override
	public void drawShape(){
		int x = getPos().getX(),
			y = getPos().getY(),
			h = getH();

		glEnd();
		glBindTexture(GL_TEXTURE_2D,Texturing.getTexIdFor("ABC"));
		glGenerateMipmap(GL_TEXTURE_2D);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
		glBegin(GL_TRIANGLES);
		for(int i = 0;i < word.length;i++){
			Symbol s = getSymb(word[i]);
			int w = (int)(s.getW()*(h/SCALE));
			
			glTexCoord2f(s.getX()/TXSIZE, s.getY()/TXSIZE);
			glVertex2i(x, y);			
			glTexCoord2f((s.getX()+s.getW())/TXSIZE, (s.getY()+SCALE)/TXSIZE);
			glVertex2i(x+w, y+h);			
			glTexCoord2f(s.getX()/TXSIZE, (s.getY()+SCALE)/TXSIZE);
			glVertex2i(x,y+h);			
			
			glTexCoord2f(s.getX()/TXSIZE, s.getY()/TXSIZE);
			glVertex2i(x, y);
			glTexCoord2f((s.getX()+s.getW())/TXSIZE, s.getY()/TXSIZE);
			glVertex2i(x+w, y);	        
			glTexCoord2f((s.getX()+s.getW())/TXSIZE, (s.getY()+SCALE)/TXSIZE);
			glVertex2i(x+w, y+h);
			
			x += w;
		}	
	}	
	
	//---------------------- static ----------------------
	//---------------------- static ----------------------
	//---------------------- static ----------------------
	
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
		
	public static Symbol getSymb(int id){
		int i = 0;
		while(id != symb[i].getId())
			i++;
		return symb[i];
	}

	@Override
	public void reScale(int w, int h) {
		setH(h);
		setW(w);
	}

	public void setX(int x) {
		getPos().setX(x);
	}
	
	public void setY(int y) {
		getPos().setX(y);
	}
}
