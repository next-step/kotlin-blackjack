package blackjack.view

import blackjack.domain.BlackJackDealer
import blackjack.domain.BlackJackGameResult
import blackjack.domain.BlackJackNormalPlayer
import blackjack.view.BlackJackInputView.drawBlackJackDealerCards
import blackjack.view.BlackJackInputView.drawBlackJackNormalPlayerCards

object BlackJackResultView {
    fun drawBlackJackPlayersCardsWithResult(blackJackNormalPlayer: List<BlackJackNormalPlayer>) {
        blackJackNormalPlayer.forEach {
            drawBlackJackNormalPlayerCards(it)
            println("결과: ${it.getBestSum()}")
        }
    }

    fun drawBlackJackDealerWithResult(blackJackDealer: BlackJackDealer) {
        drawBlackJackDealerCards(blackJackDealer)
        println("결과: ${blackJackDealer.getBestSum()}")
    }

    fun drawGameResult(blackJackGameResult: BlackJackGameResult) {
        blackJackGameResult.playerResults.forEach { println("${it.name}: ${it.result.resultString}") }
        println("딜러: ${blackJackGameResult.dealerResult.winCount}승 ${blackJackGameResult.dealerResult.lossCount}패")
    }
}
