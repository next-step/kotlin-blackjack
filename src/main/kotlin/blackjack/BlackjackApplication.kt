package blackjack

import blackjack.domain.deck.Deck
import blackjack.domain.game.Game
import blackjack.domain.player.Player
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val playersNames = InputView.inputPlayerNames()
    val players = playersNames.map { name ->
        Player(name, InputView.inputBettingFor(name))
    }
    val deck = Deck.createDeck()
    val game = Game(players, deck)

    game.start()
    OutputView.showStartStatus(game)

    playGame(game)
    OutputView.showResult(game)
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
