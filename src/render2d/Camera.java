package render2d;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.opengl.Display;

import elements.Point;
import root.App;


public class Camera {
	private final static int W = App.W;
	private final static int H = App.H;
	private final static int FPS = 60;
	
	private final static Point POSITION = new Point(0, 0);
	private final static Point RESOLUTION = new Point(W, H);
	
	public static Point getCameraPos() {
		return POSITION.getNew();
	}
	
	public static int getFps() {
		return FPS;
	}
	
	public static void create(){
        Display.update();
        Display.sync(FPS);
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
		glOrtho(
				POSITION.getX(), RESOLUTION.getX(),
				RESOLUTION.getY(), POSITION.getY(),
				1, -1
				);
        
        glMatrixMode(GL_MODELVIEW);
	}
	
	public static void rePosition(int x, int y){
		POSITION.setXY(x, y);
		RESOLUTION.setXY(x + W, y + H);
	}
	
	public static void rePosition(Point p){
		rePosition(p.getX(), p.getY());
	}
	
	public static void step(int stepX, int stepY) {
		POSITION.add(stepX, stepY);
		RESOLUTION.add(stepX, stepY);
	}
	
	public static void step(Point step) {
		step(step.getX(), step.getY());
	}
}
