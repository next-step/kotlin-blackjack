package blackjack

abstract class Gamer(val name: String) {
    val playerCards: PlayerCards = PlayerCards()

    val isBusted get() = playerCards.isBusted()
    val isBlackjack get() = playerCards.isBlackjack()

    fun getInitialCards(cards: List<PlayingCard>) {
        for (card in cards) {
            playerCards.addCard(card)
        }
    }

    fun hit(card: PlayingCard) {
        playerCards.addCard(card)
    }

    fun setInitialDeck(cardDeck: CardDeck, showCards: (t: Gamer) -> Unit) {
        getInitialCards(cardDeck.drawInitialCards())
        showCards(this)
    }

    abstract val canGetCard: Boolean
}
