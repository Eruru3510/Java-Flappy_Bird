package Engine;

import java.awt.Graphics;
import java.util.ArrayList;

public class GameObject {

	private ArrayList<Component> Components = new ArrayList<Component> ();
	private ArrayList<GameObject> GameObjects = new ArrayList<GameObject> ();
	private RectTransform RectTransform;
	private GameObject Parent;
	private String Name = "";
	private boolean Active = true;

	public GameObject (String name, Component... components) {
		Name = name;
		RectTransform = new RectTransform ();
		AddComponent (RectTransform);
		for (Component Component : components) {
			AddComponent (Component);
		}
	}

	void Initialize () {
		for (Component Component : Components) {
			Component.InsideInitialize (this);
		}
	}

	void OnColliderStay (Collision collision) {
		for (Component Component : Components) {
			Component.InsideOnColliderStay (collision);
		}
	}

	void Start () {
		if (Active) {
			for (Component Component : Components) {
				Component.InsideStart ();
			}
		}
	}

	void Update () {
		if (Active) {
			for (Component Component : Components) {
				Component.InsideUpdate ();
			}
		}
	}

	void Paint (Graphics graphics) {
		if (Active) {
			for (Component Component : Components) {
				Component.InsidePaint (graphics);
			}
		}
	}

	public <T> T GetComponent (Class clas) {
		for (Component Component : Components) {
			if (Component.getClass ().getTypeName () == clas.getTypeName ()) {
				return (T)Component;
			}
		}
		return null;
	}

	public ArrayList<Component> GetComponents (Class clas) {
		ArrayList<Component> Components = new ArrayList<Component> ();
		for (Component Component : this.Components) {
			if (Component.getClass ().getTypeName () == clas.getTypeName ()) {
				Components.add (Component);
			}
		}
		return Components;
	}

	public RectTransform GetRectTransform () {
		return RectTransform;
	}

	public String GetName () {
		return Name;
	}

	public boolean GetActive () {
		return Active;
	}

	public void SetName (String name) {
		Name = name;
	}

	public void SetActive (boolean active) {
		Active = active;
	}

	public void SetParent (GameObject gameObject) {
		if (Parent != gameObject) {
			if (Parent != null) {
				Parent.RemoveGameObject (this);
			}
			if (gameObject != null) {
				gameObject.AddGameObject (this);
			}
			Parent = gameObject;
		}
	}

	public void AddComponent (Component component) {
		Components.add (component);
	}

	void AddGameObject (GameObject gameObject) {
		gameObject.GameObjects.add (gameObject);
	}

	void RemoveGameObject (GameObject gameObject) {
		gameObject.GameObjects.remove (gameObject);
	}

}