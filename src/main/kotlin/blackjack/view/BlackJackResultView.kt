package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.view.BlackJackInputView.drawBlackJackPlayerCards

object BlackJackResultView {
    fun drawBlackJackResult(blackJackGame: BlackJackGame) {
        drawBlackJackPlayersCardsWithResult(blackJackGame)
        drawBlackJackDealerWithResult(blackJackGame)
        drawPlayerResult(blackJackGame)
    }

    private fun drawBlackJackPlayersCardsWithResult(blackJackGame: BlackJackGame) {
        blackJackGame.players.forEach {
            drawBlackJackPlayerCards(it)
            println("결과: ${it.getBestSum()}")
        }
    }

    private fun drawBlackJackDealerWithResult(blackJackGame: BlackJackGame) {
        drawBlackJackPlayerCards(blackJackGame.dealer)
        println("결과: ${blackJackGame.dealer.getBestSum()}")
    }

    private fun drawPlayerResult(blackJackGame: BlackJackGame) {
        val gameResult = blackJackGame.getGameResult()
        gameResult.playerResults.forEach { println("${it.name}: ${it.result.resultString}") }
        println("딜러: ${gameResult.dealerResult.winCount}승 ${gameResult.dealerResult.lossCount}패")
    }
}
