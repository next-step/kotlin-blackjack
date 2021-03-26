package blackjack.domain.card.state

import blackjack.domain.card.Cards

class BlackJack(cards: Cards) : Finished(cards) {
    init {
        require(cards.score == Cards.BLACKJACK_SCORE) { "점수가 21점이 아니다" }
    }
}
