package ca.pragmaticcoding.wordle

import javafx.beans.binding.Bindings
import javafx.beans.binding.BooleanBinding
import javafx.beans.property.*
import javafx.beans.value.ObservableBooleanValue
import javafx.collections.FXCollections
import javafx.collections.ObservableList

class WordleModel {
   var currentColumn = 0
   val letters = List(6) { List(5) { column -> LetterModel(column) } }
   val word: ObservableList<Char> = FXCollections.observableArrayList()
   val alphabet: Map<Char, ObjectProperty<LetterStatus>> = CharRange('A', 'Z').associateBy({ it }, { SimpleObjectProperty(LetterStatus.UNLOCKED) })
   val wordValidity = List(6) { _ -> SimpleBooleanProperty(true) }
   val wordsValid: BooleanBinding = run {
      var results = SimpleBooleanProperty(true).and(wordValidity[0])
      wordValidity.forEach { results = results.and(it) }
      results
   }
   private val _currentRow: IntegerProperty = SimpleIntegerProperty(0)
   var currentRow: Int
      get() = _currentRow.get()
      set(newValue) = _currentRow.set(newValue)

   private val _darkMode: BooleanProperty = SimpleBooleanProperty(false)
   var darkMode: Boolean
      get() = _darkMode.get()
      set(value) = _darkMode.set(value)

   fun darkModeProperty() = _darkMode

   private val _wordGuessed: BooleanProperty = SimpleBooleanProperty(false)

   var wordGuessed: Boolean
      get() = _wordGuessed.get()
      set(value) = _wordGuessed.set(value)

   private val _gameOver: ObservableBooleanValue = Bindings.createBooleanBinding({ (currentRow > 5) || wordGuessed }, _currentRow, _wordGuessed)
   val gameOver: Boolean
      get() = _gameOver.get()

}