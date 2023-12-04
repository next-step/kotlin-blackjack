package blackjack.view

import blackjack.domain.BlackjackParticipant
import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.Player

object ResultView {
    fun firstDealCard(players: List<Player>, dealer: Dealer) {
        println("${dealer.name}와 ${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.\n")
    }

    fun showPlayerCards(player: BlackjackParticipant) {
        println("${player.name}카드: ${player.cards.cards}")
    }

    fun showPlayerResult(count: Int, dealer: Dealer, players: List<Player>) {
        showDealerDrawCount(count)
        println("${dealer.name}카드: ${dealer.cards.cards} - 결과: ${dealer.getScore()}")
        for (player in players) {
            println("${player.name}카드: ${player.cards.cards} - 결과: ${player.getScore()}")
        }
        displayGameProfit(GameResult(dealer, players), dealer)
    }

    private fun displayGameProfit(gameResult: GameResult, dealer: Dealer) {
        println("\n## 최종 수익")
        val players = gameResult.setProfit()
        showProfit(dealer)
        for (player in players) {
            println("${player.name} : ${player.profit.toInt()}")
        }
    }

    private fun showProfit(blackjackParticipant: BlackjackParticipant) {
        println("${blackjackParticipant.name}: ${blackjackParticipant.profit.toInt()}")
    }

    private fun showDealerDrawCount(count: Int) {
        if (count == 0) return
        println("딜러는 16점이하라 ${count}장의 카드를 더 받았습니다.\n")
    }
}
