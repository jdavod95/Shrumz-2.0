package elements;

import render2d.shape.Shape;
import render2d.write.Word;

public class Label extends Shape{

	String word;
	
	public Label(int x, int y, int h, String word) {
		super(x, y, 0, h);
		this.word = word;
	}

	public void draw(){
		Word w = new Word(x, y, h, word);
		w.draw();
	}
	

}
