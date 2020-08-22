package blackjack

import blackjack.model.BlackJack
import blackjack.model.DEALER_NAME
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.WIN_SCORE
import blackjack.view.Input
import blackjack.view.Output

fun main() {
    val names = Input.names()
    val blackJack = BlackJack(Players(names.map { Player(it) }, Dealer()))
    Output.players(blackJack.players)
    firstTurn(blackJack, blackJack.players)
    repeat(blackJack.players.size()) {
        playerRace(blackJack, blackJack.players[it])
    }
    Output.dealerAddCard(blackJack.dealerDrawCheck())
    blackJack.gameResult(blackJack.players.dealer)
    Output.scoreCalculate(blackJack.players)
    Output.gameResult(blackJack.players)
}

fun firstTurn(blackJack: BlackJack, players: Players) {
    blackJack.firstTurn()
    repeat(players.size()) {
        Output.pickResult(players[it])
    }
}

fun playerRace(blackJack: BlackJack, player: Player) {
    var dealerWin: Boolean = player is Dealer && player.score() >= WIN_SCORE
    while (player.name != DEALER_NAME && !dealerWin && player.available() && Input.ask(player.name)) {
        blackJack.race(player)
        Output.pickResult(player)
    }
}
