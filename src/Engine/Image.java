package Engine;

import java.awt.Graphics;

public class Image extends Component {

	private Sprite Sprite;

	public Image (Sprite sprite) {
		Sprite = sprite;
	}

	void Initialize () {
		SetNativeSize ();
	}

	protected void Paint (Graphics graphics) {
		graphics.drawImage (Sprite.GetImage (), (int)GetRectTransform ().GetPosition ().GetX (), (int)GetRectTransform ().GetPosition ().GetY (), (int)GetRectTransform ().GetSize ().GetX (), (int)GetRectTransform ().GetSize ().GetY (), Engine.GetJFrame ());
	}

	public Sprite GetSprite () {
		return Sprite;
	}

	public void SetSprite (Sprite sprite) {
		Sprite = sprite;
	}

	public void SetNativeSize () {
		GetRectTransform ().SetSize (Sprite.GetWidth (), Sprite.GetHeight ());
	}

}