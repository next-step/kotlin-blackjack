package blackjack.domain.gamestate.running

import blackjack.domain.gamestate.GameState
import blackjack.domain.gamestate.finished.Finished

abstract class Running: GameState {

    final override fun score() = throw IllegalStateException("턴이 종료되지 않아 점수를 반환할 수 없다.")

    final override fun compete(gameState: Finished) = throw IllegalStateException("턴이 종료되지 않아 승부를 가릴 수 없다.")
}
