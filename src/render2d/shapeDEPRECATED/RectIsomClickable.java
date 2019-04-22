package render2d.shapeDEPRECATED;

import elements.clickable.Clickable;
import render2d.Color;

public abstract class RectIsomClickable extends RectIsom implements Clickable{
	
	public RectIsomClickable(int x, int y, int w, Color c) {
		this(x, y, w, c, 1.0);
	}
	
	public RectIsomClickable(int x, int y, int w, Color c, double op) {
		super(x, y, w, c);
		this.col = c;
		this.op = op;
	}

}
