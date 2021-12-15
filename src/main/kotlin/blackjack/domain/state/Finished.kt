package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Finished(
    override val cards: Cards
) : State {

    override fun isFinished(): Boolean = true

    override fun draw(card: Card): State = throw UnsupportedOperationException(UNSUPPORTED_DRAW_METHOD)

    override fun stay(): State = throw UnsupportedOperationException(UNSUPPORTED_STAY_METHOD)

    companion object {
        const val UNSUPPORTED_DRAW_METHOD = "Finished 상태에서는 draw()를 지원하지 않습니다."
        const val UNSUPPORTED_STAY_METHOD = "Finished 상태에서는 stay()를 지원하지 않습니다."
    }
}
