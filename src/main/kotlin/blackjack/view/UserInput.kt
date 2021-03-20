package blackjack.view

import java.io.InputStreamReader
import java.io.StringReader
import java.util.Scanner

interface UserInput<T> {
    fun answer(): T

    class StringList(
        private val question: String,
        readable: Readable = InputStreamReader(System.`in`)
    ) : UserInput<List<String>> {
        private val scanner: Scanner = Scanner(readable)

        constructor(question: String, answer: String) : this(question, StringReader(answer))

        override fun answer(): List<String> {
            println(question)
            return scanner.nextLine()
                .split(",")
        }
    }

    class Char(
        private val question: String,
        readable: Readable = InputStreamReader(System.`in`)
    ) : UserInput<kotlin.Char> {
        private val scanner: Scanner = Scanner(readable)

        constructor(question: String, answer: String) : this(question, StringReader(answer))

        override fun answer(): kotlin.Char {
            println(question)
            return scanner.nextLine().first()
        }
    }

    class Int(
        private val question: String,
        readable: Readable = InputStreamReader(System.`in`)
    ) : UserInput<kotlin.Int> {
        private val scanner: Scanner = Scanner(readable)

        constructor(question: String, answer: String) : this(question, StringReader(answer))

        override fun answer(): kotlin.Int {
            println(question)
            return scanner.nextInt()
        }
    }
}
