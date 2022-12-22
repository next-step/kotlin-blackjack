package blackjack.domain.card.state.rule

import blackjack.domain.card.PlayingCards
import blackjack.domain.card.state.Finished

class Bust(cards: PlayingCards) : Finished(cards) {
    init {
        require(cards.isBust()) { "버스트가 아닙니다." }
    }
}
