package blackjack.model

import blackjack.model.pack.Pack

data class Dealer(
    val cards: Cards = Cards.emptyCards(),
) {
    fun run(pack: Pack) {
        cards.totalScore() > 17
        TODO()
    }
}
