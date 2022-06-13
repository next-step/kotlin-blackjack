package blackjack.domain

sealed interface Rule {
    fun canDraw(score: Score): Boolean
    fun openedCards(playerCards: PlayerCards): List<Card>
}

object PlayerRule : Rule {
    override fun canDraw(score: Score): Boolean = !score.isBust()
    override fun openedCards(playerCards: PlayerCards): List<Card> = playerCards.cards
}

object DealerRule : Rule {
    private val CARD_DRAW_THRESHOLD = Score(17)
    override fun canDraw(score: Score): Boolean = score < CARD_DRAW_THRESHOLD
    override fun openedCards(playerCards: PlayerCards): List<Card> = playerCards.cards.take(1)
}
