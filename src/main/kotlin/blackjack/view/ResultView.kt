package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.player.Challengers

class ResultView {
    fun outputInitialHand(challengers: Challengers, dealer: Dealer) {
        println("딜러와 ${challengers.joinToString { it.name }}에게 2장씩 나누었습니다.")
        println("딜러: ${dealer.currentDeck().cards.last()}")
        challengers.forEach { challenger ->
            println("${challenger.name}카드: ${getAllHandString(challenger)}")
        }
    }

    fun outputCurrentHand(player: Player) {
        println(getAllHandString(player))
    }

    fun outputGameResult(challengers: Challengers, dealer: Dealer) {
        println()
        println("딜러 카드: ${getAllHandString(dealer)} ${dealer.score()}")
        challengers.forEach { challenger ->
            println("${challenger.name}카드: ${getAllHandString(challenger)} ${challenger.score()}")
        }

        println("## 최종 승패")
        val dealerLooseCount = challengers.count { it.isWin(dealer) }
        val dealerWinCount = challengers.size - dealerLooseCount
        println("딜러: ${dealerWinCount}승 ${dealerLooseCount}패")
        challengers.forEach { challenger ->
            println("${challenger.name}: ${ if (challenger.isWin(dealer)) "승" else "패" }")
        }

        println("## 최종 수익")

        val sumOfChallengersReturnAmount = challengers.sumOf { it.getEarnings(dealer) }
        println("딜러: ${-sumOfChallengersReturnAmount}")
        challengers.forEach { challenger ->
            println("${challenger.name}: ${challenger.getEarnings(dealer)}")
        }
    }

    fun outputDealerDeal() {
        println("딜러는 16이하라 한장의 카드를 더받았습니다.")
    }

    private fun getAllHandString(player: Player): String {
        return player.currentDeck().cards.joinToString { it.toString() }
    }
}
