package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.JudgmentResult
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.domain.PlayerResult

object OutputView {
    fun printInitialCards(players: List<Participant>) {
        println("${players.joinToString(", ") { it.name }}에게 2장을 나누어 주었습니다.")
        players.forEach { player ->
            println("${player.name}: ${cards(player)}")
        }
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${cards(player)}")
    }

    fun printDealerCardResult(dealer: Dealer) {
        println("${dealer.name} 는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printGameResult(players: List<Participant>) {
        println(players.joinToString("\n") { "${it.name}카드: ${cards(it)} - 결과: ${it.getScore()}" })
    }

    fun printPlayerResult(playerResults: List<PlayerResult>, dealer: Dealer) {
        println("## 최종 승패")
        val dealerWinCount = playerResults.count { it.result == JudgmentResult.LOSE }
        val dealerLoseCount = playerResults.count { it.result == JudgmentResult.WIN }
        println("${dealer.name}: ${dealerWinCount}승 ${dealerLoseCount}패")
        println(playerResults.joinToString("\n") { "${it.name}: ${it.result.description}" })
    }

    private fun cards(player: Participant): String {
        return player.cards.joinToString(", ") { it.toString() }
    }
}
