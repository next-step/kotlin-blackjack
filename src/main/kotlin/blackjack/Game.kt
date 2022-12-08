package blackjack

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.io.Input
import blackjack.io.Output

class Game(val input: Input, val output: Output) {
    lateinit var players: List<Player>
    private var deck: Deck = Deck()

    fun init() {
        players = input.getPlayers()
            .map { Player(it) }

        deck.shuffle()
    }

    fun start() {
        repeat(2) {
            players.forEach { it.draw(deck) }
        }
        output.printPlayersCard(players)
    }

    fun draw() {
        players.forEach { player ->
            while (player.score() < 21) {
                if (!input.moreDraw(player)) {
                    break
                }
                player.draw(deck)
            }
            output.printPlayerCard(player)
        }
    }

    fun result() {
        output.printPlayersResult(players)
    }
}
