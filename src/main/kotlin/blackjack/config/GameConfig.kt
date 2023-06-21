package blackjack.config

import blackjack.ui.GameController
import blackjack.ui.GameInput
import blackjack.ui.GameInputImpl
import blackjack.ui.GameInputProxy
import blackjack.ui.GameOutput
import blackjack.ui.GameOutputImpl

object GameConfig {

    val gameController: GameController by lazy {
        GameController(
            gameInput = gameInput,
            gameOutput = gameOutput
        )
    }
    private val gameOutput: GameOutput by lazy { GameOutputImpl }
    private val gameInput: GameInput by lazy { GameInputProxy(target = GameInputImpl) }
}
