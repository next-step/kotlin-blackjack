package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.PlayingCardPackFactory

fun main() {

    val players = InputView.inputPlayers()
    val blackJackGame = BlackJackGame(
        players,
        PlayingCardPackFactory.createPack(),
        ConsolePlayerInteraction()
    )

    val result = blackJackGame.play()

    ResultView.writeGameResult(result)
}
