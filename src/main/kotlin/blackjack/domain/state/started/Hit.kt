package blackjack.domain.state.started

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.state.State
import blackjack.domain.state.started.finished.Bust
import java.math.BigDecimal

class Hit(
    cards: Cards
) : Started(cards) {

    override fun takeCard(card: Card): State {
        val newCards = cards.with(card)

        if (newCards.isBust) {
            return Bust(newCards)
        }
        return Hit(newCards)
    }

    override fun profit(betAmount: Int, dealerState: State): BigDecimal {
        throw RuntimeException("Finish되지 않은 상태에서 이익을 계산할 수 없습니다.")
    }
}
