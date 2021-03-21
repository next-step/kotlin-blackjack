package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.card.CardDeck

class Dealer : User(DEALER_NAME) {
    override fun canDraw(): Boolean = calculateScore() <= DEALER_DRAW_CONDITION

    fun drawAdditional(cardDeck: CardDeck, drawDecider: DrawDecider) {
        while (canDraw()) {
            draw(cardDeck.pop(), drawDecider)
        }
    }

    companion object {
        private val DEALER_NAME = UserName("딜러")
        private const val DEALER_DRAW_CONDITION = 16
    }
}
