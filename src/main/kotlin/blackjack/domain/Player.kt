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
            score == BUST_SCORE && hand.cards.size == 2 -> Status.BLACKJACK
            score == BUST_SCORE -> Status.STAY
            score in 0 until BUST_SCORE -> Status.HIT
            else -> Status.BUST
        }
    }

    companion object {
        const val MIN_COUNT = 1
        const val MAX_COUNT = 26
        const val BUST_SCORE = 21
    }
}

class Hand(
    private val _cards: MutableList<Card> = mutableListOf()
) {
    val cards: List<Card>
        get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }
}

