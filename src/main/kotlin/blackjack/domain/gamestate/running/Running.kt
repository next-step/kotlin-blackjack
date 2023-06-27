package blackjack.domain.gamestate.running

import blackjack.domain.gamestate.GameState

abstract class Running: GameState {

    final override fun isFinished() = false

    final override fun compete(gameState: GameState) = throw IllegalStateException("턴이 종료되지 않아 승부를 가릴 수 없다.")
}
