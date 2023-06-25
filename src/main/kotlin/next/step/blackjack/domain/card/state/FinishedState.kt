package next.step.blackjack.domain.card.state

import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult

object FinishedState : CardsState() {

    override fun next(cards: Cards): CardsState {
        throw IllegalArgumentException("카드를 더 받을 수 없습니다.")
    }

    override fun fight(other: CardsState): GameResult =
        when (other) {
            FinishedState -> GameResult.TIE
            BlackjackState -> GameResult.LOSE
            else -> GameResult.WIN
        }
}
