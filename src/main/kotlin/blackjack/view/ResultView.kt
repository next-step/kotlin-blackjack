package blackjack.view

import blackjack.domain.BlackjackMember
import blackjack.domain.BlackjackRule
import blackjack.domain.CompareResult
import blackjack.domain.CompareResultItem
import blackjack.domain.Dealer
import blackjack.domain.Player

class ResultView {
    fun showInitialPlayers(dealer: Dealer, players: List<Player>) {
        println("\n딜러와 ${players.joinToString { it.name }}에게 ${BlackjackRule.INITIAL_CARD}장의 카드를 나누었습니다.")
        showDealer(dealer = dealer)
        players.forEach {
            showPlayer(player = it)
        }
        println()
    }

    private fun showDealer(dealer: Dealer) {
        println(dealerText(dealer = dealer))
    }

    fun showPlayer(player: Player) {
        println(playerText(player = player))
    }

    fun showDealerDraw(): String = "딜러는 ${BlackjackRule.DEALER_MINIMUM_SCORE - 1}이하라 한장의 카드를 더 받았습니다."

    fun showResult(dealer: Dealer, players: List<Player>) {
        println()
        println(
            "딜러 카드: ${dealer.getCardList().joinToString { "${it.number.text}${it.shape.text}" }} - ${
            memberScore(
                blackjackMember = dealer
            )
            }"
        )
        println(
            players.joinToString("\n") {
                "${playerText(player = it)} - ${memberScore(blackjackMember = it)}"
            }
        )
    }

    fun showCompareResultTitle() {
        println("\n## 최종 승패")
    }

    fun showCompareResult(compareResult: CompareResult) {
        showDealerCompareResult(compareResult.dealerCompareResultItem)
        showPlayerCompareResult(compareResult.playerCompareResultMap)
    }

    private fun showPlayerCompareResult(playerCompareResultMap: Map<Player, CompareResultItem>) {
        playerCompareResultMap.forEach { (player, result) ->
            println(
                StringBuilder().also {
                    it.append("${player.name}: ")

                    if (result == CompareResultItem.win()) {
                        it.append("승")
                    }

                    if (result == CompareResultItem.draw()) {
                        it.append("무")
                    }

                    if (result == CompareResultItem.lose()) {
                        it.append("패")
                    }
                }
            )
        }
    }

    private fun showDealerCompareResult(compareResultItem: CompareResultItem) {
        println(
            StringBuilder("딜러: ").also {
                if (compareResultItem.win > 0) {
                    it.append("${compareResultItem.win}승 ")
                }

                if (compareResultItem.draw > 0) {
                    it.append("${compareResultItem.draw}무 ")
                }

                if (compareResultItem.lose > 0) {
                    it.append("${compareResultItem.lose}패")
                }
            }
        )
    }

    private fun dealerText(dealer: Dealer): String =
        "딜러: ${dealer.getCardList().joinToString { "${it.number.text}${it.shape.text}" }}"

    private fun playerText(player: Player): String =
        "${player.name}카드: ${player.getCardList().joinToString { "${it.number.text}${it.shape.text}" }}"

    private fun memberScore(blackjackMember: BlackjackMember): String =
        "결과: ${blackjackMember.getScore()}"
}
