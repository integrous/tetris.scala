package block

import java.awt.Color

import scala.util.Random
import java.awt.image.BufferedImage

object Block extends Enumeration {
  type Block = Value
  val T, S, Z, O, I, L, J, EMPTY = Value
  val BlockSize = 31
   def nextBlock: Block = Block.apply(Random.nextInt(Block.values.size - 1))
   
   def getBlockColor(block: Block): Color = block match {
    case T     => Color.red
    case S     => Color.yellow
    case Z     => Color.green
    case O     => Color.blue
    case I     => Color.magenta
    case L     => Color.orange
    case J     => Color.lightGray
    case EMPTY => Color.black
  }
  
  def getBlockImage(block: Block): BufferedImage = {
    val img = new BufferedImage(BlockSize, BlockSize, BufferedImage.TYPE_INT_ARGB)
    val graphics = img.createGraphics()
    graphics.setPaint(getBlockColor(block))
    graphics.fillRect(0, 0, img.getWidth, img.getHeight)
    img
  }
  
  def getPositions(block: Block): Array[Array[Tuple2[Int, Int]]] = block match {
    case T => Array(Array((0, 0), (1, 0), (-1, 0), (0, -1)),
                    Array((0, 0), (0, 1), (0, -1), (-1, 0)),
                    Array((0, 0), (-1, 0), (1, 0), (0, 1)),
                    Array((0, 0), (0, -1), (0, 1), (1, 0)))
    case Z => Array(Array((0, 0), (1, 0), (0, -1), (-1, -1)),
                    Array((0, 0), (0, 1), (1, 0), (1, -1)),
                    Array((0, 0), (1, 0), (0, -1), (-1, -1)),
                    Array((0, 0), (0, 1), (1, 0), (1, -1)))
    case S => Array(Array((0, 0), (-1, 0), (0, -1), (1, -1)),
                    Array((0, 0), (0, 1), (-1, 0), (-1, -1)),
                    Array((0, 0), (-1, 0), (0, -1), (1, -1)),
                    Array((0, 0), (0, 1), (-1, 0), (-1, -1)))
    case O => Array(Array((0, 0), (1, 0), (0, -1), (1, -1)),
                    Array((0, 0), (1, 0), (0, -1), (1, -1)),
                    Array((0, 0), (1, 0), (0, -1), (1, -1)),
                    Array((0, 0), (1, 0), (0, -1), (1, -1)))
    case I => Array(Array((0, 0), (-1, 0), (1, 0), (2, 0)),
                    Array((0, 0), (0, -1), (0, 1), (0, 2)),
                    Array((0, 0), (-1, 0), (1, 0), (2, 0)),
                    Array((0, 0), (0, -1), (0, 1), (0, 2)))
    case L => Array(Array((0, 0), (1, 0), (-1, 0), (-1, -1)),
                    Array((0, 0), (0, -1), (0, 1), (-1, 1)),
                    Array((0, 0), (-1, 0), (1, 0), (1, 1)),
                    Array((0, 0), (0, 1), (0, -1), (1, -1)))
    case J => Array(Array((0, 0), (-1, 0), (1, 0), (1, -1)),
                    Array((0, 0), (0, 1), (0, -1), (-1, -1)),
                    Array((0, 0), (1, 0), (-1, 0), (-1, 1)),
                    Array((0, 0), (0, -1), (0, 1), (1, 1)))
    case EMPTY => Array()
  }
}