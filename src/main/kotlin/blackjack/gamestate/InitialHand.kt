package blackjack.gamestate

import blackjack.Card
import blackjack.Cards
import java.lang.IllegalStateException

class InitialHand(
    val cards: Cards = Cards(),
) : GameState {
    init {
        require(cards.values.size < INITIAL_HAND_CARD_LIMIT_SIZE) { "초기 핸드는 ${INITIAL_HAND_CARD_LIMIT_SIZE}장 이상 가질 수 없다." }
    }

    override fun draw(card: Card): GameState {
        val cards = this.cards.addCard(card)
        if (cards.values.size == INITIAL_HAND_CARD_LIMIT_SIZE) {
            return Hit(cards)
        }
        return InitialHand(cards)
    }

    override fun stay() = throw IllegalStateException("2장을 받기전에는 카드를 그만받을 수 없다.")

    companion object {
        private const val INITIAL_HAND_CARD_LIMIT_SIZE = 2
    }
}