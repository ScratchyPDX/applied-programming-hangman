package org.example

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import org.json.JSONObject

fun main() {
    val guessedLetters = mutableSetOf<Char>() // Set to store guessed letters
    var attempts = 6 // Number of attempts allowed

    println("Welcome to Hangman!")
    println("Guess the famous quote by entering one letter at a time.")
    println("Player may only have $attempts incorrect attempts.")
    println("You have $attempts attempts left.\n")

    val quoteString = getQuote(25).lowercase().trim('.')
    println("$quoteString")

    while (true) {
        getQuoteDisplay(quoteString, guessedLetters)
        println("\nQuote: ${getQuoteDisplay(quoteString, guessedLetters)}")
        displayAvailableLetters(guessedLetters)

        print("Enter a letter: ")
        val input = readLine()?.trim()?.lowercase()
        val letter = input!![0]
        guessedLetters.add(letter)
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
            it == ' ' -> ' '
            guessedLetters.contains(it) -> it
            else -> '_'
        }
    }.joinToString(" ")
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
