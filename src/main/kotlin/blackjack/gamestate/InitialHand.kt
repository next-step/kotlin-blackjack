package blackjack.gamestate

import java.lang.IllegalStateException

class InitialHand: GameState {
    override fun stay() = throw IllegalStateException("2장을 받기전에는 카드를 그만받을 수 없다.")
}