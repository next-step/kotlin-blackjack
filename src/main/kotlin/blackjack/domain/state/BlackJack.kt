package blackjack.domain.state

import blackjack.domain.card.Cards

class BlackJack(
    override val cards: Cards
) : Finished() {

    init {
        require(cards.isBlackJack()) {
            "only support BlackJack cards"
        }
    }
}
