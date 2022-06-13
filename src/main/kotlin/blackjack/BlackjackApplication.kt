package blackjack

import blackjack.view.InputView

fun main() {
    val inputView = InputView()
    val names = inputView.getNames()

    println(names)
}
