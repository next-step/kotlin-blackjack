package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Member
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.Result

object ResultView {

    fun printDrawResults(dealer: Dealer, players: Players) {
        println("딜러와 ${players.items.map { it.name }.joinToString { it }}에게 2장의 나누었습니다.")

        println(dealer.firstCardInfo())

        players.items.forEach {
            println(it.cardScoreDescription())
        }
    }

    fun printCardScoreDescription(player: Player) {
        println(player.cardScoreDescription())
    }

    fun printDealerDrawCardDescription() {
        println()
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        println()
    }

    fun printPlayerResults(member: Member) {
        println("${member.cardScoreDescription()} - 결과: ${member.resultScore()}")
    }

    private fun Member.cardScoreDescription() = "${this.name}카드: ${this.cardsDescription()}"
    private fun Member.cardsDescription() =
        this.cardElements.map { c -> c.number.desc + c.sharp.desc }.joinToString { it }

    private fun Dealer.firstCardInfo() = "딜러: ${this.firstCardDescription()}"
    private fun Dealer.firstCardDescription() = this.cardElements.map { c -> c.number.desc + c.sharp.desc }.first()

    fun printGamResult(result: Result) {
        println()
        println("## 최종 승패")
        println("딜러: ${result.dealerWinCount}승 ${result.dealerLoseCount}패")

        result.winner.forEach { println("${it.name}: 승") }
        result.loser.forEach { println("${it.name}: 패") }
    }
}
