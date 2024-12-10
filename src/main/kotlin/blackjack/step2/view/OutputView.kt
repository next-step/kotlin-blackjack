package blackjack.step2.view

import blackjack.step2.domain.Dealer
import blackjack.step2.domain.GameResult
import blackjack.step2.domain.GameResultType
import blackjack.step2.domain.Participant
import blackjack.step2.domain.Player

object OutputView {
    fun printCards(participant: Participant) {
        val cards =
            participant.cards.all.joinToString(", ") {
                "${it.number.denomination}${it.type.value}"
            }
        println("${participant.name} 카드: $cards - 결과: ${participant.score()}")
    }

    fun printInitialCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("딜러와 ${players.joinToString(", ") { it.name }}에게 2장의 카드를 나누었습니다.")
        this.printCards(dealer)
        players.forEach { printCards(it) }
    }

    fun printResults(results: List<GameResult>) {
        val dealer = results.first { it.participant is Dealer }
        val players = results.filter { it.participant is Player }
        println("## 최종 승패")
        this.printResult(dealer)
        players.forEach { this.printResult(it) }
    }

    private fun printResult(result: GameResult) {
        val winCount = result.resultTypes.count { it == GameResultType.WIN }
        val loseCount = result.resultTypes.count { it == GameResultType.LOSE }
        val drawCount = result.resultTypes.count { it == GameResultType.DRAW }

        println("${result.participant.name}: ${winCount}승 ${loseCount}패 ${drawCount}무")
    }
}
