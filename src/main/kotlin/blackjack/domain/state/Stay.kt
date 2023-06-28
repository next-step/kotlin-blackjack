package blackjack.domain.state

import blackjack.domain.card.Cards

class Stay(
    override val cards: Cards,
) : GamerState() {

    init {
        require(cards.value.size >= Cards.INIT_CARD_SIZE) {
            "cards size is ${cards.value.size}. support cards minimum size is ${Cards.INIT_CARD_SIZE}"
        }
    }
}
