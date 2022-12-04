package blackjack

import blackjack.strategy.CardPickStrategy

class Dealer(
    private val cardPickStrategy: CardPickStrategy
) {
    fun pickCard(): Card {
        return cardPickStrategy.pick()
    }
}
