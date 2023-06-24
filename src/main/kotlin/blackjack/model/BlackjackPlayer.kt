package blackjack.model

data class BlackjackPlayer(
    val name: PlayerName,
    val blackjackPlayerConsumer: BlackjackPlayerConsumer,
    val moreWantedCardPredicate: MoreWantedCardPredicate,
) {
    val deck: HandDeck = HandDeck()

    val deckScore: Int
        get() = deck.score

    fun add(card: TrumpCard) {
        deck + card
    }

    fun draw(cardDeck: CardDeck) {
        var isReceivedMoreCard = false
        while (isLessScoreThanLimit && moreWantedCardPredicate.isWantedMorePredicate(name.toString())) {
            add(cardDeck.draw())
            blackjackPlayerConsumer.consumePlayer(this)
            isReceivedMoreCard = true
        }
        if (!isReceivedMoreCard) {
            blackjackPlayerConsumer.consumePlayer(this)
        }
    }

    private val isLessScoreThanLimit: Boolean
        get() {
            return deckScore < LIMIT_SCORE
        }

    companion object {
        private const val LIMIT_SCORE = 21
    }
}
