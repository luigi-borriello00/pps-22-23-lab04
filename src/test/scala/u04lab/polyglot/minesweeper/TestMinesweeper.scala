package u04lab.polyglot.minesweeper
import org.junit.Assert.*
import org.junit.*
import u04lab.code.List.length
import u04lab.code.{Option, List}

//import cell from src
import u04lab.polyglot.minesweeper.*

class TestMinesweeper:

  val cell = Cell(P2d(0,0))
  val grid = Grid(3)
  @Test def testCell(): Unit =
    assertFalse(cell.isMine)
    assertFalse(cell.hasFlag)
    assertFalse(cell.isRevealed)
    assertEquals(0, cell.getAdjacentMines)
    assertEquals(P2d(0,0), cell.getPosition)
    cell.setMine()
    cell.toggleFlag()
    cell.reveal()
    assertTrue(cell.isMine)
    assertTrue(cell.hasFlag)
    assertTrue(cell.isRevealed)

  @Test def testGrid() =
    assertEquals(3, grid.getSize)
    assertEquals(3, grid.getSize)
    assertEquals(9, length(grid.getCells))
    assertEquals(3, length(grid.getMineCells))
    assertEquals(0, length(grid.getRevealedCells))
    
  

