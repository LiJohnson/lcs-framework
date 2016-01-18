package io.lcs.framework.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;

/**
 * Created by lcs on 15-5-25.
 */
public class Image {
	private static final int IMAGE_SIZE = 400;

	/**
	 * 对图片进行处理，如果不是400*400的再进行放缩
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static BufferedImage getSearch(String url) throws IOException {
		BufferedImage img = ImageIO.read( URI.create(URLTool.prase(url)).toURL().openStream() );// toBufferedImage(Toolkit.getDefaultToolkit().getImage());
		int height = img.getHeight();
		int width = img.getWidth();

		if (width == IMAGE_SIZE && height == IMAGE_SIZE) return img;

		return resize(url);
	}

	private static BufferedImage resize ( String url ) throws IOException {
		BufferedImage img = toBufferedImage(Toolkit.getDefaultToolkit().getImage(URI.create(url).toURL()));
		int height = img.getHeight();
		int width = img.getWidth();

		if (width > height) {
			height = height * IMAGE_SIZE / width;
			width = IMAGE_SIZE;
		} else {
			width = width * IMAGE_SIZE / height;
			height = IMAGE_SIZE;
		}

		BufferedImage newImage = new BufferedImage(width, height, img.getType());
		Graphics g = newImage.getGraphics();
		g.drawImage(img, 0, 0, width, height, null);
		g.dispose();

	//	ImageIO.write(newImage, "JPG", new File("/tmp/newImage.jpg"));
		return newImage;

	}
	private static BufferedImage toBufferedImage(java.awt.Image image) {
		if (image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		// This code ensures that all the pixels in the image are loaded
		image = new ImageIcon(image).getImage();

		// Determine if the image has transparent pixels; for this method's
		// implementation, see e661 Determining If an Image Has Transparent Pixels
		//boolean hasAlpha = hasAlpha(image);

		// Create a buffered image with a format that's compatible with the screen
		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
	       /* if (hasAlpha) {
                transparency = Transparency.BITMASK;
            }*/

			// Create the buffered image
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(
					image.getWidth(null), image.getHeight(null), transparency);
		} catch (HeadlessException e) {
			// The system does not have a screen
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			//int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
            /*if (hasAlpha) {
                type = BufferedImage.TYPE_INT_ARGB;
            }*/
			bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		}

		// Copy image to buffered image
		Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return bimage;
	}
}
