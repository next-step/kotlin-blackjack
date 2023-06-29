package blackjack.domain.state

import blackjack.domain.card.Cards

class BlackJack(
    override val cards: Cards
) : GamerState() {

    init {
        require(cards.isBlackJack()) {
            "only support BlackJack cards"
        }
    }
}
