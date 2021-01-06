package view

import blackjack.Players

object ResultView {
    fun showResultOfSetUp(players: Players?) {
        println("\n${players}에게 2장의 카드를 나누었습니다.")
        println(players?.stateOfPlayerCard() + "\n")
    }
}
