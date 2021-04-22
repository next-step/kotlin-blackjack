package blackjack.domain.state.notstarted

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.State
import blackjack.domain.state.started.finished.BlackJack
import blackjack.domain.state.started.run.Hit
import java.math.BigDecimal

class NotStarted(
    private val cards: Cards = Cards(emptyList())
) : State {

    override val cardNames: List<String> = emptyList()
    override val cardSize: Int = 0
    override val isRunning: Boolean = false
    override val isBust: Boolean = false
    override val isBlackJack: Boolean = false

    override fun takeCard(card: Card): State {
        val newCards = cards.with(card)
        if (newCards.size < 2) {
            return NotStarted(newCards)
        }
        if (newCards.isBlackjack) {
            return BlackJack(newCards)
        }
        return Hit(newCards)
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
