package blackjack.domain.state

import blackjack.domain.card.Cards

class Bust(
    override val cards: Cards,
) : GamerState() {

    init {
        require(cards.isBust) {
            "cards is not bust"
        }
    }
}
