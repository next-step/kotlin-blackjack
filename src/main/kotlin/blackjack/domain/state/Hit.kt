package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Hit(
    override val cards: Cards,
) : GamerState() {

    init {
        require(cards.score.isBust.not()) {
            "bust cards not support"
        }

        require(cards.value.size >= Cards.INIT_CARD_SIZE) {
            "cards size is ${cards.value.size}. support cards minimum size is ${Cards.INIT_CARD_SIZE}"
        }
    }

    override fun hit(card: Card): GamerState {
        val newCards = cards + card
        return if (newCards.score.isBust) {
            Bust(newCards)
        } else {
            Hit(newCards)
        }
    }

    override fun stay(): GamerState {
        return if (cards.isBlackJack()) {
            BlackJack(cards)
        } else {
            Stay(cards)
        }
    }
}
