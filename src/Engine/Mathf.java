package Engine;

public class Mathf {

	private Mathf () {

	}

	static Vector2 RotateSize (Vector2 size, float angle) {
		float Width = size.GetX (), Height = size.GetY ();
		if (angle >= 90) {
			if (angle / 90 % 2 == 1) {
				float temp = Width;
				Height = Width;
				Width = temp;
			}
			angle = angle % 90;
		}
		double r = Math.sqrt (Height * Height + Width * Width) / 2;
		double len = 2 * Math.sin (Math.toRadians (angle) / 2) * r;
		double angel_alpha = (Math.PI - Math.toRadians (angle)) / 2;
		double angel_dalta_width = Math.atan ((double)Height / Width);
		double angel_dalta_height = Math.atan ((double)Width / Height);
		float len_dalta_width = (int)(len * Math.cos (Math.PI - angel_alpha - angel_dalta_width));
		float len_dalta_height = (int)(len * Math.cos (Math.PI - angel_alpha - angel_dalta_height));
		float des_width = Width + len_dalta_width * 2;
		float des_height = Height + len_dalta_height * 2;
		return new Vector2 (des_width, des_height);
	}

}