import java.awt.Color;
import java.awt.Font;

import Engine.Component;
import Engine.Debug;
import Engine.Engine;
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
		Text.SetFont (new Font ("微软雅黑", Font.BOLD, 14));
		Text.GetRectTransform ().SetPosition (5, 20);
	}

	protected void Update () {
		FPSNumber++;
		if (Time.GetTime () - UpdateTime >= 1) {
			UpdateTime = Time.GetTime ();
			FPS = FPSNumber;
			FPSNumber = 0;
			Debug.LogFormat ("已运行时间：%s 目标帧数：%s 实际帧数：%s 上一帧所耗时间：%s", Time.GetTime (), Engine.GetFrameRate (), FPS, Time.GetDeltaTime ());
		}
		Text.SetText (String.format ("FPS:%s X:%s Y:%s", FPS, Input.GetMousePosition ().GetX (), Input.GetMousePosition ().GetY ()));
	}

}
