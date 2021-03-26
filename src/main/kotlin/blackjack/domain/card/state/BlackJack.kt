package blackjack.domain.card.state

import blackjack.domain.card.Cards
import blackjack.domain.card.Score

class BlackJack(cards: Cards) : Finished(cards) {
    init {
        require(cards.score == SCORE) { "점수가 21점이 아니다" }
    }

    companion object {
        val SCORE = Score.of(21)
    }
}
