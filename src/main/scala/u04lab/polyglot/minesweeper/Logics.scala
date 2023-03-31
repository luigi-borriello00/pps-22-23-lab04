package u04lab.polyglot.minesweeper
import u04lab.code.{Option, List}
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

object Logics:
  def apply(size: Int, nMines: Int): Logics = LogicImpl(size, nMines)

  private case class LogicImpl(size: Int, nMines: Int) extends Logics:
    private val grid = Grid(size, nMines)
    override def revealCell(cellCoordinates: P2d): Unit = grid.getCell(cellCoordinates).reveal()

    override def toggleFlag(cellCoordinates: P2d): Unit = grid.getCell(cellCoordinates).toggleFlag()

    override def isAMine(cellCoordinates: P2d): Boolean = grid.getCell(cellCoordinates).isMine

    override def getAdjacentMinesCounter(cellCoordinates: P2d): Int =
      List.length(List.filter(grid.getAdjacentCells(cellCoordinates))(_.isMine))

    override def isThereVictory: Boolean =
      if List.length(getRevealedCells) == List.length(getAllCells) - nMines
      then true else false

    override def isGameOver: Boolean = if List.length(List.filter(getMines)(_.isRevealed)) > 0 then
      true else false

    override def getAllCells: List[Cell] = grid.getCells

    override def getRevealedCells: List[Cell] = List.filter(getAllCells)(_.isRevealed)

    override def getFlaggedCells: List[Cell] = List.filter(getAllCells)(_.hasFlag)

    override def getMines: List[Cell] = List.filter(getAllCells)(_.isMine)
