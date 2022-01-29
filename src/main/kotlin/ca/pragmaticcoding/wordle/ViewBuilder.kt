package ca.pragmaticcoding.wordle

import javafx.beans.binding.Bindings
import javafx.css.PseudoClass
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.Separator
import javafx.scene.layout.HBox
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.util.Builder
import org.kordamp.ikonli.javafx.FontIcon

class ViewBuilder(private val model: WordleModel, private val keyboard: VirtualKeyboard) : Builder<Region> {

   override fun build(): Region = VBox(40.0, createTitle(), createTilePane(), badWordMessage(), keyboard).apply {
      ViewBuilder::class.java.getResource("/css/wordle.css")?.toString()?.let { stylesheets += it }
      styleClass.add("main-screen")
      alignment = Pos.TOP_CENTER
      val darkModePseudoClass = PseudoClass.getPseudoClass("dark-mode")
      model.darkModeProperty().addListener { _ ->
         pseudoClassStateChanged(darkModePseudoClass, model.darkMode)
      }
   }

   private fun letterBox(letterModel: LetterModel) = StackPane().apply {
      children += Label().apply {
         textProperty().bind(Bindings.createStringBinding({ letterModel.letter.toString() }, letterModel.letterProperty()))
         styleClass += "tile-letter"
      }
      styleClass += "tile-box"
      letterModel.statusProperty().addListener { _ ->
         if (letterModel.status > LetterStatus.UNLOCKED) {
            flipTile(this, letterModel.column, letterModel.status)
         } else {
            if (letterModel.status == LetterStatus.UNLOCKED) {
               flashTile(this)
            }
            LetterStatus.updatePseudoClass(this, letterModel.status)
         }
      }
   }

   private fun createTilePane() = VBox(7.0).apply {
      children += IntRange(0, 5).map { createRow(it) }
   }

   private fun badWordMessage() = Label("Not in word list").apply {
      styleClass += "bad-word"
      opacity = 0.0
      model.invalidRowProperty().addListener { _ ->
         if (model.invalidRow < 10) {
            showToast(this)
         }
      }
   }

   private fun createRow(row: Int) = HBox(5.0).apply {
      alignment = Pos.CENTER
      children += IntRange(0, 4).map {
         letterBox(model.letters[row][it])
      }
      model.invalidRowProperty().addListener { _ ->
         if (model.invalidRow == row) {
            wiggleRow(this)
         }
      }
   }

   private fun createTitle() = StackPane().apply {
      children += Label("WordleFX").apply {
         styleClass += "title"
      }
      children += FontIcon("typ-cog-outline").apply {
         iconSize = 26
         iconColor = Color.GRAY
         StackPane.setAlignment(this, Pos.CENTER_RIGHT)
         setOnMouseClicked { _ -> model.darkMode = !model.darkMode }
      }
      children += Separator().apply {
         StackPane.setAlignment(this, Pos.BOTTOM_CENTER)
      }
   }
}