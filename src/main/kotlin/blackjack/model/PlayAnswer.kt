package blackjack.model

enum class PlayAnswer(val value: String) {
    Y("y"),
    N("n");

    companion object {
        fun getKey(value: String): PlayAnswer = PlayAnswer.values().find { it.value == value } ?: PlayAnswer.N
    }
}
