package u04lab.code
import org.junit.Test;
import org.junit.Assert.*;


class TestSecondDegreePolynomial:
  val pol = SecondDegreePolynomial(1, 2, 1)
  @Test def testSecondDegreePolynomial() =
    val p = SecondDegreePolynomial(1, 2, 1)
    assertEquals(SecondDegreePolynomial(2, 4, 2), pol.+(p))
    assertEquals(SecondDegreePolynomial(0, 0, 0), pol - p)

