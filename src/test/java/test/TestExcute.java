package test;

public class TestExcute {

	public static void main(String[] args) {
		String str = "xxxx.1";
		System.out.println(str.split("\\.").length);
		System.out.println(str.indexOf("."));
		str = "abx/lllll";
		System.out.println(str.split("/").length);
	}
}
