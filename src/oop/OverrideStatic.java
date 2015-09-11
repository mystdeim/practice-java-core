package oop;

public class OverrideStatic {

	public static void main(String[] args) {
		new OverrideStatic();
	}
	
	public OverrideStatic() {
		new A(); new B();
		System.out.println(A.staticM());
		System.out.println(B.staticM());
	}
	
	abstract static class AbsctractClass {
		protected static AbsctractClass instance;
		public abstract String m();
		public static String staticM() {
			return instance.m();
		}
	}
	
	static class A extends AbsctractClass {
		protected static AbsctractClass instance;
		static {
			instance = new A();
		}
		public String m() {
			return "A";
		}
	}

	static class B extends AbsctractClass {
		protected static AbsctractClass instance;
		static {
			instance = new B();
		}
		public String m() {
			return "B";
		}
	}
	
}
