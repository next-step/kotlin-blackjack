package blackjack.domain.card.state.rule

import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.Finished

class Stay(override val cards: PlayingCards) : Finished(cards) {
    init {
        require(cards.isStay()) { "스테이가 아닙니다." }
    }
}
