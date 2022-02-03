package ca.pragmaticcoding.wordle

import javafx.beans.binding.Bindings
import javafx.beans.binding.BooleanBinding
import javafx.beans.property.*
import javafx.beans.value.ObservableBooleanValue
import javafx.collections.FXCollections
import javafx.collections.ObservableList

data class WordleModel(var currentColumn: Int = 0,
                       val letters: List<List<LetterModel>> = List(6) { List(5) { column -> LetterModel(column) } },
                       val word: ObservableList<Char> = FXCollections.observableArrayList(),
                       val alphabet: Map<Char, ObjectProperty<LetterStatus>> = CharRange('A', 'Z').associateBy({ it },
                                                                                                               { SimpleObjectProperty(LetterStatus.UNLOCKED) }),
                       val wordValidity: List<SimpleBooleanProperty> = List(6) { _ -> SimpleBooleanProperty(true) },
                       val wordsValid: BooleanBinding = run {
                          var results = SimpleBooleanProperty(true).and(wordValidity[0])
                          wordValidity.forEach { results = results.and(it) }
                          results
                       },
                       val currentRow: IntegerProperty = SimpleIntegerProperty(0),
                       val darkMode: BooleanProperty = SimpleBooleanProperty(false),
                       val wordGuessed: BooleanProperty = SimpleBooleanProperty(false),
                       val gameOver: ObservableBooleanValue = Bindings.createBooleanBinding({ (currentRow.value > 5) || wordGuessed.value },
                                                                                            currentRow,
                                                                                            wordGuessed))