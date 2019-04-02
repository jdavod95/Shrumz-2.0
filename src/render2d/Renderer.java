package render2d;

import java.util.ArrayList;
import java.util.List;

import render2d.shape.Shape;

public class Renderer {

	private List<List<Shape>> layers;
	
	public Renderer() {
		super();
		layers = new ArrayList<>();
		layers.add(new ArrayList<>());
		layers.add(new ArrayList<>());
	}

	public void addShape(Shape[] s, int l){
		for(Shape sh: s)
			addShape(sh, l);
	}

	public void addShape(Shape s, int l){
		if(l >= layers.size())
			expand(l);
		layers.get(l).add(s);
	}
	
	private void flush(){
		layers.clear();
	}
	
	private void expand(int l){
		for(int i = layers.size();i <= l;i++)
			layers.add(new ArrayList<>());
	}
	
	public void drawFrame(){
		for(List<Shape> l : layers)
			for(Shape s : l)
				s.draw();
		flush();
	}	

}
