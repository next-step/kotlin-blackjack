package blackjack.model

sealed class BlackjackParticipant(cardDeck: CardDeck) {
    val handDeck: HandDeck = HandDeck()

    val deckScore: Int
        get() = handDeck.score

    val deckCount: Int
        get() = handDeck.count

    fun isWinByScoreGap(participant: BlackjackParticipant): Boolean {
        return (HandDeck.LIMIT_SCORE - deckScore) < (HandDeck.LIMIT_SCORE - participant.deckScore)
    }

    val isSameLimitScore: Boolean
        get() = (HandDeck.LIMIT_SCORE == deckScore)

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
