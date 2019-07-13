package Engine;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sprite {

	private BufferedImage BufferedImage;
	private Vector2 Size = new Vector2 ();

	public Sprite (String path) {
		SetBufferedImage (path);
	}

	public BufferedImage GetBufferedImage () {
		return BufferedImage;
	}

	public Vector2 GetSize () {
		return Size;
	}

	public void SetBufferedImage (BufferedImage bufferedImage) {
		Size.Set (bufferedImage.getWidth (), bufferedImage.getHeight ());
		BufferedImage = bufferedImage;
	}

	public void SetBufferedImage (String path) {
		try {
			SetBufferedImage (ImageIO.read (getClass ().getResource (path)));
		} catch (Exception Exception) {
			Exception.printStackTrace ();
		}
	}

}