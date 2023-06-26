package blackjack.model

class BlackjackPlayer(
    deck: CardDeck,
    bettingMoneyProvider: (String) -> Int,
    val name: PlayerName,
    private val blackjackPlayerConsumer: (BlackjackPlayer) -> Unit,
    private val moreWantedCardPredicate: (String) -> Boolean,
) : BlackjackParticipant(deck) {

    val bettingMoney: Money = Money(bettingMoneyProvider(name.toString()))

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

    private val isLessScoreThanLimit: Boolean get() = deckScore < LIMIT_SCORE

    companion object {
        private const val LIMIT_SCORE = 21
    }
}
