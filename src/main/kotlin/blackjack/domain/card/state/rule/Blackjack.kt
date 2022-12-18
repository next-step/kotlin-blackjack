package blackjack.domain.card.state.rule

import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.Finished

data class Blackjack(override val cards: PlayingCards) : Finished(cards) {
    init {
        require(cards.isBlackjack()) { "블랙잭이 아닙니다." }
    }
}
