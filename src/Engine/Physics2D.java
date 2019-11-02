package Engine;

import java.util.ArrayList;

public class Physics2D {

	private static ArrayList<Component> Colliders = new ArrayList<Component> ();
	private static BoxCollider2D BoxCollider2D, TargetBoxCollider2D;
	private static Vector4 Bounds = new Vector4 (), TargetBounds = new Vector4 ();

	private Physics2D () {

	}

	static void Update () {
		for (int i = Colliders.size () - 1; i >= 0; i--) {
			BoxCollider2D = (BoxCollider2D)Colliders.get (i);
			Bounds.Set (BoxCollider2D.GetPosition ().GetX (), BoxCollider2D.GetPosition ().GetY (), BoxCollider2D.GetPosition ().GetX () + BoxCollider2D.GetSize ().GetX (), BoxCollider2D.GetPosition ().GetY () + BoxCollider2D.GetSize ().GetY ());
			for (int n = Colliders.size () - 1; n >= 0; n--) {
				if (n != i) {
					TargetBoxCollider2D = (BoxCollider2D)Colliders.get (n);
					TargetBounds.Set (TargetBoxCollider2D.GetPosition ().GetX (), TargetBoxCollider2D.GetPosition ().GetY (), TargetBoxCollider2D.GetPosition ().GetX () + TargetBoxCollider2D.GetSize ().GetX (), TargetBoxCollider2D.GetPosition ().GetY () + TargetBoxCollider2D.GetSize ().GetY ());
					if (Bounds.GetX () <= TargetBounds.GetZ () && Bounds.GetZ () >= TargetBounds.GetX () && Bounds.GetY () <= TargetBounds.GetW () && Bounds.GetW () >= TargetBounds.GetY ()) {
						Colliders.get (i).GetGameObject ().OnColliderStay (new Collision (TargetBoxCollider2D.GetGameObject ()));
					}
				}
			}
		}
	}

	static void RegisterCollider (Component collider) {
		Colliders.add (collider);
	}

}