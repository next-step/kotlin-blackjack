package blackjack.domain

import java.math.BigDecimal

class Dealing(
    override val hand: Hand = Hand(),
) : State {
    override val isDone: Boolean get() = false

    init {
        require(hand.size <= Hand.INITIAL_HAND_SIZE) { "카드 숫자가 두 장을 초과할 수 없습니다." }
    }

    override fun drawFrom(deck: Deck): State {
        hand.drawFrom(deck)
        return when {
            hand.size < Hand.INITIAL_HAND_SIZE -> Dealing(hand)
            hand.isBlackjack() -> Blackjack(hand)
            else -> Ready(hand)
        }
    }

    override fun stand(): State = throw IllegalStateException("햔재 상태에서는 스탠드를 할 수 없습니다.")

    override fun profit(bet: Bet): BigDecimal = throw IllegalStateException("현재 상태에서는 수익을 계산할 수 없습니다.")
}
