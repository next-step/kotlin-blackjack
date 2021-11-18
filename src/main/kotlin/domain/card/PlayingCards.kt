package domain.card

data class PlayingCards(private val cards: List<PlayingCard>) : List<PlayingCard> by cards {
    constructor(cardGenerator: CardGenerator) : this((1..START_SIZE).map { cardGenerator.getCard() })

    operator fun plus(card: PlayingCard) = PlayingCards(this.cards + listOf(card))

    fun state() = when (score()) {
        BLACKJACK_NUMBER -> CardState.FINISHED
        in runningNumbers -> CardState.RUNNING
        else -> CardState.BUST
    }

    fun score(): Int {
        val score = cards.fold(0) { acc, card -> acc + card.score() }
        return if (isBonus(score)) score + ACE_BONUS else score
    }

    private fun isBonus(score: Int) = hasAce() && score + ACE_BONUS <= BLACKJACK_NUMBER
    private fun hasAce() = cards.minOf { it.score() } == Denomination.ACE.score

    companion object {
        const val START_SIZE = 2
        private const val ACE_BONUS = 10
        private const val BLACKJACK_NUMBER = 21
        private val runningNumbers = 1 until BLACKJACK_NUMBER
    }
}
