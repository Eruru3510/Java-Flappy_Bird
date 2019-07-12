package Engine;

public class Collision {

	private GameObject GameObject;

	Collision (GameObject gameObject) {
		GameObject = gameObject;
	}

	public GameObject GetGameObject () {
		return GameObject;
	}

}