package elements;

import render2d.Render;
import render2d.shape.Clickable;
import render2d.shape.ShapeFactory;
import render2d.shape.Textureable;
import render2d.shape.rectangle.Rectangle;

public class Button{

	Rectangle label;
	Rectangle skin;
	MyEvent e;
	
	MyEvent release = new MyEvent(){
		@Override
		public void action() {
			((Textureable) skin).setCurrentFrame(0);
		}
	};
	
	MyEvent click = new MyEvent(){	
		@Override
		public void action() {
			e.action();		
			((Textureable) skin).setCurrentFrame(1);
		}
	};
		
	public Button(Point pos, int w, int h, String label, MyEvent e) {
		skin = ShapeFactory.createRectTexClick(pos, w, h, release, MyEvent.EMPTY, click, "BUTTONCOL");
	//	Rectangle label = new Label(label);
		if(e != null)
			this.e = e;
	}
	
	public Button(Point pos, int w, int h, Rectangle label, MyEvent e) {
		skin = ShapeFactory.createRectTexClick(pos, w, h, release, MyEvent.EMPTY, click, "BUTTONCOL");
		this.label = label;
		if(e != null)
			this.e = e;
	}
	
	public void toRender(int l){
		Render.addUi(skin, l);
	}

	public Clickable getClickable() {
		return (Clickable) skin;
	}

}
