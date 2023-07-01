package blackjack.controller

import blackjack.domain.model.Batting
import blackjack.domain.model.Dealer
import blackjack.domain.model.Money
import blackjack.domain.model.Player
import blackjack.domain.model.PlayerInfo
import blackjack.domain.model.Trump
import blackjack.util.InputParser
import blackjack.view.InputView
import blackjack.view.OutputView

class GameController {

    fun execute(trump: Trump) {
        val dealer = Dealer(trump)
        val players = inputName(trump)
        val batting = Batting()

        inputBattings(players, batting)

        val users = mutableListOf<Player>(dealer).apply {
            addAll(players)
        }
        divideCards(users)
        players.forEach {
            playerTurn(it, trump)
        }
        dealerTurn(dealer, trump)
        printPlayerCards(users)
        endGame(batting, dealer, players)
    }

    private fun inputName(trump: Trump): List<Player> {
        val names = InputParser.parse(InputView.inputNames())
        val players = mutableListOf<Player>().apply {
            addAll(names.map { Player(trump, PlayerInfo(it)) })
        }
        return players
    }

    private fun inputBattings(players: List<Player>, batting: Batting) {
        players.forEach { player ->
            val input = InputView.inputBatting(player.info.name)
            player.batting(batting, Money(input))
        }
    }

    private fun divideCards(players: List<Player>) {
        OutputView.divideCard(players.map { it.info.name })
        OutputView.printPlayersCards(players)
    }

    private fun dealerTurn(dealer: Dealer, trump: Trump) {
        if (dealer.canDrawCard()) {
            dealer.drawCard(trump)
            OutputView.printDealerGetCard()
        }
    }

    private fun playerTurn(player: Player, trump: Trump) {
        while (player.canDrawCard() && InputView.inputCard(player.info.name)) {
            player.drawCard(trump)
            OutputView.printCards(player)
        }
    }

    private fun printPlayerCards(players: List<Player>) {
        OutputView.printPlayersCards(players, isResult = true)
    }

    private fun endGame(batting: Batting, dealer: Dealer, players: List<Player>) {
        players.forEach {
            batting.settleMoney(it, dealer)
        }
        OutputView.printResult(mutableListOf<Player>(dealer).apply { addAll(players) })
    }
}
