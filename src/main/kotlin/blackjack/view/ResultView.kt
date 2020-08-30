package blackjack.view

import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players
import blackjack.model.profit.Profits

const val TOOK_ANOTHER_CARD = "는 합계가 16이하라 한장의 카드를 더 받았습니다."
const val TITLE_OF_PROFIT_RESULT = "## 최종 수익"

object ResultView {

    fun showSetUpResult(dealer: Dealer, players: Players) {
        println("\n${dealer}와 ${players}에게 2장의 카드를 나누었습니다")
        println("$dealer: ${dealer.cardsDetail().substringBefore(",")}")
        println("${players.cardsDetails()}\n")
    }

    fun showCardsOf(player: Player) {
        println("${player}카드: ${player.cardsDetail()}")
    }

    fun showMessageOfGettingCard(dealer: Dealer) {
        println("\n-- $dealer$TOOK_ANOTHER_CARD")
    }

    fun showScoreResult(dealer: Dealer, players: Players) {
        println("\n$dealer 카드: ${dealer.cardsDetail()} - 결과: ${dealer.score()}")
        println("${players.scoreDetails()}\n")
    }

    fun showProfitResult(profits: Profits) {
        println(TITLE_OF_PROFIT_RESULT)

        val dealerProfit = profits.dealerProfit()
        println("${dealerProfit.participantName}: ${dealerProfit.profit}")

        println(profits)
    }
}
