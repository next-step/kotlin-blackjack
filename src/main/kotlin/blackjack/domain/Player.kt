package blackjack.domain

open class Player(val name: String, private val playerCards: PlayerCards = PlayerCards()) {
    val cardCount: Int get() = playerCards.size
    val score: Int get() = playerCards.score

    fun draw(cardDeck: CardDeck) {
        playerCards.addCard(cardDeck.pop())
    }

    fun currentCards() = playerCards.cards

    open fun canDraw(): Boolean = playerCards.score < CARD_BUST_THRESHOLD

    fun isBust(): Boolean = playerCards.score > CARD_BUST_THRESHOLD

    companion object {
        private const val CARD_BUST_THRESHOLD = 21
    }
}
