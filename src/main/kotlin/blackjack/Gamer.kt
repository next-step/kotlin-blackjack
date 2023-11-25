package blackjack

abstract class Gamer(val name: String) {
    val playerCards: PlayerCards = PlayerCards()

    val isBusted get() = playerCards.isBusted()
    val isBlackjack get() = playerCards.isBlackjack()

    lateinit var initialPublicCards: PlayerCards

    fun getInitialCards(cards: List<PlayingCard>) {
        for (card in cards) {
            playerCards.addCard(card)
        }
        initialPublicCards = getInitialPublicCards(playerCards)
    }

    fun hit(card: PlayingCard) {
        playerCards.addCard(card)
    }

    fun setInitialDeck(cardDeck: CardDeck, callback: (Gamer) -> Unit) {
        getInitialCards(cardDeck.drawInitialCards())
        callback(this)
    }

    abstract val canGetCard: Boolean
    abstract fun getInitialPublicCards(playerCards: PlayerCards): PlayerCards
}
