package ca.pragmaticcoding.wordle

class WordleInteractor(val model: WordleModel) {

   private val data = WordleData()

   fun handleLetter(newLetter: Char) {
      if ((model.currentColumn < 5) && !model.gameOver.value) {
         with(model.letters[model.currentRow.value][model.currentColumn]) {
            letter.value = newLetter
            status.value = LetterStatus.UNLOCKED
         }
         model.currentColumn++
      }
   }

   fun checkWord() {
      if (model.currentColumn > 4) {
         model.wordValidity[model.currentRow.value].set(true)
         val guess = model.letters[model.currentRow.value]
         if (data.isWordValid(guess.map { it.letter.value })) {
            performCheck(guess)
            model.currentRow.value++
            model.currentColumn = 0
            setAlphabet()
         } else {
            model.wordValidity[model.currentRow.value].set(false)
         }
      }
   }

   private fun performCheck(guess: List<LetterModel>) {
      val presentLetters = model.word.filterIndexed { index, c -> guess[index].letter.value != c } as MutableList<Char>
      model.wordGuessed.value = (presentLetters.size == 0)
      guess.forEachIndexed { column, letterModel ->
         val letter = letterModel.letter.value
         letterModel.status.value = LetterStatus.WRONG
         if (model.word[column] == letter) {
            letterModel.status.value = LetterStatus.CORRECT
         } else if (presentLetters.contains(letter)) {
            letterModel.status.value = LetterStatus.PRESENT
            presentLetters.remove(letter)
         }
      }
   }

   private fun setAlphabet() {
      model.alphabet.forEach { entry ->
         model.letters.flatten().filter { it.letter.value == entry.key }.maxOfOrNull { it.status.value }?.let { entry.value.set(it) }
      }
   }

   fun eraseLetter() {
      if (model.currentColumn > 0) {
         model.currentColumn--
         model.letters[model.currentRow.value][model.currentColumn].clear()
      }
   }

   init {
      model.word.setAll(data.getWord().uppercase().toList())
   }
}