package elements;

import render2d.Render;
import render2d.Texturing;
import render2d.shape.Clickable;
import render2d.shape.Shape;
import render2d.shape.ShapeFactory;
import render2d.shape.Textureable;
import render2d.shape.rectangle.Rectangle;
import render2d.write.Label;

public class Button{

	Shape label;
	Rectangle skin;
	MyEvent e;
	private static Point nudge = new Point(1, 1);
	
	MyEvent release = new MyEvent(){
		@Override
		public void action() {
			((Textureable) skin).setCurrentFrame(0);
			label.getPos().subtract(nudge);
		}
	};
	
	MyEvent click = new MyEvent(){	
		@Override
		public void action() {
			e.action();		
			((Textureable) skin).setCurrentFrame(1);
			label.getPos().add(nudge);
		}
	};
		
	public Button(Point pos, int w, int h, String label, MyEvent e) {
		skin = ShapeFactory.createRectTexClick(
				pos.getNew(), w, h, release, MyEvent.EMPTY, click, "BUTTONCOL");
		Integer tex = Texturing.getTexIdFor(label);
		if(tex == 0)
			this.label = new Label(pos, w, label);
		else
			this.label = ShapeFactory.createRectTex(pos, w, h, label);
		if(e != null)
			this.e = e;
	}
	
	public void toRender(int l){
		Render.addUi(skin, l);
		Render.addUi(label, l);
	}

	public Clickable getClickable() {
		return (Clickable) skin;
	}

}
