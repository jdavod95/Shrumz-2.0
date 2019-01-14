package render2d;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.Point;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureLoad {
	private static TextureImpl texture = null;
	private static Point[] dims;
	
	public static TextureImpl load(String path, int fw, int fh, int fid){
		try {
			texture = (TextureImpl)TextureLoader.getTexture("PNG", new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}

		dims[fid-1] = new Point(fw, fh);
		
		return texture;
	}
	
	public static void loadAll(){
		// example  :   texture=Load("res/spore.png");		//1
		dims  = new Point[4];
		texture = load("res/abc_0.png",1,1,1);
		texture = load("res/Shrum64.png",2,2,2);
		texture = load("res/Weed64.png",2,4,3);
		texture = load("res/Controls.png",2,2,4);
		
	}
	public static int getTW(){
		return texture.getTextureWidth();
	}
	public static int getTH(){
		return texture.getTextureHeight();
	}
	public static int getIW(){
		return texture.getImageWidth();
	}
	public static int getIH(){
		return texture.getImageHeight();
	}
	public static void setDims(int fid){
		texture.setTextureWidth(texture.getImageWidth()/dims[fid-1].getX());
		texture.setTextureHeight(texture.getImageHeight()/dims[fid-1].getY());
			
	}
}
