package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.card.Deck
import blackjack.domain.state.FirstTurn
import blackjack.domain.state.Running
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
        batPlayers()
    }

    private fun batPlayers() {
        players.forEach { player: Player ->
            player.bat(input.getBet(player))
        }
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

        output.printProfitHeader()
        output.printProfit(dealer, players.sumOf { -it.profit(dealer) })
        players.forEach {
            output.printProfit(it, it.profit(dealer))
        }
    }

    private fun dealerDraw() {
        while (dealer.canDraw()) {
            output.printDealerDraw()
            dealer.draw(deck.draw())
        }
        if (dealer.state is Running) {
            dealer.stay()
        }
    }

    private fun playerDraw(player: Player) {
        while (player.canDraw()) {
            if (!input.moreDraw(player)) {
                player.stay()
                break
            }
            player.draw(deck.draw())
            output.printPlayerCard(player)
        }
    }
}
