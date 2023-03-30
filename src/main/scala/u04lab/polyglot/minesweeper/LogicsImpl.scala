package u04lab.polyglot.minesweeper

import u04lab.polyglot.a05b.Logics
import u04lab.code.{List, Option, Stream}


case class P2d(x: Int, y: Int)

trait Cell:
  def getPosition: P2d
  def isMine: Boolean
  def hasFlag: Boolean
  def isRevealed: Boolean
  def setMine(): Unit
  def toggleFlag(): Unit

  def reveal(): Unit
  def getAdjacentMines: Int
  def setAdjacentMines(adjacentMines: Int): Unit

object Cell:
  def apply(position: P2d): Cell = CellImpl(position)

  private case class CellImpl(position: P2d) extends Cell:
    private var mine: Boolean = false
    private var flag: Boolean = false
    private var revealed: Boolean = false
    private var adjacentMines: Int = 0

    override def getPosition: P2d = position
    override def isMine: Boolean = mine
    override def hasFlag: Boolean = flag
    override def isRevealed: Boolean = revealed
    override def setMine(): Unit = mine = true
    override def toggleFlag(): Unit = flag = !flag
    override def reveal(): Unit = revealed = true
    override def getAdjacentMines: Int = adjacentMines
    override def setAdjacentMines(adjacentMines: Int): Unit = this.adjacentMines = adjacentMines

    override def toString: String = s"Cell($position, $mine, $flag, $revealed, $adjacentMines)"


trait Grid:
  def getSize: Int
  def getCell(position: P2d): Option[Cell]
  def getCells: List[Cell]
  def getMineCells: List[Cell]
  def getAdjacentCells(position: P2d): List[Cell]
  def getRevealedCells: List[Cell]

object Grid:
  def apply(size: Int): Grid = GridImpl(size)

  private case class GridImpl(size: Int) extends Grid:
    private var cells: List[Cell] = List.empty
    private var mineCells: List[Cell] = List.empty
    private var revealedCells: List[Cell] = List.empty

    for x <- 0 until size do
      for y <- 0 until size do
        cells = List.append(cells, List.cons(Cell(P2d(x, y)), List.empty))

    override def getSize: Int = size

    override def getCell(position: P2d): Option[Cell] = List.find(cells)(c => c.getPosition == position)
    override def getCells: List[Cell] = cells
    override def getMineCells: List[Cell] = mineCells
    override def getAdjacentCells(position: P2d): List[Cell] =
      List.filter(cells)(c => c.getPosition.x >= position.x-1 && c.getPosition.x <= position.x+1 &&
        c.getPosition.y >= position.y-1 && c.getPosition.y <= position.y+1)
    override def getRevealedCells: List[Cell] = revealedCells

    def setCells(cells: List[Cell]): Unit = this.cells = cells
    def setMineCells(mineCells: List[Cell]): Unit = this.mineCells = mineCells
    def setRevealedCells(revealedCells: List[Cell]): Unit = this.revealedCells = revealedCells

    override def toString: String = s"Grid($size, $cells, $mineCells, $revealedCells)"




