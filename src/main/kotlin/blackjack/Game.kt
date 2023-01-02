package blackjack

import blackjack.domain.Bet
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
    private val bets: List<Bet>

    init {
        deck.shuffle()
        players = input.getPlayers().map { name -> Player(name, FirstTurn.draw(deck.draw(), deck.draw())) }
        dealer = Dealer(FirstTurn.draw(deck.draw(), deck.draw()))
        bets = players.map { player: Player ->
            Bet(player, bet(player))
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
        output.printProfit(dealer, bets.sumOf { -it.profit(dealer) })
        bets.forEach { bet: Bet ->
            output.printProfit(bet.player, bet.profit(dealer))
        }
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

    private fun bet(player: Player) = input.getBet(player)
}
