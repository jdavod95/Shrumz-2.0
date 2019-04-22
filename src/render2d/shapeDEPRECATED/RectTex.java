package render2d.shapeDEPRECATED;

import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import render2d.TextureLoad;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;

public class RectTex extends Shape{

	int fid, fcu;
	protected double opacity;
	
	public RectTex(int x, int y, int w, int h, int fid, int fcu, double opacity) {
		super(x, y, w, h);
		this.fid = fid;
		this.fcu = fcu;
		this.opacity = opacity;
	}

	public RectTex(int x, int y, int w, int h, int fid, int fcu) {
		this(x, y, w, h, fid, fcu, 1.0);
	}
	
	public void setFcu(int fcu){
		this.fcu = fcu;
	}
	
	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}

	private static void setTexture(float fx, float fy, int fid, int fcu){
		TextureLoad.setDims(fid);
		
		float fw = TextureLoad.getIW() / TextureLoad.getTW();
		float fh = TextureLoad.getIH() / TextureLoad.getTH();
		
		fx = ((fcu % fw ) + fx ) * (1 / fw) ;
		fy = (int)((fcu / fw ) + fy ) * (1 / fh) ;

		glTexCoord2f(fx, fy);
	}
	
	public void draw(){
		glBindTexture(GL_TEXTURE_2D,fid);
		glBegin(GL_TRIANGLES);
		glColor4d(1,1,1,opacity);	

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

		glEnd();
	}

	@Override
	public void reScale(int w, int h) {
		// TODO Auto-generated method stub
		
	}

	
}
