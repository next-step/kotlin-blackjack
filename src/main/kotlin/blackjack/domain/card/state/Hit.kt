package blackjack.domain.card.state

import blackjack.domain.card.Cards

class Hit(cards: Cards) : Running(cards) {
    init {
        require(cards.score < Cards.BLACKJACK_SCORE) { "hit 는 21점 미만일 경우만 가능합니다." }
    }
}
