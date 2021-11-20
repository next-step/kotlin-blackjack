package domain.card

class PlayingCards(cards: List<PlayingCard>) : List<PlayingCard> by cards {
    constructor(cardGenerator: CardGenerator) : this((1..START_SIZE).map { cardGenerator.getCard() })

    private val cards = cards.toList()
    operator fun plus(card: PlayingCard) = PlayingCards(this.cards + listOf(card))
    fun state(): CardState = when (score()) {
        BLACKJACK_NUMBER -> CardState.FINISHED
        in runningNumbers -> CardState.RUNNING
        else -> CardState.BUST
    }

    fun score(): Int {
        val score = cards.fold(0) { acc, card -> acc + card.score() }
        return if (isBonus(score)) score + ACE_BONUS else score
    }

    private fun isBonus(score: Int): Boolean = hasAce() && score + ACE_BONUS <= BLACKJACK_NUMBER
    private fun hasAce(): Boolean = cards.minOf { it.score() } == Denomination.ACE.score

    companion object {
        const val START_SIZE = 2
        private const val ACE_BONUS = 10
        private const val BLACKJACK_NUMBER = 21
        private val runningNumbers = 1 until BLACKJACK_NUMBER
    }
}
