package ca.pragmaticcoding.wordle

import javafx.event.EventHandler
import javafx.scene.layout.Region

class WordleController {

   private val model = WordleModel()
   private val interactor = WordleInteractor(model)
   val view: Region = ViewBuilder(model, VirtualKeyboard(interactor::handleLetter, interactor::checkWord, interactor::eraseLetter, model.alphabet)).build()

   init {
      view.onKeyTyped = EventHandler { event ->
         when (event.character.first().code) {
            13 -> interactor.checkWord()
            8 -> interactor.eraseLetter()
            else -> {
               if (event.character.uppercase().first() in 'A'..'Z') {
                  interactor.handleLetter(event.character.uppercase().first())
               }
            }
         }
      }
   }
}

