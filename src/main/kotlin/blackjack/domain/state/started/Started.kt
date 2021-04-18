package blackjack.domain.state.started

import blackjack.domain.card.Cards
import blackjack.domain.state.State

abstract class Started(
    val cards: Cards
) : State {
    override val cardNames: List<String>
        get() = cards.cardNames
    override val cardSize: Int
        get() = cards.size

    override fun takeFirstTwoCards(cards: Cards): State {
        throw RuntimeException("이미 시작한 경우, 2장의 카드를 받을 수 없습니다.")
    }

    override fun cardPointSum(): Int {
        return cards.calculatePoint()
    }
}
