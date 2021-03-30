package blackjack.domain.card.state

import blackjack.domain.card.Cards

class Bust(cards: Cards) : Finished(cards) {

    override val earningRate = -1.0

    init {
        require(cards.score > BlackJack.SCORE)
    }

    override fun isBust() = true
}
