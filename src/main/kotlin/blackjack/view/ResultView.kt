package blackjack.view

import blackjack.domain.BlackjackParticipant
import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.MatchResult
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
    }

    fun displayGameResult(gameResult: GameResult) {
        println("\n## 최종 승패")
        val resultMap = gameResult.getResultMap()
        displayDealerResult(gameResult.getMatchCount(resultMap))
        for (resultMapEntry in resultMap) {
            println("${resultMapEntry.keys.first().name}: ${resultMapEntry.values.first().text}")
        }
    }

    private fun showDealerDrawCount(count: Int) {
        if (count == 0) return
        println("딜러는 16점이하라 ${count}장의 카드를 더 받았습니다.\n")
    }

    private fun displayDealerResult(matches: List<Map<MatchResult, Int>>) {
        val loss = matches.sumOf { it.getOrDefault(MatchResult.WIN, 0) }
        val win = matches.sumOf { it.getOrDefault(MatchResult.LOSS, 0) }
        val tie = matches.sumOf { it.getOrDefault(MatchResult.TIE, 0) }

        println("딜러: ${win}승 ${loss}패 ${tie}무")
    }
}
