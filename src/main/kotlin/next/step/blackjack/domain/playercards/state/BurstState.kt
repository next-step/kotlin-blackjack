package next.step.blackjack.domain.playercards.state

import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult

object BurstState : PlayerCardsState() {

    override fun next(cards: Cards): PlayerCardsState {
        throw IllegalArgumentException("카드를 더 받을 수 없습니다.")
    }

    override fun fight(other: PlayerCardsState): GameResult =
        when (other) {
            BurstState -> GameResult.WIN
            else -> GameResult.LOSE
        }
}
