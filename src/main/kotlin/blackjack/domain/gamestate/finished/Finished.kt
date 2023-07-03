package blackjack.domain.gamestate.finished

import blackjack.domain.card.Card
import blackjack.domain.gamestate.GameState

abstract class Finished : GameState {

    final override fun draw(card: Card) = throw IllegalStateException("종료된 게임은 드로우할 수 없습니다.")

    final override fun stay() = throw IllegalStateException("종료된 게임은 stay할 수 없습니다.")

    final override fun isFinished() = true
}
