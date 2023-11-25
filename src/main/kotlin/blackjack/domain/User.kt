package blackjack.domain

import blackjack.domain.state.State2

abstract class User(
    val status: State2,
) {
    val hand: Hand
        get() = status.cards()

    var state = State.HIT
        protected set

    abstract fun hit(card: Card)

    open fun init(cards: List<Card>) {
        require(cards.size == 2) { "처음엔 카드 2장만 받을 수 있습니다." }
        status.init(cards)
    }

    fun isBust(): Boolean {
        return status.getSum() > BLACKJACK
    }

    fun isBlackjack(): Boolean {
        return state == State.BLACKJACK
    }

    fun stand() {
        state = State.STAND
    }

    fun canHit(): Boolean {
        return state == State.HIT
    }

    companion object {
        const val BLACKJACK = 21
    }
}
