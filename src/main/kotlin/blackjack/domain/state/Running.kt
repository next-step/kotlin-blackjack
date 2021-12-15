package blackjack.domain.state

import blackjack.domain.card.Cards

abstract class Running(
    override val cards: Cards
) : State {

    override fun isFinished(): Boolean = false

    override fun match(other: State): GameResultState = throw UnsupportedOperationException(UNSUPPORTED_MATCH_METHOD)

    override fun stay(): State = Stay(cards)

    companion object {
        const val UNSUPPORTED_MATCH_METHOD = "Running 상태에서는 match()를 지원하지 않습니다."
    }
}
