package blackjack.domain.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards

class FirstDraw(
    val cards: Cards,
) : Progress() {

    override fun draw(card: Card): State {
        cards.receiveCard(card)
        return Deal(cards)
    }

    override fun currentCards(): Cards = cards
}
