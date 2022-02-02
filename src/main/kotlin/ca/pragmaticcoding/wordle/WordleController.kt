package ca.pragmaticcoding.wordle

import javafx.event.EventHandler
import javafx.scene.input.KeyCode
import javafx.scene.layout.Region

class WordleController {

   private val model = WordleModel()
   private val interactor = WordleInteractor(model)
   val view: Region = ViewBuilder(model, VirtualKeyboard(interactor::handleLetter, interactor::checkWord, interactor::eraseLetter, model.alphabet)).build()

   init {
      view.onKeyTyped = EventHandler { event ->
         when (event.code) {
            KeyCode.ENTER -> interactor.checkWord()
            KeyCode.BACK_SPACE -> interactor.eraseLetter()
            else -> when (event.character.first().code) {
               13 -> interactor.checkWord()
               8 -> interactor.eraseLetter()
               else -> event.character.uppercase().first().also {
                  if (it in 'A'..'Z') {
                     interactor.handleLetter(it)
                  }
               }
            }
         }
      }
   }
}

