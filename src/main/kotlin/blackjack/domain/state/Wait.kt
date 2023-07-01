package blackjack.domain.state

import blackjack.domain.card.Cards

class Wait : GamerState() {

    override val cards: Cards = Cards()

    override fun init(initCards: Cards): GamerState {
        require(initCards.value.size == Cards.INIT_CARD_SIZE) {
            "init cards size should be ${Cards.INIT_CARD_SIZE}"
        }

        return Hit(
            cards = initCards,
        )
    }
}
