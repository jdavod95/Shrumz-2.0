package render2d.shape;

import elements.MyEvent;
import elements.Point;
import render2d.Color;
import render2d.shape.diamond.Diamond;
import render2d.shape.diamond.DiamondClick;
import render2d.shape.rectangle.Rectangle;
import render2d.shape.rectangle.RectangleClick;

public class ShapeFactory {
	
	public static Diamond createDiamCol(Point pos, int w, Color c){
		class DiamondCol extends Diamond implements Colorable{
			Color c;
			protected DiamondCol(Point pos, int w, Color c) {
				super(pos, w, w/2);
				this.c = c;
			}
			@Override
			public Color getColor() {
				return c;
			}
		};
			
		return new DiamondCol(pos, w, c);
	}
	
	public static Rectangle createRectCol(Point pos, int w, int h, Color c){
		class RectangleCol extends Rectangle implements Colorable{	
			Color c;
			
			protected RectangleCol(Point pos, int w, int h, Color c) {
				super(pos, w, h);
				this.c = c;
			}

			@Override
			public Color getColor() {
				return c;
			}
			
		};
			
		return new RectangleCol(pos, w, h, c);
	}
	
	public static Rectangle createRectTex(Point pos, int w, int h, String texName) {
		class RectangleTex extends Rectangle implements Textureable{
			String texName;
			int currentFrame;
			
			protected RectangleTex(Point pos, int w, int h, String texName) {
				super(pos, w, h);
				this.texName = texName;
				currentFrame = 0;
			}

			@Override
			public String getTexName() {
				return texName;
			}

			@Override
			public int getCurrentFrame() {
				return currentFrame;
			}

			@Override
			public void setCurrentFrame(int frame) {
				currentFrame = frame;
			}
			
			@Override
			public void setTexName(String texName) {
				this.texName = texName;
			}
		}
		
		return new RectangleTex(pos, w, h, texName);
	}
	
	public static Rectangle createRectTexClick(Point pos, int w, int h, MyEvent release, MyEvent hover, MyEvent click, String texName) {
		class Inner extends Rectangle implements Textureable, RectangleClick{
			String texName;
			int currentFrame;
			boolean down;
			MyEvent release, hover, click;
			protected Inner(Point pos, int w, int h, MyEvent release, MyEvent hover, MyEvent click, String texName) {
				super(pos, w, h);
				this.texName = texName;
				currentFrame = 0;
				down = false;
				this.release = release;
				this.hover = hover;
				this.click = click;
			}

			@Override
			public String getTexName() {
				return texName;
			}

			@Override
			public int getCurrentFrame() {
				return currentFrame;
			}

			@Override
			public void setCurrentFrame(int frame) {
				currentFrame = frame;
			}

			@Override
			public MyEvent getRelease() {
				return release;
			}

			@Override
			public MyEvent getHover() {
				return hover;
			}

			@Override
			public MyEvent getClick() {
				return click;
			}

			@Override
			public boolean isDown() {
				return down;
			}

			@Override
			public void setDown(boolean b) {
				down = b;
			}

			@Override
			public void setTexName(String texName) {
				this.texName = texName;
			}
		}
		
		return new Inner(pos, w, h, release, hover, click, texName);
	}

	public static Diamond createDiamColClick(Point pos, int w, Color color, MyEvent onClick, MyEvent release, MyEvent hover) {
		class DiamondColClick extends Diamond implements Colorable, DiamondClick{
			Color c;
			boolean down = false;
			MyEvent hover,
				release, 
				onClick;
			protected DiamondColClick(Point pos, int w, Color color, MyEvent release, MyEvent hover, MyEvent onClick) {
				super(pos, w, w/2);
				this.hover = hover;
				this.release = release;
				this.onClick = onClick;
				this.c = color;
			}

			@Override
			public MyEvent getRelease() {
				return release;
			}

			@Override
			public MyEvent getHover() {
				return hover;
			}

			@Override
			public MyEvent getClick() {
				return onClick;
			}

			@Override
			public boolean isDown() {
				return down;
			}

			@Override
			public void setDown(boolean b) {
				down = b;
			}

			@Override
			public Color getColor() {
				return c;
			}
			
		}
		
		return new DiamondColClick(pos, w, color, release, hover, onClick);
	}

}
