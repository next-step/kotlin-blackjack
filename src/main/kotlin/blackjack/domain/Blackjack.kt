package blackjack.domain

import java.math.BigDecimal

class Blackjack(
    hand: Hand,
) : Finished(hand) {
    override val profitRatio: BigDecimal = BigDecimal(1.5)

    init {
        require(hand.size == Hand.INITIAL_HAND_SIZE) { "블랙잭은 두 장의 카드로 이루어집니다." }
        require(hand.value() == Hand.BLACKJACK_VALUE) { "블랙잭은 21점이어야 합니다." }
    }
}
