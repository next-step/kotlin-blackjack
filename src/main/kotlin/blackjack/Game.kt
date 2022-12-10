package blackjack

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.io.Input
import blackjack.io.Output

class Game(private val input: Input, private val output: Output) {
    private val players: List<Player> by lazy {
        makePlayers()
    }
    private val deck: Deck = Deck()

    fun init() {
        deck.shuffle()
    }

    fun start() {
        repeat(INIT_HAND_COUNT) {
            players.forEach { it.draw(deck) }
        }
        output.printPlayersCard(players)
    }

    fun draw() {
        players.forEach { player ->
            playerDraw(player)
            output.printPlayerCard(player)
        }
    }

    fun result() {
        output.printPlayersResult(players)
    }

    private fun playerDraw(player: Player) {
        while (player.score() < 21) {
            if (!input.moreDraw(player)) {
                break
            }
            player.hand
                .add(deck.draw())
        }
    }

    private fun makePlayers() = input.getPlayers().map { Player(it) }

    companion object {
        private const val INIT_HAND_COUNT = 2
    }
}
