package blackjack.view

import blackjack.domain.GameResult
import blackjack.domain.member.Dealer
import blackjack.domain.member.Member
import blackjack.domain.member.Player
import blackjack.domain.member.Players

object ResultView {

    fun printDrawResults(dealer: Dealer, players: Players) {
        println("딜러와 ${players.items.map { it.name }.joinToString { it }}에게 2장의 나누었습니다.")

        println(dealer.firstCardInfo())

        players.items.forEach {
            println(it.cardScoreDescription())
        }
        println()
    }

    fun printCardScoreDescription(player: Player) {
        println(player.cardScoreDescription())
    }

    fun printDealerDrawCardDescription() {
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

    fun printGamResult(gameResult: GameResult) {
        println()
        println("## 최종 승패")
        println("딜러: ${gameResult.dealerWinCount}승 ${gameResult.dealerLoseCount}패")

        gameResult.winPlayers.forEach { println("${it.name}: 승") }
        gameResult.losePlayers.forEach { println("${it.name}: 패") }
    }
}
