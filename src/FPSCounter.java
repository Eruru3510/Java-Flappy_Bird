import java.awt.Color;
import java.awt.Font;

import Engine.Component;
import Engine.Input;
import Engine.Text;
import Engine.Time;

public class FPSCounter extends Component {

	int FPS, FPSNumber;
	float UpdateTime;
	Text Text;

	protected void Start () {
		Text = GetGameObject ().GetComponent (Text.class);
		Text.SetText ("FPS:0");
		Text.SetColor (Color.white);
		Text.SetFont (new Font ("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
		Text.GetRectTransform ().SetPosition (5, 40);
		Text.GetGameObject ().SetActive (false);
	}

	protected void Update () {
		FPSNumber++;
		if (Time.GetTime () - UpdateTime >= 1) {
			UpdateTime = Time.GetTime ();
			FPS = FPSNumber;
			FPSNumber = 0;
		}
		Text.SetText (String.format ("FPS:%s X:%s Y:%s", FPS, Input.GetMousePosition ().GetX (), Input.GetMousePosition ().GetY ()));
	}

}