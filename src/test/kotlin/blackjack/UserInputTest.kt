package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.InputStreamReader
import java.io.StringReader
import java.util.Scanner

class UserInputTest {
    @Test
    fun `쉼표로 구분된 문자를 입력받는다`() {
        val userInput = UserInput.StringList("질문", "pobi,json\n")
        assertThat(userInput.answer()).isEqualTo(listOf("pobi", "json"))
    }

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
    }
}
