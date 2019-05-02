package render2d.shape;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import elements.Point;
import render2d.TextureLoad;

public interface Textureable extends Drawable{

	int getTexId();
	void setTexId(int texId);
	int getCurrentFrame();
	void setCurrentFrame(int frame);
	
	@Override
	default void drawTriangle(Point base, Point vector1, Point vector2){
		setTexture(0f, 0f);
		glVertex2i(base.getX(),base.getY());
		//
		// !!!!!!! warning !!!!!!!	0
		//
		float x = (float)vector1.getX();
		if(x != 0)
			x = 1;
		float y = (float)vector1.getY();
		if(y != 0)
			y = 1;
		setTexture(x, y);
		glVertex2i(
				base.getX()+vector1.getX(),
				base.getY()+vector1.getY());
		
		setTexture(1f, 1f);
		glVertex2i(
				base.getX()+vector2.getX(),
				base.getY()+vector2.getY());	
	}
	
	@Override
	default void GlBindTex(){
		glBindTexture(GL_TEXTURE_2D, getTexId());
	}
	
	default void setTexture(float fx, float fy){
		int fid = getTexId();
		int fcu = getCurrentFrame();
		
		TextureLoad.setDims(fid);
		
		float fw = TextureLoad.getIW() / TextureLoad.getTW();
		float fh = TextureLoad.getIH() / TextureLoad.getTH();
		
		fx = ((fcu % fw ) + fx ) * (1 / fw) ;
		fy = (int)((fcu / fw ) + fy ) * (1 / fh) ;

		glTexCoord2f(fx, fy);
	}
	
}
