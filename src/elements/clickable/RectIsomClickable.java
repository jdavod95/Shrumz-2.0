package elements.clickable;

import org.lwjgl.util.Point;

import elements.MyEvent;
import render2d.Color;
import render2d.shapeDEPRECATED.RectIsom;

public class RectIsomClickable extends RectIsom implements Clickable{

	private boolean down;
	private MyEvent click;
	private MyEvent hover;
	private MyEvent release;
	
	public RectIsomClickable(int x, int y, int w, Color c) {
		super(x, y, w, c);
	}
	
	@Override
	public boolean getVis() {
		return true;
	}

	@Override
	public void release() {
		down = false;
		release.action();
	}
	
	@Override
	public void onClick() {
		if(!down){
			down = true;
			click.action();
		}
	}
	@Override
	public boolean contains(Point m){
		
		int x = getShape().getX();
		int y = getShape().getY();

		int w = getShape().getW()/2;
		
		int mx = m.getX();
		int my = m.getY();

		if(mx < x || mx > x + getShape().getW())
			return false;
		if(my < y || my > y + getShape().getH())
			return false;

		if(mx < x+w){
			if(my < (mx-x+w)/2+y && my > (x-mx+w)/2+y)
				return true;
		} else
			if(my < (x-mx+w)/2+w+y && my > (mx-x-w)/2+y)
				return true;
		
		return false;
	}
	
	@Override
	public void hover() {	
		hover.action();
	}


	public void setClick(MyEvent click) {
		this.click = click;
	}


	public void setHover(MyEvent hover) {
		this.hover = hover;
	}


	public void setRelease(MyEvent release) {
		this.release = release;
	}

}
