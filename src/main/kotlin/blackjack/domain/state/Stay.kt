package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.card.InitCard

class Stay(
    override val cards: Cards,
) : GamerState() {

    init {
        require(cards.value.size >= InitCard.INIT_CARD_SIZE) {
            "cards size is ${cards.value.size}. support cards minimum size is ${InitCard.INIT_CARD_SIZE}"
        }
    }
}
