package blackjack.domain.gamestate.finished

import blackjack.domain.card.Card
import blackjack.domain.gamestate.GameState

abstract class Finished : GameState {

    final override fun draw(card: Card) = throw IllegalStateException("종료된 게임은 draw할 수 없다.")

    final override fun stay() = throw IllegalStateException("종료된 게임은 stay할 수 없다.")
}
