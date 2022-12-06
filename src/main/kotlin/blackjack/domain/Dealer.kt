package blackjack.domain

import blackjack.domain.strategy.CardPickStrategy

class Dealer(
    private val cardPickStrategy: CardPickStrategy
) {
    fun pickCard(): Card {
        return cardPickStrategy.pick()
    }
}
