package render2d.shape;

import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import render2d.TextureLoad;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;

public class RectTex extends Shape{

	int fid, fcu;
	public RectTex(int x, int y, int w, int h, int fid, int fcu) {
		super(x, y, w, h);

		this.fid = fid;
		this.fcu = fcu;
	}
	public void setFcu(int fcu){
		this.fcu = fcu;
	}
	private static void setTexture(float fx, float fy, int fid, int fcu){
		TextureLoad.setDims(fid);
		
		float fw = TextureLoad.getIW() / TextureLoad.getTW();
		float fh = TextureLoad.getIH() / TextureLoad.getTH();
		
		fx = ((fcu % fw ) + fx ) * (float)(1 / fw) ;
		fy = (int)((fcu / fw ) + fy ) * (float)(1 / fh) ;

		//System.out.println(fx+" "+fy);
		glTexCoord2f(fx, fy);
	}
	public void draw(){
		glBindTexture(GL_TEXTURE_2D,fid);
		glBegin(GL_TRIANGLES);	
		
		glColor4d(1,1,1,1.0);	
	
			setTexture(0f,0f,fid,fcu);
			glVertex2i(x,y);

			setTexture(1f,0f,fid,fcu);
			glVertex2i(x+w,y);

			setTexture(1f,1f,fid,fcu);
			glVertex2i(x+w,y+h);
	
			setTexture(0f,0f,fid,fcu);
			glVertex2i(x,y);

			setTexture(0f,1f,fid,fcu);
			glVertex2i(x,y+h);

			setTexture(1f,1f,fid,fcu);
			glVertex2i(x+w,y+h);


	}
	
}
