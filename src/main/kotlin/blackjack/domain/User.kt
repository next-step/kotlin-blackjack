package blackjack.domain

abstract class User(
    val name: String,
    protected val playerCards: PlayerCards = PlayerCards(),
) {
    val score: Score get() = playerCards.score

    fun draw(cardDeck: CardDeck) {
        playerCards.addCard(cardDeck.pop())
    }

    fun currentCards() = playerCards.cards

    fun isBust(): Boolean = playerCards.isBust()

    fun isBlackJack(): Boolean = playerCards.size == 2 && score.isBlackJack()

    abstract fun openedCards(): List<Card>

    abstract fun canDraw(): Boolean
}
