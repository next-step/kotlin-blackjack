package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Game

fun main() {
    val players = InputView.inputPlayers()
    val dealer = Dealer()
    val game = Game(players, dealer)
    ResultView.printInitialCards(game.players)
    playBlackJack(game)
    ResultView.printResult(game.players)
}

private fun playBlackJack(game: Game) {
    game.players
        .value
        .forEach { player ->
            while (!player.blackjack() &&
                !player.burst() &&
                InputView.shouldHit(player)
            ) {
                player.hit(game.dealer.deliverCard())
                ResultView.printPlayerCards(player)
            }
        }
    println()
}
