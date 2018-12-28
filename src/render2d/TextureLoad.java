package render2d;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;

public class TextureLoad {
	private static TextureImpl texture = null;
	
	
	public static TextureImpl load(String path, int fw, int fh){
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
		texture.setTextureHeight(texture.getImageHeight()/fh);
		texture.setTextureWidth(texture.getImageWidth()/fw);
		
		return texture;
	}
	
	public static void loadAll(){
		// example  :   texture=Load("res/spore.png");		//1
		texture = load("res/abc_0.png",1,1);
		texture = load("res/Shroomz.png",2,2);

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
}
