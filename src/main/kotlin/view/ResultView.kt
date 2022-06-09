package view

import domain.Player
import view.InputView.displayHaveCard

object ResultView {

    fun displayResult(players: List<Player>) {
        players.forEach {
            displayHaveCard(it)
            print(" - ")
            println("결과 : ${it.cardSum()}")
        }
    }
}
