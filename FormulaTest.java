package lab02;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
public class FormulaTest {

	private String formula1, formula2;
	private String[] newFormula = new String[formula1.length()]; 
	private float q;
	
	@BeforeAll
	public void setUp()
	{
		formula1 = "(8*5-2)/(2+8)";
		
		formula2 = "(8*5+2)/(2-8))";
		
		newFormula[0] = "(";
		newFormula[1] = "8";
		newFormula[2] = "*";
		newFormula[3] = "5";
		newFormula[4] = "+";
		newFormula[5] = "2";
		newFormula[6] = ")";
		newFormula[7] = "/";
		newFormula[8] = "(";
		newFormula[9] = "2";
		newFormula[10] = "-";
		newFormula[11] = "8";
		newFormula[12] = ")";
		
		q=(float) 3.8;
	}
	
	@Test
	public void testcheckBrackets()
	{
		assertTrue(Formula.checkBrackets(formula1));
		
		assertFalse(Formula.checkBrackets(formula2));
	}
	
	@Test
	public void testnewFormulaFormat()
	{
		assertArrayEquals(Formula.newFormulaFormat(formula1), newFormula);
		
	}	
}
