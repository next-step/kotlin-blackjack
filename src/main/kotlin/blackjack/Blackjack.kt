package blackjack

import blackjack.domain.GameDealer
import blackjack.domain.Game

fun main() {
    val players = InputView.inputPlayers()
    val dealer = GameDealer()
    val game = Game(players, dealer)
    ResultView.printInitialCards(game.players, game.dealer)
    game.play()
    ResultView.printPlayerResult(game.players, game.dealer)
}

