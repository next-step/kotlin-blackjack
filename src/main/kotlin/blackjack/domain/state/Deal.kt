package blackjack.domain.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards
import blackjack.domain.state.State.Companion.FINISHED_SIGN

class Deal(
    override val cards: Cards,
) : Progress() {
    override fun draw(card: Card): State {
        cards.receiveCard(card)
        if (cards.isBlackjack()) {
            return Blackjack(cards)
        }
        return Hit(cards)
    }

    override fun isStand(sign: String): Boolean {
        return sign == FINISHED_SIGN
    }
}
