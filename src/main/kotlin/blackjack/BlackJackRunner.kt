package blackjack

import blackjack.controller.ResultProcessor
import blackjack.controller.ViewInputProcessor
import blackjack.domain.BlackJackGame

class BlackJackRunner(
    val game: BlackJackGame = BlackJackGame(
        inputProcessor = ViewInputProcessor(),
        resultProcessor = ResultProcessor(),
    ),
) {
    fun start() {
        while (true) {
            game.run()
        }
    }
}
