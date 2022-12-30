package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.io.Input
import blackjack.io.Output

class Game(private val input: Input, private val output: Output) {
    private val players: List<Player> by lazy { makePlayers() }
    private val dealer: Dealer = Dealer()
    private val deck: Deck = Deck()

    fun init() {
        deck.shuffle()
    }

    fun start() {
        repeat(INIT_HAND_COUNT) {
            dealer.draw(deck.draw())
            players.forEach { it.draw(deck.draw()) }
        }

        output.printPlayersCard(listOf(dealer) + players)
    }

    fun draw() {
        output.printEmptyLine()

        players.forEach { player ->
            playerDraw(player)
        }

        dealerDraw()
    }

    fun result() {
        output.printEmptyLine()

        output.printDealerHandWithScore(dealer)
        output.printPlayersHandWithScore(players)

        output.printEmptyLine()

        output.printDealerResult(dealer, dealer.result(players.map { it.score() }))
        output.printPlayersResult(players, dealer)
    }

    private fun dealerDraw() {
        while (dealer.canDraw()) {
            output.printDealerDraw()
            dealer.draw(deck.draw())
        }
    }

    private fun playerDraw(player: Player) {
        while (player.canDraw()) {
            if (!input.moreDraw(player)) {
                break
            }
            player.draw(deck.draw())
            output.printPlayerCard(player)
        }
    }

    private fun makePlayers() = input.getPlayers().map { Player(it) }

    companion object {
        private const val INIT_HAND_COUNT = 2
    }
}
