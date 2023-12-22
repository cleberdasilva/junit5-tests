import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	
	@Test
	public void testSomar() {
		Calculadora calc = new Calculadora();
		
		Assertions.assertTrue(calc.soma(2, 3) == 5);
		Assertions.assertEquals(5, calc.soma(2, 3));
	}
	
	@Test
	public void assertivas() {
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
		Calculadora calc = new Calculadora();
	
		float resultado = calc.dividir(6, 2);
		Assertions.assertEquals(3, resultado);
	}
	
	@Test 
	public void deveRetornarNumeroNegativoNaDivisao() {
		Calculadora calc = new Calculadora();
	
		float resultado = calc.dividir(6, -2);
		Assertions.assertEquals(-3, resultado);
	}
	
	@Test 
	public void deveRetornarNumeroDecimalNaDivisao() {
		Calculadora calc = new Calculadora();
	
		float resultado = calc.dividir(10, 3);
		Assertions.assertEquals(3.3333332538604736, resultado);

		// the 3 parameter is the delta
		Assertions.assertEquals(3.33, resultado, 0.01); 
	}
}
