package blackjack.domain.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards
import blackjack.domain.state.State.Companion.FINISHED_SIGN

class Hit(
    val cards: Cards,
) : Progress() {

    override fun draw(card: Card): State {
        cards.receiveCard(card)
        if (cards.isTwentyOne()) {
            return TwentyOne(cards)
        }
        if (cards.isBust()) {
            return Bust(cards)
        }
        return Hit(cards)
    }

    override fun isStand(sign: String): Boolean {
        return sign == FINISHED_SIGN
    }

    override fun currentCards(): Cards = cards
}
