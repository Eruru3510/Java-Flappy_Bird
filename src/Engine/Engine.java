package Engine;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Engine {

	private static JFrame JFrame = new JFrame ();
	private static JPanel JPanel;
	private static ArrayList<GameObject> GameObjects = new ArrayList<GameObject> ();
	private static int FrameRate, FrameSleepIntervalNanos;
	private static long FrameSleepInterval, FrameStartTime;

	private Engine () {
		SetFrameRate (-1);
	}

	public static int GetFrameRate () {
		return FrameRate;
	}

	public static void SetFrameRate (int frameRate) {
		float Value = 1000F / frameRate;
		FrameSleepInterval = (int)Value;
		FrameSleepIntervalNanos = (int)((Value - FrameSleepInterval) * 1000 * 1000);
		FrameRate = frameRate;
	}

	public static void SetTitle (String title) {
		JFrame.setTitle (title);
	}

	public static void SetSize (int width, int height) {
		JFrame.setSize (width, height);
	}

	public static void Start () {
		JPanel = new JPanel () {
			public void paint (Graphics graphics) {
				Paint (graphics);
			}
		};
		JFrame.add (JPanel);
		JFrame.addWindowListener (new WindowAdapter () {
			public void windowClosing (WindowEvent windowEvent) {
				super.windowClosing (windowEvent);
				System.exit (0);
			}
		});
		JFrame.addMouseListener (new MouseAdapter () {
			public void mousePressed (MouseEvent mouseEvent) {
				Input.SetMouseLeftPressed (true);
			}

			public void mouseReleased (MouseEvent mouseEvent) {
				Input.SetMouseLeftPressed (false);
			}
		});
		JFrame.addMouseMotionListener (new MouseAdapter () {
			public void mouseMoved (MouseEvent mouseEvent) {
				Input.SetMousePosition (mouseEvent.getX (), mouseEvent.getY ());
			}
		});
		JFrame.setVisible (true);
		JFrame.setResizable (false);
		JFrame.setLocationRelativeTo (null);
		while (true) {
			FrameStartTime = System.currentTimeMillis ();
			Update ();
			JPanel.repaint ();
			if (FrameRate > 0) {
				try {
					Thread.sleep (FrameSleepInterval, FrameSleepIntervalNanos);
				} catch (Exception Exception) {
					Exception.printStackTrace ();
					break;
				}
			}
			Time.SetDeltaTime ((System.currentTimeMillis () - FrameStartTime) / 1000F);
			Time.SetTime (Time.GetTime () + Time.GetDeltaTime ());
		}
	}

	public static void AddGameObject (GameObject gameObject) {
		GameObjects.add (gameObject);
	}

	public static GameObject FindGameObject (String name) {
		for (GameObject GameObject : GameObjects) {
			if (GameObject.GetName () == name) {
				return GameObject;
			}
		}
		return null;
	}

	static JFrame GetJFrame () {
		return JFrame;
	}

	private static void Update () {
		for (int i = 0; i < GameObjects.size (); i++) {
			GameObjects.get (i).Initialize ();
		}
		Physics2D.Update ();
		Input.Update ();
		for (int i = 0; i < GameObjects.size (); i++) {
			GameObjects.get (i).Start ();
			GameObjects.get (i).Update ();
		}
	}

	private static void Paint (Graphics graphics) {
		graphics.clearRect (0, 0, JPanel.getWidth (), JPanel.getHeight ());
		for (GameObject GameObject : GameObjects) {
			GameObject.Paint (graphics);
		}
	}

}