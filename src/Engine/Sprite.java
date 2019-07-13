package Engine;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sprite {

	private BufferedImage BufferedImage;
	private Vector2 Size = new Vector2 ();

	public Sprite (String path) {
		try {
			BufferedImage = ImageIO.read (getClass ().getResource (path));
			Size.Set (BufferedImage.getWidth (), BufferedImage.getHeight ());
		} catch (Exception Exception) {
			Exception.printStackTrace ();
		}
	}

	public Vector2 GetSize () {
		return Size;
	}

	BufferedImage GetBufferedImage () {
		return BufferedImage;
	}

}