package blackjack.domain.state.notstarted

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.state.State
import blackjack.domain.state.started.finished.BlackJack
import blackjack.domain.state.started.Hit
import java.math.BigDecimal

class NotStarted : State {

    override fun takeFirstTwoCards(cards: Cards): State {
        if (cards.isBlackjack) {
            return BlackJack(cards)
        }
        return Hit(cards)
    }

    override fun takeCard(card: Card): State {
        throw RuntimeException("시작하지 않은 상태에서 카드를 받을 수 없습니다.")
    }

    override fun cardPointSum(): Int {
        return 0
    }

    override fun profit(betAmount: Int, dealerState: State): BigDecimal {
        throw RuntimeException("시작하지 않은 상태에서 이익을 계산할 수 없습니다.")
    }
}
