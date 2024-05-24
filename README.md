# Overview

This program is Hangman, but instead of a simple word, it is a famous quote. The user has to guess the quote one letter at a time, has only a set number wrong attempts before they are "hung". If the user guesses the quote correctly, they win. If the user runs out of chances, they lose. At the conclusion of each game (successful or not), the user can chose to play again, and will get a new quote to guess.

The game has three levels of difficulty: easy, medium, and hard. The difficulty level determines the number of wrong attempts the user is allowed before they lose. The harder the selected level, the longer the quote.

[Software Demo Video](https://youtu.be/F1Es2WIKaZU)

# Development Environment
The purpose of this project is to demonstrate some of the features of the Kotlin programming language. IntelliJ IDEA 20424.1 Ultimate Edition was used as the IDE for this project. The project was written in Kotlin, and build with Java 17 as its core.

IntelliJ IDEA is a powerful IDE that is designed to be used with Java, Kotlin, and other JVM languages. It has a lot of features that make it easy to write, test, and debug code. It also has a lot of plugins that can be used to extend its functionality.

Maven was used as the build tool for this project. Maven is a powerful build tool that is designed to be used with Java and other JVM languages. It has a lot of features that make it easy to build, test, and deploy code. It also has a lot of plugins that can be used to extend its functionality.

## This project used the following libraries:
[**java.net.HttpClient**](https://docs.oracle.com/en%2Fjava%2Fjavase%2F11%2Fdocs%2Fapi%2F%2F/java.net.http/java/net/http/HttpClient.html) - This library allows the program to send a request to an API and get a response.

[**java.net.http.HttpRequest**](https://docs.oracle.com/en%2Fjava%2Fjavase%2F11%2Fdocs%2Fapi%2F%2F/java.net.http/java/net/http/HttpRequest.html) - This java.net.HttpRequest library is used to create an HTTP request to the API.

[**java.net.http.HttpResponse**](https://docs.oracle.com/en%2Fjava%2Fjavase%2F11%2Fdocs%2Fapi%2F%2Fjava.net.http/java/net/http/HttpResponse.html) - This library is used to get the response from the API.

[**java.net.URI**](https://docs.oracle.com/en%2Fjava%2Fjavase%2F11%2Fdocs%2Fapi%2F%2Fjava.base/java/net/URI.html) - This library is used to create a URI object that is used to create the request to the API.

## Stretch Challenges
The following features were added to the project to make it more interesting and challenging:

[**when**](https://kotlinlang.org/docs/control-flow.html#when-expression) - This feature was used to simplify the code that determines if the user has guessed the quote correctly.

[**collection**](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/) - MutableSet was used to store the letters that the user has guessed so far, to hold/maintain the set letters available to use, and as a comparator in determining when the complete quote was entered. 

## API used in the project
[Famous Quotes](https://www.postman.com/quotable/workspace/quotable/request/2817454-9e671389-92da-45b9-a5db-a1f240445b6b) - While this project only made use of the 'Get Random Quote' endpoint, this API also supports getting quotes by Author and Tag (type). This project used the query parameter "maxLength" to request quotes of 25, 50 or 75 characters long. This is used as part of the game difficulty levels.   

# Useful Websites
* [Kotlin Programming Language](https://kotlinlang.org/)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/)
* [Maven](https://maven.apache.org/)
* [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
* [Kotlin Tutorial](https://www.w3schools.com/KOTLIN/index.php)
* [Exploring the HttpClient in Java](https://www.baeldung.com/java-9-http-client)
* [Using JSONObject](https://www.tutorialspoint.com/org_json/org_json_jsonobject.htm)
