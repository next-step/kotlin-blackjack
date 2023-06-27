package blackjack.domain.gamestate.running

import blackjack.domain.gamestate.GameState
import blackjack.domain.gamestate.finished.Finished

abstract class Running: GameState {

    final override fun compete(gameState: Finished) = throw IllegalStateException("턴이 종료되지 않아 승부를 가릴 수 없다.")
}
