package model;

public final class Log {
	static final String DEBUG = "Debug",
			WARNING = "Warning",
			ERROR = "Error";
	static final boolean DEBUG_MODE = false;
	public static void d(String message) {
		print(String.format("%s %s", bucketUp(DEBUG), message));
	}
	public static void w(String message) {
		print(String.format("%s %s", bucketUp(WARNING), message));
	}
	public static void e(String message) {
		print(String.format("%s %s", bucketUp(ERROR), message));
	}
	private static String bucketUp(String s) {
		return String.format("[%s]", s);
	}
	private static void print(String s) {
		if(DEBUG_MODE) System.out.println(s);
	}
}
