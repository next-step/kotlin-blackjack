package blackjack.domain.gamestate.finished

import blackjack.domain.card.Cards
import blackjack.domain.gamestate.Competition
import blackjack.domain.gamestate.GameState
import java.lang.RuntimeException

class Stay(
    val cards: Cards,
) : Finished() {
    init {
        require(cards.isInitialHand().not()) { "2장 미만의 카드로 생성될 수 없다." }
        require(cards.isBust().not()) { "버스트 카드로 생성될 수 없다." }
    }

    override fun cards() = cards.values

    override fun isBust() = false

    override fun score() = cards.score()

    override fun compete(gameState: GameState): Competition {
        require(gameState.isFinished()) { "게임이 종료되지 않은 상대와 비교할 수 없다."}
        if (gameState.isBust()) {
            return Competition.WIN
        }
        return when (score().compareTo(gameState.score())) {
            WIN_COMPARE_VALUE -> Competition.WIN
            LOST_COMPARE_VALUE -> Competition.LOSE
            DRAW_COMPARE_VALUE -> Competition.DRAW
            else -> throw RuntimeException("승부 계산에 문제가 발생했습니다.")
        }
    }

    companion object {
        private const val WIN_COMPARE_VALUE = 1
        private const val LOST_COMPARE_VALUE = -1
        private const val DRAW_COMPARE_VALUE = 0
    }
}
