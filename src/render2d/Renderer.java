package render2d;

import java.util.ArrayList;
import java.util.List;

import render2d.drawable.Drawable;

public class Renderer {

	private List<List<Drawable>> layers;
	
	public Renderer() {
		layers = new ArrayList<>();
		layers.add(new ArrayList<>());
	}

	public void addDrawable(List<? extends Drawable> s, int l){
		for(Drawable sh: s)
			addDrawable(sh, l);
	}
	
	public void addDrawable(Drawable[] s, int l){
		for(Drawable sh: s)
			addDrawable(sh, l);
	}

	public void addDrawable(Drawable s, int l){
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
		for(List<Drawable> l : layers)
			for(Drawable s : l)
				s.draw();
		flush();
	}	

}
