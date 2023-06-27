package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.card.InitCard

class Stay(
    override val cards: Cards,
) : GamerState() {

    init {
        require(cards.size >= InitCard.INIT_CARD_SIZE) {
            "cards size is ${cards.size}. support cards minimum size is ${InitCard.INIT_CARD_SIZE}"
        }
    }
}
