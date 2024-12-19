package blackjack.domain

import java.math.BigDecimal

class Ready(
    override val hand: Hand,
) : State {
    override val isDone: Boolean get() = false

    init {
        require(hand.size >= Hand.INITIAL_HAND_SIZE) { "카드 두 장 이상이어야 합니다." }
    }

    override fun drawFrom(deck: Deck): State {
        hand.drawFrom(deck)
        return when {
            hand.isBusted() -> Busted(hand)
            hand.isTwentyOne -> Stand(hand)
            else -> Ready(hand)
        }
    }

    override fun stand(): State = Stand(hand)

    override fun profit(bet: Bet): BigDecimal = throw IllegalStateException("현재 상태에서는 수익을 계산할 수 없습니다.")
}
