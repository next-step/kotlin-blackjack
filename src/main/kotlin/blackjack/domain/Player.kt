package blackjack.domain

class Player(
    val name: String,
    val hand: Hand = Hand(),
) {
    val status = calculateStatus(AceDifferScoreCalculateStrategy)

    init {
        require(name.isNotBlank()) { "플레이어 이름은 공백일 수 없습니다" }
    }

    fun addCard(card: Card) {
        hand.add(card)
    }

    private fun calculateStatus(aceDifferScoreCalculateStrategy: AceDifferScoreCalculateStrategy): Status {
        return when (aceDifferScoreCalculateStrategy.calculate(hand.cards).score) {
            in 0..20 -> Status.NORMAL
            21 -> Status.BLACKJACK
            else -> Status.BUST
        }
    }

    companion object {
        const val MIN_COUNT = 1
        const val MAX_COUNT = 26
    }
}
