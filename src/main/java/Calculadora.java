
public class Calculadora {
	
	public Calculadora() {
		System.out.println("New Calc.");
	}
	public int soma(int a, int b) {
		return a + b;
	}
	
	public static void main(String[] args) {
		Calculadora calc = new Calculadora();
	}
	
	public float dividir(int num, int den) {
		return (float) num / den;
	}

}
