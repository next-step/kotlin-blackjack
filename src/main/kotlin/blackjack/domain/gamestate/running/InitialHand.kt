package blackjack.domain.gamestate.running

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.gamestate.GameState

class InitialHand(
    val cards: Cards = Cards(),
) : Running() {
    init {
        require(cards.isInitialHand()) { "초기 핸드는 2장 이상 가질 수 없다." }
    }

    override fun cards() = cards.values

    override fun draw(card: Card): GameState {
        val cards = this.cards.addCard(card)
        if (cards.isInitialHand().not()) {
            return Hit(cards)
        }
        return InitialHand(cards)
    }

    override fun stay() = throw IllegalStateException("2장을 받기전에는 카드를 그만받을 수 없다.")

    override fun isBust() = false
}
