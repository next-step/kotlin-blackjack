package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.User
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackController {
    fun run() {
        val names = InputView.inputPlayersName()
        val users = names.map { User(it) }
        val game = BlackJackGame(deck = Deck(), dealer = Dealer(), players = Players(users))

        game.drawInitCards()
        ResultView.printInitialStatus(game)

        game.players.values.forEach {
            playUser(it, game.deck)
            if (!it.isHit()) return@forEach
        }
        playDealer(game)

        val playerResults = game.getPlayerResults()
        ResultView.printStatus(game)
        ResultView.printResults(playerResults, game.dealer)
    }

    private fun playUser(player: Player, deck: Deck) {
        while (player.isHit() && InputView.inputIsGetCard(player)) {
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
