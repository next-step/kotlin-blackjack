package blackjack.domain.gamestate.finished

import blackjack.domain.card.Cards
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

    override fun profit(money: Int, gameState: GameState): Int {
        require(gameState.isFinished()) { "게임이 종료되지 않은 상대와 비교할 수 없다."}
        if (gameState is Bust) {
            return money
        }
        return money * competeWithoutBust(gameState)
    }

    private fun competeWithoutBust(gameState: GameState): Int {
        return when (score().compareTo(gameState.score())) {
            WIN_COMPARE_VALUE -> 1
            LOST_COMPARE_VALUE -> -1
            DRAW_COMPARE_VALUE -> 0
            else -> throw RuntimeException("승부 계산에 문제가 발생했습니다.")
        }
    }

    companion object {
        private const val WIN_COMPARE_VALUE = 1
        private const val LOST_COMPARE_VALUE = -1
        private const val DRAW_COMPARE_VALUE = 0
    }
}
