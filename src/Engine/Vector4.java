package Engine;

public class Vector4 {

	private float X, Y, Z, W;

	public Vector4 () {

	}

	public Vector4 (float x, float y, float z, float w) {
		X = x;
		Y = y;
		Z = z;
		W = w;
	}

	public Vector4 (Vector4 vector4) {
		X = vector4.GetX ();
		Y = vector4.GetY ();
		Z = vector4.GetZ ();
		W = vector4.GetW ();
	}

	public float GetX () {
		return X;
	}

	public float GetY () {
		return Y;
	}

	public float GetZ () {
		return Z;
	}

	public float GetW () {
		return W;
	}

	public void SetX (float x) {
		X = x;
	}

	public void SetY (float y) {
		Y = y;
	}

	public void SetZ (float z) {
		Z = z;
	}

	public void SetW (float w) {
		W = w;
	}

	public void Set (float x, float y, float z, float w) {
		X = x;
		Y = y;
		Z = z;
		W = w;
	}

}
