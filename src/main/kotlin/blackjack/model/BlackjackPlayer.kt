package blackjack.model

class BlackjackPlayer(
    name: PlayerName,
    deck: CardDeck,
    private val blackjackPlayerConsumer: (BlackjackPlayer) -> Unit,
    private val moreWantedCardPredicate: (String) -> Boolean,
) : BlackjackParticipant(name, deck) {

    override fun draw(cardDeck: CardDeck) {
        var isReceivedMoreCard = false
        while (isLessScoreThanLimit && moreWantedCardPredicate(name.toString())) {
            add(cardDeck.draw())
            blackjackPlayerConsumer(this)
            isReceivedMoreCard = true
        }
        if (!isReceivedMoreCard) {
            blackjackPlayerConsumer(this)
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
