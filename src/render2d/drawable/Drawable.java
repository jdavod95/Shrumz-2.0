package render2d.drawable;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glColor4d;
import static org.lwjgl.opengl.GL11.glVertex2i;

import elements.Point;
import render2d.Color;
import render2d.Texturing;

public abstract class Drawable {
	
	private static final Color BASE_COLOR = Color.WHITE;
	private static final String BASE_TEXTURE = "noTex";
	
	private Color color;
	private String texName;
	private int currentFrame;
	
	private Clickable clickable;
	
	protected Drawable() {
		this.color = BASE_COLOR;
		this.texName = BASE_TEXTURE;
		this.currentFrame = 0;
		this.clickable = new ClickableDummy();
	}

	public final void draw(){
		glBindTexture(GL_TEXTURE_2D, Texturing.getTexIdFor(texName));
		glBegin(GL_TRIANGLES);
		glColor4d(color.getRVal(), color.getGVal(), color.getBVal(), color.getOpacity());
		drawShape();
		glEnd();
	}
	
	public Color getColor() {
		return color.getNew();
	}

	public void setColor(Color color) {
		this.color = color.getNew();
	}
	
	public String getTexName() {
		return texName;
	}

	public void setTexName(String texName) {
		this.texName = texName;
	}

	public Clickable getClickable() {
		return clickable;
	}
	
	public void setClickable(Clickable clickable) {
		this.clickable = clickable;
	}
	
	public Drawable getDrawable() {
		return this;
	}
	
	public void setCurrentFrame(int frame) {
		currentFrame = frame;
	}
	
	protected void drawTriangle(Point base, Point vector1, Point vector2){
		Texturing.setTexture(0, 0, texName, currentFrame);
		glVertex2i(
				base.getX(),
				base.getY());
		
		Texturing.setTexture(
				vector1.getX() == 0 ? 0 : 1,
				vector1.getY() == 0 ? 0 : 1,
				texName, currentFrame);
		glVertex2i(
				base.getX()+vector1.getX(),
				base.getY()+vector1.getY());
		
		Texturing.setTexture(1, 1, texName, currentFrame);
		glVertex2i(
				base.getX()+vector2.getX(),
				base.getY()+vector2.getY());	
	}
	
	// implement with calling drawTriangle()
	protected abstract void drawShape();	
}
