package blackjack.domain

import blackjack.GameResult
import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Dealer(name: String, cards: Cards = Cards()) : Player(name, cards) {
    private var isFinished = false
    val gameResults = mutableListOf<GameResult>()

    constructor(cards: Cards = Cards()) : this(DEALER_DISPLAY_NAME, cards)

    // 17점이 넘으면 호출되어도 더 이상 카드를 추가하지 않는다
    override fun addCard(card: Card) {
        if (isReceivableNewCard()) {
            super.addCard(card)
        } else {
            isFinished = true
        }
    }

    override fun getGameResult(win: Boolean) {
        gameResults.add(if (win) GameResult.WIN else GameResult.LOSE)
    }

    override fun isReceivableNewCard(): Boolean {
        return !isFinished && getScore() < LIMIT_SCORE
    }

    companion object {
        private const val DEALER_DISPLAY_NAME = "딜러"
        private const val LIMIT_SCORE = 17
    }
}
