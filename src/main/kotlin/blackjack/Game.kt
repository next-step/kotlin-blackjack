package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.card.Deck
import blackjack.domain.state.FirstTurn
import blackjack.io.Input
import blackjack.io.Output

class Game(private val input: Input, private val output: Output) {
    private val players: List<Player>
    private val dealer: Dealer
    private val deck: Deck = Deck()

    init {
        deck.shuffle()
        players = input.getPlayers().map { name -> Player(name, FirstTurn.draw(deck.draw(), deck.draw())) }
        dealer = Dealer(FirstTurn.draw(deck.draw(), deck.draw()))
    }

    fun start() {
        output.printDistribute(listOf(dealer) + players)
        output.printDealerCard(dealer)
        output.printPlayersCard(players)
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
}
