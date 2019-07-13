package Engine;

public class Debug {

	private Debug () {

	}

	public static void Log (Object value) {
		if (value instanceof Vector2) {
			System.out.println (String.format ("(%s, %s)", ((Vector2)value).GetX (), ((Vector2)value).GetY ()));
		} else {
			System.out.println (value);
		}
	}

	public static void LogFormat (String format, Object... args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof Vector2) {
				args[i] = String.format ("(%s, %s)", ((Vector2)args[i]).GetX (), ((Vector2)args[i]).GetY ());
			}
		}
		System.out.println (String.format (format, args));
	}

}