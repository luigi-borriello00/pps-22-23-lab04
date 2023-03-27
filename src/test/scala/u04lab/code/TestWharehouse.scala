package u04lab.code
import org.junit.*
import org.junit.Assert.*
import u04lab.code.Warehouse.*
import u04lab.code.List.*

class TestWharehouse:

  val warehouse = Warehouse()
  @Test def testCanBeCreated() =
    assertNotNull(warehouse)

  @Test def testContains() =
    assertFalse(warehouse.contains(0))

  @Test def testStore() =
    val item = Item(0, "item")
    warehouse.store(item)
    assertTrue(warehouse.contains(0))

  @Test def testSearchItems() =
    val item1 = Item(0, "item1", Cons("tag1", Cons("tag2", Nil())))
    val item2 = Item(1, "item2", Cons("tag1", Cons("tag3", Nil())))
    warehouse.store(item1)
    warehouse.store(item2)
    assertEquals(Cons(Item(0, "item1", Cons("tag1", Cons("tag2", Nil()))), Nil()), warehouse.searchItems("tag2"))
    assertEquals(Cons(Item(1, "item2", Cons("tag1", Cons("tag3", Nil()))), Nil()), warehouse.searchItems("tag3"))


