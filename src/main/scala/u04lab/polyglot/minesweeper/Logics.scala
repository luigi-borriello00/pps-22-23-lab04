package u04lab.polyglot.minesweeper

import u04lab.code
import u04lab.code.{Option, List}
import u04lab.code.List.*
import u04lab.polyglot.minesweeper.{Grid, Cell}

trait Logics:
  def revealCell(cellCoordinates: P2d): Unit

  def toggleFlag(cellCoordinates: P2d): Unit

  def isAMine(cellCoordinates: P2d): Boolean

  def getAdjacentMinesCounter(cellCoordinates: P2d): Int

  def isThereVictory: Boolean

  def isGameOver: Boolean

  def getAllCells: List[Cell]

  def getRevealedCells: List[Cell]

  def getFlaggedCells: List[Cell]

  def getMines: List[Cell]

class LogicsImpl(size: Int, nMines: Int) extends Logics:
  private val grid = Grid(size, nMines)

  override def revealCell(cellCoordinates: P2d): Unit = grid.getCell(cellCoordinates).reveal()

  override def toggleFlag(cellCoordinates: P2d): Unit = grid.getCell(cellCoordinates).toggleFlag()

  override def isAMine(cellCoordinates: P2d): Boolean = grid.getCell(cellCoordinates).isMine

  override def getAdjacentMinesCounter(cellCoordinates: P2d): Int =
    length(filter(grid.getAdjacentCells(cellCoordinates))(_.isMine))

  override def isThereVictory: Boolean =
    if length(getRevealedCells) == length(getAllCells) - nMines
    then true else false

  override def isGameOver: Boolean = if length(filter(getMines)(_.isRevealed)) > 0 then
    true else false

  override def getAllCells: List[Cell] = grid.getCells

  override def getRevealedCells: List[Cell] = filter(getAllCells)(_.isRevealed)

  override def getFlaggedCells: List[Cell] = filter(getAllCells)(_.hasFlag)

  override def getMines: List[Cell] = filter(getAllCells)(_.isMine)
