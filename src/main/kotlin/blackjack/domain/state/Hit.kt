package blackjack.domain.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards

class Hit(
    override val cards: Cards,
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
}
