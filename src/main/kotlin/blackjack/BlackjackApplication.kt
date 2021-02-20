package blackjack

import blackjack.domain.game.Game
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val playersName = InputView.inputPlayer()
    val game = Game(playersName)

    game.start()
    OutputView.showStartStatus(game)

    if (game.existBlackJack()) {
        OutputView.showBlackJack(game.players)
    } else {
        playGame(game)
        OutputView.showResult(game.dealer, game.players)
    }
}

fun playGame(game: Game) {
    while (game.isEnableContinue()) {
        playOneStep(game)
    }

    if (game.dealer.enableAdditionalDraw()) {
        OutputView.notifyDealerDraw()
    }
    game.playDealerTurn()
}

fun playOneStep(game: Game) {
    if (InputView.additionalDraw(game.turnPlayer)) {
        game.draw()
        OutputView.showPlayer(game.turnPlayer)
    } else {
        changePlayer(game)
    }
}

fun changePlayer(game: Game) {
    if (game.existNextPlayer()) {
        game.changeNextPlayer()
    } else {
        game.endPlayerTurn()
    }
}
