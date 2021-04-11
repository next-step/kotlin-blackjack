package blackjack.domain.state.started.finished

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.state.State
import blackjack.domain.state.started.Started
import java.math.BigDecimal

abstract class Finished(
    cards: Cards
) : Started(cards) {
    protected abstract val earningRatio: BigDecimal

    override fun takeCard(card: Card): State {
        throw RuntimeException("Finished에서 카드를 더 받을 수 없습니다.")
    }
}
