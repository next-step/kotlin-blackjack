package blackjack.view

import blackjack.domain.BlackJackGame

object ResultView {

    fun gameResult(blackJackGame: BlackJackGame) {
        println()
        println("### 최종 수익")
        blackJackGame.participants.forEach {
            println("${it.name} : ${blackJackGame.getEarnAmount(it.name)}")
        }
    }
}
