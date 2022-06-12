package blackjack.view

import blackjack.domain.player.Dealer
import blackjack.domain.player.GamblingSummary
import blackjack.domain.player.Player

class ResultView {

    fun printInitDistributed(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }} 에게 2장을 나누었습니다.")
        printCardsByPlayers(players, false)
    }

    fun printCardsByPlayers(player: List<Player>, withScore: Boolean) {
        println("")
        player.map {
            printCardsByPlayer(it, withScore)
        }
    }

    fun printCardsByPlayer(player: Player, withScore: Boolean) {
        if (withScore) {
            println("${player.name}카드: ${player.receivedCards.getCardDescription()} - 결과: ${player.score}")
            return
        }

        println("${player.name}카드: ${player.receivedCards.getCardDescription()}")
    }

    fun printFinalResult(blackJackGamer: List<Player>) {
        val dealer = blackJackGamer.filterIsInstance<Dealer>().first()
        val players = blackJackGamer.filter { it !is Dealer }

        println("")
        println("## 최종 수익")
        println(getDealerResult(dealer, players))
        println(getPlayerResult(players))
    }

    private fun getDealerResult(dealer: Dealer, player: List<Player>): String {
        val incomeOfDealer = player
            .filter { !it.gamblingSummary.isWinner }
            .sumOf { (it.gamblingSummary.battingAmount * it.gamblingSummary.earningRate) }
            .unaryMinus()

        val expensesOfDealer = player
            .filter { it.gamblingSummary.isWinner }
            .sumOf { (it.gamblingSummary.battingAmount * it.gamblingSummary.earningRate) }

        return "${dealer.name}: ${calculateProfitOfDealer(incomeOfDealer, expensesOfDealer)}"
    }

    private fun getPlayerResult(players: List<Player>): String {
        return players.joinToString("\n") { "${it.name}: ${calculateProfitOfPlayer((it.gamblingSummary))}" }
    }

    private fun calculateProfitOfDealer(incomeOfDealer: Double, expensesOfDealer: Double): Int {
        return (incomeOfDealer - expensesOfDealer).toInt()
    }

    private fun calculateProfitOfPlayer(gamblingSummary: GamblingSummary): Int {
        return (gamblingSummary.battingAmount * gamblingSummary.earningRate).toInt()
    }
}
