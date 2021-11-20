package blackjack.domain.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards

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
}
