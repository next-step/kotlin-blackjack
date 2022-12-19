package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Game

fun main() {
    val players = InputView.inputPlayers()
    val dealer = Dealer()
    val game = Game(players, dealer)
    ResultView.printInitialCards(game.players, game.dealer)
    game.play()
    ResultView.printResult(game.players, game.dealer)
}

