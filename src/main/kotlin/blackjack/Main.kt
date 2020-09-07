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
    firstTurn(blackJack, blackJack.players)
    repeat(blackJack.players.size()) {
        playerRace(blackJack, blackJack.players[it])
    }
    while (blackJack.dealerDrawCheck()) {
        Output.dealerAddCard(blackJack.dealerDrawCheck())
    }
    Output.scoreCalculate(blackJack.players, blackJack.dealer)
    blackJack.gameResult(blackJack.dealer)
    Output.gameResult(blackJack.players, blackJack.dealer)
}

fun firstTurn(blackJack: BlackJack, players: Players) {
    blackJack.firstTurn()
    repeat(players.size()) {
        Output.pickResult(players[it])
    }
}

fun playerRace(blackJack: BlackJack, player: Player) {
    var dealerWin: Boolean = player is Dealer && player.score() >= WIN_SCORE
    while (blackJack.players.isPlayer(player) && !dealerWin && player.available() && Input.ask(player.name)) {
        blackJack.race(player)
        Output.pickResult(player)
    }
}
