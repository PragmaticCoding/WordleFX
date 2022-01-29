package ca.pragmaticcoding.wordle

import javafx.beans.property.ObjectProperty
import javafx.css.PseudoClass
import javafx.scene.Node

enum class LetterStatus {
   EMPTY, UNLOCKED, WRONG, PRESENT, CORRECT;

   companion object {
      private val emptyPseudoClass: PseudoClass = PseudoClass.getPseudoClass("empty")
      private val unlockedPseudoClass: PseudoClass = PseudoClass.getPseudoClass("unlocked")
      private val wrongPseudoClass: PseudoClass = PseudoClass.getPseudoClass("wrong")
      private val presentPseudoClass: PseudoClass = PseudoClass.getPseudoClass("present")
      private val correctPseudoClass: PseudoClass = PseudoClass.getPseudoClass("correct")

      fun updatePseudoClass(node: Node, letterStatus: LetterStatus) {
         with(node) {
            pseudoClassStateChanged(emptyPseudoClass, false)
            pseudoClassStateChanged(unlockedPseudoClass, false)
            pseudoClassStateChanged(wrongPseudoClass, false)
            pseudoClassStateChanged(presentPseudoClass, false)
            pseudoClassStateChanged(correctPseudoClass, false)
            when (letterStatus) {
               EMPTY -> pseudoClassStateChanged(emptyPseudoClass, true)
               UNLOCKED -> pseudoClassStateChanged(unlockedPseudoClass, true)
               WRONG -> pseudoClassStateChanged(wrongPseudoClass, true)
               PRESENT -> pseudoClassStateChanged(presentPseudoClass, true)
               CORRECT -> pseudoClassStateChanged(correctPseudoClass, true)
            }
         }
      }

      fun addPseudoClass(node: Node, property: ObjectProperty<LetterStatus>) {
         property.addListener { _ -> updatePseudoClass(node, property.get()) }
      }
   }
}