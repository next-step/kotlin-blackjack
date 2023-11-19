package blackjack.domain

abstract class User(
    val hand: Hand
) {
    var state = State.HIT
        protected set

    abstract fun hit(card: Card)

    fun init(cards: List<Card>) {
        require(cards.size == 2) { "처음엔 카드 2장만 받을 수 있습니다." }
        hand.init(cards[0], cards[1])
        if (hand.getSum() == BLACKJACK) {
            state = State.BLACKJACK
        }
    }

    fun isBust(): Boolean {
        return hand.getSum() > BLACKJACK
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
