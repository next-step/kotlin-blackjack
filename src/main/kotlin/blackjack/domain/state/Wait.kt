package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.card.InitCard

class Wait : GamerState() {

    override val cards: Cards = Cards.empty()

    override fun init(initCard: InitCard): GamerState {
        return Hit(
            cards = initCard.cards,
        )
    }
}
