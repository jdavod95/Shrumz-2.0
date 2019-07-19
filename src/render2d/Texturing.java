package render2d;

import static org.lwjgl.opengl.GL11.glTexCoord2d;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;

public class Texturing {
	
	private static HashMap<String, Texture> textures = new HashMap<>();
	private static TextureImpl texture;
	
	private static void load(File file){
		try {
			texture = (TextureImpl)TextureLoader.getTexture("PNG", new FileInputStream(file));
			storeTexture(file.getName());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
	}

	private static void storeTexture(String name) {
		// 	Shrum_64.png
		//	becomes: 64
		int frameSize = Integer.parseInt(
				name.substring(
						name.lastIndexOf('_')+1, 
						name.lastIndexOf('.')
						)
				);
		
		// 	Shrum_64.png
		//	becomes: SHRUM
		String texName = name.substring(
						0, name.lastIndexOf('_')).toUpperCase();
		
		textures.put(
				texName,
				new Texture(
					texName,
					frameSize,
					getIH()/frameSize,
					getIW()/frameSize,
					textures.size()+1
				)
			);
	}
	
	/**Returns a list of absolute paths,
	 *  of files in folders and sub-folders,
	 *  recursively.
	 * 	Ignores folders called "raw".
	 * @param target folder
	 * @return absolute paths of found files
	 */
	private static List<File> getFilesForFolder(File folder) {
		List<File> files = new ArrayList<>();
	    for (File fileEntry : folder.listFiles()) {
	    	if(!fileEntry.isDirectory()) 
	            files.add(fileEntry);
	    	else if (!fileEntry.getName().equals("raw"))
	    		files.addAll(getFilesForFolder(fileEntry));
	    	
	    }
	    return files;
	}

	public static void loadAll(){ 
		List<File> files = getFilesForFolder(new File("res/textures"));
		for(int i = 0;i < files.size();i++) 
			load(files.get(i));
	}
			
	public static int getTexIdFor(String name) {
		Texture tex = textures.get(name);
		if(tex == null)
			return 0;
		return tex.getId();
	}
	
	private static int getTW(){
		return texture.getTextureWidth();
	}
	
	private static int getTH(){
		return texture.getTextureHeight();
	}
	
	private static int getIW(){
		return texture.getImageWidth();
	}
	
	private static int getIH(){
		return texture.getImageHeight();
	}
	
	public static void setTexture(double x, double y, String texName, int fcu){
		Texture tex = textures.get(texName);
		if(tex == null)
			return;
		
		texture.setTextureHeight(getIH() / tex.getRows());
		texture.setTextureWidth(getIW() / tex.getColumns());
		
		double w = (double) getTW() / getIW();
		double h = (double) getTH() / getIH();
		x = (x * w) + ((fcu % tex.getColumns()) * w);
		/// nemjó
		y = (y * h) + (((fcu / tex.getColumns()) + (fcu % tex.getColumns())) * h);
		glTexCoord2d(x, y);
	}
}
