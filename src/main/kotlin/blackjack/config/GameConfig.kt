package blackjack.config

import blackjack.domain.BlackJackOutcomeCalculator
import blackjack.domain.GameOutcomeCalculator
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
            gameOutput = gameOutput,
            gameOutcomeCalculator = gameOutcomeCalculator
        )
    }
    private val gameOutcomeCalculator: GameOutcomeCalculator by lazy {
        BlackJackOutcomeCalculator()
    }
    private val gameOutput: GameOutput by lazy { GameOutputImpl }
    private val gameInput: GameInput by lazy { GameInputProxy(target = GameInputImpl) }
}
