package blackjack.domain

/**
 * ### 블랙잭을 플레이하는 사람을 표현하는 객체 입니다.
 */
data class Player(
    val name: String,
    val deck: Deck = Deck(),
) {

    private var isStay: Boolean = false

    val isBurst: Boolean
        get() = BlackjackCardPointCalculator.BLACKJACK_POINT_THRESHOLD < getDeckPointSum()

    val canHit: Boolean
        get() = BlackjackCardPointCalculator.BLACKJACK_POINT_THRESHOLD >= getDeckPointSum() && isStay.not()

    fun receive(card: Card) {
        check(canHit) { "Can not hit anymore" }
        deck.add(card)
    }

    fun stay() {
        isStay = true
    }

    fun getDeckPointSum(): Int {
        return deck.getCardPointSum()
    }
}
