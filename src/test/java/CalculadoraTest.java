import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

	private Calculadora calc = new Calculadora();
//	private static Calculadora calc;
	
	
	private static int contador = 0; 
	//para cada teste o contador é zerado pelo JUnit5, todas as variaveis serão novas
	//se a variavel for static não recebera um reset a cada execução
	
	//to every execution I´ll a new calculator initialized
	@BeforeEach
	public void setup() {
		//calc = new Calculadora();
		System.out.println("^^^");
	}
	
	
	@AfterEach  
	public void tearDown() {
		System.out.println("vvv");
	}
	
	//need to be a static method
	@BeforeAll
	public static void setupAll() {
		System.out.println("--- Before All ---");
//		calc = new Calculadora();
	}
	
	@AfterAll  
	public static void tearDownAll() {
		System.out.println("--- After All ---");
	}
	
	@Test
	public void testSomar() {
		System.out.println(++contador);
		Assertions.assertTrue(calc.soma(2, 3) == 5);
		Assertions.assertEquals(5, calc.soma(2, 3));
	}
	
	@Test
	public void assertivas() {
		System.out.println(++contador);
		Assertions.assertEquals("Casa", "Casa");
		Assertions.assertNotEquals("Casa", "casa");
		Assertions.assertTrue("casa".equalsIgnoreCase("CASA"));
		Assertions.assertTrue("Casa".endsWith("sa"));
		Assertions.assertTrue("casa".startsWith("ca"));
		
		List<String> s1 = new ArrayList<>();
		List<String> s2 = new ArrayList<>();
		List<String> s3 = null;
		
		Assertions.assertEquals(s1, s2);
		Assertions.assertSame(s1, s1); //same object same reference
		Assertions.assertNotEquals(s1, s3);
		Assertions.assertNull(s3);
		Assertions.assertNotNull(s1);
//		Assertions.fail("Falhou pelo motivo A");
	}
	
	@Test 
	public void deveRetornarNumeroInteiroNaDivisao() {
		System.out.println(++contador);
		float resultado = calc.dividir(6, 2);
		Assertions.assertEquals(3, resultado);
	}
	
	@Test 
	public void deveRetornarNumeroNegativoNaDivisao() {
	
		float resultado = calc.dividir(6, -2);
		Assertions.assertEquals(-3, resultado);
	}
	
	@Test 
	public void deveRetornarNumeroDecimalNaDivisao() {
	
		float resultado = calc.dividir(10, 3);
		Assertions.assertEquals(3.3333332538604736, resultado);

		// the 3 parameter is the delta
		Assertions.assertEquals(3.33, resultado, 0.01); 
	}
	
	@Test 
	public void deveRetornarZeroComNumeradorZeroNaDivisao() {
	
		float resultado = calc.dividir(0, 2);
		Assertions.assertEquals(0, resultado);
	}
	
	//RuntimeException throw a failure RuntimeError throw an error
	@Test 
	public void deveLancarExcecaoQuandoDividirPorZero_Jnit4() {
		try {
			float resultado = 10 / 0;
			Assertions.fail("Deveria ter sido lançado uma exceção na divisão");
		}catch (ArithmeticException e) {
			Assertions.assertEquals("/ by zero", e.getMessage());
		}
	}
	
	//A class and an exec
	@Test 
	public void deveLancarExcecaoQuandoDividirPorZero_Jnit5() {
		ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () ->{
			float resultado = 10 / 0;
		});
		Assertions.assertEquals("/ by zero", exception.getMessage());
	}
}