package blackjack.view

import blackjack.domain.BaseBlackjackMember
import blackjack.domain.BlackjackRule
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.result.BlackjackResult
import blackjack.domain.result.DealerResult
import blackjack.domain.result.PlayerResult

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

    fun showDealerDraw(countOfDraw: Int) {
        println("\n딜러는 ${BlackjackRule.DEALER_MINIMUM_SCORE - 1}이하라 한장의 카드를 더 받았습니다.".repeat(countOfDraw))
    }

    fun showResult(dealer: Dealer, players: List<Player>) {
        println()
        println(
            StringBuilder("딜러 카드: ").also {
                it.append(dealer.getCardList().joinToString { card -> "${card.number.text}${card.shape.text}" })
                it.append(
                    " - ${memberScore(blackjackMember = dealer)}"
                )
            }

        )
        println(
            players.joinToString("\n") {
                "${playerText(player = it)} - ${memberScore(blackjackMember = it)}"
            }
        )
    }

    fun showCompareResultTitle() {
        println("\n## 최종 수익")
    }

    fun showCompareResult(blackjackResult: BlackjackResult) {
        showDealerCompareResult(blackjackResult.dealerCompeteResult)
        showPlayerCompareResult(blackjackResult.playerCompeteResults)
    }

    private fun showPlayerCompareResult(playerCompareResultMap: List<PlayerResult>) {
        playerCompareResultMap.forEach {
            println("${it.player.name}: ${it.getEarnMoney()}")
        }
    }

    private fun showDealerCompareResult(dealerResult: DealerResult) {
        println("딜러: ${dealerResult.earnMoney}")
    }

    private fun dealerText(dealer: Dealer): String =
        "딜러: ${dealer.getCardList().joinToString { "${it.number.text}${it.shape.text}" }}"

    private fun playerText(player: Player): String =
        "${player.name}카드: ${player.getCardList().joinToString { "${it.number.text}${it.shape.text}" }}"

    private fun memberScore(blackjackMember: BaseBlackjackMember): String =
        "결과: ${blackjackMember.getScore()}"
}
