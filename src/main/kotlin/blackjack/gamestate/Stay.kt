package blackjack.gamestate

import java.lang.IllegalStateException

class Stay: GameState {
    override fun stay() = throw IllegalStateException("종료된 게임은 stay할 수 없다.")
}