package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Hit(
    cards: Cards
) : Running(cards) {
    override fun receiveCard(card: Card): State {
        val cards = cards()
        cards.add(card)

        return when (cards.isBust()) {
            true -> return Bust(cards)
            false -> return Hit(cards)
        }
    }
}
