package Engine;

import java.awt.Graphics;

public abstract class Component {

	private GameObject GameObject;
	private RectTransform RectTransform;
	private boolean Initialized, Started;

	void InsideInitialize (GameObject gameObject) {
		if (!Initialized) {
			RectTransform = gameObject.GetRectTransform ();
			GameObject = gameObject;
			Initialize ();
			Initialized = true;
		}
	}

	void InsideStart () {
		if (!Started) {
			Start ();
			Started = true;
		}
	}

	void InsideOnColliderStay (Collision collision) {
		if (Started) {
			OnColliderStay (collision);
		}
	}

	void InsideUpdate () {
		if (Started) {
			Update ();
		}
	}

	void InsidePaint (Graphics graphics) {
		if (Started) {
			Paint (graphics);
		}
	}

	void Initialize () {

	}

	protected void Start () {

	}

	protected void OnColliderStay (Collision collision) {

	}

	protected void Update () {

	}

	void Paint (Graphics graphics) {

	}

	public RectTransform GetRectTransform () {
		return RectTransform;
	}

	public GameObject GetGameObject () {
		return GameObject;
	}

}