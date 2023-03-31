package u04lab.polyglot.minesweeper

import u04lab.polyglot.a05b.Logics
import u04lab.code.{List, Option, Stream}

import scala.util.Random

trait Grid:
  def getSize: Int
  def getCell(position: P2d): Cell
  def getCells: List[Cell]
  def getAdjacentCells(position: P2d): List[Cell]

object Grid:
  def apply(size: Int, nMines: Int): Grid = GridImpl(size, nMines)

  private case class GridImpl(size: Int, val nMines: Int = 3) extends Grid:
    private var cells: List[Cell] = List.empty
    private var minePos: List[P2d] = List.Nil()
    val random = Random(42)

    for i <- 0 until nMines do
      val mine = P2d(random.nextInt(nMines), random.nextInt(nMines))
      minePos = List.append(minePos, List.cons(mine, List.Nil()))

    for x <- 0 until size do
      for y <- 0 until size do
        val cell = Cell(P2d(x, y))
        cells = List.append(cells, List.cons(cell, List.Nil()))
        if List.contains(minePos, P2d(x, y)) then cell.setMine()

    override def getSize: Int = size
    override def getCell(position: P2d): Cell = Option.orElse(List.find(cells)(c => c.getPosition == position), Cell(P2d(0,0)))
    override def getCells: List[Cell] = cells
    override def getAdjacentCells(position: P2d): List[Cell] =
      List.filter(cells)(c => c.getPosition.x >= position.x-1 && c.getPosition.x <= position.x+1 &&
        c.getPosition.y >= position.y-1 && c.getPosition.y <= position.y+1)
    override def toString: String = s"Grid($size, $cells)"




