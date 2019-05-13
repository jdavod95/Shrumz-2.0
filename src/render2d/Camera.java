package render2d;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.opengl.Display;

import root.App;


public class Camera {
	static private int fps = 60;
	
	static private int W  = App.W;
	static private int H  = App.H;
	
	static private int ch = H;
	static private int cw = W;
	static private int cx = 0;
	static private int cy = 0;
	
	public static int getCX(){
		return cx;
	}
	public static int getCY(){
		return cy;
	}
	
	public static int getFps() {
		return fps;
	}
	public static void setFps(int fps) {
		Camera.fps = fps;
	}
	
	public static void create(){
        Display.update();
        Display.sync(fps);
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
		glOrtho(cx, cw, ch, cy, 1, -1);
        
        glMatrixMode(GL_MODELVIEW);
	}
	
	public static void set(int x, int y){
		cx = x;
		cy = y;
		cw = x+W;
		ch = y+H;
	}
/*	
	public static void Centered(Body center){
		set(
			center.getPx()-(W/2),
			center.getPy()-(H/2)
		);
	}
	
	public static void ByPercent(Body center, double xperc, double yperc){
		if(xperc > 1 )
			xperc/=100;
		if(yperc > 1 )
			yperc/=100;
		set(
			(int)Math.round(center.getPx()-(W*xperc)),
			(int)Math.round(center.getPy()-(H*yperc))
			//,20
		);
	}
	
	public static void Margins(BodyEnt focus, double top, double right, double bot, double left){

		int sp = 25;
		if(focus.getPx()+focus.getW() < cx || focus.getPx() > cw)
			Transition(focus.getPx()-(W/2),
					focus.getPy()-(H/2),
					sp);
		
		if(focus.getPy()+focus.getH() < cy || focus.getPy() > ch)
			Transition(focus.getPx()-(W/2),
					focus.getPy()-(H/2),
					sp);
		
		if(focus.getPx() < (cx+(top*W)))
			set((int)Math.round(focus.getPx()-(top*W)),
				cy);
		else if(focus.getPx()+focus.getW() > (cw-(right*W)))
			set((int)Math.round((focus.getPx()+focus.getW())-((1.0-right)*W)),
				cy);
		
		
		if(focus.getPy() < (cy+(bot*H)))
			set(cx,
				(int)Math.round(focus.getPy()-(bot*H)));
		else if(focus.getPy()+focus.getH() > (ch-(left*H)))
			set(cx,
				(int)Math.round((focus.getPy()+focus.getH())-((1.0-left)*H)));
		
	
	
	}	
	
	public static void Transition(int dx, int dy, int sp){
		boolean stop = false;
		
		int vx = sp;
		int vy = sp;
		
		if(dx<cx)	vx *= -1;
		if(dy<cy)	vy *= -1;
		
		while(!stop){
			if(Math.abs(dx-cx) == 0){			}
			else if(Math.abs(dx-cx)>sp)
				set(cx+vx,cy);
			else
				set(dx,cy);
			
			if(Math.abs(dy-cy) == 0){}
			else if(Math.abs(dy-cy)>sp)
				set(cx,cy+vy);
			else
				set(cx,dy);
			
			if(Math.abs(dx-cx) == 0 && Math.abs(dy-cy) == 0)
				stop = true;
			
			Interface.toRender();
			ScreenC.toRender();
			Render.cycle();
			
			Font.writeString(0, H-30, "Cam moving", 16);
			        
			glEnd();
		}	
	}*/
}
