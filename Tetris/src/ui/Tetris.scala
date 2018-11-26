package ui

import scala.swing._

object Tetris extends SimpleSwingApplication {
  val boardPanel = new TetrisPanel
  
  def top = new MainFrame {
    title = "Tetris"
    
    contents = boardPanel
    
    val helpText = """Left: Move left
                     |Right: Move right
                     |Up: Rotate
                     |Down: Move down
                     |Space: Drop all the way down
                     |P: Toggle pause
                     |N: New game""".stripMargin
                     
    def showHelp: Unit = {
      boardPanel.controller.pauseGame
      Dialog.showMessage(boardPanel, helpText, "Tetris help", Dialog.Message.Plain)
      boardPanel.controller.resumeGame
    }
    
    menuBar_=(new MenuBar {
      contents += new Menu("Game") {
        contents += new MenuItem(Action("New game") { boardPanel.controller.newGame })
        contents += new MenuItem(Action("Pause") { boardPanel.controller.togglePause })
        contents += new MenuItem(Action("Help") { showHelp })
        contents += new Separator
        contents += new MenuItem(Action("Exit") { sys.exit(0) })
      }
    })
    
    pack
    
    boardPanel.requestFocus
  }
}