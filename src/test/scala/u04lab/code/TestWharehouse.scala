package u04lab.code
import org.junit.*
import org.junit.Assert.*
import u04lab.code.Warehouse.*
import u04lab.code.List.*
import u04lab.code.Option.*
import u04lab.code.Option

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
    val item1 = Item(0, "item1", "tag1", "tag2")
    val item2 = Item(1, "item2", "tag1", "tag3")
    warehouse.store(item1)
    warehouse.store(item2)
    assertEquals(Cons(Item(0, "item1", "tag1", "tag2"), Nil()), warehouse.searchItems("tag2"))
    assertEquals(Cons(Item(1, "item2", "tag1", "tag3"), Nil()), warehouse.searchItems("tag3"))

  @Test def testRetrieve() =
    val item1 = Item(0, "item1", "tag1", "tag2")
    warehouse.store(item1)
    assertEquals(Some(Item(0, "item1", "tag1", "tag2")), warehouse.retrieve(0))
    assertEquals(None(), warehouse.retrieve(2))

  @Test def testRemove() =
    val item1 = Item(0, "item1", "tag1", "tag2")
    warehouse.store(item1)
    warehouse.remove(item1)
    assertEquals(None(), warehouse.retrieve(0))