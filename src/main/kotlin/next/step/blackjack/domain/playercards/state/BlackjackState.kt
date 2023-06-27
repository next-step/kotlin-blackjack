package next.step.blackjack.domain.playercards.state

import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult

object BlackjackState : PlayerCardsState() {

    override fun next(cards: Cards): PlayerCardsState {
        throw IllegalArgumentException("카드를 더 받을 수 없습니다.")
    }

    override fun fight(other: PlayerCardsState): GameResult =
        when (other) {
            BlackjackState -> GameResult.TIE
            else -> GameResult.WIN
        }
}
