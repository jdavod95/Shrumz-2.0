package render2d.shapeNew.rectangle;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import render2d.TextureLoad;
import render2d.shapeNew.Point;
import render2d.shapeNew.Textureable;

public class RectangleTextured extends Rectangle implements Textureable{

	private int texId,
		currentFrame;
	
	public RectangleTextured(Point pos, int w, int h, int texId, int currentFrame) {
		super(pos, w, h);
		this.texId = texId;
		this.currentFrame = currentFrame;
	}
	
	private void setTexture(float fx, float fy){
		if(fx >= 1)
			fx = 1f;
		else
			fx = 0;
		
		if(fy >= 1)
			fy = 1f;
		else
			fy = 0;
		
		TextureLoad.setDims(texId);
		
		float fw = TextureLoad.getIW() / TextureLoad.getTW();
		float fh = TextureLoad.getIH() / TextureLoad.getTH();
		
		fx = ((currentFrame % fw ) + fx ) * (1 / fw) ;
		fy = (int)((currentFrame / fw ) + fy ) * (1 / fh) ;

		glTexCoord2f(fx, fy);
	}
	
	protected void drawTriangle(Point base, Point vector1, Point vector2){
		setTexture(0f, 0f);
		glVertex2i(base.getX(),base.getY());

		setTexture(1f*vector1.getX(), 0f*vector1.getY());
		glVertex2i(
				base.getX()+vector1.getX(),
				base.getY()+vector1.getY());

		setTexture(1f, 1f);
		glVertex2i(
				base.getX()+vector2.getX(),
				base.getY()+vector2.getY());
	}
	
	protected void GlBindTex(){
		glBindTexture(GL_TEXTURE_2D, texId);
	}
}
