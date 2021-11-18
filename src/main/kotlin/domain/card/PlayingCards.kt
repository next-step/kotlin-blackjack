package domain.card

data class PlayingCards(private val cards: List<PlayingCard>) : List<PlayingCard> by cards {
    constructor(cardGenerator: CardGenerator) : this((1..START_SIZE).map { cardGenerator.getCard() })

    operator fun plus(card: PlayingCard) = PlayingCards(this.cards + listOf(card))

    fun state() = when (score()) {
        PlayingCard.BLACKJACK_NUMBER -> CardState.FINISHED
        in runningNumbers -> CardState.RUNNING
        else -> CardState.BUST
    }

    fun score() =
        cards.fold(0) { accumulatedScore, card -> accumulatedScore + card.score(accumulatedScore) }

    companion object {
        const val START_SIZE = 2
        private val runningNumbers = 1 until PlayingCard.BLACKJACK_NUMBER
    }
}
