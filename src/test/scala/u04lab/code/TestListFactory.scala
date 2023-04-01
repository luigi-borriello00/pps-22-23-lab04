package u04lab.code

import org.junit.*
import org.junit.Assert.*
import u04lab.code.List.*

class TestListFactory:
  @Test def testListFactory() =
    val list = List(1, 2, 3)
    assertEquals(Cons(1, Cons(2, Cons(3, Nil()))), list)

