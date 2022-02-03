package ca.pragmaticcoding.wordle

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty

data class LetterModel(val column: Int,
                       val letter: ObjectProperty<Char> = SimpleObjectProperty(' '),
                       val status: ObjectProperty<LetterStatus> = SimpleObjectProperty(LetterStatus.EMPTY)) {
   fun clear() {
      letter.value = ' '
      status.value = LetterStatus.EMPTY
   }
}