package blackjack.domain

import blackjack.domain.state.State

abstract class User(
    var state: State,
) {
    val hand: Hand
        get() = state.cards()

    abstract fun hit(card: Card)

    open fun init(cards: List<Card>) {
        require(cards.size == 2) { "처음엔 카드 2장만 받을 수 있습니다." }
        state = state.init(cards)
    }

    fun isBust(): Boolean {
        return state.getSum() > BLACKJACK
    }

    fun isBlackjack(): Boolean {
        return state.isBlackjack()
    }

    fun stay() {
        state = state.stay()
    }

    fun canHit(): Boolean {
        return !state.isFinished()
    }

    companion object {
        const val BLACKJACK = 21
    }
}
