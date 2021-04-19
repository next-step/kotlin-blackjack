package blackjack.domain.state.notstarted

import blackjack.domain.card.Card
import blackjack.domain.state.State
import java.math.BigDecimal

class NotStarted : State {

    override val isRunning: Boolean
        get() = false

    override val cardNames: List<String>
        get() = emptyList()

    override val cardSize: Int
        get() = 0

    override fun takeCard(card: Card): State {
        throw IllegalStateException("시작하지 않은 상태에서 카드를 받을 수 없습니다.")
    }

    override fun cardPointSum(): Int {
        return NO_CARD_POINT
    }

    override fun profit(betAmount: Int, dealerState: State): BigDecimal {
        throw IllegalStateException("시작하지 않은 상태에서 이익을 계산할 수 없습니다.")
    }

    override fun stay(): State {
        throw IllegalStateException("시작하지 않은 상태에서 stay를 호출할 수 없습니다.")
    }

    companion object {
        private const val NO_CARD_POINT = 0
    }
}
