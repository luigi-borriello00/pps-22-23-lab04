package u04lab.polyglot.minesweeper

case class P2d(x: Int, y: Int)
trait Cell:
  def getPosition: P2d
  def isMine: Boolean
  def hasFlag: Boolean
  def isRevealed: Boolean
  def setMine(): Unit
  def toggleFlag(): Unit
  def reveal(): Unit

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
    override def toString: String = s"Cell($position, $mine, $flag, $revealed, $adjacentMines)"
