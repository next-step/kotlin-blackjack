package blackjack

import blackjack.model.BlackJack
import blackjack.model.Player
import blackjack.model.Players
import blackjack.view.Input
import blackjack.view.Output

fun main() {
    val names = Input.names()
    val players = Players(names.map { Player(it) })
    val blackJack = BlackJack(players)
    Output.players(players)
    blackJack.firstTurn()
    for (count in 0 until players.size()) {
        Output.pickResult(players.findPlayer(count))
    }
    for (count in 0 until players.size()) {
        val player = players.findPlayer(count)
        while (player.isAvailable && Input.ask(player.name)) {
            blackJack.race(player)
        }
        Output.pickResult(player)
    }
    Output.winner(players.winner())
    Output.gameResult(blackJack.players)
}
