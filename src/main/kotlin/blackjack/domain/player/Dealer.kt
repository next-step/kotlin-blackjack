package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.card.CardDeck

class Dealer : User(DEALER_NAME) {
    override fun canDraw(): Boolean = calculateScore() <= DEALER_DRAW_CONDITION

    fun drawAtFirst(cardDeck: CardDeck) {
        repeat(FIRST_DRAW_COUNT) { draw(cardDeck.pop(), DrawDecider.DRAW) }
    }

    companion object {
        private val DEALER_NAME = UserName("딜러")
        private const val DEALER_DRAW_CONDITION = 16
        private const val FIRST_DRAW_COUNT = 2
    }
}
