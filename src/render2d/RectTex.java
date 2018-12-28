package render2d;

import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;
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
	private static void SetTexture(float fx,float fy,int fcu){
		
		fx=((fcu%((float)TextureLoad.getIW()/(float)TextureLoad.getTW())+fx)*(1/((float)TextureLoad.getIW()/(float)TextureLoad.getTW())));		
		fy=((fcu/(TextureLoad.getIH()/TextureLoad.getTH())+fy)*(1/((float)TextureLoad.getIH()/(float)TextureLoad.getTH())));
		
//		System.out.println(fx+" "+fy);
		glTexCoord2f(fx, fy);
	}
	public void draw(){
		glBindTexture(GL_TEXTURE_2D,fid);
		glBegin(GL_TRIANGLES);	
		
		glColor4d(1,1,1,1.0);	
	
			SetTexture(0f,0f,fcu);
			glVertex2i(x,y);

			SetTexture(1f,0f,fcu);
			glVertex2i(x+w,y);

			SetTexture(1f,1f,fcu);
			glVertex2i(x+w,y+h);
	
			SetTexture(0f,0f,fcu);
			glVertex2i(x,y);

			SetTexture(0f,1f,fcu);
			glVertex2i(x,y+h);

			SetTexture(1f,1f,fcu);
			glVertex2i(x+w,y+h);


	}
	
}
