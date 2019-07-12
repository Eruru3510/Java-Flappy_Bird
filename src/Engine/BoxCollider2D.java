package Engine;

import java.awt.Color;
import java.awt.Graphics;

public class BoxCollider2D extends Component {

	private Vector2 LocalPosition = new Vector2 (), Size = new Vector2 ();
	private Vector2 Position = new Vector2 ();
	private boolean ShowBoundsBox;

	void Initialize () {
		Size.Set (GetRectTransform ().GetSize ());
	}

	protected void Start () {
		Physics2D.RegisterCollider (this);
	}

	protected void Update () {
		Position.Set (GetRectTransform ().GetPosition ().GetX () + LocalPosition.GetX (), GetRectTransform ().GetPosition ().GetY () + LocalPosition.GetY ());
	}

	void Paint (Graphics graphics) {
		if (ShowBoundsBox) {
			graphics.setColor (Color.green);
			graphics.drawRect ((int)Position.GetX (), (int)Position.GetY (), (int)Size.GetX (), (int)Size.GetY ());
		}
	}

	public Vector2 GetPosition () {
		return Position;
	}

	public Vector2 GetSize () {
		return Size;
	}

	public void SetLocalPosition (float x, float y) {
		LocalPosition.Set (x, y);
	}

	public void SetSize (float x, float y) {
		Size.Set (x, y);
	}

	public void ShowBoundsBox () {
		ShowBoundsBox = true;
	}

	public void HideBoundsBox () {
		ShowBoundsBox = false;
	}

}