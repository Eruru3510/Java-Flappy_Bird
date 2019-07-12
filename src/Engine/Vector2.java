package Engine;

public class Vector2 {

	public static final Vector2 Up = new Vector2 (0, -1), Down = new Vector2 (0, 1);
	public static final Vector2 Left = new Vector2 (-1, 0), Right = new Vector2 (1, 0);
	public static final Vector2 Zero = new Vector2 ();

	private float X, Y;

	public Vector2 () {

	}

	public Vector2 (float x, float y) {
		X = x;
		Y = y;
	}

	public Vector2 (Vector2 vector2) {
		X = vector2.GetX ();
		Y = vector2.GetY ();
	}

	public float GetX () {
		return X;
	}

	public float GetY () {
		return Y;
	}

	public void SetX (float x) {
		X = x;
	}

	public void SetY (float y) {
		Y = y;
	}

	public void Set (float x, float y) {
		X = x;
		Y = y;
	}

	public void Set (Vector2 vector2) {
		X = vector2.GetX ();
		Y = vector2.GetY ();
	}

	public void Addition (Vector2 vector2) {
		X += vector2.GetX ();
		Y += vector2.GetY ();
	}

	public void Multiply (float value) {
		X *= value;
		Y *= value;
	}

	public static Vector2 Addition (Vector2 left, Vector2 right) {
		return new Vector2 (left.GetX () + right.GetX (), left.GetY () + right.GetY ());
	}

	public static Vector2 Multiply (Vector2 vector2, float value) {
		return new Vector2 (vector2.GetX () * value, vector2.GetY () * value);
	}

}