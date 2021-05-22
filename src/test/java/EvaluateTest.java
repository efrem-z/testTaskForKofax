import org.junit.jupiter.api.Test;
import ru.ez.testTaskForKofax.Evaluate;

import static org.junit.jupiter.api.Assertions.*;

class EvaluateTest {

    @Test
    void eval() {
        String s1 = "5 * 2 + (11 - 1) / 2";
        String s2 = "1 * 7 + (8 - 5) * 2";
        String s3 = "1235 / 15 * 3";
        String s4 = "(12 + 17) * 23 + 6";
        String s5 = "23 / (524 * 2) / (10 * 367 * 1000)";
        assertEquals(15, Evaluate.eval(s1));
        assertEquals(13,Evaluate.eval(s2));
        assertEquals(247,Evaluate.eval(s3));
        assertEquals(673,Evaluate.eval(s4));
        assertEquals(5.979990432015308E-9,Evaluate.eval(s5));
    }
}