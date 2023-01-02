package blackjack.domain.card.state.rule

import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.Finished

class Blackjack(cards: PlayingCards) : Finished(cards) {
    override val rate: Double = 1.5

    init {
        require(cards.isBlackjack()) { "블랙잭이 아닙니다." }
    }
}
