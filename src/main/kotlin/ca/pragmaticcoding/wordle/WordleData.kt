package ca.pragmaticcoding.wordle

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class WordleData {

   private val solutions: List<String> = (WordleData::class.java.getResource("/data/solutions.txt")?.readText() ?: "").split("\n")
   private val dictionary: List<String> = (WordleData::class.java.getResource("/data/dictionary.txt")?.readText() ?: "").split("\n")

   fun getWord(): String {
      return solutions[LocalDate.of(2021, 6, 19).until(LocalDate.now(), ChronoUnit.DAYS).toInt()]
   }

   fun isWordValid(characters: List<Char>): Boolean {
      return (solutions + dictionary).contains(characters.joinToString("").lowercase())
   }
}

