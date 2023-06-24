package blackjack

import blackjack.gamestate.GameState
import blackjack.gamestate.InitialHand

class Player(
    val name: String,
    gameState: GameState = InitialHand(),
) {
    var gameState: GameState = gameState
        private set

    init {
        require(name.length <= NAME_LENGTH_LIMIT) { "플레이어 이름은 5자를 초과할 수 없다." }
    }

    companion object {
        private const val NAME_LENGTH_LIMIT = 5
    }
}
