package blackjack.domain

data class Player(val name: String, private val playerCards: PlayerCards = PlayerCards()) {
    val cardCount: Int get() = playerCards.size
    fun draw(cardDeck: CardDeck) {
        playerCards.addCard(cardDeck.pop())
    }

    fun canDraw(): Boolean = playerCards.score < CARD_DRAW_THRESHOLD

    fun currentCards() = playerCards.cards

    companion object {
        private const val CARD_DRAW_THRESHOLD = 21
    }
}
