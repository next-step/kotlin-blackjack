package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player

object Screen {
    fun displayPlayerCards(players: List<Player>, dealer: Dealer) {
        val names = players.joinToString(", ") { player -> player.name }
        println("${dealer.name}와 $names 에게 2장의 카드를 나누었습니다.")

        for (player in listOf(dealer) + players) {
            displayPlayerCard(player)
        }
    }

    fun displayPlayerCard(player: Player) {
        println("${player.name}카드: ${player.cards.map { card -> card.getName() }.joinToString(",")}")
    }

    fun displayCardScores(players: List<Player>) {
        players.map { player -> displayCardScore(player) }
    }

    private fun displayCardScore(player: Player) {
        val score = player.score()
        println("${player.name}카드: ${player.cards.map { card -> card.getName() }.joinToString(",")} - 결과: $score")
    }

    fun displayGameResults(players: List<Player>, dealer: Dealer) {
        println("## 최종 승패")
        displayDealerResult(players, dealer)
        displayPlayerResults(players, dealer)
    }

    private fun displayDealerResult(players: List<Player>, dealer: Dealer) {
        var resultString = ""
        val results = players.map { player -> dealer.isWinner(listOf(player)) }
        val (winCounts, loseCounts) = results.partition { it }

        if (winCounts.isNotEmpty()) {
            resultString += "${winCounts.size}승"
        }

        if (loseCounts.isNotEmpty()) {
            resultString += "${loseCounts.size}패"
        }

        println(resultString)
    }

    private fun displayPlayerResults(players: List<Player>, dealer: Dealer) {
        for (player in players) {
            displayPlayerResult(player, listOf(dealer) + players)
        }
    }

    private fun displayPlayerResult(player: Player, players: List<Player>) {
        val otherPlayers = players.filter { it.name != player.name }
        val isWin = player.isWinner(otherPlayers)
        println("${player.name}: ${if (isWin) "승" else "패"}")
    }

    fun displayNeedCard(name: String) {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }
}
