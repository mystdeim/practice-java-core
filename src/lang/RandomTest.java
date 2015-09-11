package lang;

public class RandomTest {

	public static void main(String[] args) {
		System.out.println(Math.random());
		for (int i=0; i<10; i++) {
			System.out.println(customP(0.5));
		}
	}
	
	public static boolean customP(double p) {
		return Math.random() <= p;
	}

}
