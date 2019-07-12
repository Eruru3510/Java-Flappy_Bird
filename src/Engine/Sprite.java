package Engine;

import javax.swing.ImageIcon;

public class Sprite {

	private ImageIcon ImageIcon;
	private java.awt.Image Image;
	private int Width, Height;

	public Sprite (String path) {
		SetImage (path);
	}

	public java.awt.Image GetImage () {
		return Image;
	}

	public int GetWidth () {
		return Width;
	}

	public int GetHeight () {
		return Height;
	}

	public void SetImage (ImageIcon imageIcon) {
		Width = imageIcon.getIconWidth ();
		Height = imageIcon.getIconHeight ();
		Image = imageIcon.getImage ();
		ImageIcon = imageIcon;
	}

	public void SetImage (String path) {
		SetImage (new ImageIcon (path));
	}

}