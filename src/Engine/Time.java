package Engine;

public class Time {

	private static float Time, DeltaTime;

	private Time () {

	}

	public static float GetTime () {
		return Time;
	}

	public static float GetDeltaTime () {
		return DeltaTime;
	}

	static void SetTime (float time) {
		Time = time;
	}

	static void SetDeltaTime (float deltaTime) {
		DeltaTime = deltaTime;
	}

}