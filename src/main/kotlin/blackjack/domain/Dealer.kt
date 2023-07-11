package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Dealer(name: String, cards: Cards = Cards()) : Player(name, cards) {
    var isFinished = false

    constructor(cards: Cards = Cards()) : this(DEALER_DISPLAY_NAME, cards)

    // 17점이 넘으면 호출되어도 더 이상 카드를 추가하지 않는다
    override fun addCard(card: Card) {
        if (isAddable()) {
            super.addCard(card)
        } else {
            isFinished = true
        }
    }

    private fun isAddable(): Boolean {
        return !isFinished && getScore() < LIMIT_SCORE
    }

    companion object {
        private const val DEALER_DISPLAY_NAME = "딜러"
        const val LIMIT_SCORE = 17
    }
}
