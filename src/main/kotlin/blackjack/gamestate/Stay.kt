package blackjack.gamestate

import blackjack.Card
import java.lang.IllegalStateException

class Stay: GameState {
    override fun draw(card: Card) = throw IllegalStateException("종료된 게임은 draw할 수 없다.")

    override fun stay() = throw IllegalStateException("종료된 게임은 stay할 수 없다.")
}