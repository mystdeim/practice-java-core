package math;

/**
 * Pairing function (Нумерация пар)
 * https://en.wikipedia.org/wiki/Pairing_function#Cantor_pairing_function
 * 
 */
public class Pairing {
	
	public static void main(String... argv) {
		
		System.out.println(pairing(10, 20));
		System.out.println(pairing(20, 10));
		// Тут будет ошибка, но исключение не выкинет
		System.out.println(pairing(-Integer.MAX_VALUE, -Integer.MAX_VALUE));
		System.out.println(pairing(-Integer.MAX_VALUE, Integer.MAX_VALUE));	
		System.out.println(pairing(Integer.MAX_VALUE, -Integer.MAX_VALUE));	
		System.out.println(pairing(Integer.MAX_VALUE, Integer.MAX_VALUE));		

		System.out.println(pairing(10.5, 20.5));
		System.out.println(pairing(20.5, 10.5));
		System.out.println(pairing(-Double.MAX_VALUE, -Double.MAX_VALUE));
		System.out.println(pairing(-Double.MAX_VALUE, Double.MAX_VALUE));	
		System.out.println(pairing(Double.MAX_VALUE, -Double.MAX_VALUE));	
		System.out.println(pairing(Double.MAX_VALUE, Double.MAX_VALUE));	

		System.out.println("Test: (i,2) ");		
		for (int i=2; i<10; i++) {
			System.out.println(pairing(i, 1));
		}		
		System.out.println("Test: (2,j) ");		
		for (int j=2; j<10; j++) {
			System.out.println(pairing(1, j));
		}
		
	}
	
	// Cantor pairing function
	public static int pairing(int a, int b) {
		return (int) ((a+b)*(a+b+1) / 2.0 + b);
	}
	
	// Cantor pairing function
	public static double pairing(double a, double b) {
		return (a+b)*(a+b+1) / 2.0 + b;
	}

}
