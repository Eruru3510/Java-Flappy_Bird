package Engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Text extends Component {

	private String Text = "";
	private Font Font;
	private Color Color;

	void Paint (Graphics graphics) {
		graphics.setFont (Font);
		graphics.setColor (Color);
		graphics.drawString (Text, (int)GetRectTransform ().GetPosition ().GetX (), (int)GetRectTransform ().GetPosition ().GetY ());
	}

	public String GetText () {
		return Text;
	}

	public void SetText (String text) {
		Text = text;
	}

	public void SetFont (Font font) {
		Font = font;
	}

	public void SetColor (Color color) {
		Color = color;
	}

}