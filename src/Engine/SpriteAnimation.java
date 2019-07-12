package Engine;

import java.awt.Graphics;
import java.util.ArrayList;

public class SpriteAnimation extends Component {

	private ArrayList<Sprite> Sprites = new ArrayList<Sprite> ();
	private float CurrentTime, UpdateTime, FrameInterval;
	private int Index, FrameRate;
	private boolean Playing = true;

	public SpriteAnimation (String... paths) {
		for (String Path : paths) {
			AddFrame (new Sprite (Path));
		}
	}

	void Initialize () {
		SetNativeSize ();
		SetFrameRate (24);
	}

	protected void Update () {
		if (Playing) {
			if (CurrentTime - UpdateTime >= FrameInterval) {
				Index += (int)((CurrentTime - UpdateTime) / FrameInterval);
				if (Index >= Sprites.size ()) {
					Index = Index % Sprites.size ();
				}
				UpdateTime = CurrentTime;
			}
			CurrentTime += Time.GetDeltaTime ();
		}
	}

	void Paint (Graphics graphics) {
		graphics.drawImage (Sprites.get (Index).GetImage (), (int)GetRectTransform ().GetPosition ().GetX (), (int)GetRectTransform ().GetPosition ().GetY (), (int)GetRectTransform ().GetSize ().GetX (), (int)GetRectTransform ().GetSize ().GetY (), Engine.GetJFrame ());
	}

	public void AddFrame (Sprite sprite) {
		Sprites.add (sprite);
	}

	public void SetFrameRate (int frameRate) {
		FrameInterval = 1F / frameRate;
		FrameRate = frameRate;
	}

	public void SetFrame (int index) {
		Index = index;
	}

	public void SetNativeSize () {
		GetRectTransform ().SetSize (Sprites.get (0).GetWidth (), Sprites.get (1).GetHeight ());
	}

	public void Play () {
		Playing = true;
	}

	public void Stop () {
		Playing = false;
	}

}