

import java.util.Scanner

// fun main() {
//   println("hello world!")
// }

fun main() {
    val mutableAlphaList = ("abcdefghijklmnopqrstuvwxyz").toMutableList()
    val words = listOf("apple", "banana", "cherry", "date", "fig", "grape") // List of words to choose from
    val randomWord = words.random() // Choose a random word from the list
    val guessedLetters = mutableSetOf<Char>() // Set to store guessed letters
    var attempts = 6 // Number of attempts allowed

    println("Welcome to Hangman!")
    println("Guess the word by entering one letter at a time.")
    println("You have $attempts attempts.")

    println("Available letters: ${mutableAlphaList}")
    val index = mutableAlphaList.binarySearch('z')
    println("index: $index")
    mutableAlphaList.removeAt(index)
    println("Available letters minus 'z': ${mutableAlphaList}")
}