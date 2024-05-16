package org.example

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import org.json.JSONObject


fun main() {
    val mutableAlphaList = ("abcdefghijklmnopqrstuvwxyz").toMutableList()
    val guessedLetters = mutableSetOf<Char>() // Set to store guessed letters
    var attempts = 6 // Number of attempts allowed

    println("Welcome to Hangman!")
    println("Guess the famous quote by entering one letter at a time.")
    println("You have $attempts attempts.\n")

    println("Available letters: ${mutableAlphaList.joinToString()}")
    val index = mutableAlphaList.binarySearch('z')
    println("index: $index")
    mutableAlphaList.removeAt(index)
    println("Available letters minus 'z': ${mutableAlphaList}")

    val quoteString = getQuote(25)
    println(quoteString)
    guessedLetters.add('a')
    println("\nQuote: ${getQuoteDisplay(quoteString, guessedLetters)}")
}

fun getQuote(maxLength: Int): String {
    val client = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.quotable.io/random?maxLength=$maxLength"))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    val jsonObject = JSONObject(response.body())
    return jsonObject.getString("content").toString()
}

fun getQuoteDisplay(quoteString: String, guessedLetters: Set<Char>): String {
    return quoteString.map {
        when {
            it == ' ' -> ' '
            guessedLetters.contains(it) -> it
            else -> '_'
        }
    }.joinToString(" ")
}
