package ca.pragmaticcoding.wordle

class WordleInteractor(val model: WordleModel) {

   private val data = WordleData()

   fun handleLetter(newLetter: Char) {
      if ((model.currentColumn < 5) && !model.gameOver) {
         with(model.letters[model.currentRow][model.currentColumn]) {
            letter = newLetter
            status = LetterStatus.UNLOCKED
         }
         model.currentColumn++
      }
   }

   fun checkWord() {
      if (model.currentColumn > 4) {
         model.wordValidity[model.currentRow].set(true)
         val guess = model.letters[model.currentRow]
         if (data.isWordValid(guess.map(LetterModel::letter))) {
            performCheck(guess)
            model.currentRow++
            model.currentColumn = 0
            setAlphabet()
         } else {
            model.wordValidity[model.currentRow].set(false)
         }
      }
   }

   private fun performCheck(guess: List<LetterModel>) {
      val presentLetters = model.word.filterIndexed { index, c -> guess[index].letter != c } as MutableList<Char>
      model.wordGuessed = (presentLetters.size == 0)
      guess.forEachIndexed { column, letterModel ->
         val letter = letterModel.letter
         letterModel.status = LetterStatus.WRONG
         if (model.word[column] == letter) {
            letterModel.status = LetterStatus.CORRECT
         } else if (presentLetters.contains(letter)) {
            letterModel.status = LetterStatus.PRESENT
            presentLetters.remove(letter)
         }
      }
   }

   private fun setAlphabet() {
      model.alphabet.forEach { entry ->
         model.letters.flatten().filter { it.letter == entry.key }.maxOfOrNull { it.status }?.let { entry.value.set(it) }
      }
   }

   fun eraseLetter() {
      if (model.currentColumn > 0) {
         model.currentColumn--
         model.letters[model.currentRow][model.currentColumn].clear()
      }
   }

   init {
      model.word.setAll(data.getWord().uppercase().toList())
   }
}