package Engine;

public class Debug {

	private Debug () {

	}

	public static void Log (Object value) {
		System.out.println (value);
	}

	public static void LogFormat (String format, Object... args) {
		System.out.println (String.format (format, args));
	}

}