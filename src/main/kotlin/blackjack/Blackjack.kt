package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Game
import blackjack.domain.Players

fun main() {
    val players = InputView.inputPlayers()
    val dealer = Dealer()
    val game = Game(players, dealer)
    ResultView.printInitialCards(game.players, game.dealer)
    playBlackJack(game)
    ResultView.printResult(game.players, game.dealer)
}

private fun playBlackJack(game: Game) {
    playPlayers(game.players, game.dealer)
    playDealer(game.dealer)
}

private fun playPlayers(players: Players, dealer: Dealer) {
    players.value
        .forEach { player ->
            while (!player.blackjack() &&
                !player.burst() &&
                InputView.shouldHit(player)
            ) {
                player.hit(dealer.deliverCard())
                ResultView.printPlayerCards(player)
            }
        }
    println()
}

private fun playDealer(dealer: Dealer) {
    while (!dealer.blackjack() && !dealer.stay() && !dealer.burst()) {
        InputView.printDealerHit()
        dealer.hit(dealer.deliverCard())
    }
}

