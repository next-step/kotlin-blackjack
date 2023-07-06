package blackjack.domain

import blackjack.domain.BlackjackCardPointCalculator
import blackjack.domain.Card
import blackjack.domain.Deck

/**
 * ### 블랙잭을 플레이하는 사람을 표현하는 객체 입니다.
 */
data class Player(
    val name: String,
    val deck: Deck = Deck(),
) {

    private var isStay: Boolean = false

    val point: Int
        get() = deck.point

    val isBurst: Boolean
        get() = BlackjackCardPointCalculator.BLACKJACK_POINT_THRESHOLD < point

    val canHit: Boolean
        get() = BlackjackCardPointCalculator.BLACKJACK_POINT_THRESHOLD >= point && isStay.not()

    fun receive(card: Card) {
        check(canHit) { "Can not hit anymore" }
        deck.add(card)
    }

    fun stay() {
        isStay = true
    }
}
