package Engine;

public class RectTransform extends Component {

	private Vector2 Position = new Vector2 (), Size = new Vector2 ();

	public Vector2 GetPosition () {
		return Position;
	}

	public Vector2 GetSize () {
		return Size;
	}

	public void SetPosition (Vector2 position) {
		Position = position;
	}

	public void SetPosition (float x, float y) {
		Position.Set (x, y);
	}

	public void SetSize (Vector2 size) {
		Size = size;
	}

	public void SetSize (float x, float y) {
		Size.Set (x, y);
	}

	public void Translate (Vector2 translation) {
		Position.Addition (Vector2.Multiply (translation, Time.GetDeltaTime ()));
	}

}