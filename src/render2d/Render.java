package render2d;
import java.util.ArrayList;
import java.util.List;

import render2d.shape.Rect;
import render2d.shape.RectTex;
import render2d.shape.Shape;
import render2d.write.Symbol;
import render2d.write.Word;
import root.Controls;

import static org.lwjgl.opengl.GL11.*;


public class Render {
	
	// TODO: draw words by layers

	//static List<Word> wrd = new ArrayList<>();
	
	static List<Shape> tex = new ArrayList<>();
	static List<ArrayList<Shape>> layer = new ArrayList<>();
	
	public static void addShape(Shape[] s, int l){
		if(l >= layer.size())
			for(int i = layer.size();i <= l;i++)
				layer.add(new ArrayList<Shape>());

		for(int i = 0; i < s.length;i++)
			layer.get(l).add(s[i]);
	}
	
	public static void addShape(Shape s, int l){
		if(l >= layer.size())
			for(int i = layer.size();i <= l;i++)
				layer.add(new ArrayList<Shape>());

		layer.get(l).add(s);
	}

	public static void flush(){
		layer.clear();
	}
	
	public static void drawFrame(){
		Camera.create();
	    Controls.navigate();

		glBindTexture(GL_TEXTURE_2D,0);
	    glBegin(GL_TRIANGLES);	
	    
		for(ArrayList<Shape> l : layer){
			for(Shape s : l){
				if(s instanceof Rect)
					s.draw();
				else if(s instanceof RectTex)
					tex.add(s);
			}
			
			if(!tex.isEmpty()){
				for(Shape s : tex){
					glEnd();	
					s.draw();
				}
				tex.clear();
				glEnd();
				glBindTexture(GL_TEXTURE_2D,0);
			    glBegin(GL_TRIANGLES);	
			}
			
		}
		glEnd();
		flush();
	}	

	public static void drawWord(Word w){
		glEnd();
		glBindTexture(GL_TEXTURE_2D,1);
		glBegin(GL_TRIANGLES);	
		
		w.draw();
		
		glEnd();
		glBindTexture(GL_TEXTURE_2D,0);
	    glBegin(GL_TRIANGLES);	
	}
}