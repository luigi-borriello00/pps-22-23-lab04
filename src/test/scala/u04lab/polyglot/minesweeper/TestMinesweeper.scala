package u04lab.polyglot.minesweeper
import org.junit.Assert.*
import org.junit.*
import u04lab.code.List.length
import u04lab.code.{Option, List}

//import cell from src
import u04lab.polyglot.minesweeper.*

class TestMinesweeper:

  val cell = Cell(P2d(0,0))
  val grid = Grid(3, 1)
  val logics = Logics(grid, 1)

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

  @Test def testLogicsBasicBehaviour() =
    val cell = P2d(0,0)
    assertEquals(0, length(logics.getRevealedCells))
    logics.revealCell(cell)
    assertEquals(1, length(logics.getRevealedCells))
    assertEquals(0, length(logics.getFlaggedCells))
    logics.toggleFlag(cell)
    assertEquals(1, length(logics.getFlaggedCells))

  @Test def testLogicsGameOver() =
    assertEquals(false, logics.isGameOver)
    // when a mine is revealed, gameOver
    val cell = logics.getMines match
      case List.Cons(c, _) => c
    cell.reveal()
    assertEquals(true, logics.isGameOver)

  private def clickAllNotMines(l: List[Cell]): Unit = l match
    case List.Cons(c, t) if !c.isMine => c.reveal(); clickAllNotMines(t)
    case List.Cons(c, t) => clickAllNotMines(t)
    case _ => ()
  @Test def testVictory() =
    assertEquals(false, logics.isThereVictory)
    // when all cells are revealed, victory
    val cellNotMines = List.filter(logics.getAllCells)(c => !c.isMine)
    clickAllNotMines(cellNotMines)
    assertEquals(true, logics.isThereVictory)


