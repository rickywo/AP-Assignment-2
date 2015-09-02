package model;

public final class Log {
	static final String DEBUG = "Debug",
			WARNING = "Warning",
			ERROR = "Error";
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
		System.out.println(s);
	}
}
