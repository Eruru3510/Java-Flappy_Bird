import Engine.Collision;
import Engine.Component;

public class Bird extends Component {

	protected void OnColliderStay (Collision collision) {
		GameManager.GetInatsnce ().SetGameStatus (GameState.GameOver);
	}

}