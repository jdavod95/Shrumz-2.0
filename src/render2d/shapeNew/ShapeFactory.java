package render2d.shapeNew;

import render2d.Color;
import render2d.shapeNew.diamond.Diamond;
import render2d.shapeNew.rectangle.Rectangle;

public class ShapeFactory {

	public static Diamond createDiamCol(Point pos, int w, int h, Color c){
		class DiamondCol extends Diamond implements Colorable{
			
			Color color;
			
			protected DiamondCol(Point pos, int w, int h, Color c) {
				super(pos, w, h);
				color = c;
			}
			@Override
			public Color getColor() {
				return color;
			}

			@Override
			public void setColor(Color color) {
				this.color = color;
			}
			
		};
			
		return new DiamondCol(pos, w, h, c);
	}
	
	public static Rectangle createRectangleCol(Point pos, int w, int h, Color c){
		class RectangleCol extends Rectangle implements Colorable{
			
			Color color;
			
			protected RectangleCol(Point pos, int w, int h, Color c) {
				super(pos, w, h);
				color = c;
			}
			@Override
			public Color getColor() {
				return color;
			}

			@Override
			public void setColor(Color color) {
				this.color = color;
			}
			
		};
			
		return new RectangleCol(pos, w, h, c);
		
	}
}
