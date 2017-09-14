package com.mengoud.babynametor.core;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class AppUtil {
	public static Image loadImage(String path, int w, int h) {
		try {
			path = "/assets/" + path;
			InputStream is = AppUtil.class.getResourceAsStream(path);
			BufferedImage bi = ImageIO.read(is);
			Image img = bi.getScaledInstance(w, h, BufferedImage.SCALE_DEFAULT);
			return img;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Icon loadIcon(String path, int w, int h) {
		Image img = loadImage(path, w, h);
		ImageIcon icon = new ImageIcon(img);
		return icon;
	}
}
