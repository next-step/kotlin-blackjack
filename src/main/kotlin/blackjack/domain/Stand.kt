package blackjack.domain

import java.math.BigDecimal

class Stand(
    hand: Hand,
) : Finished(hand) {
    override val profitRatio: BigDecimal = BigDecimal.ONE

    init {
        require(hand.size >= Hand.INITIAL_HAND_SIZE) { "스탠드는 세 장 이상의 카드로 이루어져야 합니다." }
        require(hand.value() <= Hand.BLACKJACK_VALUE) { "스탠드는 21점 이하여야 합니다." }
    }

    override fun profit(bet: Bet): BigDecimal = bet.value.times(BigDecimal(1.0))
}
