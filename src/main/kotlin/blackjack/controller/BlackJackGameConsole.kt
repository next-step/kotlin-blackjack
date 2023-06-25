package blackjack.controller

import blackjack.domain.BlackJackGame

fun main() {

    val players = InputView.inputPlayers()
    val blackJackGame = BlackJackGame(players)

    val gameInit = blackJackGame.initDraw()
    ResultView.writeGameInit(gameInit)

    val result = blackJackGame.play(
        askHit = { player -> InputView.askPlayerHit(player) },
        showPlayer = { player -> ResultView.writePlayer(player) }
    )

    ResultView.writeGameResult(result)
}
