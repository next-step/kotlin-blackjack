package blackjack.ui.input

import java.lang.IllegalArgumentException

object StringAnswerParser {
    fun answerParse(answer: String): Boolean {
        return when (answer) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("잘못 입력한 대답 입니다.")
        }
    }
}
