package blackjack

/**
 * ### 블랙잭을 플레이하는 사람을 표현하는 객체 입니다.
 */
class Player(
    val name: String,
) {
    private val deck: Deck = Deck()

    private var isStay: Boolean = false

    val point: Int
        get() = deck.point

    val isBurst: Boolean
        get() = BlackjackCardPointCalculator.BLACKJACK_POINT_THRESHOLD < point

    val canHit: Boolean
        get() = BlackjackCardPointCalculator.BLACKJACK_POINT_THRESHOLD >= point && isStay.not()

    fun receive(card: Card) {
        deck.add(card)
    }

    fun stay() {
        isStay = true
    }
}
