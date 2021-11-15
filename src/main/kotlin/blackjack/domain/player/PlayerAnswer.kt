package blackjack.domain.player

enum class PlayerAnswer(val hit: Boolean, private val answer: String) {
    YES(true, "Y"),
    NO(false, "N"),
    ;

    fun isMatch(answer: String): Boolean {
        return this.answer == answer.uppercase()
    }

    companion object {
        fun from(answer: String): PlayerAnswer {
            return values().find { it.isMatch(answer) } ?: NO
        }
    }
}

fun interface AnswerProvider {
    fun getAnswer(gamer: Gamer): PlayerAnswer
}
