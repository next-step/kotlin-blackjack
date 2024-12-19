package blackjack.domain

import java.math.BigDecimal

class Busted(
    override val hand: Hand,
) : State {
    override val isDone: Boolean get() = false

    init {
        require(hand.size > Hand.INITIAL_HAND_SIZE) { "버스트는 세 장 이상의 카드로 이루어져야 합니다." }
        require(hand.value() > Hand.BLACKJACK_VALUE) { "버스트는 21점을 초과해야 합니다." }
    }

    override fun drawFrom(deck: Deck): State = throw IllegalStateException("현재 상태에서는 카드를 뽑을 수 없습니다.")

    override fun stand(): State = throw IllegalStateException("현재 상태에서는 스탠드를 할 수 없습니다.")

    override fun profit(bet: Bet): BigDecimal = bet.value.times(BigDecimal.ZERO)
}
