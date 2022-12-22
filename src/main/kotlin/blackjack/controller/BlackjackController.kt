package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackController {
    fun run() {
        val game = BlackJackGame()

        val names = InputView.inputPlayersName()
        game.setInitDealer()
        game.setInitPlayers(names)

        ResultView.printInitialStatus(game)

        game.players.forEach {
            playUser(it, game.deck)
            if (it.isBust()) game.addPlayerResultWhenBust(it)
        }
        playDealer(game)

        game.calculateResult()
        ResultView.printStatus(game)
        ResultView.printResults(game)
    }

    private fun playUser(player: Player, deck: Deck) {
        while (!player.isBust() && InputView.inputIsGetCard(player)) {
            player.hit(deck.draw())
            ResultView.printPlayerStatus(player)
        }
    }

    private fun playDealer(game: BlackJackGame) {
        game.playDealer()
        ResultView.printDealerHitOrStay(game.isDealerDrawn())
    }
}

fun main() {
    BlackjackController().run()
}
