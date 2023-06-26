package blackjack.model

sealed class BlackjackParticipant(cardDeck: CardDeck) {
    val handDeck: HandDeck = HandDeck()

    val deckScore: Int
        get() = handDeck.score

    val isScoreOverThanLimitScore: Boolean
        get() = handDeck.isScoreOverThanLimitScore

    init {
        repeat(INITIAL_DEALING_COUNT) {
            add(cardDeck.draw())
        }
    }

    fun add(card: TrumpCard) {
        handDeck + card
    }

    abstract fun draw(cardDeck: CardDeck)

    companion object {
        const val INITIAL_DEALING_COUNT: Int = 2
    }
}
