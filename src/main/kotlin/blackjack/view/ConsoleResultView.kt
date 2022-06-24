package blackjack.view

import blackjack.model.player.Dealer.Companion.BOUNDARY_SCORE_FOR_RECEIVING_MORE_CARD
import blackjack.model.player.Player
import blackjack.model.player.PlayerGameResults
import blackjack.model.player.PlayerName
import blackjack.model.player.Players

object ConsoleResultView : ResultView {
    override fun printPlayersCardStatus(players: Players) {
        println()

        with(StringBuilder()) {
            players.players.map { this.append("${it.name}에게 ${it.cardSize}장을 ") }
            this.append("나누었습니다.")
            println(this)
        }

        players.players.map { printPlayerCardStatus(it) }

        println()
    }

    override fun printPlayerCardStatus(player: Player) = println(playerCardStatus(player))

    override fun printCardGameResult(results: PlayerGameResults) {
        println()

        results.players.forEach {
            println("${playerCardStatus(it)} - 결과: ${it.sumOfCardScore}")
        }

        println("\n## 최종 승패")
        results.results.forEach {
            println("${it.player.name}: ${it.winCount}승 ${it.lostCount}패")
        }
    }

    private fun playerCardStatus(player: Player) = "${player.name}카드: ${player.cards}"

    override fun printDealerReceiveMoreCardMessage(dealerName: PlayerName) =
        println("\n${dealerName}는 점수가 $BOUNDARY_SCORE_FOR_RECEIVING_MORE_CARD 이하라 한 장의 카드를 더 받았습니다.")
}
