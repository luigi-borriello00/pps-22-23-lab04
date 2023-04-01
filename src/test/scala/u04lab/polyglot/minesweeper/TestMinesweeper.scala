package u04lab.polyglot.minesweeper

import org.junit.Assert.*
import org.junit.*

import u04lab.code.List.*
import u04lab.code.{Option, List}

//import cell from src
import u04lab.polyglot.minesweeper.{Grid, Cell, LogicsImpl}

class TestMinesweeper:

  val cell = Cell(P2d(0, 0))
  val grid = Grid(3, 1)
  val logics = LogicsImpl(3, 1)

  @Test def testCell(): Unit =
    assertFalse(cell.isMine)
    assertFalse(cell.hasFlag)
    assertFalse(cell.isRevealed)
    assertEquals(0, cell.getAdjacentMines)
    assertEquals(P2d(0, 0), cell.getPosition)
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
    val cell = P2d(0, 0)
    assertEquals(0, length(filter(logics.getAllCells)(_.isRevealed)))
    logics.revealCell(cell.x, cell.y)
    assertEquals(1, length(filter(logics.getAllCells)(_.isRevealed)))
    assertEquals(0, length(filter(logics.getAllCells)(_.hasFlag)))
    logics.toggleFlag(cell.x, cell.y)
    assertEquals(1, length(filter(logics.getAllCells)(_.hasFlag)))

  @Test def testLogicsGameOver() =
    assertEquals(false, logics.isGameOver)
    // when a mine is revealed, gameOver
    val mines = filter(logics.getAllCells)(_.isMine)
    val cell = mines match
      case Cons(c, _) => c
    cell.reveal()
    assertEquals(true, logics.isGameOver)

  private def clickAllNotMines(l: List[Cell]): Unit = l match
    case Cons(c, t) if !c.isMine => c.reveal(); clickAllNotMines(t)
    case Cons(c, t) => clickAllNotMines(t)
    case _ => ()

  @Test def testVictory() =
    assertEquals(false, logics.isThereVictory)
    // when all cells are revealed, victory
    val cellNotMines = filter(logics.getAllCells)(c => !c.isMine)
    clickAllNotMines(cellNotMines)
    assertEquals(true, logics.isThereVictory)


