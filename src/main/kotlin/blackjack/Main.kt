package blackjack

import blackjack.model.BlackJack
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.WIN_SCORE
import blackjack.view.Input
import blackjack.view.Output

fun main() {
    val names = Input.names()
    val blackJack = BlackJack(Players(names.map { Player(it) }), Dealer())
    Output.players(blackJack.players)
    firstTurn(blackJack)
    repeat(blackJack.players.size()) {
        playerRace(blackJack, blackJack.players[it])
    }
    while (!blackJack.dealer.isOver17()) {
        Output.dealerAddCard(blackJack.dealerDrawCheck())
    }
    Output.scoreCalculate(blackJack.players, blackJack.dealer)
    blackJack.gameResult(blackJack.dealer)
    Output.gameResult(blackJack.players, blackJack.dealer)
}

fun firstTurn(blackJack: BlackJack) {
    blackJack.firstTurn()
    Output.dealerPickResult(blackJack.dealer)
    repeat(blackJack.players.size()) {
        Output.playerPickResult(blackJack.players[it])
    }
}

fun playerRace(blackJack: BlackJack, player: Player) {
    var dealerWin: Boolean = player is Dealer && player.score() >= WIN_SCORE
    while (blackJack.players.isPlayer(player) && !dealerWin && player.available() && Input.ask(player.name)) {
        blackJack.race(player)
        Output.playerPickResult(player)
    }
}
