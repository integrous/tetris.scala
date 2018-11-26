package ui

import java.awt.Color
import java.awt.image.RescaleOp

import scala.swing._
import block.Block

class TetrisPanel extends Panel {
  val controller = new BoardController(this)
  controller.listenTo(this.keys)
  preferredSize = new Dimension(Block.BlockSize * (Board.Width + 5),
                                Block.BlockSize * Board.Height)
  
  override def paintComponent(g: Graphics2D) {
    super.paintComponent(g)
    
    g.setPaint(Color.black)
    g.fillRect(0, 0, Board.Width * Block.BlockSize, Board.Height * Block.BlockSize)
    
     val darkenOperation = new RescaleOp(0.5f, 0.5f, null);
    val ghostTetromino = controller.droppedTetromino
    
    ghostTetromino.getBlockPositions.foreach {
      case (x, y) =>
        val img = Block.getBlockImage(ghostTetromino.block)
        
        g.drawImage(img, null, x * Block.BlockSize, y * Block.BlockSize)
    }
    
    controller.board.withTetromino(controller.currentTetromino).board.zipWithIndex.foreach {
      case (row, y) => row.zipWithIndex.foreach {
        case (block, x) => {
          if (block != Block.EMPTY) {
            val img = Block.getBlockImage(block)
            g.drawImage(img, null, x * Block.BlockSize, y * Block.BlockSize)
          }
        }
      }
    }
    
    g.drawString("Next tetromino:", (Board.Width + 1) * Block.BlockSize, Block.BlockSize / 2)
    val nextTetromino = controller.nextTetromino
    nextTetromino.getBlockPositions.foreach {
      case (x, y) => {
         val img = Block.getBlockImage(nextTetromino.block)
         g.drawImage(img, null, (Board.Width / 2 + 2 + x) * Block.BlockSize, (y + 1) * Block.BlockSize)
      }
    }
    
     g.drawString("Score: %d".format(controller.score), (Board.Width + 1) * Block.BlockSize, Block.BlockSize * 6)
  }
}