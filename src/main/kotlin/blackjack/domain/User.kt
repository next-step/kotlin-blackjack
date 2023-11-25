package blackjack.domain

import blackjack.domain.state.State

abstract class User(
    var state: State,
) {

    open fun hit(card: Card) {
        state = state.draw(card)
    }

    open fun init(cards: List<Card>) {
        require(cards.size == 2) { "처음엔 카드 2장만 받을 수 있습니다." }
        state = state.init(cards)
    }

    fun isBust(): Boolean {
        return state.isBust()
    }

    fun isBlackjack(): Boolean {
        return state.isBlackjack()
    }

    fun stay() {
        state = state.stay()
    }

    fun isFinished(): Boolean {
        return state.isFinished()
    }
}
