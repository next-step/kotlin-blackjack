package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.card.CardDeck

class Dealer : User(DEALER_NAME) {
    override fun canDraw(): Boolean = score.isLowerThan(DEALER_DRAW_CONDITION)

    fun drawAdditional(cardDeck: CardDeck) {
        while (canDraw()) {
            draw(cardDeck.pop(), DrawDecider.DRAW)
        }
    }

    companion object {
        private val DEALER_NAME = UserName("딜러")
        private const val DEALER_DRAW_CONDITION = 16
    }
}
