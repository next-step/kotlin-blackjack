package blackjack.utils

object Validation {

    fun isAnswer(answer: String): Boolean =
        Regex("y|n").matches(answer)
}
