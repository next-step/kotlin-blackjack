package blackjack.domain

class Player(
    val name: String,
    val hand: Hand = Hand(),
) {
    val status
        get() = calculateStatus(AceDifferScoreCalculateStrategy)

    init {
        require(name.isNotBlank()) { "플레이어 이름은 공백일 수 없습니다" }
    }

    fun addCard(card: Card) {
        hand.add(card)
    }

    private fun calculateStatus(aceDifferScoreCalculateStrategy: AceDifferScoreCalculateStrategy): Status {
        val score = aceDifferScoreCalculateStrategy.calculate(hand.cards).score

        return when {
            score == 21 && hand.cards.size == 2 -> Status.BLACKJACK
            score in 0..21 -> Status.HIT
            else -> Status.BUST
        }
    }

    companion object {
        const val MIN_COUNT = 1
        const val MAX_COUNT = 26
    }
}
