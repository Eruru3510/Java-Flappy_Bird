import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import Engine.BoxCollider2D;
import Engine.Component;
import Engine.Engine;
import Engine.GameObject;
import Engine.Input;
import Engine.RectTransform;
import Engine.SpriteAnimation;
import Engine.Text;
import Engine.Time;
import Engine.Vector2;
import Engine.Vector4;

enum GameState {
	Start, Run, GameOver
}

public class GameManager extends Component {

	private static GameManager Instance;

	private GameObject Start, GameOver;
	private RectTransform Ground, Background, CurrentColumn;
	private RectTransform[] Columns;
	private SpriteAnimation Bird;
	private Text ScoreText;

	private GameState GameStatus;
	private Random Random = new Random ();
	private Vector2 GroundPosition = new Vector2 (), BirdPosition = new Vector2 ();
	private float MoveSpeed = 175, Gravity = 2000, JumpForce = 500, Inertia, FallSpeed;
	private float ColumnStartDistance = 750, ColumnSpacing = 250, ColumnMaxY = 230, ColumnMinY = 470;
	private Vector4 RestartButtonBounds = new Vector4 (140, 360, 290, 445);
	private int Score;

	protected void Start () {
		Background = Engine.FindGameObject ("Background").GetRectTransform ();
		Columns = new RectTransform[] { Engine.FindGameObject ("Column 0").GetRectTransform (), Engine.FindGameObject ("Column 1").GetRectTransform (), Engine.FindGameObject ("Column 2").GetRectTransform () };
		Ground = Engine.FindGameObject ("Ground").GetRectTransform ();
		Bird = Engine.FindGameObject ("Bird").GetComponent (SpriteAnimation.class);
		Start = Engine.FindGameObject ("Start");
		GameOver = Engine.FindGameObject ("Game Over");

		BoxCollider2D[] BoxCollider2Ds;
		for (RectTransform Column : Columns) {
			BoxCollider2Ds = Column.GetGameObject ().GetComponents (BoxCollider2D.class).toArray (new BoxCollider2D[0]);
			BoxCollider2Ds[0].SetSize (BoxCollider2Ds[0].GetSize ().GetX (), 528);
			BoxCollider2Ds[1].SetLocalPosition (BoxCollider2Ds[1].GetPosition ().GetX (), 671);
		}

		GroundPosition.SetY (Background.GetSize ().GetY () - Ground.GetSize ().GetY ());

		ScoreText = Engine.FindGameObject ("Score").GetComponent (Text.class);
		ScoreText.SetColor (Color.yellow);
		ScoreText.SetFont (new Font ("Î¢ÈíÑÅºÚ", Font.BOLD, 16));
		ScoreText.GetRectTransform ().SetPosition (5, 40);

		SetGameStatus (GameState.Start);
	}

	protected void Update () {
		if (Input.GetMouseDown (0)) {
			ChangeGameStatus ();
		}
		if (GameStatus != GameState.GameOver) {
			MoveGround ();
		}
		if (GameStatus == GameState.Run) {
			MoveColumns ();
		}
		if (GameStatus != GameState.Start) {
			ControlBird ();
		}
	}

	void SetGameStatus (GameState gameState) {
		switch (gameState) {
			case Start:
				Start.SetActive (true);
				GameOver.SetActive (false);
				Score = 0;
				CurrentColumn = null;
				ScoreText.SetText ("Score:0");
				FallSpeed = 0;
				Bird.Play ();
				BirdPosition.Set (Background.GetSize ().GetX () * 0.25F, Background.GetSize ().GetY () * 0.4F);
				Bird.GetRectTransform ().SetPosition (BirdPosition);
				for (int i = 0; i < Columns.length; i++) {
					Columns[i].SetPosition (ColumnStartDistance + (ColumnSpacing * i), GetColumnY ());
				}
				break;
			case Run:
				Start.SetActive (false);
				break;
			case GameOver:
				Start.SetActive (false);
				GameOver.SetActive (true);
				Bird.Stop ();
				break;
		}
		GameStatus = gameState;
	}

	static GameManager GetInatsnce () {
		if (Instance == null) {
			Instance = Engine.FindGameObject ("Game Manager").GetComponent (GameManager.class);
		}
		return Instance;
	}

	private void ChangeGameStatus () {
		switch (GameStatus) {
			case Start:
				SetGameStatus (GameState.Run);
				break;
			case Run:

				break;
			case GameOver:
				if (Input.GetMousePosition ().GetX () >= RestartButtonBounds.GetX () && Input.GetMousePosition ().GetX () <= RestartButtonBounds.GetZ ()) {
					if (Input.GetMousePosition ().GetY () >= RestartButtonBounds.GetY () && Input.GetMousePosition ().GetX () <= RestartButtonBounds.GetW ()) {
						SetGameStatus (GameState.Start);
					}
				}
				break;
		}
	}

	private void ControlBird () {
		FallSpeed += Gravity * Time.GetDeltaTime ();
		if (Input.GetMouseDown (0) && GameStatus == GameState.Run) {
			FallSpeed = 0;
			Inertia = JumpForce;
		}
		BirdPosition.SetY (BirdPosition.GetY () + (FallSpeed - Inertia) * Time.GetDeltaTime ());
		Inertia -= Inertia * Time.GetDeltaTime ();
		if (Inertia < 0) {
			Inertia = 0;
		}
		if (BirdPosition.GetY () + Bird.GetRectTransform ().GetSize ().GetY () >= Ground.GetPosition ().GetY ()) {
			BirdPosition.SetY (Ground.GetPosition ().GetY () - Bird.GetRectTransform ().GetSize ().GetY ());
			SetGameStatus (GameState.GameOver);
		} else if (BirdPosition.GetY () < 0) {
			BirdPosition.SetY (0);
		}
		Bird.GetRectTransform ().SetPosition (BirdPosition);
	}

	private void MoveGround () {
		GroundPosition.SetX (GroundPosition.GetX () - MoveSpeed * Time.GetDeltaTime ());
		if (GroundPosition.GetX () + Ground.GetSize ().GetX () < Background.GetSize ().GetX ()) {
			GroundPosition.SetX (-(Background.GetSize ().GetX () - (GroundPosition.GetX () + Ground.GetSize ().GetX ()) % Background.GetSize ().GetX ()));
		}
		Ground.SetPosition (GroundPosition);
	}

	private void MoveColumns () {
		for (RectTransform Column : Columns) {
			Column.Translate (Vector2.Multiply (Vector2.Left, MoveSpeed));
			if (Column.GetPosition ().GetX () + Column.GetSize ().GetX () < Bird.GetRectTransform ().GetPosition ().GetX ()) {
				if (CurrentColumn != Column) {
					CurrentColumn = Column;
					Score++;
					ScoreText.SetText (String.format ("Score:%s", Score));
				}
				if (Column.GetPosition ().GetX () + Column.GetSize ().GetX () < 0) {
					Column.SetPosition ((ColumnSpacing * 3) - Columns[0].GetSize ().GetX (), GetColumnY ());
				}
			}
		}
	}

	private int RandomNextInt (int min, int max) {
		return Random.nextInt (max - min) + min;
	}

	private int GetColumnY () {
		return -RandomNextInt ((int)ColumnMaxY, (int)ColumnMinY);
	}

}