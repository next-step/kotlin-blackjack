package blackjack.domain

open class Player(val name: String, private val playerCards: PlayerCards = PlayerCards()) {
    val cardCount: Int get() = playerCards.size
    val score: Score get() = playerCards.score

    fun draw(cardDeck: CardDeck) {
        playerCards.addCard(cardDeck.pop())
    }

    fun currentCards() = playerCards.cards

    open fun canDraw(): Boolean = !isBust()

    fun isBust(): Boolean = playerCards.isBust()
}
