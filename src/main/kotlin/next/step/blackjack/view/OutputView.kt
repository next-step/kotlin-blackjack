package next.step.blackjack.view

import next.step.blackjack.domain.betting.BettingPlayer
import next.step.blackjack.domain.betting.BettingPlayers
import next.step.blackjack.domain.betting.BettingResults
import next.step.blackjack.domain.dealer.Dealer

object OutputView {

    private const val UNKNOWN_ERR_MSG = "알 수 없는 에러가 발생했습니다."

    fun showStart(dealer: Dealer, players: BettingPlayers, cardCnt: Int) {
        println()
        showInitCardsCnt(dealer, players, cardCnt)
        showDealerFirstCard(dealer)
        showPlayersCards(players)
        println()
    }

    private fun showInitCardsCnt(dealer: Dealer, players: BettingPlayers, cardCnt: Int) {
        val playerNames = players.joinToString(", ") { it.name() }
        println("${dealer.name()}와 ${playerNames}에게 ${cardCnt}장씩 나누었습니다.")
    }

    private fun showDealerFirstCard(dealer: Dealer) = println("${dealer.name()}: ${dealer.cardDescFirst()}")

    private fun showPlayersCards(players: BettingPlayers) = players.forEach(OutputView::showPlayerCards)

    fun showPlayerCards(player: BettingPlayer) = println(cardDescs(player))

    private fun cardDescs(player: BettingPlayer): String {
        val cardDescs = player.cardDescs().joinToString(", ")
        return "${player.name()}카드: $cardDescs"
    }

    fun showDealerHit(name: String) {
        println()
        println("${name}는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun showResult(dealer: Dealer, players: BettingPlayers) {
        showCardsWithPoints(dealer, players)
        showBettingResult(dealer.name(), players.fight(dealer))
    }

    private fun showCardsWithPoints(dealer: Dealer, players: BettingPlayers) {
        println()
        showDealerCardsWithPoints(dealer)
        showPlayersCardsWithPoints(players)
    }

    private fun showDealerCardsWithPoints(dealer: Dealer) {
        println("${dealer.name()} 카드: ${dealer.cardDescs().joinToString(", ")} - 결과: ${dealer.point()}")
    }

    private fun showPlayersCardsWithPoints(players: BettingPlayers) {
        players.forEach(OutputView::showPlayerCardsWithPoints)
    }

    private fun showPlayerCardsWithPoints(player: BettingPlayer) {
        println("${cardDescs(player)} - 결과: ${player.point()}")
    }

    private fun showBettingResult(dealerName: String, bettingResults: BettingResults) {
        println()
        println("## 최종 수익")
        showDealerBettingResult(bettingResults, dealerName)
        showPlayersBettingResults(bettingResults)
    }

    private fun showDealerBettingResult(bettingResults: BettingResults, dealerName: String) {
        println("$dealerName: ${bettingResults.dealerResult}")
    }

    private fun showPlayersBettingResults(bettingResults: BettingResults) {
        bettingResults.playersResult.forEach {
            println("${it.key}: ${it.value}")
        }
    }

    fun showError(msg: String?) {
        println(msg ?: UNKNOWN_ERR_MSG)
        println()
    }
}
