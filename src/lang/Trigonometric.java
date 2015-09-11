package lang;

public class Trigonometric {

	public static void main(String[] args) {
//		System.out.println(Math.tan(Math.PI / 4));
//		System.out.println(Math.atan(1) * (180.0 / Math.PI));
		
		atanTest();
	}
	
	// Знак atan
	public static void atanTest() {
		// 1 четверть [-pi/2, -0]
		System.out.println(Math.atan(1 / -1.0));
		// 2 четверть [+0, pi/2]
		System.out.println(Math.atan(-1 / -1.0));
		// 3 четверть [-pi/2, -0]
		System.out.println(Math.atan(-1 / 1.0));
		// 4 четверть
		System.out.println(Math.atan(10 / 10.0));
	}

}
