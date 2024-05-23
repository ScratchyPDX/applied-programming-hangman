package org.example

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import org.json.JSONObject

fun main() {

    println("\nWelcome to Hangman!")
    println("Guess the famous quote by entering one letter at a time\n")

    while(true) {
        println("Choose difficulty level - 1, 2 or 3")
        println("Level 1 - quote is 25 chars or less - 6 attempts allowed")
        println("Level 2 - quote is 50 chars or less - 7 attempts allowed")
        println("Level 3 - quote is 100 chars or less - 8 attempts allowed")
        print("Enter level: ")
        val level = readLine()?.trim()?.toIntOrNull()
        if (level == null || level !in 1..3) {
            println("Please enter a valid number between 1 and 3.")
            continue
        }
        var maxLength = 0
        when (level) {
            1 -> maxLength = 25
            2 -> maxLength = 50
            3 -> maxLength = 100
        }
        var attempts = 5 + level

        println("\nPlayer may only have $attempts incorrect attempts.")
        println("You have $attempts attempts left.\n")

        playGame(maxLength, attempts)

        print("Would you like to play again? (Y/N): ")
        val playAgain = readLine()?.trim()?.lowercase()
        if (playAgain != "y") {
            println("Thanks for playing!")
            break
        }
        println()
        println()
    }
}

// main game loop code
fun playGame(maxLength: Int, attempts: Int) {
    var localAttempts = attempts
    val guessedLetters = mutableSetOf<Char>() // Set to store guessed letters
    val guessedLettersWithSpecialChars = mutableSetOf<Char>() // Set to store guessed letters and special characters

    val quoteString = getQuote(maxLength).lowercase()
    println("$quoteString")
    val specialChars = addSpecialCharsToGuessedArray(quoteString)
    guessedLettersWithSpecialChars += stringToMutableCharSet(specialChars)

    while (true) {
        getQuoteDisplay(quoteString, guessedLetters)
        println("\nQuote: ${getQuoteDisplay(quoteString, guessedLetters)}")
        displayAvailableLetters(guessedLetters)

        print("Enter a letter: ")
        val input = readLine()?.trim()?.lowercase()

        if (input == null || input.length != 1 || !input[0].isLetter()) {
            println("Please enter a valid single letter.")
            continue
        }

        val letter = input[0]
        if (guessedLetters.contains(letter)) {
            println("You've already guessed '$letter'.")
            continue
        }

        // need on array to hold letters user enters, and one that also includes the special characters
        guessedLetters.add(letter)
        guessedLettersWithSpecialChars.add(letter)

        if (!quoteString.contains(letter)) {
            localAttempts--
            println("Incorrect guess! You have $localAttempts attempts left")
            if (localAttempts == 0) {
                println("\nGame over! The quote was '$quoteString'\n")
                break
            }
        } else if (quoteString.all { guessedLettersWithSpecialChars.contains(it) }) {
            println("\nCongratulations! You guessed the quote: '$quoteString'\n")
            break
        }
    }
}

/*
    GetQuote - Uses an API call to get a random quote from quotable.io
    - maxLength: Integer: maximum number of characters the quote may contain.
      This value will be used to increase/decrease game difficulty
*/
fun getQuote(maxLength: Int): String {
    val client = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.quotable.io/random?maxLength=$maxLength"))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    val jsonObject = JSONObject(response.body())
    return jsonObject.getString("content").toString()
}

/*
    getQuoteDisplay - creates a list of letter locations, placing guessed letters where
    they belong, and inserting underscores where letters are still missing. This also
    ensure the spaces in the quote are maintained for game readability

    - quoteString: string holding the current quote
    - guessedLetters: a list of guessed letter entered by user throughout the game
*/
fun getQuoteDisplay(quoteString: String, guessedLetters: Set<Char>): String {
    return quoteString.map {
        when {
            // maintain spaces and special characters
            it == ' ' -> ' '
            it == ',' -> ','
            it == '\'' -> '\''
            it == ':' -> ':'
            it == '.' -> '.'
            guessedLetters.contains(it) -> it
            else -> '_'
        }
    }.joinToString(" ")
}

fun addSpecialCharsToGuessedArray(quoteString: String) : String {
    return quoteString.map {
        when {
            it == ' ' -> ' '
            it == ',' -> ','
            it == '\'' -> '\''
            it == ':' -> ':'
            it == '.' -> '.'
            else -> {}
        }
    }.joinToString(" ")
}

fun stringToMutableCharSet(input: String): MutableSet<Char> {
    val charSet = mutableSetOf<Char>()
    for (char in input) {
        charSet.add(char)
    }
    return charSet
}

/*
    displayAvailableLetters - implements a list of letters (A thru Z), removes the
    letters that have been guessed, and then displays letters still available to
    be used
    - guessedLetters: a list of guessed letter entered by user throughout the game
*/
fun displayAvailableLetters(guessedLetters: MutableSet<Char>) {
    val mutableAlphaList = ("abcdefghijklmnopqrstuvwxyz").toMutableList()
    for (letter in guessedLetters) {
        val index = mutableAlphaList.binarySearch(letter)
        mutableAlphaList.removeAt(index)
    }
    println("Available letters': ${mutableAlphaList.joinToString(" ")}")
}

