package blackjack.domain

abstract class User(
    val hand: Hand
) {
    abstract fun hit(card: Card)
    abstract fun canHit(): Boolean

    fun init(cards: List<Card>) {
        require(cards.size == 2) { "처음엔 카드 2장만 받을 수 있습니다." }
        hand.init(cards[0], cards[1])
    }

    fun isBust(): Boolean {
        return hand.getSum() > BLACKJACK
    }

    companion object {
        const val BLACKJACK = 21
    }
}
