package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.io.Input
import blackjack.io.Output

class Game(private val input: Input, private val output: Output) {
    private val players: List<Player> by lazy {
        makePlayers() + Dealer()
    }
    private val dealer: Dealer = Dealer()
    private val deck: Deck = Deck()

    fun init() {
        deck.shuffle()
    }

    fun start() {
        output.printDistribution()
        repeat(INIT_HAND_COUNT) {
            dealer.addCard(deck.draw())
            players.forEach { it.addCard(deck.draw()) }
        }
        output.printPlayerResult(dealer)
        output.printPlayersCard(players)
    }

    fun draw() {
        dealerDraw()
        output.printPlayerCard(dealer)
        players.forEach { player ->
            playerDraw(player)
            output.printPlayerCard(player)
        }
    }

    fun result() {
        output.printPlayerResult(dealer)
        output.printPlayersResult(players)
    }

    private fun dealerDraw() {
        while (dealer.canDraw()) {
            dealer.addCard(deck.draw())
        }
    }

    private fun playerDraw(player: Player) {
        while (player.canDraw()) {
            if (!input.moreDraw(player)) {
                break
            }
            player.addCard(deck.draw())
        }
    }

    private fun makePlayers() = input.getPlayers().map { Player(it) }

    companion object {
        private const val INIT_HAND_COUNT = 2
    }
}
