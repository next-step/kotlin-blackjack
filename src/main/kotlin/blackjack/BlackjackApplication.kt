package blackjack

import blackjack.domain.game.Game
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val playersName = InputView.inputPlayer()
    val game = Game(playersName)

    game.start()
    OutputView.showStartStatus(game)

    playGame(game)
    OutputView.showResult(game.dealer, game.playersInGame)
}

fun playGame(game: Game) {
    while (game.isEnableContinue()) {
        game.playOneStep(
            additionalDraw = { name ->
                InputView.additionalDraw(name)
            },
            forEachStep = { player ->
                OutputView.showPlayer(player)
            })
    }

    if (game.dealer.enableAdditionalDraw()) {
        OutputView.notifyDealerDraw()
    }
    game.playDealerTurn()
}
