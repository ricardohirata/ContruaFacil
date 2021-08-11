package unitTest;

import devcalc.Calc;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class TestaCalc {
    @Test(priority = 1)
    public void testarSomarDoisNumeros() {
        // 1 - PREPARA - CONFIGURAR - GIVEN - ARRANGE
        int num1 = 5;
        int num2 = 7;
        int resultadoEsperado = 12;

        // 2 - EXECUTAR - WHEN - ACT
        int resultadoAtual = Calc.somarDoisNumeros(num1, num2);

        // 3 - VALIDAR - THEN - ASSERT
        System.out.printf("O resultado esperado e %d. \n O resultado atual foi %d. \n", resultadoEsperado, resultadoAtual);
        assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test(priority = 0)
    public void testarSubtairDoisNumeros(){
        int num1 = 6, num2 = 2, resultadoEsperado = 4;

        int resultadoAtual = Calc.subtrairDoisNumeros(num1, num2);

        System.out.printf("O resultado esperado e %d. \n O resultado atual foi %d. \n", resultadoEsperado, resultadoAtual);
        assertEquals(resultadoAtual, resultadoEsperado);

    }
}
