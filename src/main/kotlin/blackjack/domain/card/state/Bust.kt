package blackjack.domain.card.state

import blackjack.domain.card.Cards

class Bust(cards: Cards) : Finished(cards) {
    init {
        require(cards.score > BlackJack.SCORE)
    }
}
