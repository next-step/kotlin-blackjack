package blackjack.domain.card.state

import blackjack.domain.card.Cards

class Stay(cards: Cards) : Finished(cards) {

    override val earningRate = 1.0

    init {
        require(cards.score <= BlackJack.SCORE) { "stay 는 21점 이하일 경우만 가능합니다." }
    }
}
