package render2d.shapeNew.diamond;

import elements.MyEvent;
import render2d.Color;
import render2d.shapeNew.Clickable;
import render2d.shapeNew.Point;

public class DiamondClick extends DiamondColor implements Clickable{

	private boolean down;
	private MyEvent 
		click,
		hover,
		release;
	
	public DiamondClick(Point pos, int w, int h, Color color) {
		super(pos, w, h, color);
	}

	@Override
	public void release() {
		down = false;
		release.action();
	}
	
	@Override
	public void hover() {	
		hover.action();
	}

	@Override
	public void onClick() {
		if(!down){
			down = true;
			click.action();
		}
	}
	
	@Override
	public boolean contains(Point mouse){
		
		int x = getPos().getX();
		int y = getPos().getY();

		int w = getW()/2;
		
		int mx = mouse.getX();
		int my = mouse.getY();

		if(mx < x || mx > x + getW())
			return false;
		if(my < y || my > y + getH())
			return false;

		if(mx < x+w){
			if(my < (mx-x+w)/2+y && my > (x-mx+w)/2+y)
				return true;
		} else
			if(my < (x-mx+w)/2+w+y && my > (mx-x-w)/2+y)
				return true;
		
		return false;
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
