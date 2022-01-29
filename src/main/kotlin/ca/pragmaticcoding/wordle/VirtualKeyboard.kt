package ca.pragmaticcoding.wordle

import javafx.beans.property.ObjectProperty
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import org.kordamp.ikonli.javafx.FontIcon
import java.util.function.Consumer

class VirtualKeyboard(private val keystrokeConsumer: Consumer<Char>,
                      private val enterHandler: Runnable,
                      private val backspaceHandler: Runnable,
                      private val alphabet: Map<Char, ObjectProperty<LetterStatus>>) : VBox(8.0) {

   init {
      children.addAll(createRow(row1Keys), createRow(row2Keys, 20.0), createRow(row3Keys, 0.0, true, true))
      alignment = Pos.CENTER
      padding = Insets(10.0, 40.0, 10.0, 40.0)
   }

   private fun createRow(letters: List<Char>, leftPadding: Double = 0.0, includeBackspace: Boolean = false, includeEnter: Boolean = false) = HBox(6.0).apply {
      if (includeEnter) {
         children += createEnterKey()
      }
      children += letters.map { letterButtonSetup(it) }
      if (includeBackspace) {
         children += createBackspaceKey()
      }
      padding = Insets(0.0, 0.0, 0.0, leftPadding)
      isFillWidth = false
   }

   private fun letterButtonSetup(letter: Char) = createButton(letter.toString()).apply {
      onMouseClicked = EventHandler { keystrokeConsumer.accept(letter) }
      alphabet[letter]?.let { LetterStatus.addPseudoClass(this, it) }
   }

   private fun createButton(letter: String) = Button(letter).apply {
      setMinSize(44.0, 58.0)
      styleClass += "key-button"
   }

   private fun createBackspaceKey() = createButton("").apply {
      graphic = FontIcon("typ-backspace-outline")
      minWidth = 64.0
      onAction = EventHandler { backspaceHandler.run() }
   }

   private fun createEnterKey() = createButton(enter).apply {
      onAction = EventHandler { enterHandler.run() }
   }

   companion object {
      private val row1Keys = "QWERTYUIOP".toList()
      private val row2Keys = "ASDFGHJKL".toList()
      private val row3Keys = "ZXCVBNM".toList()
      private const val enter = "Enter"
   }
}
