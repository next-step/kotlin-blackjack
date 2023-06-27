package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.InitCard

class Hit(
    override val cards: Cards,
) : GamerState() {

    init {
        require(cards.isBust.not()) {
            "bust cards not support"
        }

        require(cards.size >= InitCard.INIT_CARD_SIZE) {
            "cards size is ${cards.size}. support cards minimum size is ${InitCard.INIT_CARD_SIZE}"
        }
    }

    override fun hit(card: Card): GamerState {
        val newCards = cards + card
        return if (newCards.isBust) {
            Bust(newCards)
        } else {
            Hit(newCards)
        }
    }

    override fun stay(): GamerState {
        return Stay(cards)
    }
}
