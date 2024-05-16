package org.example

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import org.json.JSONObject


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

    // println("Getting random quote")
    // val quote = getRandomQuote(25)
    // println("Random quote result: $quote")

    val client = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.quotable.io/random?maxLength=25"))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())
    println(response.body())
    val jsonObject = JSONObject(response.body())
    println(jsonObject.getString("content"))
}
