package ca.pragmaticcoding.wordle

import javafx.beans.property.SimpleObjectProperty

class LetterModel(val column: Int) {

   private val _letter = SimpleObjectProperty(' ')
   var letter: Char
      get() = _letter.get()
      set(theLetter) = _letter.set(theLetter)

   fun letterProperty() = _letter

   private val _status = SimpleObjectProperty(LetterStatus.EMPTY)
   var status: LetterStatus
      get() = _status.get()
      set(newStatus) = _status.set(newStatus)

   fun statusProperty() = _status

   fun clear() {
      letter = ' '
      status = LetterStatus.EMPTY
   }
}