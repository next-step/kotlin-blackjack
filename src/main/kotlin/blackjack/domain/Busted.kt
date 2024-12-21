package blackjack.domain

import java.math.BigDecimal

class Busted(
    hand: Hand,
) : Finished(hand) {
    override val profitRatio: BigDecimal = BigDecimal.ONE.negate()

    init {
        require(hand.size > Hand.INITIAL_HAND_SIZE) { "버스트는 세 장 이상의 카드로 이루어져야 합니다." }
        require(hand.value() > Hand.BLACKJACK_VALUE) { "버스트는 21점을 초과해야 합니다." }
    }
}
