import Engine.BoxCollider2D;
import Engine.Engine;
import Engine.GameObject;
import Engine.Image;
import Engine.Sprite;
import Engine.SpriteAnimation;
import Engine.Text;

public class Main {

	public static void main (String[] args) {
		Sprite Sprite = new Sprite ("Images/column.png");
		Engine.SetTitle ("Flappy Bird");
		Engine.SetSize (448, 683);
		Engine.SetFrameRate (60);
		Engine.AddGameObject (new GameObject ("Game Manager", new GameManager ()));
		Engine.AddGameObject (new GameObject ("Background", new Image (new Sprite ("Images/bg.png"))));
		Engine.AddGameObject (new GameObject ("Column 0", new Image (Sprite), new BoxCollider2D (), new BoxCollider2D ()));
		Engine.AddGameObject (new GameObject ("Column 1", new Image (Sprite), new BoxCollider2D (), new BoxCollider2D ()));
		Engine.AddGameObject (new GameObject ("Column 2", new Image (Sprite), new BoxCollider2D (), new BoxCollider2D ()));
		Engine.AddGameObject (new GameObject ("Ground", new Image (new Sprite ("Images/ground.png"))));
		Engine.AddGameObject (new GameObject ("Bird", new Bird (), new SpriteAnimation ("Images/0.png", "Images/1.png", "Images/2.png", "Images/3.png", "Images/4.png", "Images/5.png", "Images/6.png", "Images/7.png"), new BoxCollider2D ()));
		Engine.AddGameObject (new GameObject ("Start", new Image (new Sprite ("Images/start.png"))));
		Engine.AddGameObject (new GameObject ("Game Over", new Image (new Sprite ("Images/gameover.png"))));
		Engine.AddGameObject (new GameObject ("Score", new Text ()));
		Engine.AddGameObject (new GameObject ("FPS Counter", new Text (), new FPSCounter ()));
		Engine.Start ();
	}

}