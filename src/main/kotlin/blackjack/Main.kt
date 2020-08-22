package blackjack

import blackjack.model.BlackJack
import blackjack.model.CardDeck
import blackjack.model.Player
import blackjack.model.Players
import blackjack.view.Input
import blackjack.view.Output

fun main() {
    val names = Input.names()
    val players = Players(names.map { Player(it) })
    val blackJack = BlackJack(players, CardDeck().apply { this.shuffle() })
    Output.players(players)
    firstTurn(blackJack, players)
    repeat(players.size()) {
        playerRace(blackJack, players[it])
    }
    Output.winner(blackJack.winner())
    Output.gameResult(blackJack.players)
}

fun firstTurn(blackJack: BlackJack, players: Players) {
    blackJack.firstTurn()
    repeat(players.size()) {
        Output.pickResult(players[it])
    }
}

fun playerRace(blackJack: BlackJack, player: Player) {
    while (player.available() && Input.ask(player.name)) {
        blackJack.race(player)
        Output.pickResult(player)
    }
}
