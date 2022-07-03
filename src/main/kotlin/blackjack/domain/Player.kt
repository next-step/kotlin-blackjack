package blackjack.domain

open class Player(
    val name: String,
    val hand: Hand = Hand(),
) {
    val score: Score
        get() = AceDifferScoreCalculateStrategy.calculate(hand.cards)

    open val status
        get() = calculateStatus(score)

    init {
        require(name.isNotBlank()) { "플레이어 이름은 공백일 수 없습니다" }
    }

    fun addCard(card: Card) {
        hand.add(card)
    }

    fun getMatchResult(dealer: Dealer): MatchResult {
        val compare = this.score.compareTo(dealer.score)

        return when {
            (dealer.status == Status.BUST && this.status != Status.BUST) ||
                (this.status == Status.BLACKJACK && dealer.status != Status.BLACKJACK) ||
                (this.status != Status.BUST && compare > 0) -> MatchResult.WIN
            (dealer.status == Status.BLACKJACK && this.status == Status.BLACKJACK) ||
                (this.status != Status.BUST && compare == 0) -> MatchResult.DRAW
            else -> MatchResult.LOSE
        }
    }

    private fun calculateStatus(score: Score): Status {
        return when {
            score.score == Score.BUST_SCORE && hand.cards.size == 2 -> Status.BLACKJACK
            score.score == Score.BUST_SCORE -> Status.STAY
            score.score in 0 until Score.BUST_SCORE -> Status.HIT
            else -> Status.BUST
        }
    }

    companion object {
        const val MIN_COUNT = 1
        const val MAX_COUNT = 25
    }
}

class Dealer(
    hand: Hand = Hand(),
) : Player(
    NAME, hand
) {
    override val status
        get() = calculateDealerStatus(score)

    private fun calculateDealerStatus(score: Score): Status {
        return when {
            score.score == Score.BUST_SCORE && hand.isInitial() -> Status.BLACKJACK
            score.score in Score.DEALER_DRAW_SCORE..Score.BUST_SCORE -> Status.STAY
            score.score in 0 until Score.DEALER_DRAW_SCORE && hand.isInitial() -> Status.HIT
            else -> Status.BUST
        }
    }

    companion object {
        const val NAME = "딜러"
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

    fun isInitial() = _cards.size == INITIAL_HAND_COUNT

    companion object {
        const val INITIAL_HAND_COUNT = 2
    }
}
