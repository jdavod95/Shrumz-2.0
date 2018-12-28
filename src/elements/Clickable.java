package elements;

import org.lwjgl.util.Point;


import render2d.Shape;

public interface Clickable {

	public abstract Shape getShape();
	public abstract boolean getVis();
	public abstract void release();
	public void action();
		
	public static boolean check(Point m, Shape s){
		if(m.getX() > s.getX() && m.getX() < s.getX() + s.getW())
			if(m.getY() > s.getY() && m.getY() < s.getY() + s.getH()){
					return true;
				}
		return false;
	}
}
