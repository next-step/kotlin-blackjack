package blackjack.view

import blackjack.domain.BlackJackGame

object ResultView {

    fun gameResult(blackJackGame: BlackJackGame) {
        val eachEarnAmount = blackJackGame.players.map { it.getEarnAmount(blackJackGame.dealer) }
        val playerEarnAmount = blackJackGame.players.zip(eachEarnAmount)
        println()
        println("### 최종 수익")
        blackJackGame.dealer.also {
            println("${it.name} : -${eachEarnAmount.sum()}")
        }
        playerEarnAmount.forEach {
            println("${it.first.name} : -${it.second}")
        }
    }
}
