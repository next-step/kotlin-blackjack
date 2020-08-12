package blackjack

import blackjack.model.BlackJack
import blackjack.model.Player
import blackjack.view.Input
import blackjack.view.Output

fun main() {
    val names = Input.names()
    val players = names.map { Player(it) }
    val game = BlackJack(players)
    Output.players(players)
    game.firstTurn()
    players.forEach {
        Output.pickResult(it)
    }
    players.forEach {
        while (it.isAvailable && Input.ask(it.name)) {
            game.race(it)
        }
        Output.pickResult(it)
    }
    Output.winner(game.winner())
    Output.gameResult(game.players)
}
