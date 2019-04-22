package render2d.shape;

import elements.MyEvent;
import render2d.Color;
import render2d.shape.diamond.Diamond;
import render2d.shape.diamond.DiamondClick;
import render2d.shape.rectangle.Rectangle;

public class ShapeFactory {
	
	public static Diamond createDiamCol(Point pos, int w, int h, Color c){
		class DiamondCol extends Diamond implements Colorable{
			protected DiamondCol(Point pos, int w, int h, Color c) {
				super(pos, w, h);
				setColor(c);
			}
		};
			
		return new DiamondCol(pos, w, h, c);
	}
	
	public static Rectangle createRectCol(Point pos, int w, int h, Color c){
		class RectangleCol extends Rectangle implements Colorable{			
			protected RectangleCol(Point pos, int w, int h, Color c) {
				super(pos, w, h);
				setColor(c);
			}
		};
			
		return new RectangleCol(pos, w, h, c);
	}
	
	public static Rectangle createRectTex(Point pos, int w, int h, int texId) {
		class RectangleTex extends Rectangle implements Textureable{
			int texId, currentFrame;
			
			protected RectangleTex(Point pos, int w, int h, int texId) {
				super(pos, w, h);
				this.texId = texId;
				currentFrame = 0;
			}

			@Override
			public int getTexId() {
				return texId;
			}

			@Override
			public int getCurrentFrame() {
				return currentFrame;
			}

			@Override
			public void setCurrentFrame(int frame) {
				currentFrame = frame;
			}

			
		}
		
		return new RectangleTex(pos, w, h, texId);
	}

	public static Diamond createDiamColClick(Point pos, int w, Color color, MyEvent onClick, MyEvent release, MyEvent hover) {
		class DiamondColClick extends Diamond implements Colorable, DiamondClick{
			boolean down = false;
			MyEvent hover,
				release, 
				onClick;
			protected DiamondColClick(Point pos, int w, Color color, MyEvent release, MyEvent hover, MyEvent onClick) {
				super(pos, w, w/2);
				this.hover = hover;
				this.release = release;
				this.onClick = onClick;
				setColor(color);
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
			public MyEvent getOnClick() {
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
			
		}
		
		return new DiamondColClick(pos, w, color, release, hover, onClick);
	}

}
