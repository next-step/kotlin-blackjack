package blackjack

enum class Answer(private val symbol: String) {
    HIT("y"),
    STAY("n");

    fun isHit(): Boolean {
        return this == HIT
    }

    fun isStay(): Boolean {
        return this == STAY
    }

    companion object {
        fun of(answer: String): Answer {
            return values().find { it.symbol == answer } ?: throw IllegalArgumentException("카드를 더 받을지 여부는 y 또는 n으로 답해야합니다.")
        }
    }
}
