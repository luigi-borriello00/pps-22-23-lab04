package u04lab.polyglot.minesweeper

import u04lab.code
import u04lab.code.{Option, List}
import u04lab.code.List.*
import u04lab.polyglot.minesweeper.{Grid, Cell}

trait Logics:
  def revealCell(cellRow: Int, cellColumn: Int): Unit

  def toggleFlag(cellRow: Int, cellColumn: Int): Unit

  def isAMine(cellRow: Int, cellColumn: Int): Boolean

  def getAdjacentMinesCounter(cellRow: Int, cellColumn: Int): Int

  def isThereVictory: Boolean

  def isGameOver: Boolean

  def getAllCells: List[Cell]

  def isARevealedCell(cellRow: Int, cellColumn: Int): Boolean

  def isAFlaggedCell(cellRow: Int, cellColumn: Int): Boolean



class LogicsImpl(size: Int, nMines: Int) extends Logics:
  private val grid = Grid(size, nMines)

  override def revealCell(cellRow: Int, cellColumn: Int): Unit = grid.getCell(P2d(cellRow, cellColumn)).reveal()

  override def toggleFlag(cellRow: Int, cellColumn: Int): Unit = grid.getCell(P2d(cellRow, cellColumn)).toggleFlag()

  override def isAMine(cellRow: Int, cellColumn: Int): Boolean = grid.getCell(P2d(cellRow, cellColumn)).isMine

  override def getAdjacentMinesCounter(cellRow: Int, cellColumn: Int): Int =
    length(filter(grid.getAdjacentCells(P2d(cellRow, cellColumn)))(_.isMine))

  override def isThereVictory: Boolean =
    if length(filter(getAllCells)(_.isRevealed)) == length(getAllCells) - nMines
    then true else false

  override def isGameOver: Boolean = if length(filter(getMines)(_.isRevealed)) > 0 then
    true else false

  override def getAllCells: List[Cell] = grid.getCells

  override def isARevealedCell(cellRow: Int, cellColumn: Int): Boolean =
    contains(filter(getAllCells)(_.isRevealed), grid.getCell(P2d(cellRow, cellColumn)))

  override def isAFlaggedCell(cellRow: Int, cellColumn: Int): Boolean =
    contains(filter(getAllCells)(_.hasFlag), grid.getCell(P2d(cellRow, cellColumn)))

  private def getMines: List[Cell] = filter(getAllCells)(_.isMine)
