package ca.pragmaticcoding.wordle

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {
   override fun start(stage: Stage) {
      with(stage) {
         title = "WordleFX"
         scene = Scene(WordleController().view)
         show()
      }
   }

   fun main() {
      launch(Main::class.java)
   }
}