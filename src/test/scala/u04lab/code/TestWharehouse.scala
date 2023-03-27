package u04lab.code
import org.junit.*
import org.junit.Assert.*
import u04lab.code.Warehouse.*

class TestWharehouse:

  val warehouse = Warehouse()
  @Test def testCanBeCreated() =
    assertNotNull(warehouse)

  @Test def testContains() =
    assertFalse(warehouse.contains(0))

    /*
    assertEquals(0, warehouse.size())
    val book = Book("The Lord of the Rings", "J.R.R. Tolkien", 1954)
    warehouse.store(book)
    assertEquals(1, warehouse.size()) */

