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
            score == Score.BUST_SCORE && hand.cards.size == 2 -> Status.BLACKJACK
            score == Score.BUST_SCORE -> Status.STAY
            score in 0 until Score.BUST_SCORE -> Status.HIT
            else -> Status.BUST
        }
    }

    companion object {
        const val MIN_COUNT = 1
        const val MAX_COUNT = 26
    }
}

class Dealer(
    val hand: Hand = Hand(),
) {
    val status
        get() = calculateDealerStatus(AceDifferScoreCalculateStrategy)

    fun addCard(card: Card) {
        hand.add(card)
    }

    private fun calculateDealerStatus(aceDifferScoreCalculateStrategy: AceDifferScoreCalculateStrategy): Status {
        val score = aceDifferScoreCalculateStrategy.calculate(hand.cards).score

        return when {
            score == Score.BUST_SCORE && hand.isInitial() -> Status.BLACKJACK
            score in Score.DEALER_DRAW_SCORE..Score.BUST_SCORE || !hand.isInitial() -> Status.STAY
            score in 0 until Score.DEALER_DRAW_SCORE && hand.isInitial() -> Status.HIT
            else -> Status.BUST
        }
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

    fun isInitial() = _cards.size != INITIAL_HAND_COUNT

    companion object {
        const val INITIAL_HAND_COUNT = 2
    }
}

