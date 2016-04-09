package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public final class IconLibrary {
		
	
	public final static ImageIcon LIST_ICON = generateBufferedImageIcon("./src/icons/list.png", 30, 30);
	
	public final static ImageIcon GRAPH_ICON = generateBufferedImageIcon("./src/icons/graph.png", 30, 30);
	
	
	public static ImageIcon generateBufferedImageIcon(String path, int x, int y) {
		BufferedImage icon = null;
		
		try {
			icon = ImageIO.read(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		Image bufferedIcon = icon.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		
		ImageIcon finalIcon = new ImageIcon(bufferedIcon);
		
		return finalIcon;
	}
	
	public final static ImageIcon generateImageIcon(String filename) {
		
		BufferedImage icon = null;
		
		try {
			icon = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		ImageIcon finalIcon = new ImageIcon(icon);
		
		return finalIcon;
	}
}
