package Engine;

public class Input {

	private static Vector2 MousePosition = new Vector2 ();
	private static boolean MouseLeftDown, MouseLeftDowned, MouseLeftPressed;

	private Input () {

	}

	static void Update () {
		if (MouseLeftDown) {
			MouseLeftDown = false;
		} else if (!MouseLeftDowned && MouseLeftPressed) {
			MouseLeftDown = true;
			MouseLeftDowned = true;
		}
		if (MouseLeftDowned && !MouseLeftPressed) {
			MouseLeftDowned = false;
		}
	}

	public static boolean GetMouseDown (int index) {
		switch (index) {
			default:
				return MouseLeftDown;
		}
	}

	public static Vector2 GetMousePosition () {
		return MousePosition;
	}

	static void SetMouseLeftPressed (boolean pressed) {
		MouseLeftPressed = pressed;
	}

	static void SetMousePosition (float x, float y) {
		MousePosition.Set (x, y);
	}

}